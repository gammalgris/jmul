@Echo Off


@rem ================================================================================
@rem ===
@rem ===   void main()
@rem ===
@rem ===   This script starts Java monitoring tools.
@rem ===
@rem ===

call:defineMacros

call:setUpEnvironment
%ifError% (

	pause
	%return%
)

start /B %POSTGRESQL_BIN%
%ifError% (

	pause
	%return%
)

pause
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
