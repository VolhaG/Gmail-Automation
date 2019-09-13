package com.sam.utils.argumentsToUseWithJSCommander;

import com.beust.jcommander.Parameter;

public class CommandLineArgs {

    @Parameter(names = "-groups", description = "Comma-separated list of group names to be run")
    public String groups;

    @Parameter(names = "-email")
    public String email = "tt7381566@gmail.com";

    @Parameter(names = "-password")
    public String password = "test11111";

}