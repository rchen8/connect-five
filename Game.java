import java.util.Scanner;

/**
 * @author Richard Chen
 *
 */
public class Game {
	/**
	 * 
	 */
	private void run() {
		Board board = new Board();
		System.out.println(board);

		Scanner scan = new Scanner(System.in);
		while (scan.hasNextInt()) {
			int x = scan.nextInt();
			int y = scan.nextInt();
			if (!board.isValidMove(x, y)) {
				System.out.println("Invalid move.");
				continue;
			}

			Human.move(board, x, y);
			if (board.getWinner() == Human.HUMAN_PLAYER) {
				System.out.println(board + "\nYou win!");
				break;
			}

			AI.move(board);
			if (board.getWinner() == AI.AI_PLAYER) {
				System.out.println(board + "\nYou lose!");
				break;
			}

			System.out.println(board);
		}
		scan.close();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Game().run();
	}
}
