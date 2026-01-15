[Setup]
AppName=My Kiosk Project
AppVersion=1.0
DefaultDirName={autopf}\MyKioskProject
DefaultGroupName=My Kiosk Project
OutputDir=.
OutputBaseFilename=MyKioskSetup
Compression=lzma
SolidCompression=yes
ArchitecturesInstallIn64BitMode=x64

[Files]
; IMPORTANT: You must build the project first to generate this JAR file.
; We rename it to kiosk.jar so the bat file works consistently.
Source: "backend\target\kiosk-0.0.1-SNAPSHOT.jar"; DestDir: "{app}"; DestName: "kiosk.jar"
; Bundled JRE (copies the entire 'jre' folder to the installation directory)
Source: "jre\*"; DestDir: "{app}\jre"; Flags: ignoreversion recursesubdirs createallsubdirs
; The launcher script
Source: "Run Kiosk.bat"; DestDir: "{app}"

[Icons]
Name: "{group}\My Kiosk Project"; Filename: "{app}\Run Kiosk.bat"
Name: "{commondesktop}\My Kiosk Project"; Filename: "{app}\Run Kiosk.bat"

[Run]
Filename: "{app}\Run Kiosk.bat"; Description: "Launch My Kiosk Project"; Flags: nowait postinstall skipifsilent
