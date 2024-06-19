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
		Process process;
		File fileSecure = new File(VariableStatic.fileLocationLinux);
		File fileAuth = new File(VariableStatic.fileLocationFreebsd);
		// File fileWindows = new File(VariableStatic.fileLocationNonUnix);
		try {
			if (fileSecure.exists()) {
				process = Runtime.getRuntime().exec("tail -f " + VariableStatic.fileLocationLinux);
				System.out.println(VariableStatic.fileLocationLinux);
			} else if (fileAuth.exists()) {
				process = Runtime.getRuntime().exec("tail -f " + VariableStatic.fileLocationFreebsd);
				System.out.println(VariableStatic.fileLocationFreebsd);
			} else {
				System.out.println("Sistem Operasi: Bukan Unix/Linux/FreeBSD");
				System.out.println(VariableStatic.fileLocationNonUnix);
				try {
					// Buat perintah untuk menjalankan PowerShell
					String[] command = { "powershell.exe", "Get-Content", "-Wait", VariableStatic.fileLocationNonUnix };
					process = Runtime.getRuntime().exec(command);
					System.out.println(VariableStatic.fileLocationNonUnix);

				} catch (IOException e) {
					process = null;
					System.err.println("Error running PowerShell command: " + e.getMessage());
				}
			}
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			String line;
			String messagex = "";
			while ((line = reader.readLine()) != null) {
				if (line.contains("WEBSOCKET_PURCHASE")) {
					System.out.println(line);
					processLogEntry(line);

					String[] parts = line.split("_");

					if (parts.length >= 3) {
						String result = parts[2];
						messagex = String.format("""
														{"invoice" : "%s" , "code" : 0, "apps" : "live_agratek"}
								""", result);
					} else {
						System.out.println("Input tidak sesuai format.");
					}

					webSocketHandler.sendMessage(messagex);
				}

				if (line.contains("Hasil_notif")) {
					System.out.println(line);
					processLogEntry(line);

					String[] parts = line.split("_");

					if (parts.length >= 3) {
						String result = parts[3];
						String[] partsresult = result.split(" ");
						String kataSebelumSpasi = partsresult[0];

						/*
						 * String dataAntaraSpasi = ""; if (parts.length > 2) { // Pastikan ada spasi
						 * pertama dan kedua StringBuilder sb = new StringBuilder(); for (int i = 1; i <
						 * parts.length - 1; i++) { sb.append(parts[i]).append(" "); } dataAntaraSpasi =
						 * sb.toString().trim(); }
						 * System.out.println("Data di antara spasi pertama dan spasi kedua: " +
						 * dataAntaraSpasi);
						 * 
						 */
						messagex = String.format("""
														{"invoice" : "%s" , "code" : 1, "apps" : "live_agratek"}
								""", kataSebelumSpasi);
					} else {
						System.out.println("Input tidak sesuai format.");
					}

					webSocketHandler.sendMessage(messagex);
				}
				if (line.contains("Biller network unrecognized")) {
					System.out.println(line);
					processLogEntry(line);
					String modifiedString = line.replaceAll("[\'\"{}]", "");
					messagex = String.format("""
							{"error" : "%s" }
							
							""",modifiedString);
					webSocketHandler.sendMessage(messagex);
				}
				
				if (line.contains("ERROR_SETTING_HARGA")) {
					System.out.println(line);
					processLogEntry(line);
					String keyword = "ERROR_SETTING_HARGA";
					int startIndex = line.indexOf(keyword);
					
			            // Ambil substring dari posisi awal hingga akhir string
			            String result = line.substring(startIndex);
			           
			       
					messagex = String.format("""
							{"error" : "%s" }
							
							""",result);
					webSocketHandler.sendMessage(messagex);
				}
					
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void processLogEntry(String logEntry) {
		// Implement the logic to extract and handle the relevant information from the
		// log entry
		System.out.println("SSH Access: " + logEntry);
	}
}