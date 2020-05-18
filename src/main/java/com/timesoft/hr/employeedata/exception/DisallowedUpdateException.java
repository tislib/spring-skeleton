package com.timesoft.hr.employeedata.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
@Getter
public class DisallowedUpdateException extends RuntimeException {
    private final Set<String> fields;
    private final String message;
}
