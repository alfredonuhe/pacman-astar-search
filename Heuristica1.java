
public class Heuristica1 {
	
	static String type;
  
	public static double h1(Estado state, int posfantasma) {
		// TODO Auto-generated method stub
		Estado estado = state;
		int restaX = Ejecutar.ghosts.get(posfantasma).x - estado.x;
		int restaY = Ejecutar.ghosts.get(posfantasma).y - estado.y;
		double valorHeuristica = Math.sqrt(Math.pow(restaX, 2) + Math.pow(restaY, 2));
		
		return valorHeuristica;
	}
	
	public static double h2(Estado state, int posfantasma) {
		// TODO Auto-generated method stub
		Estado estado = (Estado)state;
		int restaX = Ejecutar.ghosts.get(posfantasma).x - estado.x;
		int restaY = Ejecutar.ghosts.get(posfantasma).y - estado.y;
		double valorHeuristica = restaX + restaY;
		
		return valorHeuristica;
	}

}