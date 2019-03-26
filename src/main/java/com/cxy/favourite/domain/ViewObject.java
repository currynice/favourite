package com.cxy.favourite.domain;

import java.util.HashMap;
import java.util.Map;

public class ViewObject {
    private Map<String,Object> objs = new HashMap<>();

    public  Object get(String key) {
        return objs.get(key);
    }

    public void set(String key,Object value) {
        this.objs.put(key,value);
    }
}
