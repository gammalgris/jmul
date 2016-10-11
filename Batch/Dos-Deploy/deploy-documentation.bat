@Echo Off


@rem ================================================================================
@rem ===
@rem ===   void main(String aPath, boolean aStartServerFlag, boolean aStartBrowserFlag)
@rem ===
@rem ===   This script deploys the documentation including a web server.
@rem ===
@rem ===
@rem ===   @param aPath
@rem ===          the target directory where to deploy the web content and the web
@rem ===          server. It's expected that the path ends with a backslash
@rem ===          character.
@rem ===   @param aStartServerFlag
@rem ===          indicates if the script shall start a web server after the
@rem ===          deployment is done. The parameter is optional. If not specified
@rem ===          then the parameter is set to the default value (i.e. FALSE).
@rem ===   @param aStartBrowserFlag
@rem ===          indicates if the script shall start a browser after the
@rem ===          deployment is done. The parameter is optional. If not specified
@rem ===          then the parameter is set to the default value (i.e. FALSE).
@rem ===

call:defineMacros
call:defineConstants

call:setUpEnvironment
%ifError% (

	pause
	%return%
)


set "target=%1"
if '%target%'=='' (

	echo ERROR ^(%0^): No target directory has been specified! >&2
	%return% 2
)
set "target=%target:"=%"
set "target=%target%\"
set "target=%target:\\=\%"

set "startServerFlag=%2"
if '%startServerFlag%'=='' (

	set startServerFlag=%FALSE%
)
set "startServerFlag=%startServerFlag:"=%"

call:checkBoolean %startServerFlag%
%ifError% (

	%return%
)


set "startBrowserFlag=%3"
if '%startBrowserFlag%'=='' (

	set startBrowserFlag=%FALSE%
)
set "startBrowserFlag=%startBrowserFlag:"=%"

call:checkBoolean %startBrowserFlag%
%ifError% (

	%return%
)


call:cleanTargetDirectory "%target%"
call:initTargetDirectory "%target%"

set "initializerPath=%~dp0"
cd /D "%initializerPath%"

call ant.bat -buildfile ..\Ant-Deploy\deploy-webserver.xml deploy
%ifError% (

	%return%
)
call:copyDirectory ..\..\tmp\web-server\ "%target%%WEB_SERVER_SUBFOLDER%"


call ant.bat -buildfile ..\Ant-Execute\run-instrumented-tests.xml coverage
%ifError% (

	%return%
)
call:copyDirectory ..\..\tmp\web-content\ "%target%%WEB_CONTENT_SUBFOLDER%"


if %startServerFlag%==%TRUE% (

	call:overrideWebServerConfiguration "%target%" 8000 0 10 "%RELATIVE_ROOT_DIRECTORY%%WEB_CONTENT_SUBFOLDER%" "html,htm,gif,js,css,png,svg,xsd"
	start %target%%WEB_SERVER_EXECUTABLE%
)


if %startBrowserFlag%==%TRUE% (

	start "" "%target%%WEB_SERVER_SUBFOLDER%web-content-link.html"
)


set target=
set initializerPath=
set startServerFlag=
set startBrowserFlag=

%return%


@rem ================================================================================
@rem ===
@rem ===   Internal Subroutines
@rem ===

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
@rem ---   void defineConstants()
@rem ---
@rem ---   The subroutine defines required constants.
@rem ---

:defineConstants

	set TRUE=TRUE
	set FALSE=FALSE

	set RELATIVE_ROOT_DIRECTORY=..\
	set WEB_CONTENT_SUBFOLDER=web-content\
	set WEB_SERVER_SUBFOLDER=web-server\

	set WEB_SERVER_EXECUTABLE=%WEB_SERVER_SUBFOLDER%startWebServer.bat
	set WEB_SERVER_CONFIGURATION=%WEB_SERVER_SUBFOLDER%CustomWebServer.properties

%return%


@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void setUpEnvironment()
@rem ---
@rem ---   The subroutine sets up the required environment.
@rem ---

:setUpEnvironment

	set "_batchDirectory=%~dp0"
	set "_currentDirectory=%CD%"

	set _setupScript=setEnv.bat
	set _checkScript=checkEnv.bat


	cd /D "%_batchDirectory%"

	call %_setupScript% 2>nul
	%ifError% (

		echo ERROR %ERRORLEVEL%: The environment couldn't be set up! >&2
		%return% 2
	)

	call %_checkScript% 2>nul
	%ifError% (

		echo ERROR %ERRORLEVEL%: The environment couldn't be set up! >&2
		%return% 2
	)

	cd /D "%_currentDirectory%"


	set _batchDirectory=
	set _currentDirectory=

	set _setupScript=
	set _checkScript=

%return%


@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void cleanTargetDirectory(String aPath)
@rem ---
@rem ---   Cleans the specified path and all its content.
@rem ---
@rem ---
@rem ---   @param aPath
@rem ---          a directory which is to be cleaned. It's expected that the path
@rem ---          ends with a backslash character.
@rem ---

:cleanTargetDirectory

	set "_path=%1"
	if '%_path%'=='' (

		echo ERROR ^(%0^): No path has been specified! >&2
		%return% 2
	)
	set "_path=%_path:"=%"
	set "_path=%_path%\"
	set "_path=%_path:\\=\%"


	if exist "%_path%" (

		rmdir /S /Q "%_path%"
	)
	mkdir %_path%
	echo cleaned %_path%

	set _path=

%return%


