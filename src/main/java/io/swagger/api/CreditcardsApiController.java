package io.swagger.api;

import io.swagger.model.request.CreditCardRequest;
import io.swagger.model.response.CreditCardResponse;
import io.swagger.model.response.CsCardResponse;
import io.swagger.model.response.ScoredCardResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.impl.tasks.FetchCsCards;
import io.swagger.impl.tasks.FetchScoredCards;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;

import java.util.Collections;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@RestController
public class CreditcardsApiController {

	private static final Logger log = LoggerFactory.getLogger(CreditcardsApiController.class);

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;
	
	private ExecutorService executor;

	@org.springframework.beans.factory.annotation.Autowired
	public CreditcardsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		log.info("Initilizing CreditcardsApiController");
		this.objectMapper = objectMapper;
		this.request = request;
		log.info("Initilized CreditcardsApiController");
	}

	@RequestMapping("/creditcards")
	public ResponseEntity<? extends CreditCardResponse> creditcardsPost(@Valid @RequestBody CreditCardRequest body) {
		executor = Executors.newFixedThreadPool(2);
		CreditCardResponse consolitedResponse = new CreditCardResponse();
		CountDownLatch latch = new CountDownLatch(2);

		Future<CsCardResponse> v1Response = executor.submit(new FetchCsCards(objectMapper, body, latch));
		Future<ScoredCardResponse> v2Response = executor.submit(new FetchScoredCards(objectMapper, body, latch));

		try {
			consolitedResponse.addAll(v1Response.get());
			consolitedResponse.addAll(v2Response.get());
			log.info("Waiting at latch");
			latch.await(500, TimeUnit.MILLISECONDS);
			log.info("Latch coundown completed");
			log.info("Sorting reponse");
			Collections.sort(consolitedResponse);
			log.info("Sorting completed");
			return new ResponseEntity<CreditCardResponse>(consolitedResponse, HttpStatus.OK);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} finally {
			log.info("Shutting down executor");
			executor.shutdown();
			log.info("Successfully Shut down executor");
		}
		try {
			log.info("Awaiting Executor termination");
			executor.awaitTermination(500, TimeUnit.MILLISECONDS);
			log.info("Executory terminated");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<CreditCardResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
