package online.store.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice 
public class GlobalControllerExceptionHandler {
    
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> defaultErrorHandler(HttpServletRequest request, Exception exception) throws Exception {
            System.out.println(String.format("Exception in handling request to %s: %s",
                request.getRequestURI(), exception.getMessage()));
        exception.printStackTrace();
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

