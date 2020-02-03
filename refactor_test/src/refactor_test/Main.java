package refactor_test;
import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;

import refactor_test.Parser;

public class Main 
{
	public static void main(String[] args) 
	{
		Parser parser;
		File f;
		try 
		{
			SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
			Date date = new Date(System.currentTimeMillis());
			parser = new Parser();
			f = new File("D:\\WorkSpace\\refactor\\refactor_test\\src\\File1.txt");
			parser.setFile(f);
			String newText = parser.getContentWithoutUnicode();
			System.out.println();
			newText += "\r\nHola mundó " + formatter.format(date); 
			parser.saveContent(newText);
			System.out.println(parser.getContentWithoutUnicode());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		 System.exit(0);
	}
}
