package ch.bbw.pr.dame;

/**
 * DameProbelm
 * 
 * @author Peter Rutschmann
 * @version 07.11.2019
 */
public class DameProblem {
	public static final int FIELD_FREE = 0;
	public static final int FIELD_OCCUPIED = 1;

	private int size;
	private int[][] board;

	public int[][] getBoard() {
		return board;
	}

	public DameProblem(int size) {
		super();
		this.size = size;
		this.board = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				board[i][j] = FIELD_FREE;
			}
		}
	}

	public boolean setQueen(int row)
	{
		//Noch nicht rekursiv
		return true;
	}

	private boolean isValid(int r, int c) {
		return true;
	}
}






