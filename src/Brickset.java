/**
 * class that manages available color bricks
 * 
 * @author psteger
 * 
 */
public class Brickset {
	static int[][] bricks = { { 1, 2, 3, 4, 5, 6 }, { 1, 2, 3, 4, 5, 6 },
			{ 1, 2, 3, 4, 5, 6 }, { 1, 2, 3, 4, 5, 6 }, { 1, 2, 3, 4, 5, 6 },
			{ 1, 2, 3, 4, 5, 6 } };

	// int[][] bricks = { { 0, 0, 0, 4, 0, 0 }, { 0, 2, 0, 0, 0, 0 },
	// { 1, 0, 0, 0, 0, 0 }, { 0, 0, 3, 0, 0, 0 }, { 0, 0, 0, 0, 5, 0 },
	// { 0, 0, 0, 0, 0, 0 } };

	private boolean[] getBrickSubset(int size) {
		boolean[] bricksOfSameHeight = new boolean[6];
		for (int color = 1; color <= 6; ++color) {
			for (int inside_color = 1; inside_color <= bricks[color - 1].length; ++inside_color) {
				if (bricks[color - 1][inside_color - 1] == size) {
					bricksOfSameHeight[color - 1] = true;
				}
			}
		}
		return bricksOfSameHeight;
	}

	private void removeBrickFromSet(int color, int size) {
		bricks[color - 1][size - 1] = 0;
	}

	private void addBrickToSet(int color, int size) {
		bricks[color - 1][size - 1] = size;
	}

	/**
	 * give away a brick
	 * 
	 * @param size
	 *            of this size
	 * @param color
	 *            with that color
	 */
	public void giveBrick(int color, int size) {
		// System.out.println("giveBrick("+color+","+size+")");
		removeBrickFromSet(color, size);
	}

	/**
	 * take back a brick of
	 * 
	 * @param color
	 *            color and
	 * @param size
	 *            size
	 */
	public void takeBrick(int color, int size) {
		addBrickToSet(color, size);
	}

	/**
	 * search for the next suitable color and return it
	 * 
	 * @param size
	 * @param offset
	 * @return -1 if no color found, else color code
	 */
	public int proposeColor(int size, int offset) {
		boolean[] bricksAvailable = getBrickSubset(size);
		for (int color = offset + 1; color <= 6; ++color) {
			if (bricksAvailable[color - 1] == true) {
				return color;
			}
		}
		return -1;
	}

	/**
	 * print to standard output the list of all brick sizes available for a
	 * given color
	 * 
	 * @param color
	 */
	public void showColorBricks(int color) {
		for (int count = 1; count <= 6; count++) {
			if (bricks[color - 1][count - 1] > 0)
				System.out.print(bricks[color - 1][count - 1]
						+ Messages.getString("Brickset.0")); //$NON-NLS-1$
		}
	}

	/**
	 * test class: initialize bricks, propose one of color 3
	 * 
	 * @param args
	 *            no meaning
	 */
	public static void main(String[] args) {
		System.out.println(Messages.getString("Brickset.1")); //$NON-NLS-1$
		Brickset br = new Brickset();
		System.out
				.println(Messages.getString("Brickset.2") + br.proposeColor(3, 0)); //$NON-NLS-1$
		System.out.println(Messages.getString("Brickset.3")); //$NON-NLS-1$
	}
}
