package com.tanvir.hellonotification.event.application.port.out;

import reactor.core.publisher.Mono;

public interface FirebaseNotificationGateway {

    Mono<Void> send(String title, String messageBody, String fcmToken);
}
