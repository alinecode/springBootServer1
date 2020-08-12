package springbootTest.test.refTest;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射测试  spring 框架的IoC(也就是控制反转) 就是大量的使用反射来加载类
 * @author 
 * https://blog.csdn.net/mlc1218559742/article/details/52774805
 */
public class ReflectTest {
	
	/**
	 * 
	 *打印出String类所有的属性和方法
	 */
	public void test1(){
		Class<String> c = String.class;
		Method[] methods = c.getMethods();
		for(int i = 0;i<methods.length;i++){
			System.out.println(methods[i].getName());
		}
		Field[] fields = c.getFields();
		for(Field f : fields){
			System.out.println(f.getType()+":"+f.getName());
		}
	}
	
	/**
	 * 根据类名动态创建类的对象
	 * @throws Exception
	 */
	public void test2() throws Exception{
		Class<?> c = Class.forName("springbootTest.test.refTest.Student");
		Student student = (Student)c.newInstance();
		student.setName("java");
		student.setId("1001");
		student.show();
	}
	
	/**
	 * 根据类名和方法名，执行对象的方法
	 * @param student
	 * @param method
	 * @param value
	 * @throws Exception
	 */
	public void test3(Student student,String method,String value) throws Exception{
		String s1 = method.substring(0,1).toUpperCase();
		String s2 = method.substring(1);
		String m = "set"+s1+s2;
		System.out.println(m);
		Class<? extends Student> c = student.getClass();
		Method set = c.getMethod(m,new Class[]{String.class});
		set.invoke(student,new Object[]{value});
	}
	
	/**
	 * 动态创建数组对象，对数组元素复制和取值
	 */
	public void test4(){
		try{
			Class<?> cls = Class.forName("java.lang.String");
			//创建一个String类型的数组，大小为10
			Object arr = Array.newInstance(cls, 10);
			//在数组5索引的位置赋值
			Array.set(arr, 5, "this is a test");
			//获取数组5索引位置的值
			String s = (String)Array.get(arr, 5);
			System.out.println(s);
		}catch(Throwable e){
			System.out.println(e);
		}
	}
}

