/**
 * @author Richard Chen
 *
 */
public class Human extends Player {
	/**
	 * 
	 */
	public static final int HUMAN_PLAYER = -1;

	/**
	 * @param game
	 * @param x
	 * @param y
	 */
	public static void move(Board game, int x, int y) {
		move(game, HUMAN_PLAYER, x, y);
	}
}
