package refactor_test;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;

import refactor_test.Parser;

public class Main {
	public static void main(String[] args) {
		Parser parser = new Parser();
		File f;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
			Date date = new Date(System.currentTimeMillis());

			f = new File("./src/File1.txt");
			if (f.exists()) {
				parser.setFile(f);
				String newText = parser.getContentWithoutUnicode();
				System.out.println();
				newText += "\r\nHola mundó " + formatter.format(date);
				parser.saveContent(newText);
				System.out.println(parser.getContentWithoutUnicode());
			} else {
				System.out.println("the file not exist");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.exit(0);
	}
}
