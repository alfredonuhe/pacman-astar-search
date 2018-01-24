import java.util.*;

public class AStar {
    static int n;
	public Deque<Estado> findPath(Estado begin, List<Estado> ghosts) {
			List <Estado> openList = new ArrayList<Estado>();
			List <Estado> closedList = new ArrayList<Estado>();
			//List<Estado> path= new ArrayList<Estado>();
			Deque<Estado> path = new ArrayDeque<Estado>();
			Estado nextBegin =begin;
		    openList.add(begin);

		     

		    boolean finish = false;
		    Estado current;
		    while (!finish) {
		        current = lowestInOpen(openList); // get node with lowest fCosts from openList
		        closedList.add(current); // add current node to closed list
		        openList.remove(current); // delete current node from open list
 
		        if (foundGoal(current, nextBegin, ghosts, openList, closedList, path)) {
		        	 current = lowestInOpen(openList); // get node with lowest fCosts from openList
				     closedList.add(current); // add current node to closed list
				     openList.remove(current); // delete current node from open list
				}
		        if (ghosts.size()==0) { // end
		                return path;
		        }		     
		        
		        List<Estado> adjacentNodes = getAdjacent(current);
	            
		        for (int i = 0; i < adjacentNodes.size(); i++) { 
		        	Estado currentAdj = adjacentNodes.get(i);
	               
		        	if (!openList.contains(currentAdj)) { 				// node is not in openList
	                    currentAdj.setfather(current); 				// set current node as previous for this node
	                    currentAdj.setvalorHeuristica(minHeuristic1(currentAdj, ghosts));// set h costs of this node (estimated costs to goal)
	                    currentAdj.setvalorCoste(calcCoste(current, currentAdj));	// set g costs of this node (costs from start to this node)
	                    currentAdj.setcosteAcc(calcCosteAcc(currentAdj));
	                    openList.add(currentAdj); 						// add node to openList
	                } else { 											// node is in openList
	                    if (currentAdj.valorCoste > calcCoste(current, currentAdj)) { // costs from current node are cheaper than previous costs
	                        currentAdj.setfather(current); 			// set current node as previous for this node
	                        currentAdj.setvalorCoste(calcCoste(current, currentAdj)); 				// set g costs of this node (costs from start to this node)
	                    }
	                }
	            }
		        if (openList.isEmpty()) { // no path exists
	                return new LinkedList<Estado>(); // return empty list
		        }
		}
			return null;
	}
	//cambiar método
	public boolean foundGoal(Estado current, Estado nextBegin, List<Estado> ghosts, List<Estado> openList, List<Estado> closedList, Deque<Estado> path){
		for (int i = 0; i < ghosts.size() ; i++) {
			if ((current.x == ghosts.get(i).x) && (current.y == ghosts.get(i).y)) { 
				calcPath(nextBegin, current, path);		//Encuentra un fantasma. Guarda camino hasta este fantasma
				nextBegin=current;						//Guarda fantasma como próximo punto de partida
				openList.clear();						//Vaciamos todas las listas
				closedList.clear();													
				openList.add(nextBegin);				//Inicializamos opnelist con nuevo punto de partida
				Ejecutar.eatenGhosts.add(ghosts.get(i));
				ghosts.remove(i);						//Eliminamos fantasma de lista de objetivos
				return true;
			}
		}
		return false;
	}
	
	public double minHeuristic1(Estado begin ,List<Estado> ghosts){
		double result=Heuristica1.h1(begin, 0);
		for (int i = 1; i < ghosts.size() ; i++) {
			if (result > Heuristica1.h1(begin, i)) {
				result=Heuristica1.h1(begin, i);
			}
		}
		return result;
	}

	public double minHeuristic2(Estado begin ,List<Estado> ghosts){
		double result=Heuristica1.h2(begin, 0);
		for (int i = 1; i < ghosts.size() ; i++) {
			if (result > Heuristica1.h2(begin, i)) {
				result=Heuristica1.h2(begin, i);
			}
		}
		return result;
	}
	
	public Estado lowestInOpen(List<Estado> list){
		Estado lowestCost= list.get(0);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).costeAcc< lowestCost.costeAcc) {
				lowestCost=list.get(i);
			}
		}
		
		return lowestCost;	
	}
	
	public void calcPath(Estado begin, Estado current, Deque<Estado> path){	
		path.push(current);
		while (begin.x!= path.peek().x || begin.y!= path.peek().y) {
			path.push(path.peek().father);
		}
	}
	
	
	public int calcCoste(Estado begin, Estado end){
		switch (Ejecutar.matrixMap[end.x][end.y]) {
		case 'O':
			
			return 2;
		case ' ':
			
			return 4;
		case 'G':
			
			return 4;
		}
		return 0;
	}
	
	public List<Estado> getAdjacent(Estado current){
		List<Estado> AdjList= new ArrayList<Estado>();
		
		if (Ejecutar.matrixMap[current.x][current.y+1]!= '%') {
			Estado up= new Estado(current.x, current.y+1);
			AdjList.add(up);
		}
		if (Ejecutar.matrixMap[current.x][current.y-1]!= '%') {
			Estado down= new Estado(current.x, current.y-1);
			AdjList.add(down);
		}
		if (Ejecutar.matrixMap[current.x-1][current.y]!= '%') {
			Estado left= new Estado(current.x-1, current.y);
			AdjList.add(left);
		}
		if (Ejecutar.matrixMap[current.x+1][current.y]!= '%') {
			Estado right= new Estado(current.x+1, current.y);
			AdjList.add(right);
		}
		return AdjList;
	}
	
	public double calcCosteAcc(Estado currentAdj){
		
		return currentAdj.father.costeAcc + currentAdj.valorCoste;
	}
}
