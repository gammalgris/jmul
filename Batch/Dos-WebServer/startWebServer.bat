@Echo Off


@rem ================================================================================
@rem ===
@rem ===   void main()
@rem ===
@rem ===   This script sets up the environment for SonarQube and starts SonarQube.
@rem ===

set "initializerPath=%~dp0"
set initializerRunner=setEnv.bat
set environmentCheck=checkEnv.bat


set "webServerLibrary=jmul-webserver-1.0.0.jar"
set "webServerLibraryPath=%initializerPath%%webServerLibrary%"
set "webServerRunner=jmul.web.WebServerRunner"
set "webServerParameters=jmul.web.WebServerImpl jmul.web.WebServer"


set "windowTitle=%webServerLibrary%"
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


call %environmentCheck% 2>nul
if %ERRORLEVEL%==0 (

	rem OK

) else (

	echo ERROR %ERRORLEVEL%: The environment couldn't be set up! >&2
	pause
	exit /b %ERRORLEVEL%
)


if exist %webServerLibraryPath% (

	rem OK

) else (

	echo ERROR %ERRORLEVEL%: The web server library doesn't exist ^(%webServerLibraryPath%^)! >&2
	pause
	exit /b %ERRORLEVEL%
)


echo.
echo.

echo java.exe -cp %webServerLibraryPath% %webServerRunner% %webServerParameters%
echo.

java.exe -cp %webServerLibraryPath% %webServerRunner% %webServerParameters%
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
set webServerLibrary=
set webServerLibraryPath=
set webServerRunner=
set webServerParameters=
set windowTitle=
