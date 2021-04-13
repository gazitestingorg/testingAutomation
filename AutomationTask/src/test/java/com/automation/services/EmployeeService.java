package com.automation.services;

import com.automation.models.Employee;
import io.restassured.response.Response;


public interface EmployeeService {

    boolean getAllEmployeeDetails();

    Employee getEmployeeDetailsById(String employeeId);

    Response deleteEmployeeById(String employeeId);

}
