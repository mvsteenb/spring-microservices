package be.flexlineitsolutions.limitsservice;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LimitsConfigurationController {

	@GetMapping("/limits")
	public LimitConfiguration retreiveLimitConfigurations() {
		return new LimitConfiguration(1000, 1);
	}

}
