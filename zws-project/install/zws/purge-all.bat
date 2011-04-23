@echo off

setlocal
	echo This will purge sync, recorder, queue, etc...
	set /P ACTION=You sure you want to do this? [y/n] 

	if '%ACTION%'=='y' goto purge
	
	echo.
	echo No action has been taken
	goto finished

:purge
	set ZWS_PURGE=YES

	cd %ZWS_HOME%\bin\synchronization
	call purge-zws-synch-log.bat

	echo.
	cd %ZWS_HOME%\bin\recorder
	call purge.bat

	echo.
	cd %ZWS_HOME%\bin\queue
	call purge.bat

	echo.
	call purge_activity.bat
	echo.

	rem if exist %ZWS_HOME%\data\queue rd /S/Q %ZWS_HOME%\data\queue
	rem if exist %ZWS_HOME%2\data\queue rd /S/Q %ZWS_HOME%2\data\queue
	rem echo creating queue directory
	rem mkdir %ZWS_HOME%\data\queue
	
	if exist %ZWS_HOME%\data-chry\queue rd /S/Q %ZWS_HOME%\data-chry\queue
	if exist %ZWS_HOME%2\data-chry\queue rd /S/Q %ZWS_HOME%2\data-chry\queue
	echo creating chrysalis queue directory
	mkdir %ZWS_HOME%\data-chry\queue

	if exist %ZWS_HOME%\data-chry\temp\chrysalis rd /S/Q %ZWS_HOME%\data-chry\temp\chrysalis
	echo creating chrysalis directory
	mkdir %ZWS_HOME%\data-chry\temp\chrysalis\in
	mkdir %ZWS_HOME%\data-chry\temp\chrysalis\out
	cd %ZWS_HOME%

:finished
	set ZWS_PURGE=
	pause

endlocal