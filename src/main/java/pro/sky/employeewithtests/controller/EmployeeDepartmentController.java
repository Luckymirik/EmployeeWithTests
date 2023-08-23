package pro.sky.employeewithtests.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.employeewithtests.entity.Employee;
import pro.sky.employeewithtests.service.EmployeeDepartmentServiceImpl;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class EmployeeDepartmentController {
    private final EmployeeDepartmentServiceImpl departmentService;
    public EmployeeDepartmentController(EmployeeDepartmentServiceImpl departmentService) {
        this.departmentService = departmentService;
    }
    @GetMapping(value = "/{id}/employees")
    public List<Employee>getAllInDepartment(@PathVariable int id){
        return departmentService.getAllInDepartment(id);
    }
    @GetMapping(value = "/{id}/salary/max")
    public Employee maxSalaryDepartment(@PathVariable int id){
        return departmentService.maxSalaryDepartment(id);
    }
    @GetMapping(value = "/{id}/salary/min")
    public Employee minSalaryDepartment(@PathVariable int id){
        return departmentService.minSalaryDepartment(id);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> getAllDepartment(){
        return departmentService.getAllDepartment();
    }
    @GetMapping("/index")
    public List<Employee> departmentIndexSalary(@RequestParam int indexSalary, @RequestParam int departmentId){
        return departmentService.departmentIndexSalary(indexSalary,departmentId);
    }
    @GetMapping(value = "/{id}/salary/sum")
    public String departmentIndexSalary( @PathVariable int id){
        return "Сумма зарплат в " + id + " отделе " + departmentService.sumDepartmentSalary(id);
    }

}
