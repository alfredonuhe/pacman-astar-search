import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Preparativos {

	public static void transcriptFile() throws FileNotFoundException {
		// Leemos el archivo y lo almacenaos en un array dinámico

		int dimensions[] = new int[2];
		dimensions[0] = 0;
		dimensions[1] = 0;

		Scanner sc = new Scanner(new File("./src/Pacman_map.txt"));
		List<String> lines = new ArrayList<String>();

		while (sc.hasNextLine()) {
			lines.add(sc.nextLine());
		}

		// Traspasamos los datos del array dinámico a uno estático
		System.out.print("Creando array...");
		String[] arr = lines.toArray(new String[0]);
		System.out.println("array creado");

		// Traspasamos los datos del array estático a una matriz de valores
		int auxColums = 0;
		// Loop que recoge las dimensiones del archivo
		for (int i = 0; i < arr.length; i++) {
			dimensions[0]++;
			for (int j = 0; j < arr[i].length(); j++) {
				auxColums++;
			}
			if (auxColums > dimensions[1]) {
				dimensions[1] = auxColums;
			}
			auxColums = 0;
		}
		System.out.println("filas: "+dimensions[0]+" columnas: "+dimensions[1]);

		// Declaramos la matriz y traspasamos el array de strings a la matriz
		Ejecutar.matrixMap = new char[dimensions[0]][dimensions[1]];
		Ejecutar.auxMatrixMap = new char[dimensions[0]][dimensions[1]];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length(); j++) {
				Ejecutar.matrixMap[i] = arr[i].toCharArray();
				Ejecutar.auxMatrixMap[i] = arr[i].toCharArray();
			}
		}

		// Imprimimos la matriz para ver que funciona
		for (int i = 0; i < Ejecutar.matrixMap.length; i++) {
			System.out.println();
			for (int j = 0; j < Ejecutar.matrixMap[i].length; j++) {
				System.out.print(Ejecutar.matrixMap[i][j]);
			}
		}
		System.out.println();
	}
	
	public static void getPosiciones(){
		
		for (int i = 0; i < Ejecutar.matrixMap.length; i++) {
			for (int j = 0; j < Ejecutar.matrixMap[i].length; j++) {
				if (Ejecutar.matrixMap[i][j]=='P') {
					Ejecutar.estadoinicial= new Estado(i,j);
				}
				if (Ejecutar.matrixMap[i][j]=='G') {
					Ejecutar.ghosts.add(new Estado(i,j));
				}
			}
		}
		System.out.println("Coordenadas pacman: x: "+Ejecutar.estadoinicial.getCoordx()+
				" y: "+Ejecutar.estadoinicial.getCoordy());
		for (int i = 0; i < Ejecutar.ghosts.size(); i++) {
			System.out.println("Coordenadas ghost: x: "+Ejecutar.ghosts.get(i).getCoordx()+
					" y: "+Ejecutar.ghosts.get(i).getCoordy());
		}
	}
	
	public static void limpiarmatrixMap(){
		for (int i = 0; i < Ejecutar.matrixMap.length; i++) {
			for (int j = 0; j < Ejecutar.matrixMap[i].length; j++) {
				Ejecutar.matrixMap[i][j]= Ejecutar.auxMatrixMap[i][j];
			}
		}
	}
	
	public static void devolverArchivo() throws IOException{
		String fichero = "./src/Pacman_map1.txt";
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(fichero));
		for (int i = 0; i < Ejecutar.eatenGhosts.size(); i++) {
			while (! Ejecutar.path.isEmpty()) {
				//System.out.println("x: "+path.peek().x +" y: "+ path.peek().y);
				 Ejecutar.matrixMap[Ejecutar.path.peek().x][Ejecutar.path.peek().y] = 'x';
				 Ejecutar.path.pop();
				if (Ejecutar.path.peek().x== Ejecutar.eatenGhosts.get(i).x && Ejecutar.path.peek().y== Ejecutar.eatenGhosts.get(i).y) {
					Ejecutar.matrixMap[Ejecutar.path.peek().x][Ejecutar.path.peek().y] = 'x';
					Ejecutar.path.pop();
					break;
				}
			}
			for (int k = 0; k < Ejecutar.matrixMap.length; k++) {
				for (int l = 0; l < Ejecutar.matrixMap[k].length; l++) {
					bw.write(Ejecutar.matrixMap[k][l]);
				}
				bw.write("\n");
			}
			bw.write("---------------------------------------------");
			bw.write("\n");
			Preparativos.limpiarmatrixMap();
		}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				bw.close();
			}
		}
	}
	
}
