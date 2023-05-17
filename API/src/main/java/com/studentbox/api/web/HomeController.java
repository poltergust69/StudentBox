package com.studentbox.api.web;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
@CrossOrigin("*")
public class HomeController {
    @GetMapping
    @ApiOperation(value="Home Page", response = String.class)
    public String getBasicResponse(){
        return "Hello World!";
    }
}
