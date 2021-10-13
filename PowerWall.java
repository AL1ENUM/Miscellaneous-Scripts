package powerwall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PowerWall {

	public static void main(String[] args) throws IOException {

		String command = "powershell.exe  Get-NetFirewallRule -Action Allow -Enabled True -Direction Inbound |\r\n"
				+ "Format-Table -Property Name,\r\n"
				+ "@{Name='Protocol';Expression={($PSItem | Get-NetFirewallPortFilter).Protocol}},\r\n"
				+ "@{Name='LocalPort';Expression={($PSItem | Get-NetFirewallPortFilter).LocalPort}},\r\n"
				+ "@{Name='RemotePort';Expression={($PSItem | Get-NetFirewallPortFilter).RemotePort}},\r\n"
				+ "@{Name='RemoteAddress';Expression={($PSItem | Get-NetFirewallAddressFilter).RemoteAddress}},\r\n"
				+ "Enabled,Profile,Direction,Action";
		Process powerShellProcess = Runtime.getRuntime().exec(command);
		powerShellProcess.getOutputStream().close();
		String line;
		System.out.println("Standard Output:");
		BufferedReader stdout = new BufferedReader(new InputStreamReader(powerShellProcess.getInputStream()));
		while ((line = stdout.readLine()) != null) {
			System.out.println(line);
		}
		stdout.close();
		System.out.println("Standard Error:");
		BufferedReader stderr = new BufferedReader(new InputStreamReader(powerShellProcess.getErrorStream()));
		while ((line = stderr.readLine()) != null) {
			System.out.println(line);
		}
		stderr.close();
		System.out.println("Done");

	}

}
