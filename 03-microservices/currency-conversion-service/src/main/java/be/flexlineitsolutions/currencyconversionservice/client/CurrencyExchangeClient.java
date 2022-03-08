package be.flexlineitsolutions.currencyconversionservice.client;

import be.flexlineitsolutions.currencyconversionservice.model.CurrencyConversionBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name="currency-exchange-service", url="localhost:8000")
@FeignClient(name="currency-exchange-service")
public interface CurrencyExchangeClient {

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	CurrencyConversionBean retrieveExchangeValue(@PathVariable String from, @PathVariable String to);

}
