import acsse.csc2a.fmb.file.DisplayFileHandler;
import acsse.csc2a.fmb.model.Firework;
/**
 * main class 
 * @author MC Nkosi 220033089
 * @version P03
 */
public class Main {

	public static void main(String[] args) {
		// Create a list of filenames to read
		String[] filenames = { "data/clean_1.txt", "data/dirty_1.txt", "data/partial_1.txt" };
		for(String fileName : filenames) {
			System.out.println("===============================================================================================");
						
			Firework[] fireworks = DisplayFileHandler.readfile(fileName);
			
			for (Firework firework: fireworks)
			{
				if (firework != null)
				{
					firework.printFirework();
				}
				else
				{
					// null reference in array
					System.err.println("Ship array has a null reference");
				}
			}
			System.out.println("===============================================================================================");
		}

	}

}
