@echo off
set PATH=
echo PEN Simulation 
set ZWS_LIBS=C:\zws\env\ugs\tc-10\bin\jar

rem set the PUB_CLASS_PATH from ZWS published libs
setlocal EnableDelayedExpansion
FOR %%c in ("%ZWS_LIBS%\*.jar") DO set ZWS_LIBS=!ZWS_LIBS!;%%c

set CLASSPATH=%ZWS_LIBS%

REM JAVA Home set for PEN JRE HOME
set JAVA_HOME=C:\zws\java2
%JAVA_HOME%\bin\java -version
call %JAVA_HOME%\bin\java -classpath %CLASSPATH% zws.qx.Test
