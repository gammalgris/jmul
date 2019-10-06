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

call:initZipTool
%ifError% (

	echo Unable to initialize zip tool. >&2
	%return%
)


set functionCalls.length=6

set functionCalls[1]=createRootDirectory
set functionCalls[2]=createFileFilterTestData
set functionCalls[3]=createArchiveScanTestData
set functionCalls[4]=createArchiveCreationTestData
set functionCalls[5]=createDeletionTestData
set functionCalls[6]=createLinks


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
@rem ---   void initZipTool()
@rem ---
@rem ---   The subroutine initializes the zip tool.
@rem ---

:initZipTool

	set ZIP_DIR=C:\Progra~1\7-Zip\
	set ZIP_EXE=%ZIP_DIR%7z.exe

	if not exist "%ZIP_EXE%" (

		echo The required zip tool doesn't exist at the expected location ^(%ZIP_DIR%^)^! >&2
		%return% 2
	)

%return%


@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void createArchive(String _archivePath, String... _somePaths)
@rem ---
@rem ---   The subroutine creates an archive according to the specified parameters.
@rem ---
@rem ---
@rem ---   @param _archivePath
@rem ---           the path of the archive which is to be created
@rem ---   @param _somePaths
@rem ---           All files and directories which have to be put into the archive
@rem ---

:createArchive

	start /B %ZIP_EXE% a %* >nul 2>&1
	%ifError% (

		echo An error occurred while creating the archive %1^! >&2
		%return% 2
	)

%return%


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
@rem ---   void createFile(String _path, String _content)
@rem ---
@rem ---   The subroutine creates a directory according to the specified path. If
@rem ---   the file already exists it will be overwritten.
@rem ---
@rem ---
@rem ---   @param _path
@rem ---          a file name
@rem ---   @param _content
@rem ---          the initial content of the file
@rem ---

:createFile

	set "_path=%1"
	if '%_path%'=='' (

		echo No file name has been specified! >&2
		%return% 2
	)
	set "_path=%_path:"=%"

	set "_content=%2"
	if '%_content%'=='' (

		echo No content has been specified! >&2
		%return% 2
	)
	set "_content=%_content:"=%"


	%cprintln% %_content%> "%_path%" 2>nul
	%ifError% (

		%cprintln% Error: Unable to create the file "%_path%"! >&2
		%return% 2
	)


	set _path=
	set _content=

%return%


@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void createRootDirectory()
@rem ---
@rem ---   The subroutine creates the root directory.
@rem ---

:createRootDirectory

	echo create root directory...

	call:createDirectory testdata-io %TRUE%

%return%


@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void createFileFilterTestData()
@rem ---
@rem ---   The subroutine creates the test data required for testing the various
@rem ---   file filters.
@rem ---

:createFileFilterTestData

	echo create test data ^(file filter tests^)...

	call:createDirectory testdata-io\folder1 %TRUE%
	call:createDirectory testdata-io\folder4 %TRUE%
	call:createFile testdata-io\file1.txt "Hello World"
	call:createFile testdata-io\file2.txt "Hello World"
	call:createFile testdata-io\file3.bat "@Echo Hello World"
	call:createFile testdata-io\folder4\file1.txt "Hello World"
	call:createFile testdata-io\folder4\file2.txt "Hello World"

%return%


@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void createArchiveScanTestData()
@rem ---
@rem ---   The subroutine creates the test data required for scanning archives.
@rem ---

:createArchiveScanTestData

	echo create test data ^(archive scan tests^)...

	call:createFile testdata-io\config1.properties "key1^=hello"
	call:createFile testdata-io\config2.properties "key2^=world"
	call:createDirectory testdata-io\folder2 %TRUE%
	call:createDirectory testdata-io\folder3 %TRUE%
	call:createFile testdata-io\folder2\config3.properties "key3^=!"
	call:createArchive testdata-io\archive1.zip testdata-io\config1.properties testdata-io\config2.properties testdata-io\folder2
	call:createArchive testdata-io\archive2.zip testdata-io\config1.properties
	call:createArchive testdata-io\archive3.zip testdata-io\config2.properties
	call:createArchive testdata-io\archive4.zip testdata-io\folder3

%return%


@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void createArchiveScanTestData()
@rem ---
@rem ---   The subroutine creates the test data required for scanning archives.
@rem ---

:createArchiveCreationTestData

	echo create test data ^(archive creation tests^)...

	call:createDirectory testdata-io\out %TRUE%

%return%


@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void createFileFilterTestData()
@rem ---
@rem ---   The subroutine creates the test data required for testing the various
@rem ---   file filters.
@rem ---

:createDeletionTestData

	echo create test data ^(deletion tests^)...

	call:createDirectory testdata-io\deletion\folder1 %TRUE%

	call:createFile testdata-io\deletion\file1.txt "Hello World"
	call:createFile testdata-io\deletion\file2.txt "Hello World"

	call:createDirectory testdata-io\deletion\folder2 %TRUE%
	call:createDirectory testdata-io\deletion\folder2\folder3 %TRUE%
	call:createFile testdata-io\deletion\folder2\file3.txt "Hello World"
	call:createFile testdata-io\deletion\folder2\folder3\file4.bat "@Echo Hello World"

	call:createDirectory testdata-io\deletion\folder4 %TRUE%
	call:createDirectory testdata-io\deletion\folder4\folder5 %TRUE%
	call:createFile testdata-io\deletion\folder4\file5.txt "Hello World"
	call:createFile testdata-io\deletion\folder4\folder5\file6.bat "@Echo Hello World"

	call:createFile testdata-io\deletion\protected.txt "Hello World"
	attrib +R testdata-io\deletion\protected.txt

	call:createFile testdata-io\deletion\archivable.txt "Hello World"
	attrib +A testdata-io\deletion\archivable.txt

	call:createFile testdata-io\deletion\sysfile.txt "Hello World"
	attrib +S testdata-io\deletion\sysfile.txt

	call:createFile testdata-io\deletion\hidden.txt "Hello World"
	attrib +H testdata-io\deletion\hidden.txt

	call:createFile testdata-io\deletion\notindexed.txt "Hello World"
	attrib +I testdata-io\deletion\notindexed.txt

	call:createDirectory testdata-io\deletion\folder6 %TRUE%
	call:createDirectory testdata-io\deletion\folder6\folder7 %TRUE%
	call:createFile testdata-io\deletion\folder6\file7.txt "Hello World"

%return%


@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void createLinks()
@rem ---
@rem ---   The subroutine creates links in the file system. For this action
@rem ---   administrative rights are required.
@rem ---

:createLinks

	echo create test data ^(links^)...

	mklink testdata-io\deletion\folder6\folder7\link.txt testdata-io\deletion\folder6\file7.txt

%return%
