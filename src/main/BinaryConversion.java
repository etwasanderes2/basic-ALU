package main;

public abstract class BinaryConversion {

	/**
	 * converts number to binary boolean array.
	 * @param x the number
	 * @return 0 is lsb, 2k.
	 */
	public static boolean[] toBin(int x, int length) {
		boolean[] results = new boolean[length];
		for (int i = 0; i < length; i++) {
			results[i] = (x & 1) == 1;
			x = x >>> 1;
		}
		return results;
	}

	public static int toInt(boolean[] x) {
		int result;
		if (x[x.length-1]) {
			result = -1;
		} else {
			result = 0;
		}

		for (int i = x.length-1; i >= 0; i--) {
			result = result << 1;
			if (x[i]) {
				result = result | 1;
			}
		}
		return result;
	}

	/**
	 * converts to String.
	 * @param x the boolean array 0 is lsb
	 * @return msb first, 1 true, 0 false.
	 */
	public static String toString(boolean[] x) {
		String result = "";
		for (int i = x.length-1; i >= 0; i--) {
			result = result + (x[i] ? "1" : "0");
		}
		return result;
	}

}
