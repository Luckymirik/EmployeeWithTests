package pro.sky.employeewithtests.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.employeewithtests.entity.Employee;
import pro.sky.employeewithtests.service.EmployeeService;

import java.util.Collection;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    @GetMapping("/add")
    public Employee add(@RequestParam String firstName, @RequestParam String lastName,
                        @RequestParam int department, @RequestParam int salary){
        return employeeService.add(firstName, lastName, department, salary);
    }
    @GetMapping("/delete")
    public Employee delete(@RequestParam String firstName,@RequestParam String lastName,
                           @RequestParam int department,@RequestParam int salary){
        return employeeService.delete(firstName, lastName, department, salary);
    }
    @GetMapping("/search")
    public Employee search(@RequestParam String firstName,@RequestParam String lastName,
                           @RequestParam int department,@RequestParam int salary){
        return employeeService.search(firstName, lastName, department, salary);
    }
    @GetMapping
    public Collection<Employee> findAll(){
        return employeeService.findAll();

    }






}