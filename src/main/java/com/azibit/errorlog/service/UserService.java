/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.azibit.errorlog.service;

import com.azibit.errorlog.entities.User;
import com.azibit.backend.crud.CrudServiceProviderLocal;
import com.azibit.errorlog.dtos.UserDto;
import com.azibit.errorlog.utils.Mapper;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

/**
 *
 * @author azibit
 */
@Stateless
public class UserService implements UserServiceLocal {

    @EJB
    private CrudServiceProviderLocal crud;

    @Override
    public Response getUsers() {
        return Response.ok(crud.findAll(User.class)).build();
    }

    @Override
    public Response createUser(UserDto user) {
        return Response.ok(crud.create(Mapper.mapToUser(user))).build();
    }

}
