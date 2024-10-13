package ru.t1.java.correctionservice.utils.exceptions;

import ru.t1.java.correctionservice.dto.TransactionErrorDto;

public class FeignBadRequestException extends RuntimeException {
    private final int status;
    private final String message;
    private final TransactionErrorDto errorDto;

    public FeignBadRequestException(int status, String message, TransactionErrorDto errorDto) {
        super(message);
        this.status = status;
        this.message = message;
        this.errorDto = errorDto;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public TransactionErrorDto getErrorDto() {
        return errorDto;
    }
}


