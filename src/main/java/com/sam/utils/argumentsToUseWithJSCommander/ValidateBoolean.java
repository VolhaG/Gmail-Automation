package com.sam.utils.argumentsToUseWithJSCommander;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

public class ValidateBoolean implements IParameterValidator {

    @Override
    public void validate(String name, String value){
        if (!((value.contains("true")) || (!value.contains("false")))) {
            throw new ParameterException("Boolean parameter " + name + " should contain in it's value true or false");
        }
    }

}
