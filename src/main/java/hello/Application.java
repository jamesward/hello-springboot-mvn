package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableScheduling
@RestController
public class Application {

  Logger logger = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @RequestMapping("/")
  public String index() {
    String revision = System.getenv("K_REVISION");
    if (revision == null) {
      revision = "(unknown version)";
    }

    return String.format("hello, world - %s", revision);
  }

  @Scheduled(fixedRate = 5000)
  public void reportCurrentTime() {
    logger.info("The time is now " + System.currentTimeMillis());
  }

}

