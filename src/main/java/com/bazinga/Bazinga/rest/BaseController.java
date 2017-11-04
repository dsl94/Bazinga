package com.bazinga.Bazinga.rest;

import org.springframework.http.ResponseEntity;

/**
 * Created by lazap on 11/4/2017.
 */
public abstract class BaseController {
    protected ResponseEntity response(Object object){
        return ResponseEntity.ok(object);
    }
}
