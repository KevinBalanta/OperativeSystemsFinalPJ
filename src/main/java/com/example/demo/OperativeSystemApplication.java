package com.example.demo;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.model.ProcessSH;
import com.example.demo.service.PowerSHService;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;

@SpringBootApplication
public class OperativeSystemApplication {
	
	
	public static void main(String[] args) {
		SpringApplication.run(OperativeSystemApplication.class, args);
//		PowerSHService pservice = new PowerSHService();
//		List<ProcessSH> process = pservice.getProcesses();
//		process.stream().forEach(proce -> {
//			System.out.println(proce.getName());
//		});
		//execute();
	}
	
	
	public static void execute() {
		PowerShellResponse response = PowerShell.executeSingleCommand("Get-Process | select Name, Id");
		// System.out.println("List Processes:" +
		// response.getCommandOutput().toString());

		String[] lines = response.getCommandOutput().split(System.getProperty("line.separator"));
		int i = 0;
		
		for (String string : lines) {
			String[] linese = string.split("[ ]+");
			i++;
			int j = 0;
			for (String string2 : linese) {
				if (i > 3) {
					System.out.println("linee "+j +" "+ string2);
					j++;
				}
			}
		}

	}

}
