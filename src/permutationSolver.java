/**
 * @author Pascal Steger
 * @date 2010-01-13
 */

public class permutationSolver {
	static int[][] perms = new int[720][6];
	static int[][] prop = new int[6][6];
	static int height[][] = { { 1, 2, 5, 4, 6, 3 }, { 5, 3, 6, 1, 4, 2 },
			{ 4, 6, 3, 5, 2, 1 }, { 2, 1, 4, 3, 5, 6 }, { 3, 5, 2, 6, 1, 4 },
			{ 6, 4, 1, 2, 3, 5 } };
	static int count = 0;
	static int min_error = 100000;

	private void perm(int anz, int[] set, int start) {
		int i, sav;

		if (start < anz) {
			sav = set[start];

			for (i = start; i < anz; ++i) {
				set[start] = set[i];
				set[i] = sav;

				perm(anz, set, start + 1);

				set[i] = set[start];
			}
			set[start] = sav;
		} else {
			for (int x = 1; x <= 6; ++x)
				perms[count][x - 1] = set[x - 1];
			++count;
			// System.out.println(count + " " + set[0] + " " + set[1] + " "
			// + set[2] + " " + set[3] + " " + set[4] + " " + set[5]);
		}
	}

	private void addLayer(int layer, int[] layerContent) {
		prop[layer - 1] = layerContent;
	}

	private void removeLayer(int layer) {
		int[] nu = { 0, 0, 0, 0, 0, 0 };
		prop[layer - 1] = nu;
	}

	private boolean is_valid(int layer) {
		for (int row = 1; row < layer; ++row) {
			for (int col = 1; col <= 6; ++col) {
				if (prop[row - 1][col - 1] == prop[layer - 1][col - 1])
					return false;
			}
		}
		return true;
	}

	private void output() {
		for (int x = 1; x <= 6; ++x) {
			for (int y = 1; y <= 6; ++y) {
				System.out.print(prop[x - 1][y - 1] + " ");
			}
			System.out.println("");
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		permutationSolver ps = new permutationSolver();

		// generate permutations and store them for later reference
		int[] seta = { 1, 2, 3, 4, 5, 6 };

		ps.perm(6, seta, 0);
		// for (int x = 1; x <= 720; ++x) {
		// for (int y = 1; y <= 6; ++y) {
		// System.out.print(perms[x - 1][y - 1] + " ");
		// }
		// System.out.println("");
		// }

		// walk through all 6 layers, try every permutation
		for (int layer1 = 278; layer1 <= 720; layer1++) {
			ps.addLayer(1, perms[layer1 - 1]);
			if (ps.is_valid(1) == false) {
				ps.removeLayer(1);
				continue;
			}
			for (int layer2 = 1; layer2 <= 720; layer2++) {
				ps.addLayer(2, perms[layer2 - 1]);
				if (ps.is_valid(2) == false) {
					ps.removeLayer(2);
					continue;
				}
				for (int layer3 = 1; layer3 <= 720; layer3++) {
					ps.addLayer(3, perms[layer3 - 1]);
					if (ps.is_valid(3) == false) {
						ps.removeLayer(3);
						continue;
					}
					for (int layer4 = 1; layer4 <= 720; layer4++) {
						ps.addLayer(4, perms[layer4 - 1]);
						if (ps.is_valid(4) == false) {
							ps.removeLayer(4);
							continue;
						}
						for (int layer5 = 1; layer5 <= 720; layer5++) {
							ps.addLayer(5, perms[layer5 - 1]);
							if (ps.is_valid(5) == false) {
								ps.removeLayer(5);
								continue;
							}
							for (int layer6 = 1; layer6 <= 720; layer6++) {
								ps.addLayer(6, perms[layer6 - 1]);
								if (ps.is_valid(6) == false) {
									ps.removeLayer(6);
									continue;
								}

								// count colors
								int[][] colspace = new int[6][6];
								for (int x = 1; x <= 6; ++x) {
									for (int y = 1; y <= 6; ++y) {
										++colspace[prop[x - 1][y - 1] - 1][height[x - 1][y - 1] - 1];
									}
								}

								// get error
								int error = 0;
								for (int x = 1; x <= 6; ++x) {
									for (int y = 1; y <= 6; ++y) {
										error += Math
												.abs(colspace[x - 1][y - 1] - 1);
									}
								}
								if (error <= min_error) {

									// output color proposal
									ps.output();
									System.out.println("");

									// output color counts
									for (int x = 1; x <= 6; ++x) {
										System.out.print("  ");
										for (int y = 1; y <= 6; ++y) {
											System.out
													.print(colspace[x - 1][y - 1]
															+ " ");
										}
										System.out.println("");
									}
									System.out.println("");
									min_error = error;
								}
							}
						}
					}
				}
			}
		}

		// get together heights of a color. must be {1,2,3,4,5,6}

		// output percentage of correct matching
	}
}
