package com.bazinga.Bazinga.rest;

import org.springframework.http.ResponseEntity;

/**
 * Created by lazap on 11/4/2017.
 */
public abstract class BaseController {
    protected ResponseEntity response(Object object){
        return ResponseEntity.ok(object);
    }

    protected ResponseEntity responseNoContent(){
        return ResponseEntity.noContent().build();
    }

    protected ResponseEntity response(){
        return ResponseEntity.ok().build();
    }

    protected ResponseEntity responseBad(Object object) {
        return ResponseEntity.badRequest().body(object);
    }
}
