/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.azibit.errorlog.service;

import com.azibit.errorlog.dtos.UserDto;
import com.azibit.errorlog.entities.User;
import com.azibit.errorlog.utils.ErrorlogKonstants;
import java.util.List;
import javax.ejb.Local;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author azibit
 */

@Local
@Path(ErrorlogKonstants.USER_SERVICE)
public interface UserServiceLocal {
    
    @GET
    @Path(ErrorlogKonstants.USERS)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers();
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(UserDto user);
}

