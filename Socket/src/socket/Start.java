package socket;
/*
 * 只能在连接同一wifi的电脑之间进行通信
 * */
public class Start {
	public static void main(String[] args) {
		new Server();
		new Client();
	}
}
