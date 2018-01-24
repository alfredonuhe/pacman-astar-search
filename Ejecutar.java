import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

public class Ejecutar {

	/*
	 * 
	 * 
	 * 
	 * */

	public static char[][] matrixMap;
	public static char[][] auxMatrixMap;
	public static String direccion = "./src/Pacman_map.txt";
	public static List<Estado> ghosts = new ArrayList<Estado>();
	public static List<Estado> eatenGhosts = new ArrayList<Estado>();
	public static Deque<Estado> path;
	public static Estado estadoinicial;

	/*
	 * 
	 * 
	 * 
	 * */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Preparativos.transcriptFile();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Preparativos.getPosiciones();

		AStar algorithm = new AStar();
		path = algorithm.findPath(estadoinicial, ghosts);

	
	try {
		Preparativos.devolverArchivo();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
			//System.out.println("\n" +ghosts);

	}
}
