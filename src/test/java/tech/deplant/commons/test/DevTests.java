package tech.deplant.commons.test;

import org.junit.jupiter.api.Test;

public class DevTests {

	public static int counter = 0;

	// MULTIPLY
	int mult(int a, int b) {
		if (b == 1) {
			return a;
		} else {
			return a + mult(a, b - 1);
		}
	}

	// TASK 1
	int div(int a, int b) {
		if (a < b) {
			return 0;
		} else {
			return 1 + div(a - b, b);
		}
	}

	// TASK 2
	int fib(int n) {
		if (n == 0 || n == 1) {
			counter++;
			return 1;
		} else {
			return fib(n - 2) + fib(n - 1);
		}
	}

//	// TASK 3
//	int fibCustom(int n, int s0, int s1) {
//		if (n == 0) {
//			return s0;
//		} else if (n == 1) {
//			return s1;
//		} else {
//			return fibCustom(n - 1, s0 + s1, )
//		}
//	}

	// TASK 4
	int remainder(int a, int b) {
		if (a < b) {
			return a;
		} else {
			return remainder(a - b, b);
		}
	}

	// TASK 5
	int digsum(int a) {
		if (a < 10) {
			return a;
		} else {
			return remainder(a, 10) + digsum(div(a, 10));
		}
	}

	void swap(Integer a, Integer b) {
		a = a + b; // a1 = a + b;
		b = a - b; // b1 = a1 - b = (a + b) - b = a
		a = a - b; // a2 = a1 - b1;  = (a + b) - a = b
	}

	@Test
	public void test_1() {

		System.out.println(String.format("%d %d %c %d %d %c %d %d %c %d %d",
		                                 '1' + '5' + '9',
		                                 '9' - '2',
		                                 '9' - 2,
		                                 '9' - 2,
		                                 '5' + 2,
		                                 '5' + 2,
		                                 1 * 1,
		                                 0 * '1',
		                                 '0' * '1',
		                                 '1' * '1',
		                                 '1' * '0'));

		//System.out.println("mult=" + mult(3, 3));
		//System.out.println("div=" + div(3, 3));
		//System.out.println("rem=" + remainder(14, 15));
		//System.out.println("fib=" + fib(4));
		//System.out.println("fib=" + fib(15));
		//System.out.println("digsum=" + digsum(12345));

		System.out.println("fib(" + 3 + ";" + 2 + ")=" + fib(15));
		System.out.println("complexity: O(n*" + counter / 15 + "): " + counter);

		//System.out.println("fib(" + 3 + ";" + 2 + ")=" + fibCustom(10, 3, 2));
		//System.out.println("complexity: O(n*" + counter / 10 + "): " + counter);
		//int a = 1;
		//int b = 2;
		//System.out.println("a=" + a + ",b=" + b);

	}

}
