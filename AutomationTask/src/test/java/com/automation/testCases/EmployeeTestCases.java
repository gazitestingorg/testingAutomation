package com.automation.testCases;

import com.automation.models.Employee;
import com.automation.services.EmployeeService;
import com.automation.services.impl.EmployeeServiceImpl;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class EmployeeTestCases {

    private EmployeeService employeeService;

    @BeforeClass
    public void setUp() {
        employeeService = new EmployeeServiceImpl();
    }

    @Test(priority = 1)
    public void getAllEmployeeDetailsApiCheck() {
        boolean status = employeeService.getAllEmployeeDetails();
        Assert.assertTrue("Employee Get call status should be 200/Successful", status);
    }

    @Test(priority = 2)
    @Parameters({"employeeId"})
    public void deleteEmployeeByIdApiCheck(String employeeId) {
        Response response = employeeService.deleteEmployeeById(employeeId);
        Assert.assertEquals("Employee Delete call status should be 200/Successful", HttpStatus.SC_OK, response.getStatusCode());
    }

    @Test(priority = 3)
    @Parameters({"employeeId"})
    public void verifyDeleteEmployeeResponseMsg(String employeeId) {
        Response response = employeeService.deleteEmployeeById(employeeId);
        Assert.assertEquals("Employee Delete call status should be Successful", "Successfully! Record has been deleted", response.jsonPath().getString("message"));
    }

    @Test(priority = 4)
    @Parameters({"employeeId"})
    public void verifyEmployeeDetails(String employeeId) {
        Employee employee = employeeService.getEmployeeDetailsById(employeeId);
        System.out.println("Fetched Employee Details are :" + employee.toString());
        Assert.assertNotNull("Employee Details should not be null", employee);
    }

}
