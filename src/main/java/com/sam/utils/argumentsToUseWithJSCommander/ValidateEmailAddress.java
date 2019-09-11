package com.sam.utils.argumentsToUseWithJSCommander;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

public class ValidateEmailAddress implements IParameterValidator {
    @Override
    public void validate(String name, String value) throws ParameterException {
        if (!((value.contains("@")) && (!value.contains(".")))) {
            throw new ParameterException("Email parameter " + name + " should contain in it's value chars '@', '.'");
        }
    }
}