/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.azibit.backend.crud;

/**
 *
 * @author faizbash
 */
public class ParameterDefinition {
    private final String name;
    private final Object value;
    private final ParameterType paramType;

    public ParameterDefinition(ParameterType paramType,
            String name, Object value) {
        this.name = name;
        this.value = value;
        this.paramType = paramType;
    }

    public ParameterType getParameterType() {
        return paramType;
    }
    
    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}