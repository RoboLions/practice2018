public class Thanos {

  /**
    Sumedh wrote this when he was bored in CS 1331. 
   */

	public int snap(int pop) {
		System.out.println("*snap*");
		pop /= 2;
		return pop;
	}

	public static void main(String[] args) {
		int population = 0;
		if (args.length >= 1) {
			population = Integer.parseInt(args[0]);
		}

		Thanos thanos = new Thanos();

		System.out.println("The population right now is: " + population);
		System.out.println("You should've aimed for the head!");
		population = thanos.snap(population);
		System.out.println("Now the population is: " + population);
	}
}
