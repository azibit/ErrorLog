/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.azibit.backend.crud;

import javax.ejb.Local;
import javax.persistence.EntityManager;
import javax.persistence.metamodel.SingularAttribute;
import java.util.List;
import java.util.Map;

/**
 *
 * @author faizbash
 */
@Local
public interface CrudServiceProviderLocal {

    <T> int count(Class<T> type);

    <T> T create(T t);

    void delete(Object t);

    <T> T find(Object id, Class<T> type);

    <T> T find(Object id, Class<T> type, Map<String, Object> properties);

    <T> List<T> findAll(Class<T> type);

    <T> List<T> findByDynamicQuery(Class<T> type,
                                   List<ParameterDefinition> parameters);

    <T> List<T> findByNamedQuery(String namedQueryName, Class<T> type);

    <T> List<T> findByNamedQuery(String namedQueryName,
                                 Map<String, Object> parameters, Class<T> type);
    <T> List<T> findByNamedQuery(String namedQueryName,
                                 Map<String, Object> parameters, Map<String, String> hints,
                                 Class<T> type);

    <T> List<T> findByNamedQuery(String queryName, int resultLimit, Class<T> type);

    <T> List<T> findByNamedQuery(String queryName, int resultLimit,
                                 int firstResult, Class<T> type);

    <T> List<T> findByNamedQuery(String namedQueryName,
                                 Map<String, Object> parameters, int resultLimit, Class<T> type);

    <T> List<T> findByNamedQuery(String namedQueryName,
                                 Map<String, Object> parameters, Map<String, String> hints,
                                 int resultLimit, Class<T> type);

    <T> List<T> findByNamedQuery(String namedQueryName,
                                 Map<String, Object> parameters, int resultLimit, int firstResult,
                                 Class<T> type);

    <T> List<T> findRange(Class<T> type, int maxResults, int firstResult);

    <T> T update(T t);

    <T, E> List<T> findByField(SingularAttribute<? super T, E> attribute,
                               E param, Class<T> type);

    <T, E> List<T> findByField(SingularAttribute<? super T, E> attribute,
                               E param, Class<T> type, OperatorType operator);

    EntityManager getEntityManager();
}