package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server.java 服务端代码
 **/
public class Server {
	private static int port = 33333;//端口号
	private Socket socket = null;   //创建一个socket实例，连接的载体
	private ServerSocket serversocket = null;//端口处监听连接
	private BufferedReader buffer = null;

	public Server() {
        /*开了一个线程，调用run函数
         * 
         * */
		new Thread() {
			public void run() {
				Run();
			};
		}.start();
	}
    /*服务器启动*/
	public void Run() {

		try {
			//先生成一个连接对象，监听30000端口的连接信息
			setServersocket(new ServerSocket(port));
			System.out.println("服务器端启动成功！");
			/*
			 * 死循环，因为服务器就是死循环，一直在等待连接
			 * */
			while (true) {
				try {
					//初始化socket对象
					//socket = serversocket.accpet()
					setSocket(getServersocket().accept());
					System.out.println("服务器端监听到连接！");
					//getInputStream() 输入流
					//setBuffer 服务端读到的数据封装到缓冲流中
					setBuffer(new BufferedReader(new InputStreamReader(getSocket().getInputStream(), "UTF-8")));
					//读取一行数据
					String msg = getBuffer().readLine();
					getBuffer().close();
					getSocket().close();
					System.out.println("消息接收完成");
                    //往客户端显示msg文本的地方传输，覆盖之前的数据
					Client.getLabManifest().setText(msg);
				} catch (IOException e) {
					// e.printStackTrace();
					System.out.println("服务器端连接失败！");
				}
			}
		} catch (IOException e) {
			// e.printStackTrace();
			System.out.println("服务器启动失败！");
		} finally {
			try {
				if (getServersocket() != null) {
					getServersocket().close();
					System.out.println("成功关闭服务器！");
				}
			} catch (IOException e) {
				// e.printStackTrace();
				System.out.println("关闭服务器失败！");
			}
		}
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public ServerSocket getServersocket() {
		return serversocket;
	}

	public void setServersocket(ServerSocket serversocket) {
		this.serversocket = serversocket;
	}

	public BufferedReader getBuffer() {
		return buffer;
	}

	public void setBuffer(BufferedReader buffer) {
		this.buffer = buffer;
	}
}
