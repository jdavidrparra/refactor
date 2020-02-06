package refactor_test.Services;

import java.util.Scanner;

import refactor_test.Interfaces.*;

public class PrinterService {
	
	private IConstructData _constructData;
	private IPaginate _paginate;
	
	public PrinterService(IConstructData constructData, IPaginate paginate)
	{
		_constructData = constructData;
		_paginate = paginate;
	}
	
	public void Execute() {
		java.util.Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the number of prime values to generate:");
		final int M = Integer.parseInt(scanner.nextLine());
		System.out.println("Enter the number of records per page:");
		final int rowsPerPage = Integer.parseInt(scanner.nextLine());
		System.out.println("Enter the number of columns per page:");
		final int colsNumber = Integer.parseInt(scanner.nextLine());
		scanner.close();
		int P[] = new int[M + 1];
		_constructData.Generate(M, P);
		_paginate.printPaginateData(M, P, rowsPerPage, colsNumber);
	}
}