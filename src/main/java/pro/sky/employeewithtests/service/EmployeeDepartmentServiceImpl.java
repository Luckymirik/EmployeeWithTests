package pro.sky.employeewithtests.service;

import org.springframework.stereotype.Service;
import pro.sky.employeewithtests.entity.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeDepartmentServiceImpl implements EmployeeDepartmentService{
    private final EmployeeServiceImpl employeeService;

    public EmployeeDepartmentServiceImpl(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }
    @Override
    public Employee maxSalaryDepartment(int department){
        return employeeService.findAll().stream()
                .filter(employee -> employee.getDepartment()==department)
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);

    }
    @Override
    public Employee minSalaryDepartment(int department){
        return employeeService.findAll().stream()
                .filter(employee -> employee.getDepartment()==department)
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);

    }
    @Override
    public List<Employee> getAllInDepartment(int department){
        return employeeService.findAll().stream()
                .filter(employee -> employee.getDepartment()==department)
                .collect(Collectors.toList());


    }
    @Override
    public Map<Integer ,List<Employee>> getAllDepartment(){
        return employeeService.findAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
    @Override
    public List<Employee> departmentIndexSalary(int indexSalary, int department) {
        return employeeService.findAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .peek((employee) -> employee.setSalary((employee.getSalary() + employee.getSalary() * indexSalary / 100)))
                .collect(Collectors.toList());
    }
    @Override
    public Integer sumDepartmentSalary(int department){
        return employeeService.findAll().stream()
                .filter(employee -> employee.getDepartment()==department)
                .map(employee -> employee.getSalary())
                .reduce((s1, s2) -> s1 + s2)
                .orElse(null);

    }

    public int sumDepartmentSalaryTwo(int department){
        return employeeService.findAll().stream()
                .mapToInt(Employee::getSalary)
                .sum();

    }
    public double averageDepartmentSalary(int department){
        return employeeService.findAll().stream()
                .filter(employee -> employee.getDepartment()==department)
                .mapToInt(Employee::getSalary)
                .average()
                .orElse(0);
    }
}
