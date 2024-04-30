package com.kopanusa.core.advice;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

@RestControllerAdvice
public class ValidationHandler 
{
  @ResponseStatus(HttpStatus.OK)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, Object> handleValidation(MethodArgumentNotValidException ex)
  {
    Map<String, Object> errors = new HashMap<>();

    errors.put("error_code", "1");
    List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
    allErrors.forEach(error -> {
      FieldError fieldError = (FieldError) error;
      errors.put("error_desc", fieldError.getDefaultMessage());
    });

    Map<String, Object> payload = new HashMap<String, Object>();
    errors.put("payload", payload);
    
    return errors;
  }
}
