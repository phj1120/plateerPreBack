cd Backend

for /f "tokens=5" %%p in (' netstat -ano ^| find "LISTENING" ^| find "8080" ') do taskkill /F /PID %%p

start gradlew clean bootRun
