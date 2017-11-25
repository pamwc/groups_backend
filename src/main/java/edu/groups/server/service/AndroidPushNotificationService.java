package edu.groups.server.service;

import com.google.gson.Gson;
import edu.groups.server.dto.Notification;
import edu.groups.server.dto.NotificationWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Dawid on 01.11.2017 at 00:51.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AndroidPushNotificationService {
    @Value("${firebase.server.key}")
    private String FIREBASE_SERVER_KEY;
    @Value("${firebase.notification.api.url}")
    private String FIREBASE_API_URL;

    private final Gson gson;

    @Async
    public void send(Long subscribeEntityId, Notification notification) {
        String topic = subscribeEntityId.toString();
        HttpEntity<String> httpNotification = prepareRequestBody(wrapNotification(topic, notification));

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(FIREBASE_API_URL, httpNotification, String.class);
        log.info("Notification response: {}", response);
    }

    private NotificationWrapper wrapNotification(String topic, Notification notification) {
        return new NotificationWrapper("/topics/" + topic, notification);
    }

    private HttpEntity<String> prepareRequestBody(NotificationWrapper notification) {
        HttpHeaders httpHeaders = createHeader();
        return new HttpEntity<>(gson.toJson(notification), httpHeaders);
    }

    private HttpHeaders createHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "key=" + FIREBASE_SERVER_KEY);
        httpHeaders.set("Content-Type", "application/json");
        return httpHeaders;
    }
}
