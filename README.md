
# 💸 CashFlowQuest

**CashFlowQuest** is a local-only Android budgeting application built with Kotlin, Room, and Jetpack components. It helps users track income, expenses, and budget goals with a focus on privacy, simplicity, and offline-first design.

---

## 📱 Features

### ✅ User Authentication
- Register/Login with secure password hashing using **BCrypt**
- Room-based user storage (no internet access required)

### 🧾 Expense Tracking
- Add expenses with optional receipt photo
- Assign to custom categories
- View a list of all recorded expenses

### 🗂️ Category Management
- Create, edit, delete categories (e.g., "School", "Pet", "Transport")
- Link expenses to categories
- (Future: Add icons/colors for each category)

### 🎯 Budget Setting
- Set total monthly budget
- (Future: Set per-category budgets)
- Logic in place to calculate over-spending

### 🏠 Dashboard/Home
- See total spent this month
- Remaining budget
- Recent expenses
- (Future: Pie/bar charts for per-category breakdown)

### ⚙️ Settings/Profile
- View basic profile info
- (Future features: theme toggle, data export/import, reset app)

---

## 📂 Link to youtube https://youtu.be/7jSl_yrhnLE

## 🛠️ Tech Stack

- **Kotlin**
- **Room Database**
- **MVVM Architecture**
- **LiveData / ViewModel**
- **Jetpack Navigation**
- **Material 3 UI**
- **XML Layouts**
- **BCrypt for password security**

---


## 🔐 Privacy First

CashFlowQuest stores all data locally on the device. No external APIs or servers are used. Your budgeting info never leaves your phone.

---

## 🚧 Roadmap / Future Enhancements

- 📊 Visual Charts (Bar/Pie per category)
- 🌓 Dark Mode Toggle
- 📤 Export/Import Data (CSV or JSON)
- 🧼 Reset All App Data
- 🛑 Monthly Spend Warnings
- ☁️ Optional Cloud Sync (for future)

---

## 🚀 Getting Started

1. Clone or download the project
2. Open in **Android Studio Giraffe or later**
3. Make sure your Gradle version is 8.7 and AGP version is 8.2.2
4. Build and run on an Android device or emulator

