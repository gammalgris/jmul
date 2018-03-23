@Echo Off

call:defineMacros


set scriptPath=%~dp0

set sourceFile=version.properties
set "sourcePath=%scriptPath%..\..\Utilities\Version\properties\%sourceFile%"

set destinationFile=gradle.properties
set "destinationPath=%scriptPath%%destinationFile%"


copy /Y %sourcePath% %destinationPath%
%ifError% (

	%cprintln% An error occurred while trying to update %destinationFile%^!
	%return% 2
)


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
