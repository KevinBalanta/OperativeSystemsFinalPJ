package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.model.ProcessSH;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellNotAvailableException;
import com.profesorfalken.jpowershell.PowerShellResponse;

@Service
public class PowerSHService implements IPowerSHService{
	
	private PowerShell powerShell = null;
	
    
	
	
	/**
	 * gets a list of all processes running in the machine
	 */
	@Override
	public List<ProcessSH> getProcesses() {
		// TODO Auto-generated method stub
		//PowerShell powerShell = null;
		List<ProcessSH> processes = new ArrayList<ProcessSH>();
		try {
		powerShell = PowerShell.openSession();
		do {
		PowerShellResponse response = null;
		Map<String, String> myConfig = new HashMap<>();
		myConfig.put("maxWait", "60000");
		
		response = powerShell.configuration(myConfig).executeCommand("Get-Process | select Name, Id");
		
		String[] lines = response.getCommandOutput().split(System.getProperty("line.separator"));
		int i = 0;
		for (String string : lines) {
			i++;
			String[] linese = string.split("[ ]+");
			//System.out.println("linese : "+ string);
			if(i>3) {
				ProcessSH process = null;
				if(linese.length > 2) {
					process = new ProcessSH(linese[0]+" "+linese[1], linese[linese.length-1]);
					
				}else if(linese.length == 2){
					 process = new ProcessSH(linese[0],linese[1]);

				}
				if(process != null) {
						processes.add(process);
				}
			}
			
		}
		}while(processes.isEmpty());
		}catch (Exception ex) {
			ex.printStackTrace();
			System.err.println( ex);
		} 
		finally {
			if (powerShell != null) {
				powerShell.close();
			}
		}
		
		return processes;
	}

	/**
	 * stop the process with specific id 
	 */
	@Override
	public void stopProcess(String id) {
		// TODO Auto-generated method stub
		//PowerShell powerShell = null;
		Map<String, String> myConfig = new HashMap<>();
		myConfig.put("maxWait", "60000");
		
		try {
			powerShell = PowerShell.openSession();
			
			powerShell.configuration(myConfig).executeCommand("Stop-Process -Id "+id);
		}
		catch (PowerShellNotAvailableException ex) {
			System.err.println( ex);
		} 
		finally {
			if (powerShell != null) {
				powerShell.close();
			}
		}
	}

}
