package com.example.gasstan.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.gasstan.com",
                ownerName = "backend.gasstan.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    @ApiMethod(name = "login")
    public MyBean login(@Named("email") String email, @Named("password") String password) {
        MyBean response = new MyBean();
        response.setSuccess(true).setMessage("Welcome " + email);

        // TO DO: Login implementation
        return response;
    }

    @ApiMethod(name = "register")
    public MyBean register(@Named("email") String email, @Named("password") String password) {
        MyBean response = new MyBean();
        response.setSuccess(true).setMessage("User: " + email + "\nwith password: " + password + "\nsuccessfully registered.");
        // TO DO: Registration implementation
        return response;
    }
}
