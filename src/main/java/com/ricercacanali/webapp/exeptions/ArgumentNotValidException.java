package com.ricercacanali.webapp.exeptions;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ArgumentNotValidException extends Exception  {
    private int code;
    private String description;

    public ArgumentNotValidException(int code, String description) {
        super();
        this.code = code;
        this.description = description;
    }
}
