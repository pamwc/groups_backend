package edu.groups.server.controller;

import edu.groups.server.entity.Notification;
import edu.groups.server.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Created by Dawid on 04.12.2017 at 19:54.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {
    private final NotificationService service;

    @GetMapping("/my")
    public Set<Notification> currentUserNotification() {
        return service.currentUserNotification();
    }
}
