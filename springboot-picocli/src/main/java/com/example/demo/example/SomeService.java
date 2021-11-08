/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.example.demo.example;

/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/11/4 19:03
 **/
public class SomeService {

    private final ServiceDependency dependency;

    public SomeService(ServiceDependency dependency) {
        this.dependency = dependency;
    }

    public String service() {
        return dependency.provideSomething();
    }
}
