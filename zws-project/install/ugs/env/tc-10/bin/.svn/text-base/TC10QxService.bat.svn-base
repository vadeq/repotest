@echo off
REM %1:	Working Directory
set WORKING_DIR=%1

if "%WORKING_DIR%"=="" goto usage

REM Do not use current working directory (./): Use place full path names 
REM Do not use file redirect in or out (java io permissions may not allow execution)

set PATH=
set JRE_HOME=%PORTAL_ROOT%\jre

set IMAN_ROOT=C:\zws\env\ugs\tc-10\rich-client
set IMAN_DATA=C:\zws\env\ugs\tc-10\tcdata
set PORTAL_ROOT=%IMAN_ROOT%\portal
set FMS_HOME=%PORTAL_ROOT%\fms

REM MAKE sure to place full path names here (do not use .\jar!)
set RICH_CLIENT_LIBS=%PORTAL_ROOT%
set FMS_LIBS=%FMS_HOME%\jar
set ZWS_LIBS=C:\zws\env\ugs\tc-10\bin\jar
set ZWS_BOOTSTRAP=C:\zws\designstate\bootstrap

rem set the PUB_CLASS_PATH from ZWS published libs
setlocal EnableDelayedExpansion
FOR %%c in ("%PORTAL_ROOT%\*.jar") DO set RICH_CLIENT_LIBS=!RICH_CLIENT_LIBS!;%%c

setlocal EnableDelayedExpansion
FOR %%c in ("%FMS_HOME%\jar\*.jar") DO set FMS_LIBS=!FMS_LIBS!;%%c

setlocal EnableDelayedExpansion
FOR %%c in ("%ZWS_LIBS%\*.jar") DO set ZWS_LIBS=!ZWS_LIBS!;%%c

set CLASSPATH=%PORTAL_ROOT%;%RICH_CLIENT_LIBS%;%FMS_LIBS%;%ZWS_LIBS%;%ZWS_BOOTSTRAP%

call %IMAN_DATA%\iman_profilevars

REM JAVA Home set for PEN JRE HOME set for TC Rich Client
set JAVA_HOME=C:\zws\java2

set HOST=zws-tc-svr
set PORT=8090

%JAVA_HOME%\bin\java -version
echo %JAVA_HOME%\bin\java -classpath %CLASSPATH% zws.repository.teamcenter.TC10Main %WORKING_DIR%
call %JAVA_HOME%\bin\java -classpath %CLASSPATH% zws.repository.teamcenter.TC10Main %WORKING_DIR%
goto done

REM %JAVA_HOME%\bin\java -classpath %CLASSPATH% zws.repository.teamcenter.TC10Test infodba infodba %HOST% %PORT% 2 "RpHZ2FmLxKbabC|000001|A"
rem C:\j2sdk1.4.2_10\bin\java -classpath %CLASSPATH% zws.repository.teamcenter.TC10Test infodba infodba tor-wkasunic2 8090 3 "RpHZ2FmLxKbabC|000001|A"
rem C:\j2sdk1.4.2_10\bin\java -classpath %CLASSPATH% zws.repository.teamcenter.TC10Test infodba infodba tor-wkasunic2 8090 1 000*

:usage
echo USAGE: TCQxService [working-directory]
echo        working directory should contain context.xml and instruction.xml


:done
echo done