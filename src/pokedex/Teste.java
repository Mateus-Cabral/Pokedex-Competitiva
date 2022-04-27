package pokedex;

public class Teste {
	
	public static void main(String[] args) {
		Pokemon snivy = new Pokemon("Snivy", "Grass", null, 495, 45, 45, 55, 45, 55, 63);
		
		System.out.println("Status antes da nature: " + snivy.getAtk());
		System.out.println(snivy.getName());
		snivy.calcStats(5, "atk", "Bold", 13, 1);
		System.out.println("Status pós nature: " + snivy.getAtk());
	}

}
