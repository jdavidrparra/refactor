package refactor_test;

import refactor_test.Services.*;

import refactor_test.Business.*;

public class Printer {

	public static void main(String[] args) {
		PrinterService printerService = new PrinterService(new ConstructData(), new Paginate());
		printerService.Execute();
	}
}
