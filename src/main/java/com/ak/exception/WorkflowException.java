package com.ak.exception;

import lombok.Data;

import javax.ws.rs.core.Response;

@Data
public class WorkflowException extends RuntimeException {

    private Response.Status status;
    private String message;


    public WorkflowException(Response.Status status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

    public WorkflowException(String message) {
        super(message);
        this.status = Response.Status.INTERNAL_SERVER_ERROR;
        this.message = message;
    }

    public WorkflowException(String message, Throwable throwable) {
        super(message, throwable);
        this.status = Response.Status.INTERNAL_SERVER_ERROR;
        this.message = message;
    }


    public WorkflowException(Response.Status status, String message, Throwable throwable) {
        super(message, throwable);
        this.status = status;
        this.message = message;
    }

    public WorkflowException(Response.Status status, Throwable throwable) {
        super(throwable.getMessage(), throwable);
        this.status = status;
        this.message = throwable.getMessage();
    }
}
