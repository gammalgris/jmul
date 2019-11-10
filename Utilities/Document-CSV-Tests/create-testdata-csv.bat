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
set functionCalls[2]=createCsvFiles


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

	rem leave two blank lines after the following set command
	set LF=^


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

	call:createDirectory testdata-csv %TRUE%

%return%


@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void createCsvFiles()
@rem ---
@rem ---   The subroutine creates CSV files.
@rem ---

:createCsvFiles

	echo create CSV files...

	(
		echo A;B;C
		echo 1;2;3
		echo 4;5;6
		echo 7;8;9
	) > testdata-csv\example01.csv


	(
		echo A,B,C
		echo 1,2,3
		echo 4,5,6
		echo 7,8,9
	) > testdata-csv\example02.csv


	<NUL set /P=A;B;C^%LF%%LF%> testdata-csv\example03.csv
	<NUL set /P=1;2;3^%LF%%LF%>> testdata-csv\example03.csv
	<NUL set /P=4;5;6^%LF%%LF%>> testdata-csv\example03.csv
	<NUL set /P=7;8;9^%LF%%LF%>> testdata-csv\example03.csv


	call:resetErrorlevel


	(
		echo A;B;C
		echo "1";"2";"3"
		echo "4";"5";"6"
		echo "7";"8";"9"
	) > testdata-csv\example04.csv


	(
		echo A;B;C
		echo 1;2;3
		echo 4;Hallo
		echo Welt;6
		echo 7;8;9
	) > testdata-csv\example05.csv


	(
		echo A;B;C
		echo 1;2;3
		echo 4;5;"Hallo
		echo Welt"
		echo 7;8;9
	) > testdata-csv\example06.csv


	(
		echo 1;2;3
		echo 4;5;6
		echo 7;8;9
	) > testdata-csv\example07.csv


	(
		echo 1;2
		echo 3;4;5
		echo 6;7;8;9
	) > testdata-csv\example08.csv


	(
		echo ;;
		echo ;;
		echo ;;
	) > testdata-csv\example09.csv


	(
		echo ÄäÖöÜüß
	) > testdata-csv\example10.csv


	(
		echo A;;C
		echo 1;2;3
		echo 4;5;6
		echo 7;8;9
	) > testdata-csv\example12.csv

%return%


@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void resetErrorlevel()
@rem ---
@rem ---   The subroutine resets the errorlevel to zero.
@rem ---

:resetErrorlevel

%return% 0
