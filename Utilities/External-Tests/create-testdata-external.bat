@Echo Off


@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void main()
@rem ---
@rem ---   Creates the test data as required by the tests.
@rem ---

:main

call:defineMacros
call:defineConstants


set functionCalls.length=5

set functionCalls[1]=createRootDirectory
set functionCalls[2]=createBatchScripts
set functionCalls[3]=createBashScripts
set functionCalls[4]=createPowershellScripts
set functionCalls[5]=createAntScripts


for /L %%i in (1, 1, %functionCalls.length%) do (

	setlocal EnableDelayedExpansion

		call:!functionCalls[%%i]!
		%ifError% (

			%cprintln% error code ^(!ERRORLEVEL!^)
			%return%
		)

	endlocal
)


for /L %%i in (1, 1, %functionCalls.length%) do (

	set functionCalls[%%i]=
)

set functionCalls.length=


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

%return% 0


@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void defineConstants()
@rem ---
@rem ---   The subroutine defines required constants.
@rem ---

:defineConstants

	set TRUE=1
	set FALSE=0

%return% 0


@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void createDirectory(String _path, boolean _clean)
@rem ---
@rem ---   The subroutine creates a directory according to the specified path.
@rem ---
@rem ---
@rem ---   @param _path
@rem ---          a directory
@rem ---   @param _clean
@rem ---          a flag which indicates that the directory's content should be
@rem ---          deleted
@rem ---

:createDirectory

	set "_path=%1"
	if '%_path%'=='' (

		echo No directory has been specified! >&2
		%return% 2
	)
	set "_path=%_path:"=%"

	set "_clean=%2"
	if '%_clean%'=='' (

		echo No flag has been specified! >&2
		%return% 2
	)
	set "_clean=%_clean:"=%"


	if exist "%_path%" (

		if %_clean%==%FALSE% (

			%return%
		)
	)


	if exist "%_path%" (

		rmdir /S /Q "%_path%" >nul 2>&1
	)

	if exist "%_path%" (

		%cprintln% Error: Unable to delete the directory "%_path%"! >&2
		%return% 2
	)

	mkdir "%_path%" >nul 2>&1

	if not exist "%_path%" (

		%cprintln% Error: Unable to create the directory "%_path%"! >&2
		%return% 2
	)


	set _path=
	set _clean=

%return%


@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void createRootDirectory()
@rem ---
@rem ---   The subroutine creates the root directory.
@rem ---

:createRootDirectory

	echo create root directory...

	call:createDirectory testdata-external %TRUE%

%return%


@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void createBatchScripts()
@rem ---
@rem ---   The subroutine creates batch scripts.
@rem ---

:createBatchScripts

	echo create batch scripts...

	call:createDirectory testdata-external\batch %TRUE%

	(
		echo @Echo Off
		echo echo Hello World^^!
	) > testdata-external\batch\example01.bat

	(
		echo @Echo Off
		echo echo An error occurred^^!^>^&2
		echo exit /b 1
	) > testdata-external\batch\example02.bat

%return%


@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void createBashScripts()
@rem ---
@rem ---   The subroutine creates bash scripts.
@rem ---

:createBashScripts

	echo create bash scripts...

	call:createDirectory testdata-external\bash %TRUE%

%return%


@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void createPowershellScripts()
@rem ---
@rem ---   The subroutine creates powershell scripts.
@rem ---

