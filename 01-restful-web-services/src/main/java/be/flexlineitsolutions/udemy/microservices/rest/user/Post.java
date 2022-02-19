package be.flexlineitsolutions.udemy.microservices.rest.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Post {

  @Id
  @GeneratedValue
  private Integer id;

  private String description;

  @JsonIgnore // avoid circular loop in json
  @ManyToOne(fetch = FetchType.LAZY)
  private User user;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
