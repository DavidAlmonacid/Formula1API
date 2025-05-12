package com.example.formula1api.exceptions;

public record ErrorResponse(int code, String status, String message) {
}
