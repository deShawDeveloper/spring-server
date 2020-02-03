package io.swagger.impl.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import io.swagger.model.CsCards;
import io.swagger.model.request.CreditCardRequest;
import io.swagger.model.response.CreditCardResponse;
import io.swagger.model.response.CsCardResponse;

public class FetchCsCards implements Callable<CsCardResponse> {
	
	public static final String API_PATH = "https://app.clearscore.com/api/global/backend-tech-test/v1/cards";
	public static final String CONTENT_TYPE = "application/json";

	private static final Logger log = LoggerFactory.getLogger(FetchCsCards.class);
	
	private final ObjectMapper objectMapper;
	private CreditCardRequest requestBody;
	private final CountDownLatch latch;

	public FetchCsCards(ObjectMapper objectMapper, CreditCardRequest request,CountDownLatch latch) {
		this.objectMapper = objectMapper;
		this.requestBody = request;
		this.latch = latch;
	}

	@Override
	public CsCardResponse call() throws Exception {
		try {
			String line = "";
			StringBuffer result = new StringBuffer();
			HttpResponse response;
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(requestBody);
			StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost responseFetchrequest = new HttpPost(getScoreCardEndPoint());
			responseFetchrequest.setEntity(entity);
			responseFetchrequest.addHeader("Content-Type", CONTENT_TYPE);
			response = httpClient.execute(responseFetchrequest);
			BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			while ((line = reader.readLine()) != null) {
				result.append(line);
			}
			log.info(result.toString());
			latch.countDown();
			log.info("Counddown latch 1");
			return objectMapper.readValue(result.toString(), CsCardResponse.class);
		} catch (IOException e) {
			log.error("Couldn't serialize response for content type application/json", e);
			return new CsCardResponse(false);
		}
	}
	
	private String getScoreCardEndPoint() {
		String path = null;
		try {
			path = System.getenv("CSCARDS_ENDPOINT");
			if(path == null) {
				path = API_PATH;
			}
		}catch(Exception e){
			log.error("CSCARDS_ENDPOINT not found in environment");
			path = API_PATH;
		}
		return path;
	}

}
