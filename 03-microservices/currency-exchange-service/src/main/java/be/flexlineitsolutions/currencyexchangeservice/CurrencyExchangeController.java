package be.flexlineitsolutions.currencyexchangeservice;

import be.flexlineitsolutions.currencyexchangeservice.model.ExchangeValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		return ExchangeValue.builder()
				.conversionMultiple(BigDecimal.valueOf(1000L))
				.from(from)
				.to(to)
				.build();
	}

}
