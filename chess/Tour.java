package chess;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * The {@code Tour} class creates the environment for  a knight's 
 * (or other piece) tour. The given width and height of the board must
 * be greater than 0, and the starting {@code Location} must have coordinates
 * within these bounds.
 * 
 * @author Alexander Muglia
 */
public class Tour {
	
	/**
	 * The width and height of the board.
	 */
	private int width;
	private int height;
	
	/**
	 * The set of all possible {@code Location}s to move to.
	 */
	private Set<Location> all;
	
	/**
	 * The current {@code Location}.
	 */
	private Location cur;
	
	/**
	 * The {@code PieceType} used for the tour.
	 */
	private final PieceType PIECE;
	
	
	/**
	 * Initializes the width, height, start, and piece type for the tour.
	 * 
	 * @param w the width of the board.
	 * @param h the height of the board.
	 * @param start the starting {@code Location} .
	 * @param p the {@code PieceType} for the tour.
	 */
	public Tour(int w, int h, Location start, PieceType p){
		this.width = w;
		this.height = h;
		this.cur = start;
		this.PIECE = p;
	}
	
	
	/**
	 * Initialized the set of all possible moves for the 
	 * tour and removes the starting location from that set.
	 * 
	 * @param loc the starting {@code Location}.
	 */
	
	public void startTour(Location loc) {
		all = new HashSet<Location>();
		//fill the set of possible locations
		for (int i = 1; i <= this.width; i++) {
			for(int j = 1; j <= this.height; j++) {
				all.add(new Location(i, j));
			}
		}
		removeFromAll(loc);
	}
	
	/**
	 * Checks if the piece has another spot to potentially move to.
	 * 
	 * @return {@code true} if the piece has a spot to move to.
	 */
	public boolean hasNext() {
		List<Location> moves = this.PIECE.movementVectors();
		for(int i = 0; i < moves.size(); i++){
			//add the movement vector to the current location.
			Location nextSpot = new Location(this.cur.x() + moves.get(i).x(), this.cur.y() + moves.get(i).y());
			if(all.contains(nextSpot)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Chooses the next spot to move to based on Warnsdorff's rule.
	 * 
	 * @return the best {@code Location} to move to.
	 */
	public Location next() {
		List<Location> moves = this.PIECE.movementVectors();
		Location result = null;
		int ind = moves.size() + 1;
		//implementation of Warnsdorff’s rule
		for(int i = 0; i < moves.size(); i++){
			//add the movement vector to the current location.
			Location nextSpot = new Location(this.cur.x() + moves.get(i).x(), this.cur.y() + moves.get(i).y());
			
			if(all.contains(nextSpot)) {
				int temp = countNext(nextSpot);
				if (temp < ind) {
					ind = temp;
					result = nextSpot;
				}
			}
		}
		return result;
	}
	
	
	/**
	 * Counts the number of moves that a certain {@code Location} has
	 * left with respect to the set remaining {@code Location}s left in the tour.
	 * 
	 * @param loc the {@code Location} to be checked.
	 * 
	 * @return the number of moves left from the given {@code Location}.
	 */
	public int countNext(Location loc) {
		int result = 0;
		List<Location> moves = this.PIECE.movementVectors();
		for(int i = 0; i < moves.size(); i++){
			//add the movement vector to the location in question
			Location nextSpot = new Location(loc.x() + moves.get(i).x(), loc.y() + moves.get(i).y());
			if(all.contains(nextSpot) && !nextSpot.equals(this.cur)) {
				result++;
			}
		}
		return result;
	}
	
	/**
	 * Updates the current {@code Location} of the tour.
	 * 
	 * @param loc the new current {@code Location}.
	 */
	public void updateCur(Location loc) {
		this.cur = loc;
	}
	
	/**
	 * Removes a {@code Location} from the set of possible options.
	 * 
	 * @param loc the {@code Location} to be removed.
	 */
	public void removeFromAll(Location loc) {
		this.all.remove(loc);
	}
	
	/**
	 * Checks if the tour has completed.
	 * 
	 * @return true if all squares have been visited.
	 */
	public boolean checkDone() {
		return this.all.size() < 2;
	}
}
