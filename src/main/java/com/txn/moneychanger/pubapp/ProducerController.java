package com.txn.moneychanger.pubapp;

import java.net.SocketException;

import org.apache.kafka.common.KafkaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.txn.moneychanger.kafka.config.CurrencyRate;
import com.txn.moneychanger.kafka.model.MoneyExchange;

@RestController
@RequestMapping("publish")
public class ProducerController {

	private static final Logger logger = LogManager.getLogger(ProducerController.class);
	@Autowired
	private KafkaTemplate<String, MoneyExchange> kafkaTemplate;

	@GetMapping("/rate")
	public String post(@RequestParam("Currency") final String currency, @RequestParam("Type") final String type,
			@RequestParam("Rate") final double rate) {

		logger.info("Post method invoked ");
		String responseMsg = "Money Change Rate Published Successfully";

		try {
			MoneyExchange moneyExchange = new MoneyExchange(currency, type, rate, 0.0);
			kafkaTemplate.send("money_exchange", CurrencyRate.convert(moneyExchange));
		} catch (KafkaException e) {
			responseMsg = "Err- while publishing in Kafka Topic ,Reason: " + e.getMessage();
			logger.error("Err- while publishing in Kafka Topic ,Reason:", e);
		} catch (Exception e) {
			responseMsg = "Err during Publishing ,Reason: " + e.getMessage();
			logger.error("Err during Publishing ,Reason:", e);
		}

		return responseMsg;
	}

}
