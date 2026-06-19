# 🔔 Notification Tracker

A lightweight Android application that listens to incoming notifications, extracts useful metadata, and displays them in a clean real-time notification feed.

Built as a learning project to explore Android background services, notification interception, reactive UI updates, and modern Android architecture.

---

## ✨ Features

* Listen to notifications using `NotificationListenerService`
* Extract notification metadata:

  * App Package Name
  * Title
  * Message Content
  * Timestamp
* Convert notification data into structured objects
* Real-time notification feed using `RecyclerView`
* Automatic UI updates using `StateFlow`
* Material Design inspired dark UI
* Custom bell launcher icon
* Works on a physical Android device without requiring an emulator

---

## 🛠️ Tech Stack

* **Kotlin**
* **Android SDK**
* **Android Studio**
* **NotificationListenerService**
* **Kotlin Coroutines**
* **StateFlow**
* **RecyclerView**
* **Material Components**
* **Gson**

---

## 🏗️ Architecture

```text
NotificationListenerService
            ↓
NotificationData Model
            ↓
NotificationStore (StateFlow)
            ↓
MainActivity
            ↓
RecyclerView Feed
```

---

## 🎯 Design Patterns & Concepts Used

### Singleton Pattern

Used for maintaining a centralized notification state.

```kotlin
object NotificationStore
```

### Observer Pattern

Implemented using `StateFlow` to automatically update the UI whenever new notifications arrive.

### Adapter Pattern

Used by `RecyclerView.Adapter` to bridge notification data with UI components.

### ViewHolder Pattern

Used inside RecyclerView for efficient view recycling and rendering.

### Reactive UI Updates

Implemented using Kotlin Coroutines and StateFlow.

### Event-Driven Architecture

Notifications are processed through Android's `NotificationListenerService` callbacks.

---

## 📂 Project Structure

```text
app/
├── MainActivity.kt
├── MyNotificationListenerService.kt
├── NotificationData.kt
├── NotificationStore.kt
├── NotificationAdapter.kt
│
├── res/
│   ├── layout/
│   │   ├── activity_main.xml
│   │   └── item_notification.xml
│   │
│   ├── drawable/
│   ├── mipmap/
│   └── values/
│
└── AndroidManifest.xml
```

---

## 🚀 Getting Started

### Prerequisites

* Android Studio
* Android SDK
* USB Debugging enabled on Android device

### Installation

1. Clone the repository

```bash
git clone https://github.com/your-username/notification-feed.git
```

2. Open the project in Android Studio

3. Connect your Android device via USB

4. Build and install the application

5. Grant **Notification Access** when prompted

6. Send test notifications and watch them appear in the feed

---

## 🔮 Future Improvements

* Store notifications using Room Database
* Export notifications as JSON
* Filter notifications by application
* Search notification history
* Notification analytics dashboard
* Notification categorization

---

## 📄 License

This project was built for educational and learning purposes.

```

