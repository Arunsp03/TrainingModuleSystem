package com.example.worldlineusecase.Service;

import com.example.worldlineusecase.Entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    void addEmployee(Employee employee);
    List<Employee> getEmployeeDetails(String name);
    List<Employee>getEmployeeByModule(String ModuleName);
    int updateProgress(Employee employee);
    List<Employee>getEmployeeById(Long id);
    int addModuleToEmployee(Employee employee);
}
