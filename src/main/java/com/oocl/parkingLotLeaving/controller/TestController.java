package com.oocl.parkingLotLeaving.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dylan Wei
 * @date 2018-08-23 15:29
 */
@RestController
public class TestController {

    @RequestMapping("/")
    public String test(){
        return "hello";
    }

}
