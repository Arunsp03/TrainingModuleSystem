package com.example.worldlineusecase.Service;

import com.example.worldlineusecase.Entity.Employee;
import com.example.worldlineusecase.Repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;
    public void addEmployee(Employee employee)
    {
        employeeRepo.save(employee);
    };
    public List<Employee> getEmployeeDetails(String name)
    {
        return employeeRepo.getEmployeeDetails(name);
    }
   public List<Employee>getEmployeeByModule(String ModuleName)
   {
       return employeeRepo.getEmployeeByModule(ModuleName);
   }
   public int updateProgress(Employee employee)
   {
       String[] moduleStatusArray=employee.getStatus().toArray(new String[0]);
       String[] moduleNameArray =employee.getModuleName().toArray(new String[0]);
       employeeRepo.updateProgressModule(employee.getEmployee_id(),moduleNameArray);
       employeeRepo.updateProgressStatus(employee.getEmployee_id(),moduleStatusArray);
       return 1;
   }
   public List<Employee>getEmployeeById(Long id)
   {
       System.out.println(id);
       return employeeRepo.getEmployeeById(id);
   };
    public int addModuleToEmployee(Employee employee)
    {
        String []moduleName=employee.getModuleName().toArray(new String [0]);
        String []moduleStatus=employee.getStatus().toArray(new String[0]);
        employeeRepo.updateProgressModule(employee.getEmployee_id(),moduleName);
        employeeRepo.updateProgressStatus(employee.getEmployee_id(),moduleStatus);
        return 1;
    }
}
