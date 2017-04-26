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


set functionCalls.length=2

set functionCalls[1]=createRootDirectory
set functionCalls[2]=createXmlFiles


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

	call:createDirectory testdata-xml %TRUE%

%return%


@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void createXmlFiles()
@rem ---
@rem ---   The subroutine creates XML files.
@rem ---

:createXmlFiles

	echo create XML files...

	copy NUL testdata-xml\empty.xml 1>NUL 2>&1

	(
		echo.
	) > testdata-xml\empty2.xml

	(
		echo ^<?xml version="1.0" encoding="UTF-8" ?^>
	) > testdata-xml\header-only.xml

	(
		echo ^<?xml version="1.0" encoding="UTF-8" ?^>
		echo ^<^>
	) > testdata-xml\invalid-root.xml

	(
		echo ^<?xml version="1.0" encoding="UTF-8" ?^>
		echo ^</root^>
	) > testdata-xml\invalid-root2.xml

	(
		echo ^<?xml version="1.0" encoding="UTF-8" ?^>
		echo ^<root
		echo ^</root^>
	) > testdata-xml\invalid-root3.xml

	(
		echo ^<?xml version="1.0" encoding="UTF-8" ?^>
		echo ^<root1^>
		echo ^</root1^>
		echo ^<root2^>
		echo ^</root2^>
	) > testdata-xml\multi-root.xml

	(
		echo ^<?xml version="1.0" encoding="UTF-8" ?^>
		echo ^<persons count=^"1^"^>
		echo   ^<person^>
		echo     ^<firstName^>John^</firstName^>
		echo     ^<lastName^>Doe^</lastName^>
		echo   ^</person^>
		echo ^</persons^>
	) > testdata-xml\valid.xml

%return%
