package com.manuels.principal.exceptions;

public class FieldInvalidException extends BadRequestException{
    
    private static final String DESCRIPTION = "Some invalid field";
    
    public FieldInvalidException(String detail){
        super(DESCRIPTION + ". " + detail);
    }
}