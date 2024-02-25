# 🤰 To be Mom
![image](https://github.com/GDSC-23-24-BABY-APP/tobemom-spring-mvc/assets/80439068/63c22d41-a927-41e5-87bc-07681ce62e5e)

## 🎯 Target UN-SDGs
![Group 15 (1)](https://github.com/GDSC-23-24-BABY-APP/front-end/assets/80439068/9eb012ad-45e0-4f62-896f-4b0d16815c3c)


## 🙌 Introduction
Until the 11th week of pregnancy, there is a higher risk of miscarriage, and an increased risk of stillbirth in the later stages. Moreover, 84% of stillbirths occur in low to middle-income countries.

But actually miscarriage is not solely a challenge of low-income countries. In 2019, in 39 high-income countries, there were more stillbirths than neonatal deaths, and in 15 countries, there were more stillbirths than infant deaths. In some regions, girls are denied access to healthcare or adequate nutrition, contributing to a higher mortality rate. 

To address such issues, we have developed a service specifically tailored for pregnant woman to manage their health effectively.

The To be Mom app tries to enable individuals to be aware of miscarriage and stillbirth risks themselves and monitor their condition attentively.

✅ This app provides a feature where it calculates the results of health checklists submitted by expectant mothers and allows them to <u> view health analysis reports based on pregnancy weeks.</u> Users can record their daily health-related information in the health record menu. In addition, ✅ it offers essential health-related information that expectant mothers must know and ✅ enables users to find nearby obstetricians based on their current location. ✅ And through the chatbot feature utilizing Google's Gemini API, users can inquire about pregnancy-related information and receive appropriate responses.

☑ Moreover, to assist users who may have limitations in reading or writing, we have incorporated features to convert speech to text and text to speech. (We used google's text-to-speech API and speech-to-text API)

<br>

## 📍How To Start
### 1) Prerequisites
   To run any Android application built with Kotlin, you need to configure the environment on your machine. You can follow the tutorial provided by Google on the Kotlin website.
   
Requirements:
	•	Kotlin SDK
	•	Android Studio (to download Android SDK)
	•	Xcode (for iOS development only)
	•	Any IDE with Kotlin SDK installed (e.g., IntelliJ, Android Studio, VSCode, etc.)
	•	A little knowledge of Kotlin and Android development

### 2) Clone
Clone this repo to your local machine using:

    git clone https://github.com/GDSC-23-24-BABY-APP/front-end.git

### 3) Setup
Set your own Google Map Key in front-end/app/java/HospitalMap.kt and res/AndroidManifest.xml :

    Places.initialize(requireContext(), "YOUR_GOOGLE_MAP_KEY")
    
    android:value="YOUR_GOOGLE_MAP_KEY"

Set your own Gemini Key in front-end/app/java/ChatFragment.kt : 

    apiKey = "YOUR_GEMINI_KEY"

To run the app you need to have an online emulator or a plugged device and run the following command in the root of the application.

## 🔎 App Demo

### ✅ Solution1 - Daily Health Checklist & Health Record
![Checklist-ezgif com-video-to-gif-converter](https://github.com/GDSC-23-24-BABY-APP/front-end/assets/80439068/8d28298e-ff63-4d85-8a31-86c7bcc3c2cc)

#### Check Pregnant women health
Users can prevent miscarriage and stillbirth by going through a daily checklist.
The questions on the checklist will be change according to the week of pregnancy. By conducting self-diagnosis and checking their physical condition immediately before childbirth, women can manage their health effectively. 

**Step 1**

Do the checklist every day.

**Step 2**

Get a information about risk conditions of miscarriage and stillbirth, and overall health status.

**Step 3**

Record daily weight, health status, and emotional diary.

**Step 4**

Review past records on the calendar. 

### ✅ Solution2 - Dr.Gemini
![Chat-ezgif com-video-to-gif-converter](https://github.com/GDSC-23-24-BABY-APP/front-end/assets/80439068/0dd72650-fb91-49cf-9868-77c0ffb4cb4e)

### ✅ Solution3 - Pregnancy Care Tips
![KakaoTalk_20240222_211559577-ezgif com-video-to-gif-converter](https://github.com/GDSC-23-24-BABY-APP/front-end/assets/80439068/61ba1b6a-dfdf-4c46-a738-80fb15ef62c4)


#### Get information about overall pregnancy

We provide pregnancy-related information, including weekly baby development, pregnancy tips, and FAQ.
For countries with high illiteracy rates, we provide a text-to-speech feature. By clicking on the volume icon on each page, you can utilize the functionality to have the information read aloud.For countries with high illiteracy rates, we provide a text-to-speech feature. By clicking on the volume icon on each page,users can utilize the functionality to have the information read aloud.

### ✅ Solution4 - Find nearby Obstetricians

If an expectant mother is in an urgent situation or wants to find nearby obstetrics and gynecology clinics, she can navigate to the 'Nearby Hospital' page. Through Google Maps, she can locate nearby obstetrics and gynecology clinics from her current location.

![KakaoTalk_20240222_212132252-ezgif com-video-to-gif-converter](https://github.com/GDSC-23-24-BABY-APP/front-end/assets/80439068/f16819b0-0d2b-4af5-b018-dcb6b322ee3d)


<br>




## 🏛 Project Architecture

![Group 14 (2)](https://github.com/GDSC-23-24-BABY-APP/front-end/assets/80439068/f5888e6e-528d-4fbe-8755-b59c89e280c0)
<br>


## 🔧 Tech Stacks

![Group 13](https://github.com/GDSC-23-24-BABY-APP/front-end/assets/80439068/0133a1e3-eca0-4aab-988d-456677b39518)
<br>


## ERD

![image](https://github.com/GDSC-23-24-BABY-APP/tobemom-spring-mvc/assets/80439068/0ca1e8e0-b0c9-4eea-a333-37483be35a57)
<br>


## Member

| **Yujin Lee** | **Minjoo Kim** | **Jiyoon Oh** | **Jihyeon Hong** |
|:-------------:|:---------------:|:-------------:|:--------------:|
| - Lead  <br>- Backend  | - Backend | - Frontend | - Frontend |


<br>
<br>
