/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.azibit.backend.crud;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author faizbash
 */
public class DynamicQueryParameter {
    private List<ParameterDefinition> parameters = null;

    private DynamicQueryParameter(String name, Object value) {
        this.parameters = new ArrayList<ParameterDefinition>();

        addParameter(ParameterType.WITH, name, value);
    }
    
    private void addParameter(ParameterType paramType,
            String name, final Object value) {
        if (value == null )
            parameters.add(new ParameterDefinition(paramType, name, value));
        else if (!value.toString().equalsIgnoreCase("ALL"))
            parameters.add(new ParameterDefinition(paramType, name, value));
    }
    public static DynamicQueryParameter with(String name, final Object value) {
        return new DynamicQueryParameter(name, value);
    }
    
    public DynamicQueryParameter and(String name, final Object value) {
        addParameter(ParameterType.AND, name, value);

        return this;
    }
    
    public DynamicQueryParameter andLike(String name, final Object value) {
        addParameter(ParameterType.LIKE, name, value);

        return this;
    }
    
    public DynamicQueryParameter or(String name, final Object value) {
        addParameter(ParameterType.OR, name, value);
        
        return this;
    }
    
     public DynamicQueryParameter like(String name, final Object value) {
        addParameter(ParameterType.LIKE, name, value);
        
        return this;
    }
    public List<ParameterDefinition> parameters() {
        return this.parameters;
    }

    public static void main(String args[]) {
        System.out.println(System.getProperty("java.class.path"));
    }
}

