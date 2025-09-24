@echo off
set SRC_DIR=src
set BIN_DIR=bin
set JAR_NAME=framework-s5.jar

if not exist %BIN_DIR% mkdir %BIN_DIR%

echo Compilation des sources...
javac -d %BIN_DIR% %SRC_DIR%\com\example\framework\*.java

if errorlevel 1 (
    echo Erreur de compilation !
    exit /b 1
)

echo Creation du fichier %JAR_NAME%...
cd %BIN_DIR%
jar cf %JAR_NAME% com/example/framework/*.class
move %JAR_NAME% ..\

echo Build termine ! Le fichier %JAR_NAME% est pret.
pause
