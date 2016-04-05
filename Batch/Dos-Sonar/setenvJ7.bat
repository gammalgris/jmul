@Echo Off

@rem ================================================================================
@rem ===
@rem ===   void main()
@rem ===
@rem ===   This Batch script defines new environment variables and modifies existing
@rem ===   environment variables in order to invoke java and ant from console. The
@rem ===   changes are only active during a single console session.
@rem ===
@rem ===   This script can be copied for the purpose of having several distinct
@rem ===   java and ant environments. In this case paths have to be adjusted
@rem ===   accordingly.
@rem ===

call:defineMacros

call:setJava
call:setAnt

call:printInfo

call:checkEnvironment

call:setPath

set JAVA_VERSION=7
call:changeConsoleTitle

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
@rem ---   void setJava()
@rem ---
@rem ---   The subroutine defines several environment variables for java.
@rem ---

:setJava

	set JAVA_HOME=D:\Programme\jdk1.7.0_80\
	set JAVA_BIN=%JAVA_HOME%bin\
	set JAVA_EXE=%JAVA_BIN%java.exe

%return%


@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void setAnt()
@rem ---
@rem ---   The subroutine defines several environment variables for ant.
@rem ---

:setAnt

	set ANT_HOME=D:\Programme\apache-ant-1.8.0\
	set ANT_BIN=%ANT_HOME%bin\
	set ANT_EXE=%ANT_BIN%ant.bat

%return%


@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void setPath()
@rem ---
@rem ---   The path environment variable is modified.
@rem ---

:setPath

	set PATH=C:\WINDOWS\system32
	set PATH=C:\WINDOWS;%PATH%

	set PATH=%ANT_HOME%bin;%PATH%
	set PATH=%JAVA_HOME%bin;%PATH%
	set PATH=.;%PATH%

%return%


@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void checkEnvironment()
@rem ---
@rem ---   The environment is checked (i.e. version check).
@rem ---

:checkEnvironment

	set array.length=2

	set executable[1]=%JAVA_EXE%
	set executable[2]=%ANT_EXE%

	set version[1]=1.7.0
	set version[2]=1.8.0


	set success=

	setlocal EnableDelayedExpansion

		set tmpSuccess=
	
		for /L %%i in (1,1,!array.length!) do (

			if not exist "!executable[%%i]!" (

				%cprintln% !executable[%%i]! doesn't exist!
				set tmpSuccess=!tmpSuccess!1
			)
		)
		
	endlocal & set success=%tmpSuccess%

	if defined success (
	
		%cprintln% The environment is not set up! >&2
		%return% 1
	)
	set success=


	setlocal EnableDelayedExpansion

		set tmpSuccess=

		for /L %%i in (1,1,!array.length!) do (

			call:checkVersion "!executable[%%i]!" !version[%%i]!
			if !ERRORLEVEL!==0 (
			
				rem OK
				
			) else (
			
				set tmpSuccess=!tmpSuccess!!ERRORLEVEL!
			)
		)

	endlocal & set success=%tmpSuccess%


	if defined success (
	
		%cprintln% The environment is not set up! >&2
		%return% 2
	)


	set success=

	for /L %%i in (1,1,%array.length%) do (

		set executable[%%i]=
		set version[%%i]=
	)

%return%


@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void printInfo()
@rem ---
@rem ---   The subroutine prints some environment details to the console.
@rem ---

:printInfo

	%cprintln%.
	%cprintln% ANT_HOME ....... %ANT_HOME%
	%cprintln% JAVA_HOME ...... %JAVA_HOME%
	%cprintln%.

%return%


@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void checkVersion(String aCommand, String anExpectedVersion)
@rem ---   
@rem ---   Checks the version of the specified command.
@rem ---
@rem ---
@rem ---   @param aCommand
@rem ---          a command
@rem ---   @param anExpectedVersion
@rem ---          the expected version
@rem ---

:checkVersion

	call %1 -version 2>&1 | findstr "%2" > NUL
	%ifError% (
		
		%cprintln% %1 ... %2 ... failed >&2
		%return% 1
		
	) else (
	
		%cprintln% %1 ... %2 ... OK
	)

%return%


@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void changeConsoleTitle()
@rem ---
@rem ---   The subroutine changes the title of the console window.
@rem ---

:changeConsoleTitle

	if '%JAVA_VERSION%'=='' (

		set "newTitle=Console ^(Java unknown Version^)"

	) else (

		set "newTitle=Console ^(Java %JAVA_VERSION%^)"
	)

	title %newTitle%

	set newTitle=

%return%
