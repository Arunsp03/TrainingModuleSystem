package com.example.worldlineusecase.Repository;

import com.example.worldlineusecase.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

public interface EmployeeRepo extends JpaRepository<Employee,Long> {
    @Query(
            nativeQuery = true,
            value = "select * from employee ")

    public List<Employee> getEmployeeDetails(String name);

    @Query(
            nativeQuery = true,
            value="SELECT * FROM employee WHERE module_name @> CAST(ARRAY[:ModuleName] AS character varying[])"
    )
    public List<Employee> getEmployeeByModule(String ModuleName);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "UPDATE employee SET status = :moduleStatusArray WHERE employee_id = :emp_id"
    )
    public void updateProgressStatus(@Param("emp_id") Long emp_id, @Param("moduleStatusArray") String[] moduleStatusArray);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "UPDATE employee SET module_name = :moduleNameArray WHERE employee_id = :emp_id"
    )
    public void updateProgressModule(@Param("emp_id") Long emp_id, @Param("moduleNameArray") String[] moduleNameArray);

    @Query(
            nativeQuery = true,
            value = "select * from employee where employee_id=:id"
    )
    public List<Employee> getEmployeeById(@Param("id") Long id);
}

