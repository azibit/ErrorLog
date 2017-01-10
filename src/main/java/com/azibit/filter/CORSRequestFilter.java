/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.azibit.filter;

import java.io.IOException;
import java.util.logging.Level;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author azibit
 */
@Provider
@PreMatching
public class CORSRequestFilter implements ContainerRequestFilter{

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        
//        LOGGER.log(Level.INFO, "**HTTP API*** Filtering REST Request {0} --- {1}", new Object[]{requestContext.getMethod(), requestContext.getUriInfo().getPath()});
        requestContext.getHeaders().add("Access-Control-Allow-Origin", "*");
        requestContext.getHeaders().add("Access-Control-Allow-Headers",
                "origin, content-type, accept, X-HTTP-Method-Override, " + HttpHeaderName.AUTHORIZATION_PROPERTY  + ", "
                        + HttpHeaderName.AUTH_TOKEN);
        requestContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
        requestContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        requestContext.getHeaders().add("Access-Control-Max-Age", "1209600");
    }
    
    
}
