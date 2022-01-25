package be.flexlineitsolutions.udemy.microservices.rest.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

  @Autowired
  private UserDaoService userDaoService;

  public UserController() {
  }

  @GetMapping("/users")
  public List<User> retreiveAllUsers() {
    return this.userDaoService.findAll();
  }

  @GetMapping("/users/{id}")
  public User retrieveUser(@PathVariable int id) {
    return this.userDaoService.findOne(id).orElse(null);
  }

}
