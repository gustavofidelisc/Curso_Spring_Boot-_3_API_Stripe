package org.example.aplicationstripe.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class InvalidDTOException extends Exception{
    private final List<String> errorMessages;
}
