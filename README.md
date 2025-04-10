
📊 Investment and Financial Planning Tracker (IFPT)

A JavaFX-based desktop application that provides multiple financial calculators 

***Alternative: Run in IntelliJ IDEA

- Open the project in IntelliJ IDEA
- Add JavaFX SDK to Project Structure > Libraries
- Set VM options in Run Configuration:
  --module-path "C:\javafx-sdk-21\lib" --add-modules javafx.controls,javafx.fxml***
~~~THIS IS PREFERED AS YOU'LL UNDERSTAND MORE EASILY~~~

Resources

All FXML, CSS, and images are placed in:
src/main/resources/


###Alternative: to Run This Project

Prerequisites
1. Java JDK 17 or 21+ installed  
2. JavaFX SDK installed from https://gluonhq.com/products/javafx/

 JavaFX is no longer included in JDK 11+, so you must install it manually.

Compile and Run (Command Line)

Folder Structure

IFPT/
│
|- src/
│   -- main/
│       ---java/
│            ----calc/
│                -----calcappfx/
│                    ------mainApp.java   ← Main file
│
|- src/
│   -- main/
│       ---resources/                 ← FXML, CSS, images
│
|- javafx-sdk-21/                     ← JavaFX SDK folder (you must set this up)
 Step-by-Step (Windows)

1. Open CMD and go to project root:
   cd "C:\path\to\your\project\IFPT\CalcAppFX"

2. Compile:
   javac --module-path "C:\javafx-sdk-21\lib" --add-modules javafx.controls,javafx.fxml -d out src\main\java\calc\calcappfx\mainApp.java

3. Run:
   java --module-path "C:\javafx-sdk-21\lib" --add-modules javafx.controls,javafx.fxml -cp out calc.calcappfx.mainApp
###
 





Created by 
-Mitansh Prajapati & Rudra bhatt  

