package be.flexlineitsolutions.udemy.microservices.rest.user;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserDaoService {

  private static List<User> users = new ArrayList<>();
  private static int usersCount = 3;

  static {
    users.add(new User(1, "Mario", new Date()));
    users.add(new User(2, "Monica", new Date()));
    users.add(new User(3, "Phoebe", new Date()));
  }

  public List<User> findAll() {
    return users;
  }

  public User save(User user) {
    if (user.getId() == null) {
      user.setId(++usersCount);
    }
    users.add(user);
    return user;
  }

  public Optional<User> findOne(int id) {
    return users.stream().filter(u -> u.getId() == id).findAny();
  }

  public User deleteUser(final int id) {
    Iterator<User> iterator = users.iterator();
    while (iterator.hasNext()) {
      User user = iterator.next();
      if (user.getId() == id) {
        iterator.remove();
        return user;
      }
    }
    return null;
  }

}
