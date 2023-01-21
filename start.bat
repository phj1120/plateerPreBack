cd Backend



for /f "tokens=5" %%p in (' netstat -ano ^| find "LISTENING" ^| find "8080" ') do taskkill /F /PID %%p > NUL
IF NOT ERRORLEVEL 1 (
	ECHO Running.
	gradlew clean bootRun
) ELSE (
	ECHO kill OK.
	start gradlew clean bootRun
)

:END

 

Exit 0
