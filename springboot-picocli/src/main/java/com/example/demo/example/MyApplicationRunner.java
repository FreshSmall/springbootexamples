/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.example.demo.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/11/4 19:04
 **/
@Component
public class MyApplicationRunner implements CommandLineRunner,ExitCodeGenerator {

    private final MyCommand myCommand;

    private final CommandLine.IFactory factory; // auto-configured to inject PicocliSpringFactory

    private int exitCode;

    public MyApplicationRunner(MyCommand myCommand, CommandLine.IFactory factory) {
        this.myCommand = myCommand;
        this.factory = factory;
    }

    @Override
    public void run(String... args) throws Exception {
        exitCode = new CommandLine(myCommand, factory).execute(args);
    }

    @Override
    public int getExitCode() {
        return exitCode;
    }
}
