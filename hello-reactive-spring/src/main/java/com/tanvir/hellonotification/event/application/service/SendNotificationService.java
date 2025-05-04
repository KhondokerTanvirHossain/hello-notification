package com.tanvir.hellonotification.event.application.service;

import com.tanvir.hellonotification.event.application.port.in.SendNotificationUseCase;
import com.tanvir.hellonotification.event.application.port.out.FirebaseNotificationGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SendNotificationService implements SendNotificationUseCase {

    private final FirebaseNotificationGateway gateway;

    @Override
    public Mono<Void> execute(String title, String message, String fcmToken) {
        return gateway.send(title, message, fcmToken);
    }

}
