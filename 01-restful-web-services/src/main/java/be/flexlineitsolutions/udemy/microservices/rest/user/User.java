package be.flexlineitsolutions.udemy.microservices.rest.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @Id
  @GeneratedValue
  private Integer id;

  @Size(min = 2, message = "Name should have at least 2 characters")
  private String name;

  @Past
  private Date birthDate;

  @OneToMany(mappedBy = "user")
  private List<Post> posts;

}
