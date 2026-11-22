package EmployeePaginationPractice;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EmployeePaginationPractice employeePaginationPractice = new EmployeePaginationPractice();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter page size: ");
        int pageSize = scanner.nextInt();
        employeePaginationPractice.getTotalPages(pageSize);

    }
}
