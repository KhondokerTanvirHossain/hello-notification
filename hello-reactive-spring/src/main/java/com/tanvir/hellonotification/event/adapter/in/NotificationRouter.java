package com.tanvir.hellonotification.event.adapter.in;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class NotificationRouter {

    @Bean
    public RouterFunction<?> notificationRoutes(NotificationHandler handler) {
        return route(POST("/api/events/trigger"), handler::trigger)
            .andRoute(POST("/api/events/no-action"), handler::noAction);
    }
}
