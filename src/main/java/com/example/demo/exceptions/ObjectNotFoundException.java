package com.example.demo.exceptions;

public class ObjectNotFoundException extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ObjectNotFoundException(Long id, String type) {
        super(type + " not found with the id :: "+ id);
    }

    public ObjectNotFoundException(Long id, Class clazz) {
        super(clazz + " not found with the id :: "+ id);
    }
}