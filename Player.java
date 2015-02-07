import java.awt.Point;
import java.util.ArrayList;

/**
 * @author Richard Chen
 *
 */
public class Player {
	/**
	 * 
	 */
	protected static ArrayList<Point> openEnded;

	/**
	 * 
	 */
	private static final int OPEN_THREE = 3;
	private static final int FIVE_IN_ROW = 5;
	private static final int NUM_DIRECTION = 8;
	private static final int BASE = 10;
	private static final int[][] ADJACENT_DIRECTION = {
			{ -1, 1, 0, 0, 1, -1, -1, 1 }, { -1, 1, -1, 1, -1, 1, 0, 0 } };

	/**
	 * @param game
	 * @param player
	 * @param x
	 * @param y
	 */
	protected static void move(Board game, int player, int x, int y) {
		int[][] board = game.getBoard(player == Human.HUMAN_PLAYER);
		game.move(player, x, y);

		openEnded = new ArrayList<>();
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
	 * @param board
	 * @param player
	 * @param x
	 * @param y
	 * @return
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
	 * @param board
	 * @param player
	 * @param x
	 * @param y
	 * @param index
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

		if (rowLength == OPEN_THREE && Board.isValid(x, y) && board[x][y] >= 0
				&& !openEnded.contains(new Point(x, y)))
			openEnded.add(new Point(x, y));

		return rowLength;
	}
}
