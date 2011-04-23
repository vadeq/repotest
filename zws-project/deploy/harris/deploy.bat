@echo off
set JAVA_HOME=\zws\java2
set path=\zws\app\ant\bin;%PATH%

ant -buildfile deploy.xml deploy

pause