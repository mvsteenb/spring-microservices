package be.flexlineitsolutions.limitsservice;

import be.flexlineitsolutions.limitsservice.config.Configuration;
import be.flexlineitsolutions.limitsservice.model.LimitConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LimitsConfigurationController {

	private final Configuration config;

	@GetMapping("/limits")
	public LimitConfiguration retreiveLimitConfigurations() {
		return new LimitConfiguration(config.getMaximum(), config.getMinimum());
	}

}
