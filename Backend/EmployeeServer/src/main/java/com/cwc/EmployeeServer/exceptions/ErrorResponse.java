package com.cwc.EmployeeServer.exceptions;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class ErrorResponse {
    private String message;
    private String date;  // formatted as MM/dd/yyyy
    private String time;  // formatted as hh:mm a
    private int status;
    private String exception;

    public ErrorResponse() {
    }

    public ErrorResponse(String message, LocalDate date, LocalTime time, int status, String exception) {
        this.message = message;
        this.date = date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        this.time = time.format(DateTimeFormatter.ofPattern("hh:mm a"));
        this.status = status;
        this.exception = exception;
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }

    public String getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time.format(DateTimeFormatter.ofPattern("hh:mm a"));
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "message='" + message + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", status=" + status +
                ", exception='" + exception + '\'' +
                '}';
    }
}
