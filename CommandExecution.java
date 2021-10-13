package al1enum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author al1enum
 *
 */

public class CommandExecution {

	public static void main(String[] args) throws IOException {
	
		
		String command = "powershell.exe Get-Service | Where-Object {$_.Status -eq 'Running'}";
		Process process = Runtime.getRuntime().exec(command);
		process.getOutputStream().close();
		
		String readLine;
		System.out.println("Output : ");
		BufferedReader stdout = new BufferedReader(new InputStreamReader(process.getInputStream()));
		
		while((readLine = stdout.readLine()) != null) {
			
			System.out.println(readLine);
		}
		stdout.close();
		
		System.out.println("Error : ");
		BufferedReader stderr = new BufferedReader(new InputStreamReader(process.getErrorStream()));
		
		while((readLine = stderr.readLine()) != null) {
			
			System.out.println(readLine);
		}
		
		stderr.close();
		
		

	}

}
