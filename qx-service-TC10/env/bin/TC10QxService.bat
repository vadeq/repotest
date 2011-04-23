@echo off
REM %1:	TC10ENV_DIR
REM %2:	WORKING_DIR

set TC10ENV_DIR=%1
set WORKING_DIR=%2

if "%TC10ENV_DIR%"=="" goto usage
if "%WORKING_DIR%"=="" goto usage

REM Do not use current working directory (./): Use place full path names 
REM Do not use file redirect in or out (java io permissions may not allow execution)

set PATH=
call "%TC10ENV_DIR%\bin\LOCAL_IMAN_ENV.bat"

set PORTAL_ROOT=%IMAN_ROOT%\portal
set FMS_HOME=%PORTAL_ROOT%\fms

set JRE_HOME=%PORTAL_ROOT%\jre

REM MAKE sure to place full path names here (do not use .\jar!)
set RICH_CLIENT_LIBS=
set FMS_LIBS=
set ZWS_LIBS=

rem set the PUB_CLASS_PATH from ZWS published libs
setlocal EnableDelayedExpansion
FOR %%c in ("%PORTAL_ROOT%\*.jar") DO set RICH_CLIENT_LIBS=!RICH_CLIENT_LIBS!;"%%c"

setlocal EnableDelayedExpansion
FOR %%c in ("%FMS_HOME%\jar\*.jar") DO set FMS_LIBS=!FMS_LIBS!;"%%c"

setlocal EnableDelayedExpansion
FOR %%c in ("%TC10ENV_DIR%\bin\jar\*.jar") DO set ZWS_LIBS=!ZWS_LIBS!;"%%c"

set CLASSPATH="%PORTAL_ROOT%";%RICH_CLIENT_LIBS%;%FMS_LIBS%;%ZWS_LIBS%;"%ZWS_BOOTSTRAP%";

call "%IMAN_DATA%\iman_profilevars"

REM JAVA Home set for PEN JRE HOME set for TC Rich Client

set JAVA_HOME=%JAVA2%
"%JAVA_HOME%\bin\java" -version
echo %JAVA_HOME%\bin\java -classpath %CLASSPATH% zws.repository.teamcenter.TC10Main %WORKING_DIR% > call.txt
call %JAVA_HOME%\bin\java -classpath %CLASSPATH% zws.repository.teamcenter.TC10Main %WORKING_DIR%
goto done

:usage
echo USAGE: TCQxService [zws-env-tc-10-directory] [working-directory]
echo        working directory should contain context.xml and instruction.xml

:done
echo done