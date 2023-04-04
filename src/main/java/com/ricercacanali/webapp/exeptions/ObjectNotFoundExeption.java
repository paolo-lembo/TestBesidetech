package com.ricercacanali.webapp.exeptions;

import lombok.*;

@Getter
@Setter
public class ObjectNotFoundExeption extends Exception {
    private int code;
    private String description;

    public ObjectNotFoundExeption(int code, String description) {
        super();
        this.code = code;
        this.description = description;
    }
}
