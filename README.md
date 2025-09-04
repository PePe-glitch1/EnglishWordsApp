# EnglishWordsApp

**EnglishWordsApp** is an Android application for learning English words through a simple quiz format.  
It uses a local Room database, shows progress statistics, and includes theme & language settings.

---

## ✨ Features

- **Training mode**: one English word + 4 translation options, instant feedback, automatic “learned” marking.
- **Progress statistics**: learned vs not-learned counters.
- **Settings**: switch theme (light/dark), switch interface language (default/uk).
- **Offline storage**: Room database (`mydb.db` asset) with words and learned state.
- **UI**: XML layouts with ViewBinding.

---

## 📱 Screens

- **Start Screen** (menu: Learn, Statistics, Settings)  
  _PLACE SCREENSHOT HERE_

- **Training Screen** (question + 4 answers)  
  _PLACE SCREENSHOT HERE_

- **Answer Feedback (Correct / Wrong)**  
  _PLACE SCREENSHOT HERE_

- **Statistics Screen** (learned vs not learned counts)  
  _PLACE SCREENSHOT HERE_

- **Settings Screen** (theme, language, notifications)  
  _PLACE SCREENSHOT HERE_

---

## 🧱 Tech Stack

- Kotlin
- Android SDK + AppCompat
- XML layouts + ViewBinding
- Room Database (Entity, DAO, Database)
- Kotlin Coroutines (Dispatchers.IO, lifecycleScope)
- SharedPreferences (Prefs wrapper)
- Application class for initialization (theme, prefs)

---

## 🗂 Project Structure

app/
├─ src/main/assets/
│ └─ mydb.db # initial Room database asset
├─ java/com/example/englishwordsappn/
│ ├─ data/ # Word.kt, WordDao.kt, Prefs.kt
│ ├─ domain/
│ │ ├─ model/ # Question.kt, WordDatabase.kt
│ │ └─ trainer/ # LearningWords.kt
│ ├─ feature/ # Activities: Start, Learn, Statistics, Settings
│ └─ App.kt # Application class
└─ res/
├─ layout/ # XML layouts (activity_*.xml)
├─ drawable/ # Shapes, icons
├─ values/ # colors.xml, strings.xml, themes.xml
└─ mipmap/ # app icons


---

## 🧠 How It Works

1. Words are stored in `word_table` with fields: `id`, `english`, `translation`, `learned`.
2. The trainer (`LearningWords`) selects 4 options, marks the correct one, and updates the database if answered correctly.
3. Statistics count learned and not learned words.
4. Preferences (theme, language) are stored in SharedPreferences and applied on startup.

---

## 🚀 Getting Started

### Requirements
- Android Studio (Giraffe / Jellyfish or newer)
- JDK 17
- Android SDK 33+ recommended
- Device or emulator (Android 8.0+)

### Clone & Open
```bash
git clone https://github.com/PePe-glitch1/EnglishWordsApp.git
```

## 📥 Download

- **[Download latest APK](https://github.com/PePe-glitch1/EnglishWordsApp/releases/latest)**


