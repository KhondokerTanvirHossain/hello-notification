package com.tanvir.hellonotification.event.adapter.in;

import com.tanvir.hellonotification.event.application.port.in.SendNotificationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class NotificationHandler {

    private final SendNotificationUseCase sendNotificationUseCase;

    @Value("${app.test-fcm-token}")
    private String testFcmToken;

    public Mono<ServerResponse> trigger(ServerRequest request) {
        return sendNotificationUseCase
            .execute("PfH Alert", "✅ This is a triggered notification!", testFcmToken)
            .then(ServerResponse.ok().bodyValue("✅ Triggered notification sent."));
    }

    public Mono<ServerResponse> noAction(ServerRequest request) {
        return ServerResponse.ok().bodyValue("ℹ️ This event does not send a notification.");
    }
}
