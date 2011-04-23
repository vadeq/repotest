; Set the compression mechanism first.
; As of NSIS 2.07, solid compression which makes installer about 1MB smaller
; is no longer the default, so use the /SOLID switch.
; This unfortunately is unknown to NSIS prior to 2.07 and creates an error.
; So if you get an error here, please update to at least NSIS 2.07!
SetCompressor /SOLID lzma 

# include files
	!include WinMessages.nsh
	!include FileFunc.nsh
	!insertmacro GetDrives
	!insertmacro GetSize
	!insertmacro DriveSpace
	!include nsDialogs.nsh
	!include LogicLib.nsh



# Application Constants
	!define APPNAME "Design State"
	!define FILES	"@FILES@"

# ShowInstDetails show
	AllowRootDirInstall true
 
# "Global" Variables
	Var Dialog
	Var DriveSize
	Var DriveList
	Var SelDrive
	Var MinimumSize
	Var MinimumSizeCaption
	
	;XPStyle on

	Name '${APPNAME}'
	Icon 'setup.ico'
	OutFile '@INSTALLER_NAME@'

	Page Custom ChooseDrive 
	Page InstFiles
 	
Section
	SetOutPath $SelDrive\zws
	File /r ${FILES}
	SetDetailsView show
SectionEnd

Function ChooseDrive
         
		nsDialogs::Create /NOUNLOAD 1018
		Pop $Dialog

		${If} $Dialog == error
			Abort
		${EndIf}
	
	# Set up static boring stuff	
		${NSD_CreateLabel} 5 5 -10 17 "Select Installation drive:"
		Pop $0
		
		${NSD_CreateLabel} 5 175 -10 185 "Space available:  "
		Pop $DriveSize
		
		#${GetSize} ${FILES} "/S=K /G=1" $MinimumSize $1 $2
 		#${NSD_CreateLabel} 5 190 -10 200 "Space required: $MinimumSize Kb"
		#Pop $MinimumSizeCaption
		
	# Create a drive list box	
		${NSD_CreateListBox} 10 25 -10 100 $DriveList
		Pop $DriveList
		
	# Set up a callback to handle user clicks	
		GetFunctionAddress $2 SelectDriveLocation
		nsDialogs::OnChange /NOUNLOAD $DriveList $2
		
	# Add the drives to the list box	
		StrCpy $R2 0
		StrCpy $R0 ''
		${GetDrives} "HDD+NET" GetDrivesCallBack		
		
	# Select the first item in the drive list box
		SendMessage $DriveList ${LB_SETCURSEL} 0 0
		call SelectDriveLocation
		
	# Show the dialog	
		nsDialogs::Show
		
FunctionEnd
 
Function GetDrivesCallBack

	# This is a callback method invoked as a result of the getdrives command
	# If sufficient space exists on the drive, add it to the list box	
		${DriveSpace} "$9" "/D=F /S=K" $R4
		IntCmp $R4 '$MinimumSize' end end add

		add:
			 SendMessage $DriveList ${LB_ADDSTRING} 0 "STR:$9"	
		end:
      
	 Push $0
	 
FunctionEnd
 
Function SelectDriveLocation
		
	# get the selected drive	
		SendMessage $DriveList ${LB_GETCURSEL} 0 0 $1
		System::Call user32::SendMessage(i$DriveList,i${LB_GETTEXT},ir1,t.r1)
		StrCpy $SelDrive $1
		
	# get the drive size
		${DriveSpace} "$SelDrive" "/D=F /S=K" $R4
		
	# update the label
		SendMessage $DriveSize ${WM_SETTEXT} 0 "STR:Space available:  $R4 Kb"
		#SendMessage $MinimumSizeCaption ${WM_SETTEXT} 0 "STR:Space required: $MinimumSize Kb"
	
		Push $0
FunctionEnd

