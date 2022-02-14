//package springbootTest.test;
//
//import java.sql.Timestamp;
//import java.util.Calendar;
//
//public class CommTest {
//
//	public static void main(String[] args) {
//		
//		System.err.println(new Timestamp(System.currentTimeMillis()));
//		System.err.println(new Timestamp(Calendar.getInstance().getTimeInMillis()));
//		
////		Predicate<String> predicate = (String aa) -> {
////			return "aaa".equals(aa);
////		};
////		System.err.println(predicate.test("aaa"));
//		
////		List<String> list = new ArrayList<String>();
////		list.add("F1");
////		list.add("F2");
////		list.add("F3");
////		
////		for (String temp : list) {
////			if ("F3".equals(temp)) {
////				list.remove(temp);
////			}
////		}
////		
////		for (String string : list) {
////			System.err.println(string);
////		}
//		
////		String bString = "0";
////		Integer a = 0;
////		System.err.println(bString.equals(a));
//		
////		List<String> list1 = new ArrayList<>();
////
////		list1.add("1");
////		list1.add("2");
////		list1.add("3");
////		
////		List<String> list2 = new ArrayList<>();
////		
////		list2.addAll(list1);
////		
////		list2.remove(0);
////		
////		for (int i = 0; i < list1.size(); i++) {
////			System.err.println(list1.get(i));
////		}
//		
////		list.add("a");
////
////		for (int i = 0; i < list.size() - 1; i++) {
////			System.err.println("tt"+list.size());
////			for (int j = list.size() - 1; j > i; j--) {
////				System.err.println("aa"+list.size());
////				if (list.get(j).equals(list.get(i))) {
////					list.remove(j);
////				}
////			}
////		}
//
////		System.err.println(list.size());
//		
////    	String iString = new String("你") ;
////    	String iString2 = "你";
////    	
////    	Object object = iString;
////    	Object object2 = iString2;
////    	
////    	boolean equals = object.equals(object2);
////		
////    	System.err.println(equals);
//
////		double aa = 6.5444444256666;
////		
////		Float abcFloat = (float) aa;
////		
////		System.err.println(abcFloat);
//
////		int c = 5;
////		int a = 1;
////		int b = (a+c++);
////		System.err.println(b);
////		System.err.println(a+c);
////		String bString = new String("ab");
////		String aString = new String("ab");
////		String aString = "aab";
//
////		System.err.println(aString == bString);
////		conCase("dkfjsdl");
//
////		shuixianhua2(aString);
////		
////		System.err.println(aString);
//
////		T1 t1 = new T1();
////		
////		shuixianhua3(t1);
////		
////		System.err.println(t1.string);
////		shuixianhua();
//
////		String string = "123.5";
////		String[] split = string.split("\\.");
////		System.err.println(split[0]);
////		string.split(",");
//
//	}
//
//	public static T1 shuixianhua3(T1 aa) {
//
////		aa.string = "123";
////
//		return aa;
//
////		System.err.println(aa == "aab");
//
//	}
//
//	public static String shuixianhua2(String aa) {
//		aa = "123";
//
//		return aa;
//
////		System.err.println(aa == "aab");
//
//	}
//
//	public static void shuixianhua() {
//
//		int a = 999;
//
////		int dangqian = a; // 当前数字
//
//		while (a > 99) {
//
//			int a1 = a % 10; // 个位
//			int a2 = a / 10 % 10; // 十位
////			int a3 = a / 100 % 10 ; // 百位 都一样
//			int a3 = a / 100; // 百位
//
//			if (Math.pow(a1, 3) + Math.pow(a2, 3) + Math.pow(a3, 3) == a) {
//
//				System.err.println(a);
//
//			}
//
//			a--;
//		}
//
//	}
//
//	public static void conCase(String str) {
//
//		// 转换指定字符为大写
//		char[] charArray = str.toCharArray();
//		charArray[0] -= 32;
//		charArray[1] -= 32;
//
//		char c = charArray[0];
//
//		int b = c + 32;
//
////		instance
//
////		if (charArray[0] instanceof type) {
////			type new_name = (type) charArray[0];
////			
////		}
//
//		System.err.println(charArray[0]);
//
//		str = String.valueOf(charArray);
//		System.out.println(charArray);
////		System.out.println(str);
//
//		// 转换指定字符为小写
//		charArray[0] += 32;
//		charArray[1] += 32;
//		str = String.valueOf(charArray);
////		System.out.println(str);
//
//		// 全部转大写或小写
////		System.out.println(str.toLowerCase());
////		System.out.println(str.toUpperCase());
//
//	}
//
//	/**
//	 * 2的出现次数
//	 * 
//	 * @param n
//	 * @return
//	 */
//	public static int count2(int n) {
//		if (n < 2)
//			return 0;
//		int count = 0;
//		int base = 1; // 初始权值。也就是处于个十百千哪一位
//		int round = n; // n移动位置后的值。也就是随着上面权值的变化，截取(权值所处的数字)后剩下的数字。
//
//		while (round > 0) {
//
//			System.err.println(round);
//
//			int weight = round % 10; // 取该位后面的数字
//
//			round /= 10; // 除以10后，取前面的数字。也就是找高位。此处控制循环次数,因为修改了round的值到最后不大于0
//
//			count += round * base; // 找规律的计算。高位 x 当前权值，就等于，所有的比当前权值低的位低位,不是等于0的值，的出现次数。比如10这个值,
////			1x10 = 10，就表示等于所有1-9出现的所有次数，都是10次
//			System.err.println("base " + base);
//			System.err.println("round " + round);
//			System.err.println("count " + count);
//			System.err.println("weight " + weight);
//
////			2*base + ;
//
//			if (weight == 2) // 如果当前这个值等于2，也就是统计的2的个数
//				count += (n % base) + 1; // 那么，2这个数字，出现在计算的这个数字的当前权位上，的次数为，低位出现的次数(所以此处是取该位后面的余数)，加上等于2的这1次，再加上面高位的基本计算。
//			else if (weight > 2)
//				count += base; // 如果比2要多，说明当前位，出现2的次数，肯定是出现了 2*权值
//								// 这么多次。比如256，中间的5比2大，说明肯定已经过了2了，变化次数是10次，也就是出现了且只出现了10次
//			base *= 10; // 继续下一个数字
//		}
//		return count;
//	}
//
//	/**
//	 * 1-n的自然数中，1出现了多少次
//	 * 
//	 * @param n
//	 * @return
//	 */
//	public static int count1(int n) {
//		if (n < 1)
//			return 0;
//		int count = 0;
//		int base = 1; // 初始权值。也就是处于个十百千哪一位
//		int round = n;
//
//		while (round > 0) {
//
//			System.err.println(round);
//
//			int weight = round % 10; // 往前移动一位
//
//			round /= 10; // 除以10后，取前面的数字。也就是找高位。此处也为控制循环次数
//
//			count += round * base; // 找规律的计算。高位 x 当前权值，所有的不是等于0的这个值，的出现次数。比如10这个值,
////			1x10 = 10，就表示等于所有1-9出现的所有次数，都是10次
//
//			if (weight == 1) // 如果当前这个值等于1，也就是统计的1的个数
//				count += (n % base) + 1; // 那么，1这个数字，出现在计算的这个数字的当前权位上，的次数为，低位出现的次数，加上等于1的这1次，再加上面高位的基本计算。
//			else if (weight > 1)
//				count += base; // 如果比1要多，说明当前位，出现1的次数，肯定是出现了 1*权值这么多次
//			base *= 10; // 继续下一个数字
//		}
//		return count;
//	}
//
//	public static void minname() {
//
//		// 写一个循环程序，算出计算机一秒钟的时间内循环的次数。
//		long i = 0, startTime = System.currentTimeMillis();
//		while (System.currentTimeMillis() - startTime < 1000) {
//
//			i++;
//
//		}
//
//		System.out.println("一秒钟的时间内循环" + i + "次");
//
//		// 下面是循环执行时每休眠一毫秒时,每秒钟for循环可以执行多少次
////				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
////				long count=0;
////				for(;;){
////					String sentTime1 = sdf.format(new java.util.Date());
////					try {
////						Thread.sleep(1L);
////					} catch (InterruptedException e) {
////						// TODO Auto-generated catch block
////						e.printStackTrace();
////					}
////					String sentTime2 = sdf.format(new java.util.Date());
////					
////					if(sentTime1.equals(sentTime2)){
////						count++;
////					}else{
////					System.out.println("一秒钟循环了:"+count+"次!");
////					count=0;
////					}
////				}
//
//	}
//
//	public static int mytest(int n) {
//		if (n < 2)
//			return 0;
//
//		int count = 0;
//		int qz = 1;
//		int jiequ = n;
//
//		while (jiequ > 0) {
//
//			int zh = jiequ % 10; // 最后这个数字。也就是当前权所在位置的值。
//
//			jiequ /= 10; // 把n这个字符串往前移动一个位置
//
//			count += (jiequ * qz); // 前面的值，乘以当前权值，等于当前位置，2的出现数量。（实际大概1-9所有的都一样）
//
//			// 如果小于2，那么此处仅会出现高位*权重 的 次数。上面已经计算了。
//
//			// 如果要判断的这个位置的值就等于2，那么就计算它的低位数字，就等于2在该权位置的变换次数，变换一次，就有一个2出现
//			if (zh == 2) {
//				// 所以使用源数字n除以已有权重取余，就可以得到低高位数字，再加上本来的
//				count += n % qz + 1;
//			} else if (zh > 2) {
//				// 如果比2还要大，
//				count += qz;
//			}
//
//			qz *= 10; // 权值向左移动一位
//
//		}
//
//		return count;
//	}
//
//}
