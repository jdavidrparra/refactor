# Refactor Test

Refactor Test es una solución realizada en java, en que se realiza el análisis y refactorización del código , para un ejercicio de procesamiento de números primos y la escritura de un documento de texto. 


## Autor

**David Rodriguez** - jdavidrparra@gmail.com 


## 1. Parte conceptual

Realice una descripción breve y puntual de los principales problemas identificados en el siguiente código:

```java {.line-numbers}
import java.io.File;
import java.io.FileInputStream;
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
		FileInputStream i = new FileInputStream(file);
		String output = "";
		int data;
		while ((data = i.read()) > 0) {
			output += (char) data;
		}
		return output;
	}

	public String getContentWithoutUnicode() throws IOException {
		FileInputStream i = new FileInputStream(file);
		String output = "";
		int data;
		while ((data = i.read()) > 0) {
			if (data < 0x80) {
				output += (char) data;
			}
		}
		return output;
	}

	public void saveContent(String content) {
		FileOutputStream o = new FileOutputStream(file);
		try {
			for (int i = 0; i < content.length(); i += 1) {
				o.write(content.charAt(i));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

```

Los principales problemas detectados son los siguientes: 

1. No se están haciendo uso correcto de los objetos en memoria de los tipos **FileInputStream** y **FileOutputStream** ya que estos no están siendo cerrados luego de su uso.

2. En los métodos **getContent()** y **getContentWithoutUnicode()** se esta realizando la el uso de **throws** en la definición del método pero no se captura la excepción con un **try-catch**, para evitar el uso del **try-catch** se podría validar si el archivo existe, un ejemplo de esto serial el siguiente: 

 ```java {.line-numbers}
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

```

3. En el método **saveContent(String content)** no se esta utilizando **throws**

## 2. Refactor de código

Haga refactor del siguiente código (nota: es código en java y de un buen refactor puede salir más de una clase).

Código entregado: 
```java {.line-numbers}
public class Printer {
	public static void main(String[] args) {
		final int M = 1000;
		final int RR = 50;
		final int CC = 4;
		final int ORDMAX = 30;
		int P[] = new int[M + 1];
		int PAGENUMBER;
		int PAGEOFFSET;
		int ROWOFFSET;
		int C;
		int J;
		int K;
		boolean JPRIME;
		int ORD;
		int SQUARE;
		int N = 0;
		int MULT[] = new int[ORDMAX + 1];
		J = 1;
		K = 1;
		P[1] = 2;
		ORD = 2;
		SQUARE = 9;
		while (K < M) {
			do {
				J += 2;
				if (J == SQUARE) {
					ORD++;
					SQUARE = P[ORD] * P[ORD];
					MULT[ORD - 1] = J;
				}
				N = 2;
				JPRIME = true;
				while (N < ORD && JPRIME) {
					while (MULT[N] < J)
						MULT[N] += P[N] + P[N];
					if (MULT[N] == J)
						JPRIME = false;
					N++;
				}
			} while (!JPRIME);
			K++;
			P[K] = J;
		}
		PAGENUMBER = 1;
		PAGEOFFSET = 1;
		while (PAGEOFFSET <= M) {
			System.out.print("The First ");
			System.out.print(Integer.toString(M));
			System.out.print(" Prime Numbers === Page ");
			System.out.print(Integer.toString(PAGENUMBER));
			System.out.println("\n");
			for (ROWOFFSET = PAGEOFFSET; ROWOFFSET <= PAGEOFFSET + RR - 1; ROWOFFSET++) {
				for (C = 0; C <= CC - 1; C++)
					if (ROWOFFSET + C * RR <= M)
						System.out.printf("%10d", P[ROWOFFSET + C * RR]);
				System.out.println();
			}
			System.out.println("\f");
			PAGENUMBER++;
			PAGEOFFSET += RR * CC;
		}
	}
}
```

Para el refactor del código anterior se tuvieron en cuenta los principios **SOLID** aplicando así los siguientes: 
1. Responsabilidad Única
2. Sustitución de Liskov
3. Segregación de interfaz
4. Inversión de Dependencia

El lanzador de la funcionalidad se encuentra en la clase **Printer.java**,  adicionalmente se dejo la funcionalidad de ingreso de datos  para evitar variables fijas en donde se debe ingresar el valor de los números primos a generar así como el numero de registros por pagina y las columnas a mostrar.
