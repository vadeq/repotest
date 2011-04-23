@echo off
set JAVA_HOME=\zws\java2
set path=\zws\app\ant\bin
ant -buildfile deploy.xml deploy
pause