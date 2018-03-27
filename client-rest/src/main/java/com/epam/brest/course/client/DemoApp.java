package com.epam.brest.course.client;

import com.epam.brest.course.dto.DepartmentAvgSalary;
import com.epam.brest.course.model.Department;
import com.epam.brest.course.service.api.DepartmentService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Collection;
import java.util.Scanner;

/**
 * REST client console application demo.
 */
public class DemoApp {

    private DepartmentService departmentService;

    private Scanner scanner = new Scanner(System.in);

    public DemoApp(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public static void main(String[] args ){
        ClassPathXmlApplicationContext ctx =
                new ClassPathXmlApplicationContext("spring-context.xml");
        DepartmentService departmentService = ctx.getBean(DepartmentService.class);
        DemoApp demoApp = new DemoApp(departmentService);
        demoApp.menu();
    }

    private void menu() {
        System.out.println("|===================================|");
        System.out.println("|MENU SELECTION DEMO                |");
        System.out.println("=====================================");
        System.out.println("|OPTIONS:                           |");
        System.out.println("|       1.Get all departments       |");
        System.out.println("|       2.Get departments by id     |");
        System.out.println("|       3.Add department            |");
        System.out.println("|       4.Exit                      |");
        System.out.println("|===================================|");

        int switchValue = 0;
        while (switchValue != 4) {
            System.out.print("select options: ");
            if (scanner.hasNextInt()) {
                switchValue = scanner.nextInt();
                try {
                    checkValue(switchValue);
                }catch ( ServletDataAccesException e) {
                    System.out.println(" RESPONSE ERR " + e.getMessage());
                }
               } else {
                System.out.println(" Bad value " + scanner.next());
            }

        }
    }

    public void checkValue(int item){
        switch (item){
            case 1:
                getAllDepartments();
                break;
            case 2:
                getDepartmentById();
                break;
            case 3:
                addDepartment();
                break;
            case 4:
                System.out.println("GOOD BYE");
                break;
            default:
                    System.out.println("Invalid selection");
        }
    }

    private void getAllDepartments() {
      Collection<DepartmentAvgSalary> departmentAvgSalaries =
              departmentService.getDepartmentsAvgSalary();
      System.out.println("departments: "  + departmentAvgSalaries);
    }

    /**
     * get by id.
     */
    private void getDepartmentById() {
        System.out.println("Enter department id: ");
        int id;
        if (scanner.hasNextInt() && (id = scanner.nextInt()) > 0) {
            Department department = departmentService.getDepartmentById(id);
            System.out.println("department: " + department);
        } else {
            System.out.println("Bad value: " + scanner.next());
        }
    }

    /**
     * add department
     */
    private void addDepartment() {
        System.out.println(" Enter department name: ");
        String name = scanner.next();

        System.out.println(" Enter department description: ");
        String description = scanner.next();

        Department department = new Department(name,description);
              department =  departmentService.saveDepartment(department);

              System.out.println("department " + department);
    }
}
