package org.unclesniper.remurlopen;

import java.net.Socket;
import java.util.Arrays;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ClientHandler extends Thread {

	private final Socket client;

	private final String[] command;

	public ClientHandler(Socket client, String[] command) {
		this.client = client;
		this.command = command;
		start();
	}

	public void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));
			String line;
			while((line = br.readLine()) != null) {
				System.out.println("Opening '" + line + "' for " + client.getInetAddress().getHostAddress());
				String[] argv = Arrays.copyOfRange(command, 0, command.length + 1);
				argv[command.length] = line;
				new ProcessBuilder(argv)
						.redirectError(ProcessBuilder.Redirect.INHERIT)
						.redirectOutput(ProcessBuilder.Redirect.INHERIT)
						.start();
			}
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}

}
