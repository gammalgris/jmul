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

	set directory.length=58

	set directory[1]=%projectDir%.data\
	set directory[2]=%projectDir%Checks\classes\
	set directory[3]=%projectDir%Checks-Tests\classes\
	set directory[4]=%projectDir%Concurrent\classes\
	set directory[5]=%projectDir%Constants\classes\
	set directory[6]=%projectDir%Document\classes\
	set directory[7]=%projectDir%Document-Binary\classes\
	set directory[8]=%projectDir%Document-Binary-Tests\classes\
	set directory[9]=%projectDir%Document-CSV\classes\
	set directory[10]=%projectDir%Document-CSV-Tests\classes\
	set directory[11]=%projectDir%Document-MD\classes\
	set directory[12]=%projectDir%Document-MD-Tests\classes\
	set directory[13]=%projectDir%Document-Text\classes\
	set directory[14]=%projectDir%Document-Text-Tests\classes\
	set directory[15]=%projectDir%Formula\classes\
	set directory[16]=%projectDir%Formula-Tests\classes\
	set directory[17]=%projectDir%Helper-Tests\classes\
	set directory[18]=%projectDir%IO\classes\
	set directory[19]=%projectDir%IO-Tests\classes\
	set directory[20]=%projectDir%Logging\classes\
	set directory[21]=%projectDir%Mail\classes\
	set directory[22]=%projectDir%Math\classes\
	set directory[23]=%projectDir%Math-Tests\classes\
	set directory[24]=%projectDir%Measures\classes\
	set directory[25]=%projectDir%Measures-Tests\classes\
	set directory[26]=%projectDir%Metainfo\classes\
	set directory[27]=%projectDir%Misc\classes\
	set directory[28]=%projectDir%Misc-Tests\classes\
	set directory[29]=%projectDir%Network\classes\
	set directory[30]=%projectDir%Network-Tests\classes\
	set directory[31]=%projectDir%Persistence\classes\
	set directory[32]=%projectDir%Persistence-Tests\classes\
	set directory[33]=%projectDir%Reflection\classes\
	set directory[34]=%projectDir%Reflection-Tests\classes\
	set directory[35]=%projectDir%String\classes\
	set directory[36]=%projectDir%String-Tests\classes\
	set directory[37]=%projectDir%Test\classes\
	set directory[38]=%projectDir%Test-Base\classes\
	set directory[39]=%projectDir%Time\classes\
	set directory[40]=%projectDir%Time-Tests\classes\
	set directory[41]=%projectDir%Transformation\classes\
	set directory[42]=%projectDir%Transformation-Tests\classes\
	set directory[43]=%projectDir%Transformation-XML\classes\
	set directory[44]=%projectDir%Version\classes\
	set directory[45]=%projectDir%Web\classes\
	set directory[46]=%projectDir%Web-Tests\classes\
	set directory[47]=%projectDir%XML\classes\
	set directory[48]=%projectDir%XML-Tests\classes\

	set directory[49]=%projectDir%..\Batch\classes\
	set directory[50]=%projectDir%..\Batch\Ant-Sonar\.sonar\
	set directory[51]=%projectDir%..\tmp\

	set directory[52]=%projectDir%IO-Tests\testdata-io\
	set directory[53]=%projectDir%XML-Tests\testdata-xml\
	set directory[54]=%projectDir%Document-CSV-Tests\testdata-csv\
	set directory[55]=%projectDir%Document-Binary-Tests\testdata-binary\
	set directory[56]=%projectDir%Persistence-Tests\testdata-persistence\

	set directory[57]=%projectDir%..\Batch\Gradle-Build\.gradle\
	set directory[58]=%projectDir%..\Batch\Gradle-Build\build\

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
