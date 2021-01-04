package com.pl42.controller;

import com.google.gson.Gson;
import com.pl42.kahului.mind.Kahului;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KahuluiController {
  private static final Logger logger = Logger.getLogger(KahuluiController.class);
  private static final String PATH_BALANCE = "/balance/btc";
  private static final String PATH_PROFIT = "/balance/profit";
  private static final String PATH_SHUTDOWN = "/seppuku";
  private static final String PATH_STATUS = "/status";
  private static final String PATH_OPEN_ORDERS = "/orders";
  private static final String RESPONSE_SUFFIX = " endpoint hit";
  private final Kahului kahului;

  @Autowired
  public KahuluiController(Kahului kahului) {
    this.kahului = kahului;
  }

  @GetMapping(path = PATH_BALANCE)
  public ResponseEntity getTotalBTC() {
    logger.trace(PATH_BALANCE + RESPONSE_SUFFIX);
    return new ResponseEntity<>(kahului.getTotalBalance(), HttpStatus.OK);
  }

  @GetMapping(path = PATH_PROFIT)
  public ResponseEntity getTotalProfit() {
    logger.trace(PATH_PROFIT + RESPONSE_SUFFIX);
    return new ResponseEntity<>(kahului.getTotalProfit(), HttpStatus.OK);
  }

  @GetMapping(path = PATH_SHUTDOWN)
  public void seppuku() {
    logger.trace(PATH_SHUTDOWN + RESPONSE_SUFFIX);
    logger.info("Shutdown down now...");
    System.exit(-1);
  }

  @GetMapping(path = PATH_STATUS)
  public ResponseEntity getState() {
    logger.trace(PATH_STATUS + RESPONSE_SUFFIX);
    String response = "Have you ever seen anything so full of splendor?";
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping(path = PATH_OPEN_ORDERS)
  public ResponseEntity getOpenOrders() {
    logger.trace(PATH_OPEN_ORDERS + RESPONSE_SUFFIX);
    return new ResponseEntity<>(new Gson().toJson(kahului.getOpenOrders()), HttpStatus.OK);
  }
}
