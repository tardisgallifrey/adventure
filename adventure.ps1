$SDIR = Split-Path - Parent $MyInvocation.MyCommand.Path
Start-Process -FilePath "$SDIR\java -jar adventure.jar" -NoNewWindow
Read-Host "Press Enter to Exit"

