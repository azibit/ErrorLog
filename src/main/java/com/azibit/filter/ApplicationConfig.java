package com.azibit.filter;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import java.util.Collection;


/**
 * Created by pitaside on 6/30/2015.
 * These set the webservice container
 * to listen for
 */


@javax.ws.rs.ApplicationPath("rest")
public class ApplicationConfig extends ResourceConfig {

    public ApplicationConfig() {
        //register the necessary headers files needed from client
        register(CORSConfigurationFilter.class);
        register(CORSRequestFilter.class);
        //The jackson feature and provider is used for object serialization
        //between client and server objects in to a json
        register(JacksonFeature.class);
//        register(JacksonProvider.class);
        //Glassfish multipart file uploader feature
        register(MultiPartFeature.class);
//        register(RolesAllowedDynamicFeature.class);
        //inject and registered all resources class using the package
        //not to be tempered with
        packages("com.azibit.errorlog.service");
//        register(RESTAuthenticationRequestFilter.class);
    }

    @Override
    public Collection<String> getPropertyNames() {
        return super.getPropertyNames();
    }
}


