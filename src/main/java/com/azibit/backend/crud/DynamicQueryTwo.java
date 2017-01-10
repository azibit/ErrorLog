/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.azibit.backend.crud;

/**
 *
 * @author azibit
 */
import java.util.ArrayList;
import java.util.List;

public class DynamicQueryTwo {

    String className;
    StringBuilder query;
    List<Object> parameters;

    public DynamicQueryTwo(String className) {
        parameters = new ArrayList<>();
        this.className = className;
        query = new StringBuilder();
        query.append("SELECT s FROM ").append(className).append(" s ");
    }

    public String getQuery() {
        return query.toString();
    }
    
    public void setQuery(String newQuery){
        query = new StringBuilder(newQuery);
    }

    public void addParameter(Object parameterValue) {
        parameters.add(parameterValue);
    }

    public String where(String parameterName, Object parameterValue) {
        StringBuilder whereQuery = new StringBuilder();
        whereQuery.append("WHERE s.").append(parameterName).append(" = ?").append(parameters.size() + 1).append(" ");
        addParameter(parameterValue);
        return query.append(whereQuery).toString();
    }
    
    public String andWithBracket(String parameterName, Object parameterValue) {
        StringBuilder andQuery = new StringBuilder();
        andQuery.append("AND ( s.").append(parameterName).append(" = ?").append(parameters.size() + 1).append(" ");
        addParameter(parameterValue);
        return query.append(andQuery).toString();
    }

    

    public String and(String parameterName, Object parameterValue) {
        StringBuilder andQuery = new StringBuilder();
        andQuery.append("AND s.").append(parameterName).append(" = ?").append(parameters.size() + 1).append(" ");
        addParameter(parameterValue);
        return query.append(andQuery).toString();
    }

    public String or(String parameterName, Object parameterValue) {
        StringBuilder orQuery = new StringBuilder();
        orQuery.append("OR s.").append(parameterName).append(" = ?").append(parameters.size() + 1).append(" ");
        addParameter(parameterValue);
        return query.append(orQuery).toString();
    }

    public String whereLike(String parameterName, String parameterValue) {
        StringBuilder whereLikeQuery = new StringBuilder();
        whereLikeQuery.append("WHERE s.").append(parameterName).append(" LIKE ");

        if (parameterValue.isEmpty()) {
            parameterValue = "%";
            whereLikeQuery.append("?").append(parameters.size() + 1).append(" ");
            addParameter(parameterValue);
        } else {
            whereLikeQuery.append("?").append(parameters.size() + 1).append(" ");
            addParameter("%" + parameterValue + "%");
        }

        return query.append(whereLikeQuery).toString();
    }

    public String andLike(String parameterName, String parameterValue) {
        StringBuilder andLikeQuery = new StringBuilder();
        andLikeQuery.append("AND s.").append(parameterName).append(" LIKE ");

        if (parameterValue.isEmpty()) {
            parameterValue = "%";
            andLikeQuery.append("?").append(parameters.size() + 1).append(" ");
            addParameter(parameterValue);
        } else {
            andLikeQuery.append("?").append(parameters.size() + 1).append(" ");
            parameters.add("%" + parameterValue + "%");
        }

        return query.append(andLikeQuery).toString();
    }

    public String orLike(String parameterName, String parameterValue) {
        StringBuilder orLikeQuery = new StringBuilder();
        orLikeQuery.append("OR s.").append(parameterName).append(" LIKE ");

        if (parameterValue.isEmpty()) {
            parameterValue = "%";
            orLikeQuery.append("?").append(parameters.size() + 1).append(" ");
            addParameter(parameterValue);
        } else {
            orLikeQuery.append("?").append(parameters.size() + 1).append(" ");
            addParameter("%" + parameterValue + "%");
        }

        return query.append(orLikeQuery).toString();
    }

    public String whereNotLike(String parameterName, String parameterValue) {
        StringBuilder whereLikeQuery = new StringBuilder();
        whereLikeQuery.append("WHERE s.").append(parameterName).append(" NOT LIKE ");

        if (parameterValue.isEmpty()) {
            parameterValue = "%";
            whereLikeQuery.append("?").append(parameters.size() + 1).append(" ");
            addParameter(parameterValue);
        } else {
            whereLikeQuery.append("?").append(parameters.size() + 1).append(" ");
            addParameter("%" + parameterValue + "%");
        }

        return query.append(whereLikeQuery).toString();
    }

    public String andNotLike(String parameterName, String parameterValue) {
        StringBuilder andLikeQuery = new StringBuilder();
        andLikeQuery.append("AND s.").append(parameterName).append(" NOT LIKE ");

        if (parameterValue.isEmpty()) {
            parameterValue = "%";
            andLikeQuery.append("?").append(parameters.size() + 1).append(" ");
            addParameter(parameterValue);
        } else {
            andLikeQuery.append("?").append(parameters.size() + 1).append(" ");
            addParameter("%" + parameterValue + "%");
        }

        return query.append(andLikeQuery).toString();
    }

    public String orNotLike(String parameterName, String parameterValue) {
        StringBuilder orLikeQuery = new StringBuilder();
        orLikeQuery.append("OR s.").append(parameterName).append(" NOT LIKE ");

        if (parameterValue.isEmpty()) {
            parameterValue = "%";
            orLikeQuery.append("?").append(parameters.size() + 1).append(" ");
            addParameter(parameterValue);
        } else {
            orLikeQuery.append("?").append(parameters.size() + 1).append(" ");
            addParameter("%" + parameterValue + "%");
        }

        return query.append(orLikeQuery).toString();
    }

    public List<Object> getParameters() {
        return parameters;
    }


}
