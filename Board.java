import java.util.Arrays;

/**
 * @author Richard Chen
 *
 */
public class Board {
	/**
	 * 
	 */
	public static final int BOARD_SIZE = 15;
	
	/**
	 * 
	 */
	private int winner;
	private int[][] human, ai;

	/**
	 * 
	 */
	public Board() {
		human = new int[BOARD_SIZE][BOARD_SIZE];
		ai = new int[BOARD_SIZE][BOARD_SIZE];
		winner = 0;
	}

	/** 
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Board clone() {
		Board copy = new Board();
		for (int i = 0; i < BOARD_SIZE; i++) {
			copy.getBoard(true)[i] = Arrays.copyOf(getBoard(true)[i],
					BOARD_SIZE);
			copy.getBoard(false)[i] = Arrays.copyOf(getBoard(false)[i],
					BOARD_SIZE);
		}
		copy.setWinner(winner);
		return copy;
	}

	/**
	 * @param isHuman
	 * @return
	 */
	public int[][] getBoard(boolean isHuman) {
		return isHuman ? human : ai;
	}

	/**
	 * @return
	 */
	public int getBoardValue() {
		if (winner != 0)
			return winner == Human.HUMAN_PLAYER ? Integer.MIN_VALUE
					: Integer.MAX_VALUE;

		int value = 0;
		for (int i = 0; i < BOARD_SIZE; i++)
			for (int j = 0; j < BOARD_SIZE; j++)
				if (human[i][j] >= 0)
					value += ai[i][j] - human[i][j];
		return value;
	}

	/**
	 * @return
	 */
	public int getWinner() {
		return winner;
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 */
	public static boolean isValid(int x, int y) {
		return x >= 0 && y >= 0 && x < BOARD_SIZE && y < BOARD_SIZE;
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isValidMove(int x, int y) {
		return human[x][y] >= 0;
	}

	/**
	 * @param player
	 * @param x
	 * @param y
	 */
	public void move(int player, int x, int y) {
		human[x][y] = player;
		ai[x][y] = player;
	}

	/**
	 * @param player
	 * @param x
	 * @param y
	 * @param value
	 */
	public void setValue(int player, int x, int y, int value) {
		if (player == Human.HUMAN_PLAYER)
			human[x][y] = value;
		else if (player == AI.AI_PLAYER)
			ai[x][y] = value;
	}

	/**
	 * @param player
	 */
	public void setWinner(int player) {
		winner = player;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String print = "   ";
		for (int i = 0; i < BOARD_SIZE; i++)
			print += String.format("%3d", i);
		print += "\n";

		for (int i = 0; i < BOARD_SIZE; i++) {
			print += String.format("%3d", i);
			for (int j = 0; j < BOARD_SIZE; j++)
				print += String.format("%3s", human[i][j] >= 0 ? "-"
						: human[i][j] == Human.HUMAN_PLAYER ? "X" : "O");
			print += "\n";
		}

		return print;
	}
}
