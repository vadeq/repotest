setlocal

set PATH=%PATH%;c:\zws\java2\bin;c:\zws\app\ant\bin
set JAVA_HOME=c:\zws\java2

ant -buildfile validate.xml validate_chrysalis