:createPowershellScripts

	echo create powershell scripts...

	call:createDirectory testdata-external\powershell %TRUE%

	(
		echo @rem
		echo @rem The MIT License ^(MIT^)
		echo @rem
		echo @rem Copyright ^(c^) 2018 Kristian Kutin
		echo @rem
		echo @rem Permission is hereby granted, free of charge, to any person obtaining a copy
		echo @rem of this software and associated documentation files ^(the "Software"^), to deal
		echo @rem in the Software without restriction, including without limitation the rights
		echo @rem to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
		echo @rem copies of the Software, and to permit persons to whom the Software is
		echo @rem furnished to do so, subject to the following conditions:
		echo @rem
		echo @rem The above copyright notice and this permission notice shall be included in all
		echo @rem copies or substantial portions of the Software.
		echo @rem
		echo @rem THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
		echo @rem IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
		echo @rem FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
		echo @rem AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
		echo @rem LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
		echo @rem OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
		echo @rem SOFTWARE.
		echo @rem
		echo.
		echo @Echo Off
		echo.
		echo.
		echo @rem ================================================================================
		echo @rem ===
		echo @rem ===   void main^(String aScriptFile, String... someParameters^)
		echo @rem ===
		echo @rem ===   This batch script invokes a powershell script and passes all command line
		echo @rem ===   parameters to the invoked powershell script.
		echo @rem ===
		echo @rem ===
		echo @rem ===   @param aScriptFile
		echo @rem ===          the file name of a powershell script without path
		echo @rem ===   @param someParameters
		echo @rem ===          all remaining command line parameters
		echo @rem ===
		echo.
		echo call:defineMacros
		echo call:defineConstants
		echo.
		echo.
		echo set "prefix=%%0"
		echo.
		echo set "scriptFile=%%1"
		echo if '%%scriptFile%%'=='' ^(
		echo.
		echo 	call:logError %%prefix%%: No script file was specified^^!
		echo 	call:logError The runner stopped due to an error.
		echo 	%%return%% 2
		echo ^)
		echo set "scriptFile=%%scriptFile:"=%%"
		echo set "scriptFile=%%~dp0%%scriptFile%%"
		echo.
		echo %%cprintln%% running %%scriptFile%%...
		echo.
		echo.
		echo set parameters=
		echo.
		echo :while_processParameters
		echo.
		echo 	shift
		echo.
		echo 	if '%%1'=='' ^(
		echo.
		echo 		goto elihw_processParameters
		echo 	^)
		echo.
		echo 	call:addParameter parameters %%1
		echo 	%%ifError%% ^(
		echo.
		echo 		call:logError An unexpected error occurred while building the parameter string^^!
		echo 		call:logError The runner stopped due to an error.
		echo 	^)
		echo.
		echo 	goto while_processParameters
		echo.
		echo :elihw_processParameters
		echo.
		echo.
		echo if not exist "%%scriptFile%%" ^(
		echo.
		echo 	call:logError %%prefix%%: The specified script file %%scriptFile%% doesn't exist^^!
		echo 	call:logError The runner stopped due to an error.
		echo 	%%return%% 3
		echo ^)
		echo.
		echo.
		echo call:logInfo The runner executes the specified powershell script.
		echo call:logInfo script: %%scriptFile%%
		echo call:logInfo parameters: %%parameters%%
		echo.
		echo.
		echo start /B /WAIT powershell.exe -ExecutionPolicy ByPass -File "%%scriptFile%%" %%parameters%%
		echo %%ifError%% ^(
		echo.
		echo 	call:logError The powershell script stopped due to an error^^!
		echo 	%%return%% 4
		echo ^)
		echo.
		echo call:logInfo The powershell script has stopped.
		echo.
		echo.
		echo set scriptFile=
		echo set parameters=
		echo.
		echo %%return%%
		echo.
		echo.
		echo @rem ================================================================================
		echo @rem ===
		echo @rem ===   Internal Subroutines
		echo @rem ===
		echo.
		echo @rem --------------------------------------------------------------------------------
		echo @rem ---
		echo @rem ---   void defineMacros^(^)
		echo @rem ---
		echo @rem ---   This subroutine defines required macros.
		echo @rem ---
		echo.
		echo :defineMacros
		echo.
		echo 	set "ifError=set foundErr=1&(if errorlevel 0 if not errorlevel 1 set foundErr=)&if defined foundErr"
		echo.
		echo 	set "cprintln=echo"
		echo 	set "cprint=echo|set /p="
		echo.
		echo 	set "return=exit /b"
		echo.
		echo %%return%%
		echo.
		echo.
		echo @rem --------------------------------------------------------------------------------
		echo @rem ---
		echo @rem ---   void defineConstants^(^)
		echo @rem ---
		echo @rem ---   The subroutine defines required constants.
		echo @rem ---
		echo.
		echo :defineConstants
		echo.
		echo 	set TRUE=TRUE
		echo 	set FALSE=FALSE
		echo.
		echo 	set SCRIPTFILE=%%~n0%%~x0
		echo 	set "LOGFILE=%%~n0.log"
		echo.
		echo 	set SILENT=%%FALSE%%
		echo 	set VERBOSE=%%TRUE%%
		echo.
		echo 	set LOGGING_MODE=%%SILENT%%
		echo.
		echo %%return%%
		echo.
		echo.
		echo @rem --------------------------------------------------------------------------------
		echo @rem ---
		echo @rem ---   void addParameter^(String aVariableName, String aParameter^)
		echo @rem ---
		echo @rem ---   This subroutine adds the specified parameter to the specified variable.
		echo @rem ---
		echo @rem ---
		echo @rem ---   @param aVariableName
		echo @rem ---          the name of a variable
		echo @rem ---   @param aParameter
		echo @rem ---          a parameter
		echo @rem ---
		echo.
		echo :addParameter
		echo.
		echo 	set "_variableName=%%1"
		echo 	if '%%_variableName%%'=='' ^(
		echo.
		echo 		call:logError %%0: No variable name was specified^^!
		echo 		%%return%% 2
		echo 	^)
		echo 	set "_variableName=%%_variableName:"=%%"
		echo.
		echo 	set "_parameter=%%2"
		echo 	if '%%_parameter%%'=='' ^(
		echo.
		echo 		%%return%%
		echo 	^)
		echo 	set "_parameter=%%_parameter:"=%%"
		echo.
		echo.
		echo 	setlocal EnableDelayedExpansion
		echo.
		echo 		set "_value=^!%%_variableName%%^!"
		echo 		set "_newValue=%%_value%% "%%_parameter%%""
		echo.
		echo 	endlocal ^& set "%%_variableName%%=%%_newValue%%"
		echo.
		echo.
		echo 	set _variableName=
		echo 	set _parameter=
		echo.
		echo %%return%%
		echo.
		echo.
		echo @rem --------------------------------------------------------------------------------
		echo @rem ---
		echo @rem ---   void logInfo^(String... someTexts^)
		echo @rem ---
		echo @rem ---   The subroutine logs the specified info text.
		echo @rem ---
		echo @rem ---
		echo @rem ---   @param someTexts
		echo @rem ---          any number of text parameters with info messages
		echo @rem ---
		echo.
		echo :logInfo
		echo.
		echo 	if %%LOGGING_MODE%%==%%SILENT%% ^(
		echo.
		echo 		goto logInfo_skipConsole
		echo 	^)
		echo.
		echo 	^(
		echo 		%%cprintln%% %%date%%::%%time%%::%%username%%:: INFO::%%*
		echo 	^) 1^>^&2
		echo.
		echo 	:logInfo_skipConsole
		echo.
		echo 	^(
		echo 		%%cprintln%% %%date%%::%%time%%::%%username%%:: INFO::%%*
		echo 	^) ^>^> "%%LOGFILE%%"
		echo.
		echo %%return%%
		echo.
		echo.
		echo @rem --------------------------------------------------------------------------------
		echo @rem ---
		echo @rem ---   void logError^(String... someTexts^)
		echo @rem ---
		echo @rem ---   The subroutine logs the specified error text.
		echo @rem ---
		echo @rem ---
		echo @rem ---   @param someTexts
		echo @rem ---          any number of text parameters with error details
		echo @rem ---
		echo.
		echo :logError
		echo.
		echo 	if %%LOGGING_MODE%%==%%SILENT%% ^(
		echo.
		echo 		goto logError_skipConsole
		echo 	^)
		echo.
		echo 	^(
		echo 		%%cprintln%% %%date%%::%%time%%::%%username%%::ERROR::%%*
		echo 	^) 1^>^&2
		echo.
		echo 	:logError_skipConsole
		echo.
		echo 	^(
		echo 		%%cprintln%% %%date%%::%%time%%::%%username%%::ERROR::%%*
		echo 	^) ^>^> "%%LOGFILE%%"
		echo.
		echo %%return%%
	) > testdata-external\powershell\runner.bat

	(
		echo Write-Host Hello World^^!
	) > testdata-external\powershell\example01.ps1

	(
		echo Write-Host "An error occurred^!"
		echo exit 1
	) > testdata-external\powershell\example02.ps1

	(
		echo throw "Oops^!"
	) > testdata-external\powershell\example03.ps1

