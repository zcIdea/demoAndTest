package com.thread;

public class ThreadTest extends Thread {
	
	int a=1;
	
	@Override
	public void run() {
		
		try {
			Thread.currentThread().sleep(3000);
			System.out.println("当前线程："+Thread.currentThread().getName()+";a="+a);
			a++;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		
		ThreadTest tt= new ThreadTest();
		
		for (int i = 0; i < 10; i++) {
			new ThreadTest().start();
		}
	}

}
