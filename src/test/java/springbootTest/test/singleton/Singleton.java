package springbootTest.test.singleton;

/**
 * 
 * 单例模式
 * @author 
 *
 */
public class Singleton {

	/**
	 * 这是饿汉式。还有一个懒汉式，就是再写一个方法,同样是private static final Singleton INSTANCE，然后new，
	 * get方法内，通过 return SingletonHolder.INSTANCE; 返回实例行了。
	 * singleton
	 */
	private Singleton() {  // 不让new
		
	}
	
	private static final Singleton singleton = new Singleton();  // 仅加载这一个
	
	public static Singleton getInstance() {
		
		return singleton;
		
	}
	
	
}
