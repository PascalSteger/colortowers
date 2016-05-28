/**
 * find factorial(n)=n! by recursively calling this function
 * 
 * @author psteger
 * @date 2010-01-19
 */
public class factorial {

	static int factors(int n) {
		if (n == 0)
			return 1;
		return n * factors(n - 1);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("6! is " + factors(6)); //$NON-NLS-1$
	}

}
