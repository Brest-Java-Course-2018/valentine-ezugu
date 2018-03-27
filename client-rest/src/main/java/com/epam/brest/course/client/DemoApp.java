package com.epam.brest.course.client;

import com.epam.brest.course.dto.DepartmentAvgSalary;
import com.epam.brest.course.model.Department;
import com.epam.brest.course.service.DepartmentService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Collection;
import java.util.Scanner;

/**
 * REST client console application demo.
 */
public class DemoApp {
    /**
     * variable.
     */
    private static final int ONE = 1;
    /**
     * variable.
     */
    private static final int TWO = 2;
    /**
     * variable.
     */
    private static final int THREE = 3;
    /**
     * variable.
     */
    private static final int FOUR = 4;
    /**
     * variable.
     */
    private static final int ZERO = 0;

    /**
     * service.
     */
    private DepartmentService departmentService;

    /**
     * scanner.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     *
     * @param departmentService1 injected.
     */
    public DemoApp(final DepartmentService departmentService1) {
        this.departmentService = departmentService1;
    }

    /**
     *
     * @param args main.
     */
    public static void main(final String[] args) {
        ClassPathXmlApplicationContext ctx =
                new ClassPathXmlApplicationContext("spring-context.xml");
        DepartmentService departmentService =
                ctx.getBean(DepartmentService.class);
        DemoApp demoApp = new DemoApp(departmentService);
        demoApp.menu();
    }

    /**
     * menu.
     */
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

        int switchValue = ZERO;
        while (switchValue != FOUR) {
            System.out.print("select options: ");
            if (scanner.hasNextInt()) {
                switchValue = scanner.nextInt();
                try {
                    checkValue(switchValue);
                } catch (ServletDataAccesException e) {
                    System.out.println(" RESPONSE ERR " + e.getMessage());
                }
               } else {
                System.out.println(" Bad value " + scanner.next());
            }

        }
    }

    /**
     * @param item for switch.
     */
    private void checkValue(final int item) {
        switch (item) {
            case ONE:
                getAllDepartments();
                break;
            case TWO:
                getDepartmentById();
                break;
            case THREE:
                addDepartment();
                break;
            case FOUR:
                System.out.println("GOOD BYE");
                break;
            default:
                    System.out.println("Invalid selection");
        }
    }

    /**
     * list.
     */
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
     * add department.
     */
    private void addDepartment() {
        System.out.println(" Enter department name: ");
        String name = scanner.next();

        System.out.println(" Enter department description: ");
        String description = scanner.next();

        System.out.println(" Enter head of department: ");
        String headOfDepartment = scanner.next();

        Department department =
                new Department(name, description, headOfDepartment);
              department =  departmentService.saveDepartment(department);

              System.out.println("department " + department);
    }
}
