package com.hanains.network.echo;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class EchoThread extends Thread{



	private Socket socket;
	public EchoThread(Socket socket) {
		this.socket = socket;
		// TODO Auto-generated constructor stub
	}



	@Override
	public void run() {
		try{
			InetAddress inetaddress = socket.getInetAddress();
			System.out.println(inetaddress.getHostAddress() + "로 부터");

			String line  = null;

			OutputStream out  = socket.getOutputStream();
			InputStream input = socket.getInputStream();

			PrintWriter printw = new PrintWriter(new OutputStreamWriter(out));
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));


			while(( line=bufferedReader.readLine())!= null){
				System.out.println("클라로부터 받은 전송 문자열 : " + line);
				printw.println(line);
				printw.flush();
			}

			printw.close();
			bufferedReader.close();
			socket.close();
		}catch(Exception e){
			System.out.println(e);

		}

	}

}
