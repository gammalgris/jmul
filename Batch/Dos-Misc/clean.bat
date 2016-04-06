@Echo off


@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void main()
@rem ---
@rem ---   Deletes all directories within this project which contain temporary data
@rem ---   (e.g. compiler output, log files, etc.).
@rem ---

:main

	call:setUp

	echo.
	echo project dir .... %projectDir%
	echo.

	for /L %%i in (1, 1, %directory.length%) do (

		setlocal EnableDelayedExpansion

			call:deleteDirectory !directory[%%i]!

		endlocal
	)

	call:tearDown

	pause

exit /b 0


@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void setUp()
@rem ---
@rem ---   Declares and initializes variables and constants.
@rem ---

:setUp

	set projectDir=%~dp0..\..\Utilities\

	set directory.length=15
	set directory[1]=%projectDir%.data\
	set directory[2]=%projectDir%Concurrent\classes\
	set directory[3]=%projectDir%IO\classes\
	set directory[4]=%projectDir%Math\classes\
	set directory[5]=%projectDir%Math-Tests\classes\
	set directory[6]=%projectDir%Misc\classes\
	set directory[7]=%projectDir%Misc-Tests\classes\
	set directory[8]=%projectDir%Network\classes\
	set directory[9]=%projectDir%Persistence\classes\
	set directory[10]=%projectDir%Reflection\classes\
	set directory[11]=%projectDir%String\classes\
	set directory[12]=%projectDir%String-Tests\classes\
	set directory[13]=%projectDir%Test\classes\
	set directory[14]=%projectDir%Transformation\classes\
	set directory[15]=%projectDir%XML\classes\

exit /b 0


@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void tearDown()
@rem ---
@rem ---   Declared variables and constants are cleaned up.
@rem ---

:tearDown

	for /L %%i in (1, 1, %directory.length%) do (

		set directory[%%i]=
	)

	set directory.length=

	set projectDir=

exit /b 0


@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void deleteDirectory(String _path)
@rem ---
@rem ---   Deletes the specified directory and all its content.
@rem ---
@rem ---
@rem ---   @param _path
@rem ---          the specified path represents a directory
@rem ---

:deleteDirectory

	set "_path=%1"
	if '%_path%'=='' (

		echo No directory has been specified! >&2
		exit /b 2
	)
	set "_path=%_path:"=%"


	if exist %_path% (

		echo deleting %_path%
		rmdir /S /Q %_path%

	) else (

		echo %_path% doesn't exist.
	)


	set _path=

exit /b 0
