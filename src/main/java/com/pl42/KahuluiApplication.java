package com.pl42;

import com.pl42.kahului.mind.Kahului;
import com.pl42.kahului.utils.CalcUtils;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class KahuluiApplication {
  private static final Logger logger = Logger.getLogger(KahuluiApplication.class);
  private static final String VERSION = "6.5.0";

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(KahuluiApplication.class, args);
    logger.info("Starting KAHULUI (v" + VERSION + ") ...");
    if (args.length < 6) {
      logger.error("Not enough arguments have been given");
      System.exit(-1);
    }
    Kahului dolores = context.getBean(Kahului.class);
    dolores.setBinanceCreds(args[0], args[1]);
    dolores.setTwitterCreds(args[2], args[3], args[4], args[5]);
    for (; ; ) {
      dolores.gatherMindData();
      dolores.predictAndTrade();
      dolores.printBalances();
      dolores.reset();
      new CalcUtils().sleeper(25000);
    }
  }

  public static String getVersion() {
    return VERSION;
  }
}
