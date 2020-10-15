package com.bl.datastructure.employeepayrollservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeePayrollService {
	public enum IOService {
		CONSOLE_IO, FILE_IO, DB_IO, REST_IO
	}
	private List<EmployeePayrollData> empPayrollList;

	public EmployeePayrollService() { }
	
	// Parameterized constructor
	public EmployeePayrollService(List<EmployeePayrollData> empPayrollList) {
		this.empPayrollList = empPayrollList;
	}

	public static void main(String[] args) {
		Scanner consoleInput = new Scanner(System.in);
		ArrayList<EmployeePayrollData> employeePayrollList = new ArrayList<>();
		EmployeePayrollService empPayrollService = new EmployeePayrollService(employeePayrollList);
		empPayrollService.readEmpPayrollDataFromConsole(consoleInput);
		empPayrollService.writeToConsole();
	}

	// read data from console
	private void readEmpPayrollDataFromConsole(Scanner consoleInputReader) {
		System.out.println("Enter emp id: ");
		int id = consoleInputReader.nextInt();
		System.out.println("Enter emp name: ");
		String name = consoleInputReader.next();
		System.out.println("Enter emp salary: ");
		double salary = consoleInputReader.nextDouble();
		empPayrollList.add(new EmployeePayrollData(id, name, salary));
	}

	// read from file
	public long readEmployeePayrollData(IOService ioService) {
		if (ioService.equals(IOService.FILE_IO)) {
			this.empPayrollList = new EmployeePayrollFileIOService().readData();
		}
		return empPayrollList.size();
	}
	
	// write data to console
	private void writeToConsole() {
		System.out.println("\nWriting emp payroll roaster to console\n" + empPayrollList);
	}

	// write to file using File IO
	public void writeEmployeePayrollData(IOService ioService) {
		if (ioService.equals(IOService.FILE_IO)) {
			new EmployeePayrollFileIOService().writeData(empPayrollList);
		}
	}

	// count the entries
	public long countEntries(IOService ioService) {
		if (ioService.equals(IOService.FILE_IO))
			return new EmployeePayrollFileIOService().countEntries();
		return 0;
	}

	// print file data
	public void printData(IOService ioService) {
		if (ioService.equals(IOService.FILE_IO))
			new EmployeePayrollFileIOService().printData();
	}
}
