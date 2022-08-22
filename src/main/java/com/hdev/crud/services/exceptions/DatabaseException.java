package com.hdev.crud.services.exceptions;

//Execpetion é obrigatoria se for usada sua extensão
public class DatabaseException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public DatabaseException (String msg){
        super(); //repassa 
    }

}
