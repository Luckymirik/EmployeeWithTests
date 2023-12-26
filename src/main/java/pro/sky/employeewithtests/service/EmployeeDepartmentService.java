package pro.sky.employeewithtests.service;

import pro.sky.employeewithtests.entity.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeDepartmentService {
    Employee maxSalaryDepartment(int department);

    Employee minSalaryDepartment(int department);

    List<Employee> getAllInDepartment(int department);

    Map<Integer, List<Employee>> getAllDepartment();

    List<Employee> departmentIndexSalary(int indexSalary, int department);

    Integer sumDepartmentSalary(int department);
}
