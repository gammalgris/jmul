@Echo Off


@rem ================================================================================
@rem ===
@rem ===   void main()
@rem ===
@rem ===   This script sets up the environment required by the web server and starts
@rem ===   the web server.
@rem ===

call:resetErrorlevel
call:defineMacros

set "initializerPath=%~dp0"
set "LOGFILE=%~n0.log"

call:loadProperties "%initializerPath%startWebServer.properties"
%ifError% (

	echo ERROR %ERRORLEVEL%: The properties couldn't be loaded! >&2
	pause
	%return% %ERRORLEVEL%
)


set "windowTitle=%webServerLibrary%"
title %windowTitle%


cd /D "%initializerPath%"

call "%initializerRunner%" 2>nul
if %ERRORLEVEL%==0 (

	rem OK

) else (

	echo ERROR %ERRORLEVEL%: The environment couldn't be set up! >&2
	pause
	%return% %ERRORLEVEL%
)


call "%environmentCheck%" 2>nul
if %ERRORLEVEL%==0 (

	rem OK

) else (

	echo ERROR %ERRORLEVEL%: The environment couldn't be set up! >&2
	pause
	exit /b %ERRORLEVEL%
)


if exist "%webServerLibraryPath%" (

	rem OK

) else (

	echo ERROR %ERRORLEVEL%: The web server library doesn't exist ^(%webServerLibraryPath%^)! >&2
	pause
	exit /b %ERRORLEVEL%
)


echo.
echo.

echo java.exe -cp "%webServerClasspath%" %webServerRunner% %webServerParameters%
echo.


(
	start /B java -cp "%webServerClasspath%" %webServerRunner% %webServerParameters%
) >> "%LOGFILE%"
if %ERRORLEVEL%==0 (

	rem OK
	echo ^(%ERRORLEVEL%^)

) else (

	if %ERRORLEVEL%==130 (

		echo ^(%ERRORLEVEL%^) Shutdown by Ctrl+C.
		pause
		%return% 0

	) else (

		echo ERROR %ERRORLEVEL%: An error occurred while invoking the web server! >&2
		pause
		%return% %ERRORLEVEL%
	)
)

pause


set initializerPath=
set initializerRunner=
set webServerLibrary=
set webServerLibraryPath=
set webServerClasspath=
set webServerRunner=
set webServerParameters=
set windowTitle=

%return%


@rem ================================================================================
@rem ===
@rem ===   Internal Subroutines
@rem ===

@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void resetErrorlevel()
@rem ---
@rem ---   The subroutine resets the errorlevel.
@rem ---

:resetErrorlevel

exit /b 0


@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void defineMacros()
@rem ---
@rem ---   The subroutine defines required macros.
@rem ---

:defineMacros

	set "ifError=set foundErr=1&(if errorlevel 0 if not errorlevel 1 set foundErr=)&if defined foundErr"
	
	set "cprintln=echo"
	set "cprint=echo|set /p="
	
	set "return=exit /b"

%return%


@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void loadProperties(String filename)
@rem ---
@rem ---   The subroutine loads properties from the specified file (i.e. the file
@rem ---   is processed and for each entry a environment variable is set).
@rem ---
@rem ---
@rem ---   @param filename
@rem ---          the relative of absolute path of a property file
@rem ---

:loadProperties

	set "_fileName=%1"
	if '%_fileName%'=='' (

		echo ERROR ^(%0^): No file name has been specified! >&2
		%return% 2
	)
	set "_fileName=%_fileName:"=%"


	if not exist "%_fileName%" (

		echo ERROR ^(%0^): The specified file '%_fileName%' doesn't exist! >&2
		%return% 3
	)

	for /F "tokens=*" %%a in ('type "%_fileName%"') do (

		call:setProperty "%%a"
		%ifError% (

			%return%
		)
	)


	set _fileName=

%return%


@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void setProperty(String declaration)
@rem ---
@rem ---   The subroutine sets an environment variable according to the specified
@rem ---   declaration. Referenced variables will be resolved.
@rem ---
@rem ---
@rem ---   @param declaration
@rem ---          the declaration of a property (e.g. propertyName=someValue)
@rem ---

:setProperty

	set "_declaration=%1"
	if '%_declaration%'=='' (

		echo ERROR ^(%0^): No declaration has been specified! >&2
		%return% 2
	)
	set "_declaration=%_declaration:"=%"
	if "%_declaration%"=="" (

		echo ERROR ^(%0^): No declaration has been specified! >&2
		%return% 2
	)


	set "%_declaration%"


	set _declaration=

%return%
