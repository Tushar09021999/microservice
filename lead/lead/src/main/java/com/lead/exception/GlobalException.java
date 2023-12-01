package com.lead.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> ResourceNotFoundException(ResourceNotFoundException e){

        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ErrorDetails error = new ErrorDetails(e.getMessage(), httpStatus);
        return  new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
//        BindingResult bindingResult = ex.getBindingResult();
//        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
//
//        // Extract custom message for phoneNumber validation
//        String customErrorMessage = fieldErrors.stream()
//                .filter(error -> "phoneNumber".equals(error.getField()))
//                .map(FieldError::getDefaultMessage)
//                .findFirst()
//                .orElse("Validation failed.phone");
//
//        // Construct your error response using the customErrorMessage
//        ErrorDetails errorResponse = new ErrorDetails();
//        errorResponse.setMessage(customErrorMessage);// Replace ErrorResponse with your actual response object
//        errorResponse.setHttpStatus(HttpStatus.NOT_ACCEPTABLE);
//
//        return ResponseEntity.badRequest().body(errorResponse);
//    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
//        BindingResult bindingResult = ex.getBindingResult();
//        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
//
//        // Extract custom messages for different validation errors
//        String customNameErrorMessage = fieldErrors.stream()
//                .filter(error -> "name".equals(error.getField()))
//                .map(FieldError::getDefaultMessage)
//                .findFirst()
//                .orElse("Validation failed, Name must not be empty");
//
//        String customEmailErrorMessage = fieldErrors.stream()
//                .filter(error -> "email".equals(error.getField()))
//                .map(FieldError::getDefaultMessage)
//                .findFirst()
//                .orElse("Validation failed, Invalid Email Format");
//
//        String customPhoneNumberErrorMessage = fieldErrors.stream()
//                .filter(error -> "phoneNumber".equals(error.getField()))
//                .map(FieldError::getDefaultMessage)
//                .findFirst()
//                .orElse("Validation failed, phoneNumber must be 10 digits");
//
//        // Construct your error response using the custom error messages
//        ErrorDetails errorResponse = new ErrorDetails();
//        errorResponse.setMessage(customNameErrorMessage);
//        errorResponse.setMessage(customEmailErrorMessage);
//        errorResponse.setMessage(customPhoneNumberErrorMessage);
//
//        // Set other response properties as needed
//        errorResponse.setHttpStatus(HttpStatus.NOT_ACCEPTABLE);
//
//        return ResponseEntity.badRequest().body(errorResponse);
//    }


}
