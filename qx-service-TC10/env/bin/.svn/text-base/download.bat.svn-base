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

REM PARAMETERS:

set USER=infodba
set /P USER=USER[%USER%]:

set PASSWORD=infodba
set /P PASSWORD=PASSWORD[%PASSWORD%]:

set ITEMID=000001
set /P ITEMID=Item ID[%ITEMID%]:

set REV=A
set /P REV=Rev[%REV%]:

set DATASET_TYPE=TEXT
set /P DATASET_TYPE=DATASET_TYPE[%DATASET_TYPE%]:

set DATASET_NAME=000001/A
set /P DATASET_NAME=DATASET_NAME[%DATASET_NAME%]:

set FILE_NAME=000001_A.dat
set /P FILE_NAME=FILE_NAME[%FILE_NAME%]:

%JAVA_HOME%\bin\java -version
ECHO %JAVA_HOME%\bin\java -classpath %CLASSPATH% zws.qx.Test download %USER% %PASSWORD% %ITEMID% %REV% %DATASET_TYPE% %DATASET_NAME% %FILE_NAME%
call %JAVA_HOME%\bin\java -classpath %CLASSPATH% zws.qx.Test download %USER% %PASSWORD% %ITEMID% %REV% %DATASET_TYPE% %DATASET_NAME% %FILE_NAME%
