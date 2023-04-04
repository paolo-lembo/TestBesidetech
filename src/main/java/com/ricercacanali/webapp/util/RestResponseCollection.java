package com.ricercacanali.webapp.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Collection;
@Data
@AllArgsConstructor
public class RestResponseCollection <T>{

    private int status;
    private String message;
    private Collection<T> result;

}
