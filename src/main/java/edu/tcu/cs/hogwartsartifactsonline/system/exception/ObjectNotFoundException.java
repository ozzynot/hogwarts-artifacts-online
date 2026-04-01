package edu.tcu.cs.hogwartsartifactsonline.system.exception;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String ObjectName , String id ) {
        super("Could not find " + ObjectName +" with Id " + id);
    }

    public ObjectNotFoundException(String ObjectName, Integer id) {
        super("Could not find " + ObjectName +" with Id " + id);
    }
}