@echo off

//clean:
// delete the config directory
// delete the war file

//deploy zws configs and overwrites
// copy zws to /zws

//deploy war
// copy WAR file to tmp loczation
// unpack the war file
// overwrite custom jsp
// repackage war file
// deploy new war file to /zws/designstate/webapp

@echo off
set JAVA_HOME=\zws\java2
set path=\zws\app\ant\bin
ant -buildfile deploy.xml get-download-script
pause