package be.flexlineitsolutions.udemy.microservices.rest.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJPAController {

  @Autowired
  private UserRepository userRepository;

  public UserJPAController() {
  }

  @GetMapping("/jpa/users")
  public List<User> retreiveAllUsers() {
    return this.userRepository.findAll();
  }

  @GetMapping("/jpa/users/{id}")
  public EntityModel<User> retrieveUser(@PathVariable int id) {
    Optional<User> user = this.userRepository.findById(id);
    if (!user.isPresent())
      throw new UserNotFoundException("id:" + id);

    EntityModel<User> model = EntityModel.of(user.get());
    WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).retreiveAllUsers());
    model.add(linkToUsers.withRel("all-users"));

    return model;
  }

  @DeleteMapping("/jpa/users/{id}")
  public void deleteUser(@PathVariable int id) {
    this.userRepository.deleteById(id);
  }

  @PostMapping("/jpa/users")
  public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
    final User savedUser = this.userRepository.save(user);
    URI uri = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(savedUser.getId())
        .toUri();

    return ResponseEntity.created(uri).build();
  }
}
