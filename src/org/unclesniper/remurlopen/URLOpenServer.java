package org.unclesniper.remurlopen;

import java.net.Socket;
import java.util.Arrays;
import java.io.IOException;
import java.net.ServerSocket;

public class URLOpenServer {

	private static final String LAUNCH_CMD = "java " + URLOpenServer.class.getName();

	public static void main(String[] args) throws IOException {
		if(args.length < 2) {
			System.err.println("Usage: " + URLOpenServer.LAUNCH_CMD + " <port> <command...>");
			System.exit(1);
		}
		int port;
		try {
			port = Integer.parseInt(args[0]);
		}
		catch(NumberFormatException nfe) {
			System.err.println("Not a valid integer: " + args[0]);
			System.exit(1);
			return;
		}
		if(port < 1 || port > 65535) {
			System.err.println("Port number out of range: " + port);
			System.exit(1);
		}
		String[] cmd = Arrays.copyOfRange(args, 1, args.length);
		ServerSocket server = new ServerSocket(port);
		for(;;) {
			Socket client = server.accept();
			System.out.println("Connection from " + client.getInetAddress().getHostAddress());
			System.out.flush();
			new ClientHandler(client, cmd);
		}
	}

}
