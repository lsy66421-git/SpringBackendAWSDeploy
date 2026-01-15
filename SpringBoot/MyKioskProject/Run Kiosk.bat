@echo off
set "JRE_PATH=%~dp0jre"

echo [Checking Java Environment]
:: 1. Try Bundled JDK/JRE
if exist "%JRE_PATH%\bin\java.exe" (
    echo Found bundled Java at: %JRE_PATH%
    echo Starting Kiosk Application...
    echo ---------------------------------------------------
    "%JRE_PATH%\bin\java.exe" -jar kiosk.jar
    goto :FINISHED
)

:: 2. Try System Java
echo Bundled Java not found. Trying system Java...
java -version >nul 2>&1
if %errorlevel% equ 0 (
    echo Found system Java.
    echo Starting Kiosk Application...
    echo ---------------------------------------------------
    java -jar kiosk.jar
    goto :FINISHED
)

:ERROR
echo ========================================================
echo  [ERROR] Java Runtime Environment (JRE) not found!
echo  Please install Java or check the 'jre' folder.
echo ========================================================
goto :END

:FINISHED
echo ---------------------------------------------------
echo  Application stopped.
echo ========================================================

:END
pause
