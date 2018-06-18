package ro.kiuny.practic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import ro.kiuny.practic.config.AppConfig;

@SpringBootApplication(scanBasePackages = "ro.kiuny.practic")
@Import(AppConfig.class)
public class PracticApplication {

    public static void main(String[] args) {
        SpringApplication.run(PracticApplication.class, args);
    }
}
