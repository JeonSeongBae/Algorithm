import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class JavaSocketClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			int portNumber = 5001;

			Socket sock = new Socket("localhost", portNumber);
			ObjectOutputStream outstream = new ObjectOutputStream(sock.getOutputStream());
			outstream.writeObject("Hello Android!");
			outstream.flush();

			ObjectInputStream inStream = new ObjectInputStream(sock.getInputStream());
			System.out.println(inStream.readObject());
			sock.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}