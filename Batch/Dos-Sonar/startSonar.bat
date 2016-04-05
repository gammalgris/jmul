@Echo Off


@rem ================================================================================
@rem ===
@rem ===   void main()
@rem ===
@rem ===   This script sets up the environment for SonarQube and starts SonarQube.
@rem ===

set "initializerPath=%~dp0"
set initializerRunner=setenvJ7.bat

set sonarVersion=5.3
set "sonarPath=D:\Programme\sonarqube-5.3\bin\windows-x86-64"
set sonarRunner=StartSonar.bat

set "windowTitle=SonarQube %sonarVersion%"


title %windowTitle%


cd /D "%initializerPath%"

call %initializerRunner% 2>nul
if %ERRORLEVEL%==0 (

	rem OK

) else (

	echo ERROR %ERRORLEVEL%: The environment couldn't be set up! >&2
	pause
	exit /b %ERRORLEVEL%
)


cd /D "%sonarPath%" 2>nul
if %ERRORLEVEL%==0 (

	rem OK

) else (

	echo ERROR %ERRORLEVEL%: The path to sonar ^(%sonarPath%^) is invalid! >&2
	pause
	exit /b %ERRORLEVEL%
)


echo.
echo.

call %sonarRunner%
if %ERRORLEVEL%==0 (

	rem OK
	echo ^(%ERRORLEVEL%^)

) else (

	if %ERRORLEVEL%==130 (

		echo ^(%ERRORLEVEL%^) Shutdown by Ctrl+C.

	) else (

		echo ERROR %ERRORLEVEL%: An error occurred while invoking the sonar runner! >&2
		pause
		exit /b %ERRORLEVEL%
	)
)

pause


set initializerPath=
set initializerRunner=
set sonarVersion=
set sonarPath=
set sonarRunner=
set windowTitle=
