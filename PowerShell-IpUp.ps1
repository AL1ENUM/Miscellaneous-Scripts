1..254 | ForEach-Object {Get-WmiObject Win32_PingStatus -Filter "Address='X.X.X.$_' and Timeout=200 and ResolveAddressNames='true' and StatusCode=0" | select ProtocolAddress*}
