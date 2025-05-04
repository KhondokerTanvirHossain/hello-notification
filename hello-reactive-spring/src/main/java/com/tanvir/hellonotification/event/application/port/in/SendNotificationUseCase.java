package com.tanvir.hellonotification.event.application.port.in;

import reactor.core.publisher.Mono;

public interface SendNotificationUseCase {

    Mono<Void> execute(String title, String message, String fcmToken);
}
