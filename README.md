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
z<img width="1080" height="2400" alt="Screenshot_20250904-184459" src="https://github.com/user-attachments/assets/93947d7f-ce29-42ae-bf2a-fa0d26ca125b" />

- **Training Screen** (question + 4 answers)  
<img width="1080" height="2400" alt="Screenshot_20250904-184522" src="https://github.com/user-attachments/assets/24b7d8e0-fb16-48dd-98ea-8b39525742a2" />


- **Answer Feedback (Correct / Wrong)**  
<img width="1080" height="2400" alt="Screenshot_20250904-184511" src="https://github.com/user-attachments/assets/57dd8186-198e-4160-8382-2e3f4a78578b" />
<img width="1080" height="2400" alt="Screenshot_20250904-184518" src="https://github.com/user-attachments/assets/f93b8d10-c328-4d20-bd59-f511918fda66" />

- **Statistics Screen** (learned vs not learned counts)  
<img width="1080" height="2400" alt="Screenshot_20250904-184528" src="https://github.com/user-attachments/assets/b6826de9-78eb-4c19-8928-c40ce9ec27f7" />

- **Settings Screen** (theme, language, notifications)  
<img width="1080" height="2400" alt="Screenshot_20250904-184535" src="https://github.com/user-attachments/assets/84fbe006-dcb1-4bea-9dc4-5baa98c73d28" />

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


