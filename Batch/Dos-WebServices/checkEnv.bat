@Echo Off

@rem ================================================================================
@rem ===
@rem ===   void main()
@rem ===
@rem ===   This Batch script checks if the environment variables have been changed.
@rem ===   Depending on the security settings changes to environment variables will
@rem ===   be reverted after a batch file has finished execution.
@rem ===

if not defined OLD_PATH (

	echo ERROR^(%0^): The environment hasn't been set or the modifications have been reverted. >&2
	exit /b 2
)


set "_currentPath=%PATH%"
set "_currentPath=%_currentPath:"=%"

set "_previousPath=%OLD_PATH%"
set "_previousPath=%_previousPath:"=%"


if "%_currentPath%"=="%_previousPath%" (

	echo Error^(%0^): The path variable is unchanged! >&2
	echo /b 2

) else (

	echo.
	echo The modifications to the path variable have been retained.
)


exit /b
