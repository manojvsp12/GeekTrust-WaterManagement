@echo off

call mvn clean install assembly:single -q
call java -jar target\geektrust.jar sample_input\input1.txt