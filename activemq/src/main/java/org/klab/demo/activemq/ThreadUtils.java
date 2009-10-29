package org.klab.demo.activemq;

public class ThreadUtils {

	static public void pause(int ms) {
		try {
			Thread.currentThread().sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
