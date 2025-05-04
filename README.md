# hello-notification

A simple **Hello World Notification** project that demonstrates sending push notifications from a **Spring Boot backend** to a **Flutter mobile app** using **Firebase Cloud Messaging (FCM)**.

## 📌 Project Overview

This project is a basic starting point to show how a backend system can emit two types of events:

- One that **triggers a push notification**
- One that **does not trigger** any notification

Useful for understanding real-time user notification workflows in mobile applications, especially in healthcare and HIPAA-compliant environments.

---

## ⚙️ Technologies Used

### 🔧 Backend (Spring Boot)

- Java 17+
- Spring Boot (Web, DevTools)
- Firebase Admin SDK (Java)
- REST API for event triggering

### 📱 Frontend (Flutter)

- Flutter SDK 3.x
- `firebase_messaging` package
- Receives and displays push notifications

### ☁️ Cloud

- Firebase Cloud Messaging (FCM)
- Firebase Console for device registration & credentials

---

## 🚀 Getting Started

### 1. Clone the Repo

```bash
git clone https://github.com/your-org/hello-notification.git
cd hello-notification
```

## 🖥️ Spring Boot Backend Setup

### Prerequisites

1. Java 17+

2. Gradle

3. Firebase Project (download firebase-adminsdk.json)

### Run Locally

Add your Firebase service account JSON to:

```bash
    src/main/resources/firebase/firebase-adminsdk.json
```

Replace your test FCM token in EventController.java.

### Start the app

./gradlew spring-boot:run

### Trigger notification

```bash
curl -X POST http://localhost:8080/api/events/trigger
```

## 📱 Flutter App Setup

### Prerequisites

1. Flutter SDK installed

2. Firebase project linked to your app (via google-services.json)

3. Real Android/iOS device (emulator may not support FCM)

### Run

1. Install dependencies:

```bash
flutter pub get
```

2. Run the app:

```bash
flutter run
```

3. Monitor console logs to see incoming push notifications.

## 🧪 Demo Endpoints

| Endpoint                     | Description                                |
| ---------------------------- | ------------------------------------------ |
| `POST /api/events/trigger`   | Emits event that sends push notification   |
| `POST /api/events/no-action` | Emits event that does **not** trigger push |

## 📌 Roadmap

[] Push notification from Spring Boot to Flutter

[] Add user-configurable notification hours/days

[] Add Kafka for event-driven architecture

[] Add FF4J for feature toggling
