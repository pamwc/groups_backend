package edu.groups.server.configuration;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;

/**
 * Created by Dawid on 01.11.2017 at 14:19.
 */
@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    Gson gson() {
        return new Gson();
    }
}
