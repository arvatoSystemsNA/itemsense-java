package com.impinj.itemsense.client.coordinator.user;

import com.google.gson.Gson;
import com.impinj.itemsense.client.helpers.RestApiHelper;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by jcombopi on 1/25/16.
 */
public class UserController {

    private Gson gson;
    private WebTarget target;
    private RestApiHelper<User> restApiHelper;

    public UserController(final Gson gson, WebTarget target) {
        this.gson = gson;
        this.target = target;
        this.restApiHelper = new RestApiHelper<User>(User.class);
    }

    public User createUser(User user) {
        return this.restApiHelper.post(user, "/configuration/v1/users/create", target, gson);
    }

    public User updateUser(User user) {
        return this.restApiHelper.put(user, "/configuration/v1/users/create", target, gson);
    }

    public Response deleteUser(String userName) {
        return this.restApiHelper.delete(userName, "/configuration/v1/users/destroy", target);
    }

    public User getUser(String userName) {
        return this.restApiHelper.get(userName, "/configuration/v1/users/show", target);
    }

    public ArrayList<User> getUsers() {
        return this.restApiHelper.getMultiple(null, "/configuration/v1/users/show", target, gson);
    }
}
