package com.bl.datastructure.employeepayrollservice;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollFileIOService {
	public static String PAYROLL_FILE_NAME = "payroll-file.txt";

	// write data to file
	public void writeData(List<EmployeePayrollData> empPayrollList) {
		StringBuffer empBuffer = new StringBuffer();
		empPayrollList.forEach(employee -> {
			String empDataString = employee.toString().concat("\n");
			empBuffer.append(empDataString);
		});
		try {
			Files.write(Paths.get(PAYROLL_FILE_NAME), empBuffer.toString().getBytes());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	// count the number of entries
	public long countEntries() {
		long entriesNum = 0;
		try {
			entriesNum = Files.lines(new File(PAYROLL_FILE_NAME).toPath()).count();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return entriesNum;
	}

	// print file data
	public void printData() {
		try {
			Files.lines(new File(PAYROLL_FILE_NAME).toPath()).forEach(System.out::println);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	// read data from file
	public List<EmployeePayrollData> readData() {
		List<EmployeePayrollData> empPayrollList = new ArrayList<>();
		try {
			Files.lines(new File(PAYROLL_FILE_NAME).toPath()).map(line -> line.trim()).forEach(line -> {
				line = line.replace(",", "");
				line = line.replace("=", " ");
				String words[] = line.split(" ");
				empPayrollList.add(
						new EmployeePayrollData(Integer.parseInt(words[1]), words[3], Double.parseDouble(words[6])));
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return empPayrollList;
	}
}
