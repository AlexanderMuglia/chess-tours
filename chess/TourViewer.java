package chess;

import java.awt.Color;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import princeton.introcs.StdDraw;

/**
 * The {@code TourViewer} class sets up the visual aspect of the chess tour and
 * continues the tour until it has failed or succeeded.
 * 
 * @author Alexander Muglia
 *
 */
public class TourViewer {

	/**
	 * The width and height of the board to be toured.
	 */
	static int DIMX;
	static int DIMY;

	/**
	 * The {@code PieceType} to tour with.
	 */
	static PieceType PIECE;

	/**
	 * Asks the user for a width and height for the board. Also asks the user to
	 * pick a piece to tour with.
	 */
	private static void setup() {
		StdDraw.setScale(0.5, 8.5);

		int w = TourViewer.getWidth();
		int h = TourViewer.getHeight(w);

		if (w < 1 || h < 1) {
			throw new IllegalArgumentException("Width and height must be greater than 0.");
		}

		TourViewer.DIMX = w;
		TourViewer.DIMY = h;
		TourViewer.getPiece();
		TourViewer.drawBoard(w, h);

		StdDraw.setPenColor(StdDraw.BLACK);
	}

	/**
	 * Gets the width of the board from the user and displays it on screen.
	 * 
	 * @return the width of the board.
	 */
	private static int getWidth() {
		Character[] numArr = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		Set<Character> numSet = new HashSet<Character>(Arrays.asList(numArr));

		char input = ';';
		String result = "";
		while (input != (char) 32) {
			while (!StdDraw.hasNextKeyTyped()) {
				StdDraw.setPenColor(new Color(0));
				StdDraw.text(4, 7, "Please enter a width (integer followed by 'SPACE')");
				StdDraw.setPenColor(new Color(255, 0, 0));
				StdDraw.text(4, 6, "Please only type integers or SPACE");
			}
			input = StdDraw.nextKeyTyped();
			if (numSet.contains(input)) {
				StdDraw.clear();
				result += input;
				StdDraw.setPenColor(new Color(0));
				StdDraw.text(4, 5, "Width: " + result);
			} else if (input == ' ' && result.length() == 0) {
				input = ';';
			}
		}
		return Integer.parseInt(result);
	}

	/**
	 * Gets the height of the board from the user and displays it on screen.
	 * 
	 * @param width the width of the board
	 * 
	 * @return the height of the board.
	 */
	private static int getHeight(int width) {
		Character[] numArr = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		Set<Character> numSet = new HashSet<Character>(Arrays.asList(numArr));

		char input = ';';
		String result = "";
		StdDraw.clear();
		while (input != (char) 32) {
			while (!StdDraw.hasNextKeyTyped()) {
				StdDraw.setPenColor(new Color(0));
				StdDraw.text(4, 7, "Please enter a height (integer followed by 'SPACE')");
				StdDraw.text(4, 5, "width: " + width);
				StdDraw.setPenColor(new Color(255, 0, 0));
				StdDraw.text(4, 6, "Please only type integers or SPACE");
			}
			input = StdDraw.nextKeyTyped();
			if (numSet.contains(input)) {
				StdDraw.clear();
				result += input;
				StdDraw.setPenColor(new Color(0));
				StdDraw.text(4, 4, "height: " + result);
			} else if (input == ' ' && result.length() == 0) {
				input = ';';
			}
		}
		return Integer.parseInt(result);
	}

	/**
	 * Gets the piece to tour with from the user.
	 */
	private static void getPiece() {
		Character[] pieceArr = { 'n', 'b', 'r', 'q', 'k', 'j' };
		Set<Character> pieceSet = new HashSet<Character>(Arrays.asList(pieceArr));

		StdDraw.clear();
		char input = ';';
		boolean inputGood = false;
		while (!inputGood) {
			while (!StdDraw.hasNextKeyTyped()) {
				StdDraw.setPenColor(new Color(0));
				StdDraw.text(4, 7, "Please pick a piece to tour with: ");
				StdDraw.text(4, 5, "n = knight, b = bishop, r = rook, q = queen, k = king, j = jack");
				StdDraw.setPenColor(new Color(255, 0, 0));
				StdDraw.text(4, 6, "Please select a valid piece");
			}
			input = StdDraw.nextKeyTyped();
			if (pieceSet.contains(input)) {
				inputGood = true;
				if (input == 'n') {
					TourViewer.PIECE = PieceType.KNIGHT;
				} else if (input == 'b') {
					TourViewer.PIECE = PieceType.BISHOP;
				} else if (input == 'r') {
					TourViewer.PIECE = PieceType.ROOK;
				} else if (input == 'q') {
					TourViewer.PIECE = PieceType.QUEEN;
				} else if (input == 'k') {
					TourViewer.PIECE = PieceType.KING;
				} else if (input == 'j') {
					TourViewer.PIECE = PieceType.JACK;
				}
			}
		}
	}

	/**
	 * Draws a rectangular chess board of the specified size.
	 * 
	 * @param w the number of squares in the width of the board
	 * @param h the number of squares in the height of the board
	 */
	private static void drawBoard(int w, int h) {
		StdDraw.clear();
		int max = Math.max(w, h);
		StdDraw.setScale(0.5, max + 0.5);
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				if ((i + j) % 2 == 0) {
					StdDraw.setPenColor(new Color(163, 97, 3));
				} else {
					StdDraw.setPenColor(new Color(242, 218, 162));
				}
				StdDraw.filledSquare(i + 1, j + 1, 0.5);
			}
		}
	}

	/**
	 * Picks a random starting {@code Location} for the tour within the width and
	 * height.
	 * 
	 * @return the starting {@code Location}.
	 */
	private static Location pickStart() {
		Random random = new Random();
		int xStart = TourViewer.DIMX == 1 ? 1 : random.nextInt(TourViewer.DIMX - 1) + 1;
		int yStart = TourViewer.DIMY == 1 ? 1 : random.nextInt(TourViewer.DIMY - 1) + 1;
		return new Location(xStart, yStart);
	}

	/**
	 * Starts the tour and iterates over all of the moves, drawing it on screen each
	 * time.
	 * 
	 * @param start the starting {@code Location}.
	 * @param t the {@code Tour} object to be completed.
	 */
	private static void watchTour(Location start, Tour t) {
		t.startTour(start);
		Location curr = start;
		int i = 0;
		while (t.hasNext()) {
			Location next = t.next();
			System.out.println(i + " : moving from " + curr + " to " + next);
			StdDraw.line(curr.x(), curr.y(), next.x(), next.y());
			StdDraw.filledCircle(next.x(), next.y(), 0.1);
			t.removeFromAll(curr);
			curr = new Location(next);
			t.updateCur(next);
			// uncomment the next line to slow down the viewer; 500 is the pause time in
			// milliseconds
			// Thread.sleep(500);
			i++;
		}
		if (t.checkDone()) {
			System.out.println("We did it!");
		} else {
			System.out.println("We didn't find the path.");
		}

	}

	/**
	 * Begins the chess tour.
	 * 
	 * @param args not used.
	 * @throws IllegalArgumentException if the given width or height is less than 1.
	 */
	public static void main(String[] args) throws Exception {
		TourViewer.setup();
		Location start = TourViewer.pickStart();
		Tour t = new Tour(TourViewer.DIMX, TourViewer.DIMY, start, TourViewer.PIECE);
		TourViewer.watchTour(start, t);
	}
}
