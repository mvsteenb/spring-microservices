package be.flexlineitsolutions.udemy.microservices.rest.user;

import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserDaoService userDaoService;

  @GetMapping("/users")
  public List<User> retreiveAllUsers() {
    return this.userDaoService.findAll();
  }

  @GetMapping("/users/{id}")
  public EntityModel<User> retrieveUser(@PathVariable int id) {
    User user = this.userDaoService.findOne(id).orElse(null);
    if (user == null)
      throw new UserNotFoundException("id:" + id);

    EntityModel<User> model = EntityModel.of(user);
    WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).retreiveAllUsers());
    model.add(linkToUsers.withRel("all-users"));

    return model;
  }

  @DeleteMapping("/users/{id}")
  public User deleteUser(@PathVariable int id) {
    User user = this.userDaoService.deleteUser(id);
    if (user == null)
      throw new UserNotFoundException("id:" + id);
    return user;
  }

  @PostMapping("/users")
  public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
    final User savedUser = this.userDaoService.save(user);
    URI uri = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(savedUser.getId())
        .toUri();

    return ResponseEntity.created(uri).build();
  }
}
