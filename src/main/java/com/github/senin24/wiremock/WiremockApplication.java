package com.github.senin24.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import wiremock.com.google.common.collect.ImmutableMap;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

@SpringBootApplication
public class WiremockApplication {


	/**
	 * run
	 *
	 * create get http://localhost:8080/transform with basic auth 'user' 'password'
   * See configuration at: http://localhost:8080/__admin/"
   *
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		WireMockConfiguration wmConfig =
				wireMockConfig()
						.port(8080);

		WireMockServer wireMockServer = new WireMockServer(wmConfig);

		wireMockServer.start();

		stubFor(get(urlEqualTo("/transform"))
				.withBasicAuth("user", "password")
				.willReturn(
				aResponse()
						.withBody("\"goz_account\": 407068100000000140614")
						.withTransformerParameter("newValue", 66)
						.withTransformerParameter("inner", ImmutableMap.of("thing", "value"))));



		SpringApplication.run(WiremockApplication.class, args);


	}




}
