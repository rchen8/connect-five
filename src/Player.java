/**
 * The Player class represents a player, human or AI, in the game.
 * 
 * @author Richard Chen
 *
 */
public class Player {
	/**
	 * Constants used to calculate the heuristic value of the move
	 */
	private static final int FIVE_IN_ROW = 5;
	private static final int NUM_DIRECTION = 8;
	private static final int BASE = 10;
	private static final int[][] ADJACENT_DIRECTION = {
			{ -1, 1, 0, 0, 1, -1, -1, 1 }, { -1, 1, -1, 1, -1, 1, 0, 0 } };

	/**
	 * Moves the player to the given coordinate
	 * 
	 * @param game
	 *            the connect-five game
	 * @param player
	 *            a value indicating if the player is human or AI
	 * @param x
	 *            the x-coordinate of the move
	 * @param y
	 *            the y-coordinate of the move
	 */
	protected static void move(Board game, int player, int x, int y) {
		int[][] board = game.getBoard(player == Human.HUMAN_PLAYER);
		game.move(player, x, y);

		for (int i = 0; i < NUM_DIRECTION; i++) {
			int dx = x + ADJACENT_DIRECTION[0][i];
			int dy = y + ADJACENT_DIRECTION[1][i];

			int row = 1;
			while (Board.isValid(dx, dy) && board[dx][dy] == player) {
				dx += ADJACENT_DIRECTION[0][i];
				dy += ADJACENT_DIRECTION[1][i];
				row++;
			}

			if (row == FIVE_IN_ROW)
				game.setWinner(player);

			if (Board.isValid(dx, dy) && board[dx][dy] >= 0)
				game.setValue(player, dx, dy, getValue(board, player, dx, dy));
		}
	}

	/**
	 * Calculates the heuristic value of the given coordinate
	 * 
	 * @param board
	 *            the connect-five game board
	 * @param player
	 *            a value indicating if the player is human or AI
	 * @param x
	 *            the given x-coordinate
	 * @param y
	 *            the given y-coordinate
	 * @return the heuristic value of the coordinate
	 */
	private static int getValue(int[][] board, int player, int x, int y) {
		int totalValue = 0;
		for (int i = 0; i < NUM_DIRECTION; i += 2) {
			int dx = x + ADJACENT_DIRECTION[0][i];
			int dy = y + ADJACENT_DIRECTION[1][i];
			int rowLength1 = countRow(board, player, dx, dy, i);

			dx = x + ADJACENT_DIRECTION[0][i + 1];
			dy = y + ADJACENT_DIRECTION[1][i + 1];
			int rowLength2 = countRow(board, player, dx, dy, i + 1);

			if (rowLength1 + rowLength2 != 0)
				totalValue += Math.pow(BASE, rowLength1 + rowLength2);
		}

		return totalValue;
	}

	/**
	 * Counts the number of adjacent moves in a given row
	 * 
	 * @param board
	 *            the connect-five game board
	 * @param player
	 *            a value indicating if the player is human or AI
	 * @param x
	 *            the x-coordinate of the given coordinate
	 * @param y
	 *            the y-coordinate of the given coordinate
	 * @param index
	 *            indicates which direction to count starting from the reference
	 *            coordinate
	 * @return
	 */
	private static int countRow(int[][] board, int player, int x, int y,
			int index) {
		int rowLength = 0;
		while (Board.isValid(x, y) && board[x][y] == player) {
			rowLength++;
			x += ADJACENT_DIRECTION[0][index];
			y += ADJACENT_DIRECTION[1][index];
		}

		return rowLength;
	}
}
