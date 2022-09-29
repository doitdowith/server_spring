package com.seoultech.capstone.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

  @GetMapping("/test")
  public String test() {
    return "test";
  }

}
