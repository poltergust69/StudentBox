package com.studentbox.api.web.rest;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeRestController {
    @GetMapping
    @ApiOperation(value="Home Page", response = String.class)
    public String getBasicResponse(){
        return "Hello World!";
    }
}
