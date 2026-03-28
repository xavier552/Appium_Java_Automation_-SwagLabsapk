# 🤖 Appium Java Automation — SwagLabs APK

> End-to-end mobile automation for the **Sauce Labs Sample Android App** using Appium 2, Java, and TestNG.

![Java](https://img.shields.io/badge/Java-11+-ED8B00?style=flat-square&logo=openjdk&logoColor=white)
![Appium](https://img.shields.io/badge/Appium-2.x-662D91?style=flat-square&logo=appium&logoColor=white)
![TestNG](https://img.shields.io/badge/TestNG-7.12-orange?style=flat-square)
![Platform](https://img.shields.io/badge/Platform-Android-3DDC84?style=flat-square&logo=android&logoColor=white)
![Build](https://img.shields.io/badge/Build-Maven-C71A36?style=flat-square&logo=apachemaven&logoColor=white)

---

## 📋 Overview

This project automates four core user journeys on the **Sauce Labs Mobile Sample App (v2.7.1)**:

| # | Test | Description |
|---|------|-------------|
| `test_01` | **Login** | Selects `standard_user` from AutoFill suggestion and taps LOGIN |
| `test_02` | **Add to Cart** | Adds two products to the cart sequentially |
| `test_03` | **Checkout** | Opens cart, fills in First Name / Last Name / ZIP, completes order |
| `test_04` | **Logout** | Returns home after order, opens hamburger menu, taps LOGOUT |

---

## 🗂️ Project Structure

```
Appium_Java_Automation_-SwagLabsapk/
├── apps/
│   └── Android.SauceLabs.Mobile.Sample.app.2.7.1.apk
├── src/
│   └── test/
│       └── java/
│           └── FirstTest.java          ← Main test class (all 4 tests)
├── pom.xml                             ← Maven dependencies
└── README.md
```

---

## 🛠️ Tech Stack

| Tool | Version | Purpose |
|------|---------|---------|
| Java JDK | 11+ | Programming language |
| Apache Maven | 3.8+ | Build & dependency management |
| Appium Server | 2.x | Mobile automation server |
| UiAutomator2 Driver | Latest | Android automation engine |
| TestNG | 7.12.0 | Test execution & reporting |
| Appium Java Client | 10.1.0 | Java bindings for Appium |
| Android Studio | Latest | Emulator & SDK management |
| Appium Inspector | Latest | Element identification |
| IntelliJ IDEA | Any | IDE |

---

## ⚙️ Prerequisites

Complete every step below before running the tests.

### 1 · Java JDK 11+

Download from [[oracle.com)](https://www.oracle.com/in/java/technologies/downloads/) and verify:

```bash
java -version
```

Set `JAVA_HOME`:

```bash
# Windows
set JAVA_HOME=C:\Program Files\Java\jdk-26

# macOS / Linux
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-26.jdk/Contents/Home
```

---

### 2 · Node.js 18+

Download from [nodejs.org](https://nodejs.org) and verify:

```bash
node -v
```

---

### 3 · Appium Server 2.x + UiAutomator2 Driver

```bash
npm install -g appium@next
appium driver install uiautomator2
appium driver list --installed    # confirm uiautomator2 appears
```

---

### 4 · Android Studio & Emulator

1. Download [Android Studio](https://developer.android.com/studio)
2. Open **SDK Manager** and install:
   - Android SDK Platform (API 28+)
   - Android SDK Build-Tools
   - Android Emulator
   - Android SDK Platform-Tools (`adb`)
3. Open **Device Manager → Create Device** → pick any Pixel device → select **API 34** system image

---

### 5 · Environment Variables

**Windows:**
```
ANDROID_HOME = C:\Users\<YourName>\AppData\Local\Android\Sdk
JAVA_HOME    = C:\Program Files\Java\jdk-11
PATH        += %ANDROID_HOME%\platform-tools
PATH        += %ANDROID_HOME%\emulator
PATH        += %ANDROID_HOME%\tools
```

**macOS / Linux** (add to `~/.zshrc` or `~/.bashrc`):
```bash
export ANDROID_HOME=$HOME/Library/Android/sdk
export PATH=$ANDROID_HOME/platform-tools:$ANDROID_HOME/emulator:$PATH
```

Verify `adb` is working:
```bash
adb version
```

---

### 6 · Place the APK

The APK must be in the `apps/` folder at the project root:

```
apps/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk
```

---

## 🚀 Running the Tests

### Step 1 — Start the Android Emulator

Open Android Studio → **Device Manager** → start your AVD.

Confirm the device is detected:
```bash
adb devices
# Expected output: emulator-5554   device
```

---

### Step 2 — Start the Appium Server

Open a terminal and run:
```bash
appium
```

You should see:
```
Appium REST http interface listener started on 0.0.0.0:4723
```

> **Keep this terminal open** for the entire duration of your test run.

Verify the server is live: open `http://127.0.0.1:4723/status` in a browser — you should see `"ready": true`.

---

### Step 3 — Run the Tests

Open **IntelliJ IDEA:**
- Right-click `FirstTest.java` → **Run**
- Or use the TestNG run configuration

---

## 🔍 Locator Strategies Used

| Strategy | Used For |
|----------|----------|
| `AppiumBy.xpath` | Login AutoFill credential suggestions |
| `AppiumBy.accessibilityId` | Form fields: `test-First Name`, `test-Last Name`, `test-Zip/Postal Code` |
| `AppiumBy.androidUIAutomator` (text + instance) | ADD TO CART buttons |
| `AppiumBy.androidUIAutomator` (UiScrollable) | Scrolling to CHECKOUT and FINISH buttons |
| `AppiumBy.androidUIAutomator` (className) | Cart icon and hamburger menu (`ImageView` by instance) |

---

## ⌨️ Keyboard Input Strategy

Three different input strategies are used depending on the field:

```java
// Standard fields (First Name, Last Name)
element.sendKeys("John");

// ZIP / Postal Code (W3C Actions API)
new Actions(driver).sendKeys(zipField, "10001").perform();

// Submit the ZIP field
driver.pressKey(new KeyEvent(AndroidKey.ENTER));
```

---

## ⏱️ Explicit Waits

All interactions use `WebDriverWait` with a **5-second timeout** to wait for element visibility before acting — preventing flakiness on slower emulators.

```java
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
```

---

## 🧪 Test Credentials

| Username | Password | Status |
|----------|----------|--------|
| `standard_user` | `secret_sauce` | ✅ Valid |
| `locked_out_user` | `secret_sauce` | 🔒 Locked |
| `problem_user` | `secret_sauce` | ⚠️ Buggy UI |

---

## 🐛 Troubleshooting

| Issue | Fix |
|-------|-----|
| `TimeoutException` on `ADD TO CART instance(1)` | Use `.text("ADD TO CART").instance(1)` instead of `.description()` |
| `NoSuchElementException` on form fields | Ensure emulator is fully booted; increase wait timeout to `10s` |
| Appium server not connecting | Check server is running on port `4723`; verify `ANDROID_HOME` is set |
| APK not found | Confirm APK is in the `apps/` folder at the project root |
| `adb devices` shows no device | Start emulator first; then run `adb kill-server` → `adb start-server` |
| Keyboard not appearing | Call `element.click()` before `sendKeys()` to focus the field first |
| `appium doctor` warnings | Run `appium doctor --android` and fix each flagged item |

---

## 📦 Maven Dependencies

```xml
<!-- Appium Java Client -->
<dependency>
    <groupId>io.appium</groupId>
    <artifactId>java-client</artifactId>
    <version>10.1.0</version>
    <scope>compile</scope>
</dependency>

<!-- TestNG -->
<dependency>
    <groupId>org.testng</groupId>
    <artifactId>testng</artifactId>
    <version>7.12.0</version>
</dependency>
```

---



---

*Built with Appium 2 + Java on Windows 11 · Android Emulator API 34 (sdk_gphone64_x86_64)*
