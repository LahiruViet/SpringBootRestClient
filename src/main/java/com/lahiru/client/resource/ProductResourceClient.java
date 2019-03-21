package com.lahiru.client.resource;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.lahiru.client.ClientApplication;
import com.lahiru.client.entity.Product;

public class ProductResourceClient {

	private static final Logger logger = LoggerFactory.getLogger(ClientApplication.class);

	private static final String URL_PRODUCTS = "http://localhost:8090/demo/rest/api/products";

	public static void findAll() {

		// HttpHeaders
		HttpHeaders headers = new HttpHeaders();

		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));

		HttpEntity<Product[]> entity = new HttpEntity<Product[]>(headers);

		RestTemplate restTemplate = new RestTemplate();

		// Send request with GET method, and Headers.
		ResponseEntity<Product[]> response = restTemplate.exchange(URL_PRODUCTS, HttpMethod.GET, entity,
				Product[].class);

		HttpStatus statusCode = response.getStatusCode();
		logger.info("Response Satus Code: " + statusCode);

		// Status Code: 200
		if (statusCode == HttpStatus.OK) {
			// Response Body Data
			Product[] list = response.getBody();

			if (list != null) {
				for (Product product : list) {
					logger.info("Product: " + product.getId() + " - " + product.getName());
				}
			}
		} else {
			logger.info("Response Satus Code: " + statusCode);
		}
	}

	public static void save() {

		Product newProduct = new Product("Scooter");

		// HttpHeaders
		HttpHeaders headers = new HttpHeaders();

		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
		headers.setContentType(MediaType.APPLICATION_JSON);

		RestTemplate restTemplate = new RestTemplate();

		// Data attached to the request.
		HttpEntity<Product> requestBody = new HttpEntity<>(newProduct, headers);

		// Send request with POST method.
		ResponseEntity<Product> result = restTemplate.postForEntity (URL_PRODUCTS, requestBody, Product.class);

		logger.info("Status code:" + result.getStatusCode());

		// Code = 201.
		if (result.getStatusCode() == HttpStatus.CREATED) {
			Product product = result.getBody();
			logger.info("(Client Side) Product Created: " + product.getId());
		} else {
			logger.info("Status code:" + result.getStatusCode());
		}
	}

}
