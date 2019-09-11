package com.sam.utils.argumentsToUseWithJSCommander;

import com.beust.jcommander.Parameter;

public class ArgPassword {
    @Parameter(names = "-password", description = "GMail password", password = true)
    private String password;
}
