package com.automation.services.impl;

import com.automation.constants.EmployeeUrls;
import com.automation.models.Employee;
import com.automation.services.EmployeeService;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;

public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public boolean getAllEmployeeDetails() {
        return given().log().all().when().get(EmployeeUrls.GET_ALL_EMPLOYEES_DETAILS).then().log().all()
                .statusCode(HttpStatus.SC_OK).extract().response().getStatusCode() == 200;

    }

    @Override
    public Employee getEmployeeDetailsById(String employeeId) {
        return given().log().all().pathParam("employeeId", employeeId).when().get(EmployeeUrls.GET_EMPLOYEES_DETAILS)
                .then().log().all().statusCode(HttpStatus.SC_OK).extract().response().jsonPath().getObject("data", Employee.class);
    }

    @Override
    public Response deleteEmployeeById(String employeeId) {
        return given().log().all().pathParam("employeeId", employeeId).when().delete(EmployeeUrls.DELETE_EMPLOYEE).then().log().all()
                .statusCode(HttpStatus.SC_OK).extract().response();
    }
}
