package org.example.wtfgameserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication(scanBasePackages = {"cc.cafebabe.testserver", "org.example.wtfgameserver"})
@EnableWebSocket
public class WtfGameServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WtfGameServerApplication.class, args);
    }

}
