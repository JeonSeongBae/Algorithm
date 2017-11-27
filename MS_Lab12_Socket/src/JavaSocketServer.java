import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class JavaSocketServer {
	public static void main(String[] args) {
		try {
			int portNumber = 5001;

			System.out.println("자바 소켓 서버 시작...");

			ServerSocket aServerSocker = new ServerSocket(portNumber);
			System.out.println("Listening at port: " + portNumber + "...");

			while (true) {
				Socket sock = aServerSocker.accept();
				InetAddress clientHost = sock.getLocalAddress();
				int clientPort = sock.getPort();
				System.out.println("클라이언트 연결. 호스트: " + clientHost + ", 포트: " + clientPort);

				ObjectInputStream instream = new ObjectInputStream(sock.getInputStream());
				Object obj = instream.readObject();
				System.out.println("Input: " + obj);

				ObjectOutputStream outstream = new ObjectOutputStream(sock.getOutputStream());
				outstream.writeObject(obj + "... from Server.");
				outstream.flush();
				sock.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}