package be.flexlineitsolutions.currencyexchangeservice;

import be.flexlineitsolutions.currencyexchangeservice.exception.NotFoundException;
import be.flexlineitsolutions.currencyexchangeservice.model.ExchangeValue;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class CurrencyExchangeController {

	private final Environment environment;
	private final ExchangeValueRepository repository;

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		return repository.findByFromAndTo(from, to).orElseThrow(NotFoundException::new);
	}

}
