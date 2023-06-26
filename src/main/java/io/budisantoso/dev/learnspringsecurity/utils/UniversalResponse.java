package io.budisantoso.dev.learnspringsecurity.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class UniversalResponse<T> {
    private Integer statusCode;
    private String message;
    private T data;

    public UniversalResponse(Integer paramStatusCode, String paramMessage) {
        statusCode = paramStatusCode;
        message = paramMessage;
    }
}
