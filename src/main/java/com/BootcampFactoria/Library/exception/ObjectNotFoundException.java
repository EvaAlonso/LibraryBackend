package com.BootcampFactoria.Library.exception;

import java.util.List;

public class ObjectNotFoundException extends RuntimeException{
    public ObjectNotFoundException(String objectName, List<Integer> id){

        super("Could not find " + objectName + " with id: " + id);
    }

}
