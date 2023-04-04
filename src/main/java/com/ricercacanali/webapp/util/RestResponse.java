package com.ricercacanali.webapp.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestResponse<T>{

    private int status;
    private String message;
    private T result;

    public RestResponse() {}
}
