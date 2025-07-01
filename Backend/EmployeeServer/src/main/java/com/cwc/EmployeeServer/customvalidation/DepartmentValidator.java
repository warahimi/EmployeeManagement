package com.cwc.EmployeeServer.customvalidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DepartmentValidator implements ConstraintValidator<AllowedDepartments, String> {

    private Set<String> allowedValues;

    @Override
    public void initialize(AllowedDepartments constraintAnnotation) {
        allowedValues = new HashSet<>(Arrays.asList(constraintAnnotation.anyOf()));
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && allowedValues.contains(value);
    }
}
