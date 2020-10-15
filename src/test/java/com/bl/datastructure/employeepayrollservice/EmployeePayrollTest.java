package com.bl.datastructure.employeepayrollservice;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class EmployeePayrollTest {

	@Test
	public void givenThreeEmpData_whenWrittenToFile_shouldMatchEmpEntriesCount() {
		EmployeePayrollData[] empsArray = { 
				new EmployeePayrollData(1, "Jeff Bezos", 1020.0),
				new EmployeePayrollData(2, "Bill Bezos", 2020.0), 
				new EmployeePayrollData(3, "Mark Bezos", 3020.0) 
			};
		EmployeePayrollService empPayrollService = new EmployeePayrollService(Arrays.asList(empsArray));
		empPayrollService.writeEmployeePayrollData(EmployeePayrollService.IOService.FILE_IO);
		long entriesCount = empPayrollService.countEntries(EmployeePayrollService.IOService.FILE_IO);
		Assert.assertEquals(3, entriesCount);
	}
}
