package com.example.worldlineusecase.Controller;
import com.example.worldlineusecase.Entity.Employee;
import com.example.worldlineusecase.Entity.TrainingModule;
import com.example.worldlineusecase.Repository.EmployeeRepo;
import com.example.worldlineusecase.Repository.TrainingModuleRepo;
import com.example.worldlineusecase.Service.EmployeeService;
import com.example.worldlineusecase.Service.TrainingModuleService;
import com.example.worldlineusecase.Utility.PdfGenerator;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@RestController
public class AdminController {
    @Autowired
    private TrainingModuleService trainingModuleService;
    @Autowired
    private TrainingModuleRepo trainingModuleRepo;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeRepo employeeRepo;
@PostMapping("/addModule/{role}")
    public void addTrainingModule(@RequestBody TrainingModule trainingModule,@PathVariable String role) {
    if(role.equals("admin")) {
        System.out.println(trainingModule.getDescription());
        System.out.println(trainingModule.getList_of_topics());
        trainingModuleService.addTrainingmodule(trainingModule);
    }
    else{
        return;
    }
}
@DeleteMapping("/deleteModule/{id}")
    public void deleteTrainingModule(@PathVariable Long id)
{
    trainingModuleService.deleteModule(id);
}
@PutMapping("/updateModule")
    public void updateTrainingModule(@RequestBody TrainingModule trainingModule)
{
    trainingModuleService.updateModule(trainingModule);
}
@GetMapping("/searchByName")
    public List<TrainingModule> searchTrainingModuleByName(@RequestParam String name)
{
   return trainingModuleService.searchTrainingModuleByName(name);
}
@GetMapping("/searchByTopic")
public List<TrainingModule> searchTrainingModuleByTopic(@RequestParam String topic)
{
    return trainingModuleService.searchTrainingModuleByTopic(topic);
}
@GetMapping("/getAllEmployeesDetails")
public List<Employee> getAllEmployees(){

    return employeeRepo.findAll();
}
@PostMapping("/addEmployee")
public void addEmployee(@RequestBody Employee employee)
{
    if(employee.getCanEdit()) {
        employeeService.addEmployee(employee);
    }
}
@GetMapping("/getModules")
    public List<TrainingModule> getTrainingModules()
{
    return trainingModuleRepo.findAll();
}
@GetMapping("/getModule/{name}")
    public List<TrainingModule> getTrainingModule(@PathVariable String name)
{

    return trainingModuleRepo.findByName(name);
}
@GetMapping("/getEmployeeById/{id}")
    public List<Employee>getEmployeeById(@PathVariable Long id)
{
        return employeeService.getEmployeeById(id);
    }

@GetMapping("/getEmployees/{ModuleName}")
    public List<Employee> getEmployeeByModule(@PathVariable String ModuleName)
{
    return employeeService.getEmployeeByModule(ModuleName);
}
@PostMapping("/addModule")
public int addModuleToEmployee(@RequestBody Employee employee)
{
    return employeeService.addModuleToEmployee(employee);
}
@PostMapping("/progressUpdate")
    public int progressUpdate(@RequestBody Employee employee)
{

    return employeeService.updateProgress(employee);
}

    @GetMapping("/pdf/employees/{ModuleName}")
    public void generatePdf(HttpServletResponse response ,@PathVariable String ModuleName) throws DocumentException, IOException {

        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String currentDateTime = dateFormat.format(new Date());
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerkey, headervalue);

        List<Employee> employeelist = employeeService.getEmployeeByModule(ModuleName);

        PdfGenerator generator = new PdfGenerator();

        generator.generate(response,employeelist);

    }
}

