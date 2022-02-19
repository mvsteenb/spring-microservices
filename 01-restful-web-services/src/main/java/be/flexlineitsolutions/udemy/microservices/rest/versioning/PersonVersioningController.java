package be.flexlineitsolutions.udemy.microservices.rest.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

  @GetMapping("v1/person")
  public PersonV1 personV1() {
    return new PersonV1("mario");
  }

  @GetMapping("v2/person")
  public PersonV2 getPersonV2() {
    return new PersonV2(new Name("mario", "van steenberghe"));
  }

  @GetMapping(value = "person/param", params = "version=1")
  public PersonV1 paramV1() {
    return new PersonV1("mario");
  }

  @GetMapping(value = "person/param", params = "version=2")
  public PersonV2 paramV2() {
    return new PersonV2(new Name("mario", "van steenberghe"));
  }

  @GetMapping(value = "person/header", headers = "X-API-VERSION=1")
  public PersonV1 headerV1() {
    return new PersonV1("mario");
  }

  @GetMapping(value = "person/header", headers = "X-API-VERSION=2")
  public PersonV2 headerV2() {
    return new PersonV2(new Name("mario", "van steenberghe"));
  }

  @GetMapping(value = "person/producer", produces = "application/vnd.company.app-v1+json")
  public PersonV1 producerV1() {
    return new PersonV1("mario");
  }

  @GetMapping(value = "person/producer", produces = "application/vnd.company.app-v2+json")
  public PersonV2 producerV2() {
    return new PersonV2(new Name("mario", "van steenberghe"));
  }

}
