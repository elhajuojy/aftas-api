package ma.aftas.aflasclubapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("ma.yc.api")
public class AftasClubApiApplication {


    public static void main(String[] args) {
        SpringApplication.run(AftasClubApiApplication.class, args);

    }



}
