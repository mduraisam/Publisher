package com.txn.moneychanger.kafka.config;

import com.txn.moneychanger.kafka.model.MoneyExchange;

public class CurrencyRate {

	final static double rate = 1.3392;

	public static double convert(String amt) {
		double amtData = Double.valueOf(amt);
		return amtData;
	}

	public static MoneyExchange convert(MoneyExchange moneyExchange) {
		double convertValue = 0.0;

		if ("USD".equals(moneyExchange.getCurrency())) {
			convertValue = moneyExchange.getRate() * rate;
		} else if ("SGD".equals(moneyExchange.getCurrency())) {
			convertValue = moneyExchange.getRate() / rate;
		}
		moneyExchange.setConvertedRate(convertValue);
		return moneyExchange;
	}
}
