import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class KaratsubaAlgorithm {

	public static void main(String[] args) throws IOException {
		KaratsubaAlgorithm self = new KaratsubaAlgorithm();
		String fp = "data/data05_karatsuba.txt";

		// Count the number of tokens to determine the size of the array
		FileInputStream fis = new FileInputStream(fp);
		InputStreamReader isr = new InputStreamReader(fis, "euc-kr");
		BufferedReader br = new BufferedReader(isr);

		// make first array
		String line = br.readLine();
		BigInteger bigA = new BigInteger(line);

		// make second array
		line = br.readLine();
		BigInteger bigB = new BigInteger(line);
		System.out.println(self.KARATSUBA(bigA, bigB));

	}

	private BigInteger KARATSUBA(BigInteger A, BigInteger B) {
		int length = Math.max(A.toString().length(), B.toString().length());

		if (length <= 1)
			return A.multiply(B);

		int M = length / 2;
		BigInteger mask = new BigInteger(String.valueOf((int) Math.pow(10, M)));

		BigInteger X2 = A.divide(mask);
		BigInteger Y2 = B.divide(mask);
		BigInteger X1 = A.remainder(mask);
		BigInteger Y1 = B.remainder(mask);

		BigInteger z2 = KARATSUBA(X2, Y2);
		BigInteger z0 = KARATSUBA(X1, Y1);
		BigInteger z1 = KARATSUBA((X2.add(X1)), (Y2.add(Y1))).subtract(z2).subtract(z0);

		return z2.multiply(mask.multiply(mask)).add(z1.multiply(mask)).add(z0);
	}
}