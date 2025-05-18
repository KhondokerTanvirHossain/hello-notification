# 📬 Hello Notification Spring Boot App

A minimal end-to-end push notification demo using:

- ✅ Spring Boot (Reactive WebFlux + Clean Architecture)
- ✅ Firebase Cloud Messaging (FCM)
- ✅ Flutter App (to receive push notifications)

---

## 🚀 Features

- Emits two types of events:
    - `/api/events/trigger` → Sends a push notification
    - `/api/events/no-action` → Logs event without notification

- Firebase integration with push notification support

---

## 🛠️ Firebase Setup

To get started:

### 1. Create Firebase Project

- Go to [Firebase Console](https://console.firebase.google.com/)
- Create a project (e.g., `hello-notification`)
- Enable **Firebase Cloud Messaging (FCM)** in the project settings

![firebase_Screenshot_২০২৫০৫০৪_১৯২৮৩৫.png](firebase_Screenshot_২০২৫০৫০৪_১৯২৮৩৫.png)

### 2. Set Up Firebase Admin SDK in Spring Boot

1. Go to **Firebase Console → Project Settings → Service Accounts**
2. Click **"Generate new private key"**
![firebaseScreenshot_২০২৫০৫০৪_১৯৫৯৩৯.png](firebaseScreenshot_২০২৫০৫০৪_১৯৫৯৩৯.png)
3. Save the downloaded file as `firebase-adminsdk.json`
4. Place the file in your `src/main/resources` folder (or somewhere appropriate)
5. Configure the path in `application.yml`:

```properties
firebase.config.path=classpath:firebase/firebase-adminsdk.json

```

### 3. Create a Firebase configuration bean:

```java
@Slf4j
@Configuration
public class FirebaseConfig {

    @Value("${firebase.config.path}")
    private Resource firebaseConfig;

    @PostConstruct
    public void initializeFirebase() {
        try {
            FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(firebaseConfig.getInputStream()))
                .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                log.info("✅ Firebase initialized successfully.");
            } else {
                log.info("⚠️ Firebase already initialized.");
            }
        } catch (IOException e) {
            log.error("❌ Failed to initialize Firebase", e);
            throw new RuntimeException(e);
        }
    }
}
```

### 5. 🔥 Triggering Notifications

#### 5.1 Trigger Event With Push Notification

```bash
curl -X POST http://localhost:8080/api/events/trigger
```

Expected: Sends a push notification to the device with the configured FCM token.

#### 5.2 Trigger Event With No Action

```bash
curl -X POST http://localhost:8080/api/events/no-action
```

Expected: Just logs a message; no notification is sent.

### 6. ⚠️ Troubleshooting

   1. If you see errors like INVALID_ARGUMENT or UNREGISTERED:

        * Make sure you're using a valid FCM registration token
        * Ensure that Firebase Cloud Messaging API (V1) is enabled
        * Check if the Flutter app is correctly registered and initialized with Firebase

# 📣 Phase 2: Broadcasting with FCM Topics

This phase upgrades your notification system to use **FCM Topics** for broadcasting messages to multiple devices at once, without managing individual tokens.

---

## 🆕 What's New in This Phase

- Devices subscribe to a shared **topic** (e.g., `all_users`)
- Backend sends notifications to the topic, reaching all subscribed devices
- No need to collect or manage device tokens for broadcast messages

## 🔄 Changes in Spring Boot Backend

1. **Send to Topic Instead of Token**

   In your notification sending service, update the message builder:

   ```java
   // ...existing code...
   Message message = Message.builder()
       .setTopic("all_users") // Send to topic
       .setNotification(
           Notification.builder()
               .setTitle(title)
               .setBody(messageBody)
               .build()
       )
       .build();
   // ...existing code...
   ```

   > You no longer need to pass or manage FCM tokens for broadcast notifications.

## 📝 Example: Sending a Broadcast Notification

Trigger a notification as before:

```bash
curl -X POST http://localhost:8080/api/events/trigger
```

All devices subscribed to `all_users` will receive the notification.


## 🛠️ Optional: Custom Topics

- You can use any topic name (e.g., `doctors`, `patients`, `alerts`)
- Devices can subscribe to multiple topics as needed
