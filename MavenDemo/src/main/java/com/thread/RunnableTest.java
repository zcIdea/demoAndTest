package com.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.internal.runners.statements.RunAfters;

/**
 * 
 * Lock与synchronized对比
	  1）Lock是一个接口，而synchronized是Java中的关键字，synchronized是内置的语言实现；
	　  2）synchronized在发生异常时，会自动释放线程占有的锁，因此不会导致死锁现象发生；而Lock在发生异常时，
	            如果没有主动通过unLock()去释放锁，则很可能造成死锁现象，因此使用Lock时需要在finally块中释放锁；
	　  3）Lock可以让等待锁的线程响应中断，而synchronized却不行，使用synchronized时，等待的线程会一直等待下去，不能够响应中断；
	  　4）通过Lock可以知道有没有成功获取锁，而synchronized却无法办到。
	　  5）Lock可以提高多个线程进行读操作的效率。
　　在性能上来说，如果竞争资源不激烈，两者的性能是差不多的，而当竞争资源非常激烈时（即有大量线程同时竞争），
        此时Lock的性能要远远优于synchronized。因此，在具体使用时要根据适当情况选择。
 *
 */

public class RunnableTest implements Runnable {
	
	//创建lock锁
	Lock lock=new ReentrantLock();//非公平锁  非公平锁就是一种获取锁的抢占机制，是随机获得锁的，和公平锁不一样的就是先来的不一定先得到锁，这个方式可能造成某些线程一直拿不到锁，结果也就是不公平。
//	Lock lock=new ReentrantLock(true);//公平锁表示线程获取锁的顺序是按照线程加锁的顺序来分配的，即先来先得的FIFO先进先出顺序。

	int a=0;
	
	/**
	 * synchronized进行同步有四种情况
		第一种：修饰一个代码块，被修饰的代码块称为同步语句块，其作用的范围是大括号{}括起来的代码，作用的对象是调用这个代码块的对象；
		第二种：修饰一个方法：被修饰的方法称为同步方法，其作用的范围是整个方法，作用的对象是调用这个方法的对象；
		第三种：修饰一个静态的方法：其作用的范围是整个静态方法，作用的对象是这个类的所有对象；
		第四种：修饰一个类：其作用的范围是synchronized后面括号括起来的部分，作用主的对象是这个类的所有对象。
	 */
	
	public void run() {
		/*try {
		 *  System.out.println("当前线程："+Thread.currentThread().getName()+",已经运行");
		 *  //synchronized 同步锁，锁当前对象
			synchronized (this) {
				Thread.currentThread().sleep(3000);
				System.out.println("当前线程："+Thread.currentThread().getName()+";a="+a);
				a++;
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		
		test();
		
	}
	
	
	public void test(){

		try {
			System.out.println("当前线程："+Thread.currentThread().getName()+",已经运行");
			//当前代码加锁
			lock.lock();
			
			Thread.currentThread().sleep(1000);
			System.out.println("当前线程获得锁："+Thread.currentThread().getName()+";a="+a);
			a++;
			
		} catch (InterruptedException e) {
			if(lock!=null){
				//释放锁
				lock.unlock();
			}
			e.printStackTrace();
		} finally {
			if(lock!=null){
				//释放锁
				lock.unlock();
			}
		}
	}
	
	public static void main(String[] args) {
		
		RunnableTest test=new RunnableTest();
		
		for (int i = 0; i < 10; i++) {
			new Thread(test).start();;
		}
		
	}

}
