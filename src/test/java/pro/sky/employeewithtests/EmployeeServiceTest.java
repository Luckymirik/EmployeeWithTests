package pro.sky.employeewithtests;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.employeewithtests.entity.Employee;
import pro.sky.employeewithtests.exception.EmployeeAlreadyAddedException;
import pro.sky.employeewithtests.exception.EmployeeNotFoundException;
import pro.sky.employeewithtests.service.EmployeeService;
import pro.sky.employeewithtests.service.EmployeeServiceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EmployeeServiceTest {
    private final EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
    @BeforeEach
    public void beforeEach(){
        employeeService.add("Мирослав","Цадкин",1,30_000);
        employeeService.add("Тимур","Ласынов",2,100_000);
        employeeService.add("Сергей","Коростылев",3,150000);
        employeeService.add("Параничев","Александр",3,50000);

    }
    @Test
    public void addTest(){
        Assertions.assertThrows(EmployeeAlreadyAddedException.class,
                ()->employeeService.add("Мирослав","Цадкин",1,30_000));
        Assertions.assertThrows(EmployeeAlreadyAddedException.class,
                ()->employeeService.add("Тимур","Ласынов",2,100_000));
    }
    @Test
    public void deleteTest(){
        Assertions.assertThrows(EmployeeNotFoundException.class,
                ()->employeeService.delete("Ильдар","Макаев",2,22332));
        Assertions.assertThrows(EmployeeNotFoundException.class,
                ()->employeeService.delete("Симаков","Дмитрий",3,232422));
    }
    @Test
    public void searchTest(){
        Employee actual = employeeService.search("Мирослав","Цадкин",1,30_000);
        Employee expected = new Employee("Мирослав","Цадкин",1,30_000);
        Assertions.assertEquals(actual,expected);
    }
    @Test
    public void exceptionSearchTest(){
        Assertions.assertThrows(EmployeeNotFoundException.class,
                ()->employeeService.search("Бабушкина","Бабуля",11,10));
        Assertions.assertThrows(EmployeeNotFoundException.class,
                ()->employeeService.search("Дедушкин","Дедуля",20,1000000000));

    }
    @Test
    public void findAllTest(){
        Assertions.assertNotNull(employeeService.findAll());
    }



}
