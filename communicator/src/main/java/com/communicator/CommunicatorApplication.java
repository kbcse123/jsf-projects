package com.communicator;

import com.communicator.util.AppUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.event.EventListener;


@SpringBootApplication
public class CommunicatorApplication extends SpringBootServletInitializer {
    static long startMemory;
    public static void main(String[] args) {
        startMemory=AppUtil.startMemory();
        SpringApplication.run(CommunicatorApplication.class, args);
    }

    /*@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CommunicatorApplication.class, Initializer.class);
    }
*/

    @EventListener(ApplicationReadyEvent.class)
    public void applicationReadyEvent() {
        System.out.println("Application is Ready. Memory utilized so far:"+ AppUtil.memoryConsumed(startMemory));
    }
}
