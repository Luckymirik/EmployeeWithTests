package pro.sky.employeewithtests.service;

import org.springframework.stereotype.Service;
import pro.sky.employeewithtests.entity.Employee;
import pro.sky.employeewithtests.exception.EmployeeAlreadyAddedException;
import pro.sky.employeewithtests.exception.EmployeeNotFoundException;
import pro.sky.employeewithtests.exception.EmployeeStorageIsFullException;
import pro.sky.employeewithtests.exception.InvalidException;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isAlpha;

@Service

public class EmployeeServiceImpl implements EmployeeService {
    private final int SIZE=10;

    private final List<Employee> employees = new ArrayList<>(SIZE);
    @Override
    public Employee add(String firstName, String lastName, int department, int salary){
        if(!validate(firstName,lastName)){
            throw new InvalidException();
        }
        Employee employee = new Employee(firstName,lastName,department,salary);
        if(employees.size()>SIZE){
            throw new EmployeeStorageIsFullException();
        }
        for (Employee e: employees) {
            if(e.equals(employee)){
                throw new EmployeeAlreadyAddedException();
            }

        }
        employees.add(employee);
        return employee;
    }
    @Override
    public Employee delete(String firstName, String lastName, int department, int salary){
        if(!validate(firstName,lastName)){
            throw new InvalidException();
        }
        Employee employee = new Employee(firstName,lastName,department,salary);
        if(employees.contains(employee)) {
            employees.remove(employee);
            return employee;
        }
        throw new EmployeeNotFoundException();
    }
    @Override
    public Employee search(String firstName, String lastName, int department, int salary) {
        if (!validate(firstName,lastName)){
            throw new InvalidException();
        }
        Employee employee = new Employee(firstName, lastName,department,salary);
        if (employees.contains(employee)) {
            return employee;
        }
        throw new EmployeeNotFoundException();
    }


    public List<Employee> findAll() {
        return new  ArrayList<>(employees);
    }
    private boolean validate(String firstName, String lastName){
        return isAlpha(firstName)&& isAlpha(lastName);
    }
}
