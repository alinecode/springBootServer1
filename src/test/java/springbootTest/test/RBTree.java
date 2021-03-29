package springbootTest.test;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 红黑树
 * 
 * @author AL
 *
 */
public class RBTree {

	public static void main(String[] args) {

//		name();
//		a2();
//
//		Map<Thread, StackTraceElement[]> maps = Thread.getAllStackTraces();
//		for (Thread thread : maps.keySet()) {
//			System.err.println(thread.getId() + "--" + thread.getName() + "---" + thread.getPriority());
//
//		}
		a3();
//		Thread t = Thread.currentThread();
//        System.out.println(t.getName());

	}

	public static void a3(){
		
		final HashMap<String, String> firstHashMap = new HashMap<String, String>();
		Thread t1=new Thread(){
		    public void run() {
		        for(int i=0;i<25;i++){
		            firstHashMap.put(String.valueOf(i), String.valueOf(i));
		        }
		    }
		};
		
		Thread t2=new Thread(){
		    public void run() {
		        for(int j=25;j<50;j++){
		            firstHashMap.put(String.valueOf(j), String.valueOf(j));
		        }
		    }
		};
		 
		t1.start();
		t2.start();

		//主线程休眠1秒钟，以便t1和t2两个线程将firstHashMap填装完毕。
		try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int l=0;l<50;l++){
		    //如果key和value不同，说明在两个线程put的过程中出现异常。
		    if(!String.valueOf(l).equals(firstHashMap.get(String.valueOf(l)))){
		        System.err.println(String.valueOf(l)+":"+firstHashMap.get(String.valueOf(l)));
		    }
		}

	}
	
	public static void name() {
		String aaString = "aa";

		TreeMap<String, String> map = new TreeMap<>();
		map.put(aaString, "2");
		map.put(null, "2");
		map.put("bb", "2");
		map.get("");
		System.err.println(map.toString());
		map.remove("");

		HashMap<String, String> map2 = new HashMap<>();
		map2.put("", "");
		ConcurrentHashMap<String, String> map3 = new ConcurrentHashMap<>();
		map3.put("", "");
	}

	private static void a2() {
		HashMap<String, String> map2 = new HashMap<>();
		Long long1 = System.currentTimeMillis();
		System.err.println("开始时间：" + long1);
		for (int i = 0; i < 100000; i++) {

			map2.put(String.valueOf(i), String.valueOf(i));
		}

		Long long2 = System.currentTimeMillis();
		System.err.println("结束时间：" + long2);
		System.err.println("所用时：" + (long2 - long1));
		System.err.println("map2.size：" + map2.size());

		
		Thread t = Thread.currentThread();
        System.out.println(t.getName());
//        System.out.println((n & (n - 1)) == 0);
	}

	
	
}
