package com.txn.moneychanger.pubapp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;


@RunWith(SpringJUnit4ClassRunner.class)
class PublisherAppTest {

	@Autowired
	RestTemplate restTemplate;

	@Test
	public void testPublish() throws Exception {

		final String uri = "http://localhost:8081/publish/rate?Currency=USD&Rate=10&Type=BUY";

		String result = restTemplate.getForObject(uri, String.class);

		Assert.assertEquals(true, result.indexOf("Money ") > 0);
	}

}
