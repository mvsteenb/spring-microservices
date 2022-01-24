package be.flexlineitsolutions.udemy.microservices.rest;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

  public String helloWorld() {
    return "Hello World";
  }

}
