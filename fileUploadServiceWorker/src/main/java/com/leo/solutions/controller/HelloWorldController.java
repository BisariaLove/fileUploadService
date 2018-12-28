package com.leo.solutions.controller;
/*
 * @author love.bisaria on 27/12/18
 */

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/file")
public class HelloWorldController {

    @RequestMapping(value="/helloWorld")
    public String helloWorld(){

        return "Hello World!!";
    }

}
