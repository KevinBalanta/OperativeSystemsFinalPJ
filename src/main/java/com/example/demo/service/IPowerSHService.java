package com.example.demo.service;

import java.util.List;

import com.example.demo.model.ProcessSH;

public interface IPowerSHService {
	
	public List<ProcessSH> getProcesses();
	
	public void stopProcess(String id);

}
