package org.unclesniper.remurlopen;

import java.net.Socket;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class URLOpenClient {

	private static final String LAUNCH_CMD = "java " + URLOpenClient.class.getName();

	public static void main(String[] args) throws IOException {
		if(args.length != 3) {
			System.err.println("Usage: " + URLOpenClient.LAUNCH_CMD + " <host> <port> <url>");
			System.exit(1);
		}
		int port;
		try {
			port = Integer.parseInt(args[1]);
		}
		catch(NumberFormatException nfe) {
			System.err.println("Not a valid integer: " + args[1]);
			System.exit(1);
			return;
		}
		if(port < 1 || port > 65535) {
			System.err.println("Port number out of range: " + port);
			System.exit(1);
		}
		Socket socket = new Socket(args[0], port);
		OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
		osw.write(args[2] + '\n');
		osw.flush();
		socket.close();
	}

}
