package com.tanvir.hellonotification.event.adapter.out;

import com.tanvir.hellonotification.event.application.port.out.FirebaseNotificationGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

@Component
@RequiredArgsConstructor
public class FirebaseNotificationGatewayImp implements FirebaseNotificationGateway {
    @Override
    public Mono<Void> send(String title, String messageBody, String fcmToken) {
        Message message = Message.builder()
            .setToken(fcmToken)
            .setNotification(
                Notification.builder()
                    .setTitle(title)
                    .setBody(messageBody)
                    .build()
            )

            .build();

        return Mono.fromCallable(() -> {
            FirebaseMessaging.getInstance().send(message);
            return true;
        }).then();
    }
}
