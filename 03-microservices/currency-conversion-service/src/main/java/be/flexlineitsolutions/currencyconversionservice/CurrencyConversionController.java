package be.flexlineitsolutions.currencyconversionservice;

import be.flexlineitsolutions.currencyconversionservice.model.CurrencyConversionBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionController {

	@GetMapping("currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {

		final Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);

		final ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity(
	"http://localhost:8001/currency-exchange/from/{from}/to/{to}",
			CurrencyConversionBean.class,
			uriVariables);
		final CurrencyConversionBean response = responseEntity.getBody();

		return new CurrencyConversionBean(
				response.getId(),
				from,
				to,
				response.getConversionMultiple(),
				quantity,
				quantity.multiply(response.getConversionMultiple()),
				0
		);
	}

}
