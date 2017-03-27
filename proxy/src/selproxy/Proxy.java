package selproxy;

public class Proxy {

	public static void main(String[] args) {

		System.out.println("running proxy");
		Thread thread = new Thread(new ProxyMain());
		thread.start();

	}

}
