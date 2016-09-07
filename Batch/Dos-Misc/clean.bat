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

	set directory.length=33

	set directory[1]=%projectDir%.data\
	set directory[2]=%projectDir%Concurrent\classes\
	set directory[3]=%projectDir%Constants\classes\
	set directory[4]=%projectDir%Helper-Tests\classes\
	set directory[5]=%projectDir%IO\classes\
	set directory[6]=%projectDir%IO-Tests\classes\
	set directory[7]=%projectDir%Logging\classes\
	set directory[8]=%projectDir%Markdown\classes\
	set directory[9]=%projectDir%Markdown-Tests\classes\
	set directory[10]=%projectDir%Math\classes\
	set directory[11]=%projectDir%Math-Tests\classes\
	set directory[12]=%projectDir%Measures\classes\
	set directory[13]=%projectDir%Measures-Tests\classes\
	set directory[14]=%projectDir%Misc\classes\
	set directory[15]=%projectDir%Misc-Tests\classes\
	set directory[16]=%projectDir%Network\classes\
	set directory[17]=%projectDir%Network-Tests\classes\
	set directory[18]=%projectDir%Persistence\classes\
	set directory[19]=%projectDir%Reflection\classes\
	set directory[20]=%projectDir%String\classes\
	set directory[21]=%projectDir%String-Tests\classes\
	set directory[22]=%projectDir%Test\classes\
	set directory[23]=%projectDir%Test-Base\classes\
	set directory[24]=%projectDir%Time\classes\
	set directory[25]=%projectDir%Transformation\classes\
	set directory[26]=%projectDir%Version\classes\
	set directory[27]=%projectDir%Web\classes\
	set directory[29]=%projectDir%Web-Tests\classes\
	set directory[29]=%projectDir%XML\classes\

	set directory[30]=%projectDir%..\Batch\classes\
	set directory[31]=%projectDir%..\Batch\Ant-Sonar\.sonar\
	set directory[32]=%projectDir%..\tmp\

	set directory[33]=%projectDir%IO-Tests\testdata-io\

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
