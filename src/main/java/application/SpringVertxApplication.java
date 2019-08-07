package application;

import application.verticle.CarVerticle;
import io.vertx.core.Vertx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class SpringVertxApplication {

    @Autowired
    private CarVerticle carVerticle;

    public static void main(String[] args) {
        SpringApplication.run(SpringVertxApplication.class, args);
    }

    @PostConstruct
    public void deployVerticle() {
        Vertx.vertx().deployVerticle(carVerticle);
    }
}