@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void initTargetDirectory(String aPath)
@rem ---
@rem ---   Initializes the specified target directory (i.e. specific subfolders are
@rem ---   created.
@rem ---
@rem ---
@rem ---   @param aPath
@rem ---          a directory which is to be initialized. It's expected that the path
@rem ---          ends with a backslash character.
@rem ---

:initTargetDirectory

	set "_path=%1"
	if '%_path%'=='' (

		echo ERROR ^(%0^): No path has been specified! >&2
		%return% 2
	)
	set "_path=%_path:"=%"
	set "_path=%_path%\"
	set "_path=%_path:\\=\%"


	mkdir %_path%%WEB_SERVER_SUBFOLDER%
	echo created %_path%%WEB_SERVER_SUBFOLDER%

	mkdir %_path%%WEB_CONTENT_SUBFOLDER%
	echo created %_path%%WEB_CONTENT_SUBFOLDER%

	set _path=

%return%

@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void copyDirectory(String aSource, String aTarget)
@rem ---
@rem ---   Initializes the specified target directory (i.e. specific subfolders are
@rem ---   created.
@rem ---
@rem ---
@rem ---   @param aPath
@rem ---          a directory which is to be initialized. It's expected that the path
@rem ---          ends with a backslash character.
@rem ---

:copyDirectory

	set "_source=%1"
	if '%_source%'=='' (

		echo ERROR ^(%0^): No source directory has been specified! >&2
		%return% 2
	)
	set "_source=%_source:"=%"
	set "_source=%_source%\"
	set "_source=%_source:\\=\%"

	set "_target=%2"
	if '%_target%'=='' (

		echo ERROR ^(%0^): No target directory has been specified! >&2
		%return% 2
	)
	set "_target=%_target:"=%"
	set "_target=%_target%\"
	set "_target=%_target:\\=\%"


	echo.
	echo copy %_source% --^> %_target%
	xcopy %_source%* %_target% /E /Y
	echo.

	set _source=
	set _target=

%return%


@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void overrideWebServerConfiguration(String aPath, int aPort, int aBacklog,
@rem ---                                       int aShutdownTime, String aBaseDirectory,
@rem ---                                       String someContentTypes)
@rem ---
@rem ---   Overrides a web server configuration file according to the specified
@rem ---   values.
@rem ---
@rem ---
@rem ---   @param aPath
@rem ---          a directory which is to be initialized. It's expected that the path
@rem ---          ends with a backslash character.
@rem ---   @param aPort
@rem ---          a directory which is to be initialized. It's expected that the path
@rem ---          ends with a backslash character.
@rem ---   @param aBacklog
@rem ---          the size of the request backlog
@rem ---   @param aShutdownTime
@rem ---          a shutdown time for the web server
@rem ---   @param aBaseDirectory
@rem ---          the directory of the web content. It's expected that the path
@rem ---          ends with a backslash character.
@rem ---   @param someContentTypes
@rem ---          the allowed content types (comma separated list of file suffixes)
@rem ---

:overrideWebServerConfiguration

	set "_path=%1"
	if '%_path%'=='' (

		echo ERROR ^(%0^): No path has been specified! >&2
		%return% 2
	)
	set "_path=%_path:"=%"
	set "_path=%_path%\"
	set "_path=%_path:\\=\%"

	set "_port=%2"
	if '%_port%'=='' (

		echo ERROR ^(%0^): No port has been specified! >&2
		%return% 2
	)
	set "_port=%_port:"=%"

	set "_backlog=%3"
	if '%_backlog%'=='' (

		echo ERROR ^(%0^): No backlog size has been specified! >&2
		%return% 2
	)
	set "_backlog=%_backlog:"=%"

	set "_shutdownTime=%4"
	if '%_shutdownTime%'=='' (

		echo ERROR ^(%0^): No shutdown time has been specified! >&2
		%return% 2
	)
	set "_shutdownTime=%_shutdownTime:"=%"

	set "_baseDirectory=%5"
	if '%_baseDirectory%'=='' (

		echo ERROR ^(%0^): No base directory has been specified! >&2
		%return% 2
	)
	set "_baseDirectory=%_baseDirectory:"=%"
	set "_baseDirectory=%_baseDirectory%\"
	set "_baseDirectory=%_baseDirectory:\\=\%"
	set "_baseDirectory=%_baseDirectory:\=/%"

	set "_contentTypes=%6"
	if '%_contentTypes%'=='' (

		echo ERROR ^(%0^): No content types have been specified! >&2
		%return% 2
	)
	set "_contentTypes=%_contentTypes:"=%"


	set "_configurationFile=%_path%%WEB_SERVER_CONFIGURATION%"

	(
		echo socket.port=%_port%
	)> %_configurationFile%
	(
		echo socket.backlog=%_backlog%
	)>> %_configurationFile%
	(
		echo socket.shutdown-time=%_shutdownTime%
	)>> %_configurationFile%
	(
		echo content.base-directory=%_baseDirectory%
	)>> %_configurationFile%
	(
		echo content.content-types=%_contentTypes%
	)>> %_configurationFile%

	echo modified %_configurationFile%


	set _path=
	set _port=
	set _backlog=
	set _shutdownTime=
	set _baseDirectory=
	set _contentTypes=

%return%

@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void checkBoolean(boolean aBooleanValue)
@rem ---
@rem ---   Checks if the specified value is a boolean value. If no boolean value was
@rem ---   specified then the subroutine exists with an error.
@rem ---
@rem ---
@rem ---   @param aBooleanValue
@rem ---          a boolean value
@rem ---

:checkBoolean

	set "_booleanValue=%1"
	if '%_booleanValue%'=='' (

		echo ERROR ^(%0^): No boolean value has been specified! >&2
		%return% 2
	)
	set "_booleanValue=%_booleanValue:"=%"


	if %_booleanValue%==%TRUE% (

		%return% 0
	)

	if %_booleanValue%==%FALSE% (

		%return% 0
	)


	echo ERROR ^(%0^): No boolean value has been specified '%_booleanValue%'! >&2
%return% 2 && set _booleanValue=
