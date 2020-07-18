package javabasics;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Estudiantes {

	public static void main(String[] ARGS) {
		
		//variables
		Scanner obtener = new Scanner(System.in);
		List<Float> calificaciones = new ArrayList<>();
		
		float cantidadNotas, nota, sumaNotas = 0f;
		
		
		System.out.print("Ingrese la cantidad de Notas: ");
		cantidadNotas = obtener.nextFloat();
		
			//	6       4    3     	
		for (int i = 1; i <= cantidadNotas; i++) {

			System.out.print("La nota numero: " + i + " : ");
			nota = obtener.nextFloat();

			while (nota < 0 || nota > 100) {
				System.out.println("Numero fuera de rango, Escoje una calificacion mayor que 0 y menor que 100: ");
				System.out.print("vuelve a ingresar la nota numero: " + i + " : \n");
				nota = obtener.nextFloat();
			}
			calificaciones.add(nota);

		} // end for 
		obtener.close();

		//Sumatoria de Calificaciones
		for (float arr : calificaciones) {
			//sumaNotas = sumaNotas + arr;
			sumaNotas += arr;
		}
		
		System.out.print("La nota mayor es: " + Collections.max(calificaciones) + "\n");
		System.out.print("La nota menor es: " + Collections.min(calificaciones) + "\n");

		System.out.print("El promedio es: " + sumaNotas / cantidadNotas + "\n");

		if (sumaNotas / cantidadNotas < 70) {
			System.out.println("No aprobaste");
		} // end if
		else {
			System.out.println("Aprobaste");
		} // end else

	}// end main
}// end class