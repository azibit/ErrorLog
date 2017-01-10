/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.azibit.filter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;

@Provider
@PreMatching
public class CORSConfigurationFilter implements ContainerResponseFilter {


    private final static Logger LOGGER = Logger.getLogger(CORSConfigurationFilter.class.getName());
    @Override
    public void filter(ContainerRequestContext requestContext,
                       ContainerResponseContext responseContext) throws IOException {

        LOGGER.log(Level.INFO, "**HTTP API*** Filtering REST Request {0} --- {1}", new Object[]{requestContext.getMethod(), requestContext.getUriInfo().getPath()});
        responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
        responseContext.getHeaders().add("Access-Control-Allow-Headers",
                "origin, content-type, accept, X-HTTP-Method-Override, " + HttpHeaderName.AUTHORIZATION_PROPERTY  + ", "
                        + HttpHeaderName.AUTH_TOKEN);
        responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
        responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        responseContext.getHeaders().add("Access-Control-Max-Age", "1209600");
        
        
        
    }
}