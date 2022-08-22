package com.hdev.crud.services.exceptions;

//Execpetion é obrigatoria se for usada sua extensão
public class EntityNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public EntityNotFoundException (String msg){
        super(); //repassa 
    }

}
