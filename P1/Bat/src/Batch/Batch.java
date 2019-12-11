package Batch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Batch {

	private static final String RUTABAT = "helloBatch.bat";

	public static void main(String[] args) throws IOException {

		
		String nombre;
		nombre= JOptionPane.showInputDialog("Dime tu nombre: ");
		leerBat(nombre);

	}

	public static void leerBat(String nombre) throws IOException {
		File file = new File(RUTABAT);
		String linea2;

		FileReader fr = new FileReader(file);

		BufferedReader br = new BufferedReader(fr);
		String linea;
		List command = new ArrayList<String>();
		while ((linea = br.readLine()) != null) {
			command.add(linea);

		}

		Process processBuilder = new ProcessBuilder(file.getAbsolutePath(), nombre).start();

		BufferedReader br2 = new BufferedReader(new InputStreamReader(processBuilder.getInputStream()));

		while ((linea2 = br2.readLine()) != null) {

			System.out.println(linea2);

		}

	}

}
