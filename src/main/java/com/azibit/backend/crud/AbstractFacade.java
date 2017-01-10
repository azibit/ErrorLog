package com.azibit.backend.crud;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Muizzuddeen
 */
public class AbstractFacade {

    /**
     * This utility method checks if there are any constraints violations on entities,
     * at the point of persistence.
     * By default, JPA does not tell exactly on what field the violation exist which makes it difficult in debugging,
     * this method will.
     *
     * @param <T>
     * @param entity
     * @return true if violation exists and false if there are none.
     */
    public static <T> boolean constraintValidationsDetected(T entity) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(entity);
        if (constraintViolations.size() > 0) {
            Iterator<ConstraintViolation<T>> iterator = constraintViolations.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation<T> cv = iterator.next();
                System.err.println(cv.getRootBeanClass().getName() + "." + cv.getPropertyPath() + " " + cv.getMessage());
                System.err.println(cv.getRootBeanClass().getSimpleName() + "." + cv.getPropertyPath() + " " + cv.getMessage());
            }
            return true;
        } else {
            return false;
        }
    }

}