package com.spbl.assignment1.domain;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenericResponse {

    private int code;
    private String message;
    
}
