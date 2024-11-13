package org.example.aplicationstripe.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.example.aplicationstripe.exception.InvalidDTOException;
import org.example.aplicationstripe.web.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ProductsExcepionHandler {

    @ExceptionHandler(InvalidDTOException.class)
    public ResponseEntity<ErrorDTO> handleInvalidDTOException(InvalidDTOException ex) {
        HttpServletRequest request =((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        List<String> messages = ex.getErrorMessages();
        String errorMessage = String.join("\n", messages).trim();
        ErrorDTO errorDTO = new ErrorDTO(request.getRequestURI(), errorMessage,LocalDateTime.now());
        return new ResponseEntity<>(errorDTO,HttpStatus.BAD_REQUEST);
    }
}
