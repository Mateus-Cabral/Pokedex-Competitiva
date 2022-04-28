package pokedex;

public class Teste {
	
	public static void main(String[] args) {
		Pokemon snivy = new Pokemon("Snivy", "Grass", null, 495, 45, 45, 55, 45, 55, 63);
		Pokemon tepig = new Pokemon("Tepig", "Fire", null, 498, 65, 63, 45, 45, 45, 45);
		Pokemon oshawott = new Pokemon("Oshawott", "Water", null, 501, 55, 55, 45, 63, 45, 45);
		
		System.out.println(snivy.getName());
		System.out.println("Status de atk antes da nature: " + snivy.getAtk());
		System.out.println(tepig.getName());
		System.out.println("Status de atk antes da nature: " + tepig.getAtk());
		System.out.println(oshawott.getName());
		System.out.println("Status de atk antes da nature: " + oshawott.getAtk());
		System.out.println();
		snivy.calcStats(5, "atk", "Bold", 13, 1);
		tepig.calcStats(5, "atk", "adamant", 13, 1);
		oshawott.calcStats(5, "atk", "relaxed", 13, 1);
		System.out.println(snivy.getName());
		System.out.println("Status pós nature desfavorável: " + snivy.getAtk());
		System.out.println(tepig.getName());
		System.out.println("Status pós nature favorável: " + tepig.getAtk());
		System.out.println(oshawott.getName());
		System.out.println("Status pós nature neutra: " + oshawott.getAtk());
		
		
	}

}
