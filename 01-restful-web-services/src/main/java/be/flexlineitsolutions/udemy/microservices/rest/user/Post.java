package be.flexlineitsolutions.udemy.microservices.rest.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

  @Id
  @GeneratedValue
  private Integer id;

  private String description;

  @JsonIgnore // avoid circular loop in json
  @ManyToOne(fetch = FetchType.LAZY)
  private User user;

}
