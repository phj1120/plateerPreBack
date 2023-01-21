cd Backend



for /f "tokens=5" %%p in (' netstat -ano ^| find "LISTENING" ^| find "8080" ') do taskkill /F /PID %%p > NUL
IF NOT ERRORLEVEL 1 (
	ECHO NOT START PROJECT.
	gradlew clean bootRun
) ELSE (
	ECHO PROJECT KILL OK.
	start gradlew clean bootRun
)

:END

 

Exit 0
