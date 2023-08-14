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
import java.io.ObjectInputFilter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
@PostMapping("/addTrainingModule")
    public void addTrainingModule(@RequestBody TrainingModule trainingModule,@RequestParam String role) {
    if(role.equals("Admin")) {
        System.out.println(trainingModule.getDescription());
        System.out.println(trainingModule.getList_of_topics());
        trainingModuleService.addTrainingmodule(trainingModule);
    }
}
@DeleteMapping("/deleteModule/{id}")
    public void deleteTrainingModule(@PathVariable Long id,@RequestParam String role)
{
    if(role.equals("Admin")) {
        trainingModuleService.deleteModule(id);
    }
}
@PutMapping("/updateModule")
    public void updateTrainingModule(@RequestBody TrainingModule trainingModule,@RequestParam String role) {
    if (role.equals("Admin")) {
        trainingModuleService.updateModule(trainingModule);
    }
}
@GetMapping("/searchByName/{name}/")
    public List<TrainingModule> searchTrainingModuleByName(@RequestParam String role,@PathVariable String name)
{
    if(role.equals("Admin")) {
        return trainingModuleService.searchTrainingModuleByName(name);
    }
    return null;
}
@GetMapping("/searchByTopic")
public List<TrainingModule> searchTrainingModuleByTopic(@RequestParam String topic)
{
    return trainingModuleService.searchTrainingModuleByTopic(topic);
}
@GetMapping("/getAllEmployeesDetails")
public List<Employee> getAllEmployees(@RequestParam String role){
    if(role.equals("Admin")){
    return employeeRepo.findAll();
    }
    return null;
}
@PostMapping("/addEmployee")
public void addEmployee(@RequestBody Employee employee,@RequestParam String role)
{
    if(role.equals("Admin")){
        employeeService.addEmployee(employee);
    }

}
@GetMapping("/getModules")
    public List<TrainingModule> getTrainingModules(@RequestParam String role)
{
    if(role.equals("Admin"))
    {
        return trainingModuleRepo.findAll();
    }
   return null;
}
@GetMapping("/getModule/{name}")
    public List<TrainingModule> getTrainingModule(@PathVariable String name,@RequestParam String role)
{
    if(role.equals("Admin"))
    {
        return trainingModuleRepo.findByName(name);
    }
    return  null;
}
@GetMapping("/getEmployeeById/{id}")
    public List<Employee>getEmployeeById(@PathVariable Long id,@RequestParam String role)
{
    if(role.equals("Admin")){
        return employeeService.getEmployeeById(id);
    }
    return null;
    }

@GetMapping("/getEmployees/{ModuleName}")
    public List<Employee> getEmployeeByModule(@PathVariable String ModuleName,@RequestParam String role)
{
    if(role.equals("Admin")) {
        return employeeService.getEmployeeByModule(ModuleName);
    }
    return null;
}
@PostMapping("/addModuleToEmployee")
public int addModuleToEmployee(@RequestBody Employee employee,@RequestParam String role)
{
    if(role.equals("Admin")) {
        return employeeService.addModuleToEmployee(employee);
    }
    return -1;
}
@PostMapping("/progressUpdate")
    public int progressUpdate(@RequestBody Employee employee)
{

    return employeeService.updateProgress(employee);
}

    @GetMapping("/pdf/employees/{ModuleName}")
    public void generatePdf(HttpServletResponse response ,@PathVariable String ModuleName,@RequestParam String role) throws DocumentException, IOException {
    if(role.equals("Admin")) {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String currentDateTime = dateFormat.format(new Date());
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerkey, headervalue);

        List<Employee> employeelist = employeeService.getEmployeeByModule(ModuleName);

        PdfGenerator generator = new PdfGenerator();

        generator.generate(response, employeelist);
    }
    }
}

