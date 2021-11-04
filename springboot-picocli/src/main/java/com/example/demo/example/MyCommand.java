/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.example.demo.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/11/4 19:05
 **/
@Component
@CommandLine.Command(name = "mycommand", mixinStandardHelpOptions = true, subcommands = MyCommand.Sub.class)
public class MyCommand implements Callable<Integer> {
    @CommandLine.Option(names = "-x", description = "optional option")
    private String x;

    @CommandLine.Parameters(description = "positional params")
    private List<String> positionals;

    @Override
    public Integer call() {
        System.out.printf("mycommand was called with -x=%s and positionals: %s%n", x, positionals);
        return 23;
    }

    @Component
    @CommandLine.Command(name = "sub", mixinStandardHelpOptions = true, subcommands = MyCommand.SubSub.class,
        exitCodeOnExecutionException = 34)
    static class Sub implements Callable<Integer> {
        @CommandLine.Option(names = "-y", description = "optional option")
        private String y;

        @CommandLine.Parameters(description = "positional params")
        private List<String> positionals;

        @Override
        public Integer call() {
            System.out.printf("mycommand sub was called with -y=%s and positionals: %s%n", y, positionals);
            return 33;
        }
    }

    @Component
    @CommandLine.Command(name = "subsub", mixinStandardHelpOptions = true,
        exitCodeOnExecutionException = 44)
    static class SubSub implements Callable<Integer> {
        @CommandLine.Option(names = "-z", description = "optional option")
        private String z;

        @Autowired
        private SomeService service;

        @Override
        public Integer call() {
            System.out.printf("mycommand sub subsub was called with -z=%s. Service says: '%s'%n", z, service.service());
            return 43;
        }
    }
}
