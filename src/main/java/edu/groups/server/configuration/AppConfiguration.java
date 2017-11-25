package edu.groups.server.configuration;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Created by Dawid on 01.11.2017 at 14:19.
 */
@Configuration
@EnableAsync
public class AppConfiguration {
    @Bean
    Gson gson() {
        return new Gson();
    }
}
