package chess;

import java.util.ArrayList;
import java.util.List;

/**
 * An enumeration for piece types of the game of chess. A {@code PieceType}
 * instance can return the movement vectors useful to that piece for a tour.
 */
public enum PieceType {
	/**
	 * The knight piece.
	 */
	KNIGHT(0),

	/**
	 * The bishop piece.
	 */
	BISHOP(1),

	/**
	 * The rook piece.
	 */
	ROOK(2),

	/**
	 * The queen piece.
	 */
	QUEEN(3),

	/**
	 * The king piece.
	 */
	KING(4),

	/**
	 * The new Jack piece.
	 */
	JACK(5);

	/**
	 * The list of all possible movements for a given piece.
	 */
	private final List<Location> movementVectors;

	private PieceType(int key) {
		int dimx = TourViewer.DIMX;
		int dimy = TourViewer.DIMY;

		int len = (dimx > dimy) ? dimx : dimy;
		movementVectors = new ArrayList<Location>();

		// Fill the list of movements with all possible moves based on the piece.
		switch (key) {
		case 0:
			movementVectors.add(new Location(2, 1));
			movementVectors.add(new Location(-2, 1));
			movementVectors.add(new Location(2, -1));
			movementVectors.add(new Location(-2, -1));
			movementVectors.add(new Location(1, 2));
			movementVectors.add(new Location(-1, 2));
			movementVectors.add(new Location(-1, -2));
			movementVectors.add(new Location(1, -2));
			break;
		case 1:
			for (int i = 1; i <= len; i++) {
				movementVectors.add(new Location(1 * i, 1 * i));
				movementVectors.add(new Location(-1 * i, 1 * i));
				movementVectors.add(new Location(1 * i, -1 * i));
				movementVectors.add(new Location(-1 * i, -1 * i));
			}
			break;
		case 2:
			for (int i = 1; i <= len; i++) {
				movementVectors.add(new Location(1 * i, 0));
				movementVectors.add(new Location(-1 * i, 0));
				movementVectors.add(new Location(0, -1 * i));
				movementVectors.add(new Location(0, 1 * i));
			}

			break;
		case 3:
			for (int i = 1; i <= len; i++) {
				movementVectors.add(new Location(1 * i, 0));
				movementVectors.add(new Location(-1 * i, 0));
				movementVectors.add(new Location(0, -1 * i));
				movementVectors.add(new Location(0, 1 * i));
				movementVectors.add(new Location(1 * i, 1 * i));
				movementVectors.add(new Location(-1 * i, 1 * i));
				movementVectors.add(new Location(1 * i, -1 * i));
				movementVectors.add(new Location(-1 * i, -1 * i));
			}
			break;
		case 4:
			movementVectors.add(new Location(1, 0));
			movementVectors.add(new Location(-1, 0));
			movementVectors.add(new Location(0, -1));
			movementVectors.add(new Location(0, 1));
			movementVectors.add(new Location(1, 1));
			movementVectors.add(new Location(-1, 1));
			movementVectors.add(new Location(1, -1));
			movementVectors.add(new Location(-1, -1));
			break;
		case 5:
			movementVectors.add(new Location(3, 2));
			movementVectors.add(new Location(-3, 2));
			movementVectors.add(new Location(3, -2));
			movementVectors.add(new Location(-3, -2));
			movementVectors.add(new Location(2, 3));
			movementVectors.add(new Location(-2, 3));
			movementVectors.add(new Location(-2, -3));
			movementVectors.add(new Location(2, -3));
			break;
		default:
			break;
		}
	}

	/**
	 * Returns the list of moves that a piece can make.
	 * 
	 * @return the list of moves that a piece can make.
	 */
	public List<Location> movementVectors() {
		return this.movementVectors;
	}
}
