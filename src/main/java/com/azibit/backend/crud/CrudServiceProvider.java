/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.azibit.backend.crud;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author faizbash
 */
@Stateless
public class CrudServiceProvider implements CrudServiceProviderLocal {

    @PersistenceContext(unitName = "ErrorlogPU")
    private EntityManager em;

    @Override
    public <T> T create(T t) {
        AbstractFacade.constraintValidationsDetected(t);
        em.persist(t);
        em.flush();
        return t;
    }

    @Override
    public <T> T find(Object id, Class<T> type) {
        return (T) em.find(type, id);
    }

    @Override
    public <T> T find(Object id, Class<T> type, Map<String, Object> properties) {
        return (T) em.find(type, id, properties);
    }

    @Override
    public void delete(Object t) {
        em.remove(em.merge(t));
    }

    @Override
    public <T> T update(T t) {
         AbstractFacade.constraintValidationsDetected(t);
         return (T) em.merge(t);
    }

    @Override
    public <T> List<T> findByNamedQuery(String namedQueryName, Class<T> type) {
        return em.createNamedQuery(namedQueryName).getResultList();
    }

    @Override
    public <T> List<T> findByNamedQuery(String namedQueryName,
            Map<String, Object> parameters, Class<T> type) {
        return findByNamedQuery(namedQueryName, parameters, 0, type);
    }

    @Override
    public <T> List<T> findByNamedQuery(String namedQueryName,
            Map<String, Object> parameters, Map<String, String> hints,
            Class<T> type) {
        return findByNamedQuery(namedQueryName, parameters, hints, 0, type);
    }

    @Override
    public <T> List<T> findByNamedQuery(String queryName, int resultLimit,
            Class<T> type) {
        return this.em.createNamedQuery(queryName).
                setMaxResults(resultLimit).
                getResultList();
    }

    @Override
    public <T> List<T> findByNamedQuery(String queryName, int resultLimit,
            int firstResult, Class<T> type) {

        Query query = this.em.createNamedQuery(queryName);
        query.setFirstResult(firstResult);
        if (resultLimit > 0) {
            query.setMaxResults(resultLimit);
        }
        return query.getResultList();
    }

    @Override
    public <T> List<T> findByNamedQuery(String namedQueryName,
            Map<String, Object> parameters,
            int resultLimit, Class<T> type) {
        Set<Entry<String, Object>> rawParameters = parameters.entrySet();
        Query query = this.em.createNamedQuery(namedQueryName);
        if (resultLimit > 0) {
            query.setMaxResults(resultLimit);
        }
        for (Entry<String, Object> entry : rawParameters) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.getResultList();
    }

    @Override
    public <T> List<T> findByNamedQuery(String namedQueryName,
            Map<String, Object> parameters, Map<String, String> hints,
            int resultLimit, Class<T> type) {
        Set<Entry<String, Object>> rawParameters = parameters.entrySet();
        Query query = this.em.createNamedQuery(namedQueryName);
        if (resultLimit > 0) {
            query.setMaxResults(resultLimit);
        }
        for (Entry<String, Object> entry : rawParameters) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        //hints
        Set<Entry<String, Object>> hintsEntry = parameters.entrySet();
        for (Entry<String, Object> entry : hintsEntry) {
            query.setHint(entry.getKey(), entry.getValue());
        }

        return query.getResultList();
    }

    @Override
    public <T> List<T> findByNamedQuery(String namedQueryName,
            Map<String, Object> parameters, int resultLimit, int firstResult, Class<T> type) {
        Set<Entry<String, Object>> rawParameters = parameters.entrySet();
        Query query = this.em.createNamedQuery(namedQueryName);
        query.setFirstResult(firstResult);
        if (resultLimit > 0) {
            query.setMaxResults(resultLimit);
        }
        for (Entry<String, Object> entry : rawParameters) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.getResultList();
    }

    @Override
    public <T> List<T> findByDynamicQuery(Class<T> type,
            List<ParameterDefinition> parameters) {
        //generate query
        String queryString = generateQuery(type, parameters);

        Query query = this.em.createQuery(queryString);

        //set parameters
        int k = 1;  //param position
        for (ParameterDefinition param : parameters) {
            if (param.getValue() != null) {
                query.setParameter(k++, param.getValue());
            }
        }

        return query.getResultList();
    }

    //helper method
    private <T> String generateQuery(Class<T> type,
            List<ParameterDefinition> parameters) {
        final String VAR = "obj";
        StringBuilder queryBuilder = new StringBuilder(
                String.format("SELECT %s FROM %s %s", VAR,
                        type.getSimpleName(), VAR)
        );
        if (parameters.isEmpty()) { //no conditions
            return queryBuilder.toString();
        }
        //WHERE clause
        Iterator<ParameterDefinition> itr = parameters.iterator();
        int k = 1;  //parameter position
        ParameterDefinition firstParam = itr.next(); //always type WITH
        if (firstParam.getValue() == null) {
            queryBuilder.append(String.format(
                    " WHERE %s.%s IS NULL", VAR, firstParam.getName()));
        } else {
            queryBuilder.append(String.format(" WHERE %s.%s = ?%d",
                    VAR, firstParam.getName(), k++));
        }
        //append parameters
        for (; itr.hasNext();) {
            ParameterDefinition nextParam = itr.next();
            if (nextParam.getValue() == null) {
                queryBuilder.append(String.format(
                        " %s %s.%s IS NULL", nextParam.getParameterType(),
                        VAR, nextParam.getName()));
//            } else if (nextParam.getParameterType().equals(ParameterType.LIKE)) {
//                    queryBuilder.append(String.format(
//                        " %s %s.%s LIKE ?%s", nextParam.getParameterType(), VAR,
//                        nextParam.getName(), k++));
            } else {
                queryBuilder.append(String.format(
                        " %s %s.%s = ?%s", nextParam.getParameterType(), VAR,
                        nextParam.getName(), k++));
            }
        }
        return queryBuilder.toString();
    }

    @Override
    public <T> List<T> findAll(Class<T> type) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(type));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public <T> List<T> findRange(Class<T> type, int maxResults, int firstResult) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(type));
        Query q = em.createQuery(cq);
        q.setMaxResults(maxResults);
        q.setFirstResult(firstResult);
        return q.getResultList();
    }

    @Override
    public <T> int count(Class<T> type) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<T> rt = cq.from(type);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
        //return findAll(type).size();
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public <T, E> List<T> findByField(SingularAttribute<? super T, E> attribute, E param, Class<T> type) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(type);
        Root<T> root = cq.from(type);
        cq.where(cb.equal(root.get(attribute), param));
        TypedQuery<T> q = getEntityManager().createQuery(cq);
        return q.getResultList();
    }

    @Override
    public <T, E> List<T> findByField(SingularAttribute<? super T, E> attribute,
            E param, Class<T> type, OperatorType operator) {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(type);
        Root<T> root = cq.from(type);

        switch (operator) {
            case LIKE:
                cq.where(cb.like(root.<String>get(attribute.getName()), "%" + param + "%"));
                break;
            case EQUAL:
                return findByField(attribute, param, type);

        }

        TypedQuery<T> q = getEntityManager().createQuery(cq);
        return q.getResultList();
    }

}
