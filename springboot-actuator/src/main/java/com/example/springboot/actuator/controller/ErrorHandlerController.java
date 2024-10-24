/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.example.springboot.actuator.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zoujie
 * @description ErrorHandlerController
 * @team wuhan operational dev.
 * @date 2020/3/17 11:43 AM
 **/
@Slf4j
@RestControllerAdvice
public class ErrorHandlerController {




    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.OK)
    public String requestHandlingException(Exception e, HttpServletRequest request) {
        log.error(String.valueOf(e), e);
        System.out.println("问题出现了");
        return "error";
    }

}
