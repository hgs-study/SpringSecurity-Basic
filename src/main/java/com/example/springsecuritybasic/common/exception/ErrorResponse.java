package com.example.springsecuritybasic.common.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse {
    private String message;
    @Builder
    private ErrorResponse(String message){
        this.message=message;
    }
}
