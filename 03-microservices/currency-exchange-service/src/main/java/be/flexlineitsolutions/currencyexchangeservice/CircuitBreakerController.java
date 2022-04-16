package be.flexlineitsolutions.currencyexchangeservice;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class CircuitBreakerController {

    @GetMapping("/sample-api")
    //@Retry(name="sample-api", fallbackMethod = "hardcodedResponse")
    @CircuitBreaker(name="sample-api", fallbackMethod = "hardcodedResponse")
    @RateLimiter(name="sample-api")  // e.g. in 10s => allow only 1000s calls to sample API
    @Bulkhead(name="sample-api")
    public String sampleAPI() {
        log.info("Sample API call received !");
        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", String.class);
        return forEntity.getBody();
//        return "sample api";
    }

    public String hardcodedResponse(Exception e) {
        return "fallback-response";
    }

}
