$ips = ("X.X.X.X","Y.Y.Y.Y","Z.Z.Z.Z");
$ports = (21, 22, 80, 443, 445, 8080, 3389);
foreach ($ip in $ips) {
 if (Test-Connection -BufferSize 32 -Count 1 -ComputerName $ip -Quiet) {
 Write-Host "[+] The "$ip" is Online"
 Write-Host "[!] Port Scan starting ..."
 foreach ($port in $ports) {
 try {
 $socket = New-Object System.Net.Sockets.TcpClient($ip, $port);
 }
 catch {
 };
 if ($socket -eq $null) {
 Write-Host $ip":"$port" - Closed";
 }
 else {
 Write-Host $ip":"$port" - Open";
 $socket = $null;
 }
 }
 }
 else {
 Write-Host "[-] The "$ip" is Down"
 }
}
