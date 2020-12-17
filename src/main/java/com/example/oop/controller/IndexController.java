package com.example.oop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/seller")
public class IndexController {

    @GetMapping("/index")
    public ModelAndView index(Map<String, Object> map) {
        return new ModelAndView("index");
    }
}
