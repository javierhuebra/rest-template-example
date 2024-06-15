package com.example.rest_template.classes;

import com.example.rest_template.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificacionesScheduled {
    private final UserService userService;

    @Scheduled(cron = "*/20 * * * * *")
    public void enviarNotificacionesScheduled() {
        System.out.println("Pidiendo usuarios...");
        System.out.println(userService.getUsers());
    }
}
