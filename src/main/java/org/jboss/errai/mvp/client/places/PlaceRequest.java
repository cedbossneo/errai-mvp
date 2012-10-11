/*
 * Copyright (c) 2012.
 * Cedric Hauber
 */

package org.jboss.errai.mvp.client.places;


import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Cedric
 * Date: 10/3/12
 * Time: 8:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlaceRequest {
    private String token;
    private Map<String, String> args;
    private String request;

    public PlaceRequest() {
        args = new HashMap<String, String>();
    }

    public PlaceRequest(String request) {
        this();
        this.request = request;
        parseRequest(request);
    }

    private void parseRequest(String request) {
        String[] params = request.split("\\;");
        token = params[0];
        if (params.length == 1)
            return;
        for (int i = 1; i < params.length; i++){
            String[] val = params[i].split("\\=");
            setParameter(val[0], val.length > 1 ? val[1] : "");
        }
    }

    private void makeRequest() {
        request = token;
        for (String param : args.keySet()){
            request += ";" + param + "=" + args.get(param);
        }
    }

    private void setParameter(String name, String value) {
        args.put(name, value);
        makeRequest();
    }


    public PlaceRequest with(String name, String value){
        setParameter(name, value);
        return this;
    }

    @Override
    public String toString() {
        return request;
    }
}
