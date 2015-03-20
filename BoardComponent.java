import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

/**
 * 
 * @author Richard Chen
 *
 */
@SuppressWarnings("serial")
public class BoardComponent extends JComponent {
	public static final int SQUARE_SIZE = 30;

	private Board game;

	/**
	 * 
	 * @param game
	 */
	public BoardComponent(Board game) {
		this.game = game;
	}

	/**
	 * @see javax.swing.JComponent#paintComponents(Graphics)
	 */
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(Color.white);
		for (int i = 0; i < Board.BOARD_SIZE; i++) {
			for (int j = 0; j < Board.BOARD_SIZE; j++) {
				if (game.getBoard(true)[i][j] == Human.HUMAN_PLAYER)
					g2.setColor(Color.blue);
				else if (game.getBoard(true)[i][j] == AI.AI_PLAYER)
					g2.setColor(Color.red);

				g2.fillRect(j * SQUARE_SIZE, i * SQUARE_SIZE, SQUARE_SIZE,
						SQUARE_SIZE);
				g2.setColor(Color.white);
			}
		}

		g2.setColor(Color.black);
		for (int i = 0; i <= Board.BOARD_SIZE; i++) {
			g2.drawLine(0, i * SQUARE_SIZE, Board.BOARD_SIZE * SQUARE_SIZE, i
					* SQUARE_SIZE);
			g2.drawLine(i * SQUARE_SIZE, 0, i * SQUARE_SIZE, Board.BOARD_SIZE
					* SQUARE_SIZE);
		}
	}
}
