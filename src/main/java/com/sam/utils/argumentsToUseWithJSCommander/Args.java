package com.sam.utils.argumentsToUseWithJSCommander;

import com.beust.jcommander.Parameter;

public class Args {

    @Parameter(names = "-groups", description = "Comma-separated list of group names to be run")
    private String groups;

    @Parameter(names = "-debug", description = "Debug mode")
    private boolean debug = false;

    @Parameter(names = "-email", validateWith = ValidateEmailAddress.class)
    private Integer age;

}