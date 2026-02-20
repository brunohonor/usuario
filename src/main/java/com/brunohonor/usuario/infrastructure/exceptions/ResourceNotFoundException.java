package com.bruno.aprendendo.spring.infrastructure.exceptions;


import javax.swing.*;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException (String mensagem){
        super(mensagem);
    }

    public ResourceNotFoundException (String mensagem, Throwable throwable){
        super(mensagem, throwable);
    }
}
