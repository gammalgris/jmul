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

%return%
