package com.naythan.QuizApp.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponce<T> {
    private String message;
    private  T data;
}
