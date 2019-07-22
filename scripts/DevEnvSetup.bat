::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
::													      ::
::	DevEnvSetup.bat (Windows Gradle Script For Celestia)  ::
::													      ::
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

@echo off

cd ..

call gradlew setupDecompWorkspace
call gradlew eclipse