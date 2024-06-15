package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
@Component
public class SSHLogMonitorService {

   
    private final MyWebSocketHandler webSocketHandler = new MyWebSocketHandler();

    public void startMonitoring() {
    	Process process ;
    	File fileSecure = new File(VariableStatic.fileLocationLinux);
    	File fileAuth = new File(VariableStatic.fileLocationFreebsd);
    	//File fileWindows = new File(VariableStatic.fileLocationNonUnix);
        try {
        	if (fileSecure.exists()) {
             process = Runtime.getRuntime().exec("tail -f " + VariableStatic.fileLocationLinux);
             System.out.println(VariableStatic.fileLocationLinux);
        	}
        	else if (fileAuth.exists())
        	{
        		process = Runtime.getRuntime().exec("tail -f " + VariableStatic.fileLocationFreebsd);
        		System.out.println(VariableStatic.fileLocationFreebsd);
        	}
        	else {
        		 System.out.println("Sistem Operasi: Bukan Unix/Linux/FreeBSD");
        		 System.out.println(VariableStatic.fileLocationNonUnix);
        		 try {
        	            // Buat perintah untuk menjalankan PowerShell
        	            String[] command = {"powershell.exe", "Get-Content", "-Wait", VariableStatic.fileLocationNonUnix};
        	            process = Runtime.getRuntime().exec(command);
        	            System.out.println(VariableStatic.fileLocationNonUnix);

        	        } catch (IOException e) {
        	        	process = null ;
        	           System.err.println("Error running PowerShell command: " + e.getMessage());
        	        }
        	}
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("sshd") && line.contains("Accepted")) {
                	System.out.println(line);
                    processLogEntry(line);
                    webSocketHandler.sendMessage(line.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processLogEntry(String logEntry) {
        // Implement the logic to extract and handle the relevant information from the log entry
        System.out.println("SSH Access: " + logEntry);
    }
}