cd Backend

for /f "tokens=5" %%p in (' netstat -ano ^| find "LISTENING" ^| find "8080" ') do taskkill /F /PID %%p
IF NOT ERRORLEVEL 1 (
	ECHO Running.
) ELSE (
	ECHO kill OK.
)
 
start gradlew clean bootRun
