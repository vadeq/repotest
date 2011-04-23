@echo off
set PATH=
echo PEN Simulation 
set ZWS_LIBS=C:\zws\designstate\webapp\ZeroWait-State\WEB-INF\lib

rem set the PUB_CLASS_PATH from ZWS published libs
setlocal EnableDelayedExpansion
FOR %%c in ("%ZWS_LIBS%\*.jar") DO set ZWS_LIBS=!ZWS_LIBS!;%%c

set CLASSPATH=%ZWS_LIBS%

REM JAVA Home set for PEN JRE HOME
set JAVA_HOME=C:\zws\java2

REM PARAMETERS:

set USER=infodba
set /P USER=USER[%USER%]:

set PASSWORD=infodba
set /P PASSWORD=PASSWORD[%PASSWORD%]:

set ITEMID=000001
set /P ITEMID=Item ID[%ITEMID%]:

set REV=A
set /P REV=Rev[%REV%]:

set CONFIG=Latest Working
set /P CONFIG=CONFIG[%CONFIG%]:

%JAVA_HOME%\bin\java -version
ECHO %JAVA_HOME%\bin\java -classpath %CLASSPATH% zws.repository.teamcenter.proxy.TC10ProxyInterface reportBOM %USER% %PASSWORD% %ITEMID% %REV% "%CONFIG%"
call %JAVA_HOME%\bin\java -classpath %CLASSPATH% zws.repository.teamcenter.proxy.TC10ProxyInterface reportBOM %USER% %PASSWORD% %ITEMID% %REV% "%CONFIG%"
