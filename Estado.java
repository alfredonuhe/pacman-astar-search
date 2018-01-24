
public class Estado {

	int x;
	int y;
	double costeAcc;
	double valorHeuristica;
	int valorCoste;
	Estado father;

	public Estado(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getCoordx() {
		return x;
	}

	public int getCoordy() {
		return y;
	}
	
	public double getcosteAcc(){
		
		return costeAcc;
	}
	
	public double getvalorHeuristica() {
		return valorHeuristica;
	}

	public int getvalorCoste() {
		return valorCoste;
	}
	public Estado getfather() {
		return father;
	}
	
	public void setCoordx(int newVar) {
		this.x = newVar;
	}

	public void setCoordy(int newVar) {
		this.y = newVar;
	}
	
	public void setcosteAcc(double newVar){
		
		 this.costeAcc=newVar;
	}
	
	public void setvalorHeuristica(double newVar) {
		this.valorHeuristica = newVar;
	}

	public void setvalorCoste(int newVar) {
		this.valorCoste = newVar;
	}
	
	public void setfather(Estado newVar) {
		this.father = newVar;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estado other = (Estado) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
}
