package be.flexlineitsolutions.udemy.microservices.rest.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    User user = this.userDaoService.findOne(id).orElse(null);
    if (user == null)
      throw new UserNotFoundException("id:" + id);
    return user;
  }

  @DeleteMapping("/users/{id}")
  public User deleteUser(@PathVariable int id) {
    User user = this.userDaoService.deleteUser(id);
    if (user == null)
      throw new UserNotFoundException("id:" + id);
    return user;
  }

  @PostMapping("/users")
  public ResponseEntity<Object> createUser(@RequestBody User user) {
    final User savedUser = this.userDaoService.save(user);
    URI uri = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(savedUser.getId())
        .toUri();

    return ResponseEntity.created(uri).build();
  }
}
