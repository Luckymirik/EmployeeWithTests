package pro.sky.employeewithtests;

import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.employeewithtests.entity.Employee;
import pro.sky.employeewithtests.service.EmployeeDepartmentService;
import pro.sky.employeewithtests.service.EmployeeDepartmentServiceImpl;
import pro.sky.employeewithtests.service.EmployeeServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class EmployeeDepartmentServiceTest {
    @Mock
    private EmployeeServiceImpl employeeService;
    @InjectMocks
    private EmployeeDepartmentServiceImpl employeeDepartmentService;
    @BeforeEach
    public void beforeEach() {
        Mockito.when(employeeService.findAll()).thenReturn(
                List.of(
                        new Employee("Мирослав", "Цадкин", 2, 30_000),
                        new Employee("Тимур", "Ласынов", 1, 100_000),
                        new Employee("Сергей", "Коростылев", 3, 150000),
                        new Employee("Параничев", "Александр", 3, 50000)
                )
        );
    }
    @Test
    public void maxSalaryDepartmentTest() {
        Employee expected = new Employee("Сергей", "Коростылев", 3, 150000);
        Employee actual = employeeDepartmentService.maxSalaryDepartment(3);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void maxSalaryDepartmentTestNull(){
        Employee expected = null;
        Employee actual = employeeDepartmentService.maxSalaryDepartment(4);
        Assertions.assertEquals(expected, actual);


    }
   @Test
    public void minSalaryDepartmentTest(){
       Employee expected = new Employee("Параничев", "Александр", 3, 50000);
       Employee actual = employeeDepartmentService.minSalaryDepartment(3);
       Assertions.assertEquals(expected,actual);
   }
   @Test
    public void sumDepartmentSalaryTest(){
        int actual = employeeDepartmentService.sumDepartmentSalary(3);
        int expected = 200000;
        Assertions.assertEquals(actual,expected);
   }
   @Test
    public void departmentIndexedSalaryTest(){
       List<Employee>  actual = employeeDepartmentService.departmentIndexSalary(20,1);
       List<Employee> expected = new ArrayList<>();
       expected.add(new Employee("Мирослав", "Цадкин", 1, 36_000));
       Assertions.assertEquals(actual,expected);

   }
   @Test
    public void getAllInDepartmentTest(){
       List<Employee> actual = employeeDepartmentService.getAllInDepartment(3);
       List<Employee> expected = new ArrayList<>();
       expected.add(new Employee("Сергей", "Коростылев", 3, 150000));
       expected.add(new Employee("Параничев", "Александр", 3, 50000));
       Assertions.assertEquals(actual,expected);
   }
   @Test
    public void getAllDepartmentTest(){
       Map<Integer,List<Employee>> actual = employeeDepartmentService.getAllDepartment();
       Map<Integer,List<Employee>> expected = Map.of(1,
       List.of( new Employee("Тимур", "Ласынов", 1, 100_000)),
        2,
        List.of(
        new Employee("Мирослав", "Цадкин", 2, 30_000)
       ),
       3,
       List.of(new Employee("Сергей", "Коростылев", 3, 150000),
       new Employee("Параничев", "Александр", 3, 50000)));
       Assertions.assertEquals(actual,expected);
   }


}
