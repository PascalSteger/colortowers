/**
 * implements a cube with given bars and searches for unique color set
 * 
 * @author Pascal Steger
 * @date 2010-01-19
 */
public class dddcube {

	// static variable as only one for all instances of the class is used
	static int height[][] = { { 1, 2, 5, 4, 6, 3 }, { 5, 3, 6, 1, 4, 2 },
			{ 4, 6, 3, 5, 2, 1 }, { 2, 1, 4, 3, 5, 6 }, { 3, 5, 2, 6, 1, 4 },
			{ 6, 4, 1, 2, 3, 5 } };
	static int color[][] = new int[6][6];
	static int offset[][] = new int[6][6];
	static Brickset bricks = new Brickset();
	static int depth = 0;

	/**
	 * check if a proposed color is valid at the current point
	 * 
	 * @param x
	 *            x-coordinate
	 * @param y
	 *            y-coordinate
	 * @param proposed_color
	 * @return true if it's ok to use this color here, false else
	 */
	private boolean is_valid(int x, int y, int proposed_color) {
		for (int x1 = 1; x1 <= x; x1++) {
			if (color[x1 - 1][y - 1] == proposed_color)
				return false;
		}
		for (int y1 = 1; y1 <= y; y1++) {
			if (color[x - 1][y1 - 1] == proposed_color)
				return false;
		}
		return true;
	}

	/**
	 * show all information available at this point of solution finding process
	 */
	static private void showSolution() {
		for (int x = 1; x <= 6; ++x) {
			for (int y = 1; y <= 6; ++y) {
				System.out.print(color[x - 1][y - 1]
						+ Messages.getString("dddcube.stringkey.0")); //$NON-NLS-1$
			}
			System.out.print(Messages.getString("dddcube.stringkey.1")); //$NON-NLS-1$
			for (int y = 1; y <= 6; ++y) {
				System.out.print(offset[x - 1][y - 1]
						+ Messages.getString("dddcube.stringkey.0")); //$NON-NLS-1$
			}
			System.out.print(Messages.getString("dddcube.stringkey.1")); //$NON-NLS-1$
			bricks.showColorBricks(x);

			System.out.println(Messages.getString("dddcube.stringkey.2")); //$NON-NLS-1$
		}
	}

	void rec_fill() {

		int x = depth / 6 + 1;
		int y = depth % 6 + 1;
		// System.out.print("x = " + x + ", y = " + y + ", size = "
		// + height[x - 1][y - 1] + ", offset = " + offset[x - 1][y - 1]
		// + " ");

		if (color[x - 1][y - 1] > 0) {
			// System.out.println("gave back brick[" + x + "," + y + "]");
			bricks.takeBrick(color[x - 1][y - 1], height[x - 1][y - 1]);
			offset[x - 1][y - 1]++;
			color[x - 1][y - 1] = 0;
			return;
		}

		int proposedColor;
		proposedColor = bricks.proposeColor(height[x - 1][y - 1],
				offset[x - 1][y - 1]);
		// System.out.println("We got color " + proposedColor);
		if (proposedColor == -1) {
			//System.out.println("Dead end. **********************************")
			// ;
			color[x - 1][y - 1] = 0;
			offset[x - 1][y - 1] = 0;
			depth--;
			if (depth == 0) {
				System.out.println(Messages.getString("dddcube.stringkey.5")); //$NON-NLS-1$
				showSolution();
			}
			return;
		}
		if (is_valid(x, y, proposedColor)) {
			// System.out.println("Success!");
			color[x - 1][y - 1] = proposedColor;
			offset[x - 1][y - 1] = proposedColor - 1;
			bricks.giveBrick(proposedColor, height[x - 1][y - 1]);
			depth++;
			return;
		}
		// System.out.println("Search other color.");
		offset[x - 1][y - 1] = proposedColor;
		return;

	}

	/**
	 * a possible color match on the towers
	 * 
	 * @param args
	 * @author Pascal Steger
	 */
	public static void main(String[] args) {
		System.out.println(Messages.getString("dddcube.stringkey.8")); //$NON-NLS-1$
		dddcube dr = new dddcube();
		for (int count = 0; depth >= 0 && depth < 32; ++count) {
			dr.rec_fill();
			// showSolution();
			// dr.toString();
			if (count == 100000) {
				System.out.println(Messages.getString("dddcube.stringkey.2")); //$NON-NLS-1$
				showSolution();
				count = 0;
			}
		}
		showSolution();
		System.out.println(Messages.getString("dddcube.stringkey.9")); //$NON-NLS-1$
	}
}