%return%


@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void createAntScripts()
@rem ---
@rem ---   The subroutine creates ant scripts.
@rem ---

:createAntScripts

	echo create ant scripts...

	call:createDirectory testdata-external\ant %TRUE%

	(
		echo @Echo Off
		echo.
		echo @rem ================================================================================
		echo @rem ===
		echo @rem ===   void main^(^)
		echo @rem ===
		echo @rem ===   This Batch script defines new environment variables and modifies existing
		echo @rem ===   environment variables in order to invoke java and ant from console. The
		echo @rem ===   changes are only active during a single console session.
		echo @rem ===
		echo @rem ===   This script can be copied for the purpose of having several distinct
		echo @rem ===   java and ant environments. In this case paths have to be adjusted
		echo @rem ===   accordingly.
		echo @rem ===
		echo.
		echo call:defineMacros
		echo call:defineVariables
		echo.
		echo.
		echo set subroutineCalls.length=5
		echo set subroutineCalls[1]=setJava
		echo set subroutineCalls[2]=setAnt
		echo set subroutineCalls[3]=printInfo
		echo set subroutineCalls[4]=checkEnvironment
		echo set subroutineCalls[5]=setPath
		echo.
		echo.
		echo for /L %%%%i in ^(1,1,%%subroutineCalls.length%%^) do ^(
		echo.
		echo 	call:invokeSubroutine %%%%i
		echo 	%%ifError%% ^(
		echo.
		echo 		%%return%%
		echo 	^)
		echo ^)
		echo.
		echo.
		echo call:changeConsoleTitle "Java 8"
		echo.
		echo.
		echo for /L %%%%i in ^(1,1,%%subroutineCalls.length%%^) do ^(
		echo.
		echo 	set subroutineCalls[%%%%i]=
		echo ^)
		echo set subroutineCalls.length=
		echo.
		echo.
		echo call:cleanVariables
		echo.
		echo %%return%%
		echo.
		echo.
		echo @rem ================================================================================
		echo @rem ===
		echo @rem ===   Internal Subroutines
		echo @rem ===
		echo.
		echo @rem --------------------------------------------------------------------------------
		echo @rem ---
		echo @rem ---   void defineMacros^(^)
		echo @rem ---
		echo @rem ---   The subroutine defines required macros.
		echo @rem ---
		echo.
		echo :defineMacros
		echo.
		echo 	set "ifError=set foundErr=1&(if errorlevel 0 if not errorlevel 1 set foundErr=)&if defined foundErr"
		echo.
		echo 	set "cprintln=echo"
		echo 	set "cprint=echo|set /p="
		echo.
		echo 	set "return=exit /b"
		echo.
		echo %%return%%
		echo.
		echo.
		echo @rem --------------------------------------------------------------------------------
		echo @rem ---
		echo @rem ---   void defineVariables^(^)
		echo @rem ---
		echo @rem ---   The subroutine defines required variables.
		echo @rem ---
		echo.
		echo :defineVariables
		echo.
		echo 	set applications=0
		echo.
		echo %%return%%
		echo.
		echo.
		echo @rem --------------------------------------------------------------------------------
		echo @rem ---
		echo @rem ---   void cleanVariables^(^)
		echo @rem ---
		echo @rem ---   The subroutine cleans up required variables.
		echo @rem ---
		echo.
		echo :cleanVariables
		echo.
		echo 	for /L %%%%i in ^(1,1,%%applications%%^) do ^(
		echo.
		echo 		set applicationNames[%%%%i]=
		echo 		set applicationHomeReferences[%%%%i]=
		echo 		set applicationExecutableReferences[%%%%i]=
		echo 		set applicationExpectedVersions[%%%%i]=
		echo 	^)
		echo.
		echo 	set applications=
		echo.
		echo %%return%%
		echo.
		echo.
		echo @rem --------------------------------------------------------------------------------
		echo @rem ---
		echo @rem ---   void addApplication^(String aName, String aHomeReference,
		echo @rem ---                       String anExecutableReference, String anExpectedVersion^)
		echo @rem ---
		echo @rem ---   The subroutine adds an application according to the specified parameters.
		echo @rem ---
		echo @rem ---
		echo @rem ---   @param aName
		echo @rem ---          the name of the application
		echo @rem ---   @param aHomeReference
		echo @rem ---          the name of the variable which contains the application's home
		echo @rem ---          directory
		echo @rem ---   @param anExecutableReference
		echo @rem ---          the name of the variable which contains the application's
		echo @rem ---          executable file
		echo @rem ---   @param anExpectedVersion
		echo @rem ---          the application's expected version
		echo @rem ---
		echo.
		echo :addApplication
		echo.
		echo 	set "_name=%%1"
		echo 	if '%%_name%%'=='' ^(
		echo.
		echo 		%%cprintln%% Error^^^^^(%%0^^^^^): No application name has been specified^^! ^>^&2
		echo 		%%return%% 2
		echo 	^)
		echo 	set "_name=%%_name:"=%%"
		echo.
		echo 	set "_homeReference=%%2"
		echo 	if '%%_homeReference%%'=='' ^(
		echo.
		echo 		%%cprintln%% Error^^^^^(%%0^^^^^): No home reference has been specified^^! ^>^&2
		echo 		%%return%% 2
		echo 	^)
		echo 	set "_homeReference=%%_homeReference:"=%%"
		echo.
		echo 	set "_executableReference=%%3"
		echo 	if '%%_executableReference%%'=='' ^(
		echo.
		echo 		%%cprintln%% Error^^^^^(%%0^^^^^): No executable reference has been specified^^! ^>^&2
		echo 		%%return%% 2
		echo 	^)
		echo 	set "_executableReference=%%_executableReference:"=%%"
		echo.
		echo 	set "_expectedVersion=%%4"
		echo 	if '%%_expectedVersion%%'=='' ^(
		echo.
		echo 		%%cprintln%% Error^^^^^(%%0^^^^^): No expected version has been specified^^! ^>^&2
		echo 		%%return%% 2
		echo 	^)
		echo 	set "_expectedVersion=%%_expectedVersion:"=%%"
		echo.
		echo.
		echo 	set /a applications=%%applications%%+1
		echo 	set _currentIndex=%%applications%%
		echo.
		echo.
		echo 	set "applicationNames[%%_currentIndex%%]=%%_name%%"
		echo 	set "applicationHomeReferences[%%_currentIndex%%]=%%_homeReference%%"
		echo 	set "applicationExecutableReferences[%%_currentIndex%%]=%%_executableReference%%"
		echo 	set "applicationExpectedVersions[%%_currentIndex%%]=%%_expectedVersion%%"
		echo.
		echo.
		echo 	set _currentIndex=
		echo 	set _name=
		echo 	set _homeReference=
		echo 	set _executableReference=
		echo 	set _expectedVersion=
		echo.
		echo %%return%%
		echo.
		echo.
		echo @rem --------------------------------------------------------------------------------
		echo @rem ---
		echo @rem ---   void setJava^(^)
		echo @rem ---
		echo @rem ---   The subroutine defines several environment variables for java.
		echo @rem ---
		echo.
		echo :setJava
		echo.
		echo 	set JAVA_HOME=C:\Oracle\Middleware\Oracle_Home\oracle_common\jdk\
		echo 	set JAVA_BIN=%%JAVA_HOME%%bin\
		echo 	set JAVA_EXE=%%JAVA_BIN%%java.exe
		echo.
		echo 	call:addApplication JAVA JAVA_HOME JAVA_EXE 1.8.0
		echo.
		echo %%return%%
		echo.
		echo.
		echo @rem --------------------------------------------------------------------------------
		echo @rem ---
		echo @rem ---   void setAnt^(^)
		echo @rem ---
		echo @rem ---   The subroutine defines several environment variables for ant.
		echo @rem ---
		echo.
		echo :setAnt
		echo.
		echo 	set ANT_HOME=D:\Programme\Ant\apache-ant-1.8.0\
		echo 	set ANT_BIN=%%ANT_HOME%%bin\
		echo 	set ANT_EXE=%%ANT_BIN%%ant.bat
		echo.
		echo 	call:addApplication ANT ANT_HOME ANT_EXE 1.8.0
		echo.
		echo %%return%%
		echo.
		echo.
		echo @rem --------------------------------------------------------------------------------
		echo @rem ---
		echo @rem ---   void setPath^(^)
		echo @rem ---
		echo @rem ---   The path environment variable is modified.
		echo @rem ---
		echo @rem ---   The environment variable NEW_PATH is defined which can be used to verify
		echo @rem ---   that the changes have not be reverted.
		echo @rem ---
		echo.
		echo :setPath
		echo.
		echo 	set NEW_PATH=C:\WINDOWS\system32
		echo 	set NEW_PATH=C:\WINDOWS;%%NEW_PATH%%
		echo.
		echo 	set NEW_PATH=%%ANT_BIN%%;%%NEW_PATH%%
		echo 	set NEW_PATH=%%JAVA_BIN%%;%%NEW_PATH%%
		echo 	set NEW_PATH=.;%%NEW_PATH%%
		echo.
		echo.
		echo 	set "_normalizedOldPath=%%PATH%%"
		echo 	set "_normalizedOldPath=%%_normalizedOldPath:"=%%"
		echo.
		echo 	set "_normalizedNewPath=%%NEW_PATH%%"
		echo 	set "_normalizedNewPath=%%_normalizedNewPath:"=%%"
		echo.
		echo.
		echo 	if "%%_normalizedNewPath%%"=="%%_normalizedOldPath%%" ^(
		echo.
		echo 		rem OK, no changes required
		echo.
		echo 	^) else ^(
		echo.
		echo 		set "OLD_PATH=%%PATH%%"
		echo 		set "PATH=%%NEW_PATH%%"
		echo 	^)
		echo.
		echo.
		echo 	%%cprintln%%.
		echo 	%%cprintln%% PATH:
		echo 	%%cprintln%% %%PATH%%
		echo 	%%cprintln%%.
		echo 	%%cprintln%% current path:
		echo 	%%cprintln%% %%CD%%
		echo 	%%cprintln%%.
		echo.
		echo.
		echo 	set _normalizedNewPath=
		echo 	set _normalizedOldPath=
		echo 	set NEW_PATH=
		echo.
		echo %%return%%
		echo.
		echo.
		echo @rem --------------------------------------------------------------------------------
		echo @rem ---
		echo @rem ---   void checkEnvironment^(^)
		echo @rem ---
		echo @rem ---   The environment is checked ^(i.e. version check^).
		echo @rem ---
		echo.
		echo :checkEnvironment
		echo.
		echo 	%%cprintln%%.
		echo.
		echo 	set success=
		echo.
		echo 	setlocal EnableDelayedExpansion
		echo.
		echo 		set tmpSuccess=
		echo.
		echo 		for /L %%%%i in ^(1,1,^^!applications^^!^) do ^(
		echo.
		echo 			call:existsExecutable ^^!applicationExecutableReferences[%%%%i]^^!
		echo 			%%ifError%% ^(
		echo.
		echo 				set tmpSuccess=^^!tmpSuccess^^!1
		echo 			^)
		echo 		^)
		echo.
		echo 	endlocal ^& set success=%%tmpSuccess%%
		echo.
		echo 	if defined success ^(
		echo.
		echo 		%%cprintln%% The environment is not set up^^! ^>^&2
		echo 		%%return%% 1
		echo 	^)
		echo 	set success=
		echo.
		echo.
		echo 	setlocal EnableDelayedExpansion
		echo.
		echo 		set tmpSuccess=
		echo.
		echo 		for /L %%%%i in ^(1,1,^^!applications^^!^) do ^(
		echo.
		echo 			call:checkVersion ^^!applicationExecutableReferences[%%%%i]^^! ^^!applicationExpectedVersions[%%%%i]^^!
		echo 			if ^^!ERRORLEVEL^^!==0 ^(
		echo.
		echo 				rem OK
		echo.
		echo 			^) else ^(
		echo.
		echo 				set tmpSuccess=^^!tmpSuccess^^!^^!ERRORLEVEL^^!
		echo 			^)
		echo 		^)
		echo.
		echo 	endlocal ^& set success=%%tmpSuccess%%
		echo.
		echo.
		echo 	if defined success (
		echo.
		echo 		%%cprintln%%.
		echo 		%%cprintln%% The environment is not set up^^! ^>^&2
		echo 		%%return%% 2
		echo 	^)
		echo 	set success=
		echo.
		echo 	%%cprintln%%.
		echo.
		echo %%return%%
		echo.
		echo.
		echo @rem --------------------------------------------------------------------------------
		echo @rem ---
		echo @rem ---   void printInfo^(^)
		echo @rem ---
		echo @rem ---   The subroutine prints some environment details to the console.
		echo @rem ---
		echo.
		echo :printInfo
		echo.
		echo 	%%cprintln%%.
		echo.
		echo 	setlocal EnableDelayedExpansion
		echo.
		echo 		set tmpSuccess=
		echo.
		echo 		for /L %%%%i in ^(1,1,^^!applications^^!^) do ^(
		echo.
		echo 			call:printApplicationInfo ^^!applicationNames[%%%%i]^^! ^^!applicationHomeReferences[%%%%i]^^!
		echo 		^)
		echo.
		echo 	endlocal
		echo.
		echo 	%%cprintln%%.
		echo.
		echo %%return%%
		echo.
		echo.
		echo @rem --------------------------------------------------------------------------------
		echo @rem ---
		echo @rem ---   void printApplicationInfo^(String aName, String aHomeReference^)
		echo @rem ---
		echo @rem ---   The subroutine prints some environment details to the console.
		echo @rem ---
		echo @rem ---
		echo @rem ---   @param aName
		echo @rem ---          the name of the application
		echo @rem ---   @param aHomeReference
		echo @rem ---          the name of the variable which contains the application's home
		echo @rem ---          directory
		echo @rem ---
		echo.
		echo :printApplicationInfo
		echo.
		echo 	set "_name=%%1"
		echo 	if '%%_name%%'=='' ^(
		echo.
		echo 		%%cprintln%% Error^^^^^(%%0^^^^^): No application name has been specified^^! ^>^&2
		echo 		%%return%% 2
		echo 	^)
		echo 	set "_name=%%_name:"=%%"
		echo.
		echo 	set "_homeReference=%%2"
		echo 	if '%%_homeReference%%'=='' ^(
		echo.
		echo 		%%cprintln%% Error^^^^^(%%0^^^^^): No home reference has been specified^^! ^>^&2
		echo 		%%return%% 2
		echo 	^)
		echo 	set "_homeReference=%%_homeReference:"=%%"
		echo.
		echo.
		echo 	setlocal EnableDelayedExpansion
		echo.
		echo 		set "_tmp=^!%%_homeReference%%^!"
		echo.
		echo 	endlocal ^& set "_home=%%_tmp%%"
		echo.
		echo 	%%cprintln%% %%_name%% ... %%_home%%
		echo.
		echo.
		echo 	set _home=
		echo 	set _name=
		echo 	set _homeReference=
		echo.
		echo %%return%%
		echo.
		echo.
		echo @rem --------------------------------------------------------------------------------
		echo @rem ---
		echo @rem ---   void existsExecutable^(String anExecutableReference^)
		echo @rem ---   
		echo @rem ---   Checks if the specified executable exists.
		echo @rem ---
		echo @rem ---
		echo @rem ---   @param anExecutableReference
		echo @rem ---          the name of the variable which contains the application's
		echo @rem ---          executable
		echo @rem ---
		echo.
		echo :existsExecutable
		echo.
		echo 	set "_executableReference=%%1"
		echo 	if '%%_executableReference%%'=='' ^(
		echo.
		echo 		%%cprintln%% Error^^^^^(%%0^^^^^): No executable reference has been specified^^! ^>^&2
		echo 		%%return%% 2
		echo 	^)
		echo 	set "_executableReference=%%_executableReference:"=%%"
		echo.
		echo.
		echo 	setlocal EnableDelayedExpansion
		echo.
		echo 		set "_tmp=^!%%_executableReference%%^!"
		echo.
		echo 	endlocal ^& set "_executable=%%_tmp%%"
		echo.
		echo.
		echo 	if not exist "%%_executable%%" ^(
		echo.
		echo 		%%cprintln%% %%_executable%% doesn't exist^^!
		echo 		%%return%% 1
		echo 	^)
		echo.
		echo.
		echo 	set _executable=
		echo 	set _executableReference=
		echo.
		echo %%return%%
		echo.
		echo.
		echo @rem --------------------------------------------------------------------------------
		echo @rem ---
		echo @rem ---   void checkVersion^(String aCommand, String anExpectedVersion^)
		echo @rem ---   
		echo @rem ---   Checks the version of the specified command.
		echo @rem ---
		echo @rem ---
		echo @rem ---   @param anExecutableReference
		echo @rem ---          the name of the variable which contains the application's
		echo @rem ---          executable
		echo @rem ---   @param anExpectedVersion
		echo @rem ---          the expected version
		echo @rem ---
		echo.
		echo :checkVersion
		echo.
		echo 	set "_executableReference=%%1"
		echo 	if '%%_executableReference%%'=='' ^(
		echo.
		echo 		%%cprintln%% Error^^^^^(%%0^^^^^): No executable reference has been specified^^! ^>^&2
		echo 		%%return%% 2
		echo 	^)
		echo 	set "_executableReference=%%_executableReference:"=%%"
		echo.
		echo 	set "_expectedVersion=%%2"
		echo 	if '%%_expectedVersion%%'=='' ^(
		echo.
		echo 		%%cprintln%% Error^^^^^(%%0^^^^^): No expected version has been specified^^! ^>^&2
		echo 		%%return%% 2
		echo 	^)
		echo 	set "_expectedVersion=%%_expectedVersion:"=%%"
		echo.
		echo.
		echo 	setlocal EnableDelayedExpansion
		echo.
		echo 		set "_tmp=^!%%_executableReference%%^!"
		echo.
		echo 	endlocal ^& set "_executable=%%_tmp%%"
		echo.
		echo.
		echo 	call "%%_executable%%" -version 2^>^&1 ^| findstr /L "%%_expectedVersion%%" ^> NUL
		echo 	%%ifError%% ^(
		echo.
		echo 		%%cprintln%% %%_executable%% ... %%_expectedVersion%% ... failed ^>^&2
		echo 		%%return%% 1
		echo.
		echo 	^) else ^(
		echo.
		echo 		%%cprintln%% %%_executable%% ... %%_expectedVersion%% ... OK
		echo 	^)
		echo.
		echo.
		echo 	set _executable=
		echo 	set _executableReference=
		echo 	set _expectedVersion=
		echo.
		echo %%return%%
		echo.
		echo.
		echo @rem --------------------------------------------------------------------------------
		echo @rem ---
		echo @rem ---   void changeConsoleTitle^(String aTitle^)
		echo @rem ---
		echo @rem ---   The subroutine changes the title of the console window.
		echo @rem ---
		echo @rem ---
		echo @rem ---   @param aTitle
		echo @rem ---          the new title of the console window
		echo @rem ---
		echo.
		echo :changeConsoleTitle
		echo.
		echo 	set "_title=%%1"
		echo 	if '%%_title%%'=='' ^(
		echo.
		echo 		%%cprintln%% Error^^^^^(%%0^^^^^): No title has been specified^^! ^>^&2
		echo 		%%return%% 2
		echo 	^)
		echo 	set "_title=%%_title:"=%%"
		echo.
		echo.
		echo 	title %%_title%%
		echo.
		echo.
		echo 	set _title=
		echo.
		echo %%return%%
		echo.
		echo.
		echo @rem --------------------------------------------------------------------------------
		echo @rem ---
		echo @rem ---   void invokeSubroutine^(int anIndex^)
		echo @rem ---
		echo @rem ---   The subroutine invokes a subroutine which is specified in an array.
		echo @rem ---
		echo @rem ---
		echo @rem ---   @param anIndex
		echo @rem ---          the index of the subroutine
		echo @rem ---
		echo.
		echo :invokeSubroutine
		echo.
		echo 	set "_index=%%1"
		echo 	if '%%_index%%'=='' ^(
		echo.
		echo 		%%cprintln%% Error^^^^^(%%0^^^^^): No index has been specified^^! ^>^&2
		echo 		%%return%% 2
		echo 	^)
		echo 	set "_index=%%_index:"=%%"
		echo.
		echo.
		echo 	setlocal EnableDelayedExpansion
		echo.
		echo 		set _tmpName=^^!subroutineCalls[%%_index%%]^^!
		echo.
		echo 	endlocal ^& set _subroutineName=%%_tmpName%%
		echo.
		echo.
		echo 	%%cprintln%% execute %%_subroutineName%% ...
		echo.
		echo.
		echo 	call:%%_subroutineName%%
		echo 	%%ifError%% ^(
		echo.
		echo 		%%cprintln%%.
		echo 		%%cprintln%% ERROR^^^^^(%%_subroutineName%%^^^^^): An error occured^^! ^>^&2
		echo 		%%return%% 2
		echo 	^)
		echo.
		echo.
		echo 	set _subroutineName=
		echo 	set _index=
		echo.
		echo %%return%%
	) > testdata-external\ant\setenv.bat

	(
		echo @Echo Off
		echo cd /D %%~dp0
		echo call setenv.bat ^>NUL
		echo call ant.bat -f %%1
		echo exit /b %%ERRORLEVEL%%
	) > testdata-external\ant\runner.bat

	(
		echo ^<?xml version="1.0" encoding="UTF-8" ?^>
		echo ^<project xmlns="antlib:org.apache.tools.ant" name="test" default="test"^>
		echo.
		echo 	^<target name="test"^>
		echo 		^<echo message="Hello World^!" /^>
		echo 	^</target^>
		echo.
		echo ^</project^>
	) > testdata-external\ant\example01.xml

	(
		echo ^<?xml version="1.0" encoding="UTF-8" ?^>
		echo ^<project xmlns="antlib:org.apache.tools.ant" name="test" default="test"^>
		echo.
		echo 	^<target name="test"^>
		echo 		^<fail message="Oops^!" /^>
		echo 	^</target^>
		echo.
		echo ^</project^>
	) > testdata-external\ant\example02.xml

%return%
