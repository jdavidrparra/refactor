package refactor_test;

import refactor_test.Services.*;

import refactor_test.Business.*;

public class Printer {

	public static void main(String[] args) {
		try {
			PrinterService printerService = new PrinterService(new ConstructData(), new Paginate());
			printerService.Execute();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
