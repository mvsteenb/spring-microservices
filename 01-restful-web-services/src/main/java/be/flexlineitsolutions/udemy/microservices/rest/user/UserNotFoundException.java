package be.flexlineitsolutions.udemy.microservices.rest.user;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException(String s) {
    super(s);
  }
}
