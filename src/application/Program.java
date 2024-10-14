package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import model.entities.Employee;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter full file path: ");
		/*
		 Sample file:
		 Maria,maria@gmail.com,3200.00
		 Alex,alex@gmail.com,1900.00
		 Marco,marco@gmail.com,1700.00
		 Bob,bob@gmail.com,3500.00
		 Anna,anna@gmail.com,2800.00
		 */
		String path = sc.nextLine();
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {	
			List<Employee> employees = new ArrayList<>();
			
			System.out.print("Enter salary: ");
			double salary = sc.nextDouble();
			
			String line = br.readLine();
			while (line != null) {
				String[] data = line.split(",");
				Employee e = new Employee(data[0], data[1], Double.parseDouble(data[2]));
				employees.add(e);
				line = br.readLine();
			}
			
			System.out.printf("Email of people whose salary is more than %.2f:%n", salary);
			List<String> emails = employees.stream()
					.filter(e -> e.getSalary() > salary)
					.map(e -> e.getEmail())
					.sorted(String.CASE_INSENSITIVE_ORDER)
					.collect(Collectors.toList());
			for (String e: emails) {
				System.out.println(e);
			}
			
			System.out.print("Sum of salary of people whose name starts with 'M': ");
			Double sum = employees.stream()
					.filter(e -> e.getName().charAt(0) == 'M')
					.map(e -> e.getSalary())
					.reduce(0.0, (x,y) -> x + y);
			System.out.println(String.format("%.2f", sum));
			
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		sc.close();

	}

}
