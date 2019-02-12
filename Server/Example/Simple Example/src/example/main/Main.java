package example.main;

import example.proto.SimpleParser;
import example.serial.SerialTest;


//!! YOU MUST INSTALL RXTX native files before this will work!  follow the directions in the RxTx INSTALL file.

public class Main {
	
	public static void main(String[] args) throws Exception {
		SerialTest main = new SerialTest();
		SimpleParser p = new SimpleParser();
		main.registerListener(p);
		main.initialize();
		Thread t=new Thread() {
			public void run() {
				while(true)
				{
					
				}
			}
		};
		t.start();
		System.out.println("Started");
	}

}
