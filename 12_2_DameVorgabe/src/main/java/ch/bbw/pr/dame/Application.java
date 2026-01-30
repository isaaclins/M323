package ch.bbw.pr.dame;

/**
 * Dame Application
 * 
 * @author Peter Rutschmann
 * @version 07.11.2019
 */
public class Application {

	public static void main(String[] args) {
		int size =8;
		DameProblem solver = new DameProblem(size);
		
		System.out.println("Damen Problem");
		System.out.println();
		
		//Start mit Zeile 0
		if (solver.setQueen(0)) 
		{
			//Printout des Spielfeldes
			for (int i = 0; i < size; i++) 
			{
				for (int j = 0; j < size; j++) 
				{
					if (solver.getBoard()[i][j] == DameProblem.FIELD_OCCUPIED)
					{
						System.out.print("D ");
					}
					else
					{
						System.out.print("* ");
					}
				}
				System.out.println();
			}
		}
		
	}
}
