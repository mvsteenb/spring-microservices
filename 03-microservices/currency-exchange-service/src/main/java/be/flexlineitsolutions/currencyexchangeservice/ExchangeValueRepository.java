package be.flexlineitsolutions.currencyexchangeservice;

import be.flexlineitsolutions.currencyexchangeservice.model.ExchangeValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {

	Optional<ExchangeValue> findByFromAndTo(final String from, final String to);

}
