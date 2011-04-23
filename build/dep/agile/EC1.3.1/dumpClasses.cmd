@echo off
setlocal
CALL setPAMEnv.cmd
%JAVA_HOME%\bin\java -cp %CLASSPATH% org.apache.tools.ant.launch.Launcher -verbose -buildfile pam-build.xml generate_dumpclasses_csv
PAUSE
endlocal