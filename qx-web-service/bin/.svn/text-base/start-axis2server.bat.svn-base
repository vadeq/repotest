@echo off
rem ---------------------------------------------------------------------------
rem   AXIS2_HOME      Must point at your AXIS2 directory 
rem   JAVA_HOME       Must point at your Java Development Kit installation.
rem   JAVA_OPTS       (Optional) Java runtime options 
rem   AXIS2_CMD_LINE_ARGS  
rem ---------------------------------------------------------------------------
set JAVA_HOME=\zws\java2
set AXIS2_HOME=\zws\server\axis2-1.1.1
set AXIS2_CMD_LINE_ARGS=-repo "%AXIS2_HOME%\repository" -conf "\zws\conf\axis2.xml"
call %AXIS2_HOME%\bin\axis2server.bat
