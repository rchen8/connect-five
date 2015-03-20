import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Richard Chen
 *
 */
public class AI extends Player {
	public static final int AI_PLAYER = -2;

	private static final int BRANCHING_FACTOR = 4;
	private static final int LOOK_AHEAD = 9;

	private Board game;

	/**
	 * 
	 * @param game
	 */
	public AI(Board game) {
		this.game = game;
	}

	/**
	 * 
	 */
	public void move() {
		ArrayList<Point> moves = getPossibleMoves(game);

		int max = Integer.MIN_VALUE;
		Point bestMove = null;
		for (int i = 0; i < Math.min(moves.size(), BRANCHING_FACTOR); i++) {
			int value = minimax(game, moves.get(i), LOOK_AHEAD,
					Integer.MIN_VALUE, Integer.MAX_VALUE, true);

			// System.out.println(moves.get(i) + " " + value);
			if (value > max) {
				max = value;
				bestMove = moves.get(i);
			}
		}

		// System.out.println();
		super.move(game, AI_PLAYER, bestMove.x, bestMove.y);
	}

	/**
	 * @param game
	 * @param move
	 * @param depth
	 * @param alpha
	 * @param beta
	 * @param max
	 * @return
	 */
	private int minimax(Board game, Point move, int depth, int alpha, int beta,
			boolean max) {
		if (depth == 0 || game.getWinner() != 0)
			return game.getBoardValue();

		Board copy = game.clone();
		super.move(copy, max ? AI_PLAYER : Human.HUMAN_PLAYER, move.x, move.y);

		ArrayList<Point> moves = getPossibleMoves(game);
		if (max) {
			int bestValue = Integer.MIN_VALUE;
			for (int i = 0; i < Math.min(moves.size(), BRANCHING_FACTOR); i++) {
				bestValue = Math.max(
						bestValue,
						minimax(copy, moves.get(i), depth - 1, alpha, beta,
								false));

				alpha = Math.max(alpha, bestValue);
				if (beta <= alpha) // beta cut-off
					break;
			}
			return bestValue;
		} else {
			int bestValue = Integer.MAX_VALUE;
			for (int i = 0; i < Math.min(moves.size(), BRANCHING_FACTOR); i++) {
				bestValue = Math.min(
						bestValue,
						minimax(copy, moves.get(i), depth - 1, alpha, beta,
								true));

				beta = Math.min(beta, bestValue);
				if (beta <= alpha) // alpha cut-off
					break;
			}
			return bestValue;
		}
	}

	/**
	 * @param game
	 * @return
	 */
	private ArrayList<Point> getPossibleMoves(Board game) {
		ArrayList<Point3D> moves = new ArrayList<>();
		int[][] human = game.getBoard(true);
		int[][] ai = game.getBoard(false);

		for (int i = 0; i < Board.BOARD_SIZE; i++)
			for (int j = 0; j < Board.BOARD_SIZE; j++)
				if (human[i][j] >= 0)
					moves.add(new Point3D(i, j, human[i][j] + ai[i][j]));

		Collections.sort(moves);
		return new ArrayList<Point>(moves);
	}

	/**
	 * @author Richard Chen
	 *
	 */
	@SuppressWarnings("serial")
	private class Point3D extends Point implements Comparable<Point3D> {
		private int z;

		/**
		 * @param x
		 * @param y
		 * @param z
		 */
		private Point3D(int x, int y, int z) {
			super(x, y);
			this.z = z;
		}

		/**
		 * @see java.lang.Comparable#compareTo(java.lang.Object)
		 */
		@Override
		public int compareTo(Point3D pt) {
			return pt.z - z;
		}
	}
}
