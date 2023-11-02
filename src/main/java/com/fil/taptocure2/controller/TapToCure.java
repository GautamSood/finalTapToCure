package com.fil.taptocure2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TapToCure {
    @GetMapping("/")
    public String tapToCure(){
        return "TapTocure";
    }



}
