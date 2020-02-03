package refactor_test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * This class is thread safe.
 */
public class Parser {
	private File file;

	public synchronized void setFile(File f) {
		file = f;
	}

	public synchronized File getFile() {
		return file;
	}

	public String getContent() throws IOException {
		String output = "";
		int data;
		try {
			FileInputStream i = new FileInputStream(file);
			while ((data = i.read()) > 0) {
				output += (char) data;
			}
			i.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return output;
	}

	public String getContentWithoutUnicode() throws IOException 
	{
		int size = 0x80;
		String output = "";
		int data;
		try 
		{
			FileInputStream i = new FileInputStream(file);
			while ((data = i.read()) > 0) 
			{
				if (data < size) {
					output += (char) data;
				}
			}
			i.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return output;
	}

	public void saveContent(String content) throws FileNotFoundException {
		try {
			FileOutputStream o = new FileOutputStream(file);
			for (int i = 0; i < content.length(); i += 1) {
				o.write(content.charAt(i));
			}
			o.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
