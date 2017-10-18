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
		int lengthA = String.valueOf(A.longValue()).length();
		int lengthB = String.valueOf(B.longValue()).length();
		if (lengthA <= 2 || lengthB <= 2) {
			return A.multiply(B);
		}
		// Divide A and store data in X2 and X0
		BigInteger X2 = new BigInteger(String.valueOf(A.intValue()).substring(0, lengthA / 2));
		BigInteger X1 = new BigInteger(String.valueOf(A.intValue()).substring(lengthA / 2));

		// Divide B and store data in Y2 and Y0
		BigInteger Y2 = new BigInteger(String.valueOf(B.intValue()).substring(0, lengthB / 2));
		BigInteger Y1 = new BigInteger(String.valueOf(B.intValue()).substring(lengthB / 2));

		BigInteger z0 = KARATSUBA(X2, Y2);
		BigInteger z2 = KARATSUBA(X1, Y1);
		BigInteger z1 = KARATSUBA((X2.add(X1)), (Y2.add(Y1)));
		z1 = z1.subtract(z2).subtract(z0);

		BigInteger temp = new BigInteger(String.valueOf((int)Math.pow(10, lengthA / 2 * 2)));
		z0 = z0.multiply(temp);
		temp = new BigInteger(String.valueOf((int)Math.pow(10, lengthA / 2)));
		z1 = z1.multiply(temp);

		return z0.add(z1).add(z2);
	}
}