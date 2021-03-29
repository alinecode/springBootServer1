package com.hello.store.test.service.algorithm;

import java.util.Arrays;

/**
 * 排序算法
 * 
 * 这里发现一个坑：System.err.println和System.out.println不能在一个方法里同时使用，否则输出顺序是乱的（
 * 因为源码有synchronized，所以不混用时都是同步的）
 * 
 * @author AL
 *
 */
public class PX {

	public static void main(String[] args) {
//		printtest();
		maopao();
//		int[] aa = { 100,101,55,100,2 };
////		quick(aa, 0, aa.length-1);
//		quickpx(aa, 0, aa.length - 1);
//		System.err.println(Arrays.toString(aa));
		
//		kspxcs();
		
	}

	/**
	 * 测试快速排序
	 */
	private static void kspxcs() {
		
		int[] arr = new int[100000];
		for (int i=0;i<100000;i++){
            arr[i] = (int)(Math.random()*100000);//生成一个有十万个数的数组
        }
		long currentTimeMillis = System.currentTimeMillis();
		System.err.println("排序前："+currentTimeMillis);
		
		quickpx(arr, 0, arr.length-1);
		
		long currentTimeMillis2 = System.currentTimeMillis();
		System.err.println("排序后："+currentTimeMillis2);
		
		System.err.println("差值："+(currentTimeMillis2-currentTimeMillis));
		
		
	}
	
	/**
	 * 快速排序 -new
	 * @param arr
	 * @param start
	 * @param end
	 */
	static synchronized void quickpx(int[] arr, int start, int end) {

//		System.err.println("*****能否排序："+Arrays.toString(arr));
		if (start < end) {
//			System.err.println("√√√√√可以排序");

			int stand = arr[start];  // 小的放到stand左边，大的放到stand右边，这就是stand的意义
			// 开始位置
			int low = start;
			// 结束位置
			int high = end;

			while (low < high) { // 一直会循环到第一次左右下标碰撞。这个while循环实际上在做一件事：从右边找到小的、从左边找到大的、
								// 还有一个固定值stand，
								// 这三个角色之间互相交换顺序。寻找小的和大的值过程中，在不断向中间靠拢，一直到中间下标冲突的时候，把stand放进去
								// 这样就完成了一次找排序。排序的结果是，找到了stand的固定位置。
				
//				String string = "low：" + low;
//				String endsString = "==high：" + high;
//				System.err.println("初始高低位："+endsString+" "+string+"，stand值："+stand+"，高位值："+arr[high]+"，低位值："+arr[low]);
				while (low < high && arr[high] >= stand) {
					high--; // 如果数组高位往左移的下标还没有超过左边的low位，而且高位的值要大一点，那么就继续数组继续往左边找小的值。因为
					// 大的总在右边所以大的就不用管。
				}
				arr[low] = arr[high];  // 数组右边的(第一次排序也可能是stand自己)把低位的(第一次是stand，后来每一次都是往后移动的一位)覆盖掉
				
//				System.err.println("====高位找到并赋值："+Arrays.toString(arr));
				
				while (low < high && arr[low] <= stand) {
					low++; // 如果数组低位向右移的下标还没有超过右边的high位，而且... 反正目的是寻找左边大的值。
				}
				arr[high] = arr[low]; // 把左边大的值赋给右边
				
//				System.err.println("====低位找到并赋值："+Arrays.toString(arr));
//				
//				String lowsString = "low：" + low;
//				String highsString = "==high：" + high;
//				System.err.println("然后高低位："+highsString+" "+lowsString+"，stand值："+stand+"，高位值："+arr[high]+"，低位值："+arr[low]);
				
//				System.err.println("一一次循环完毕：" + Arrays.toString(arr));
//				System.err.println(highsString);
//				System.err.println(lowsString);
				
			}

			arr[low] = stand;
//			System.err.println(new StringBuffer().append("---stand赋值：" + Arrays.toString(arr)));
			quickpx(arr, start, low);
			quickpx(arr, low + 1, end);
		}else {
//			System.err.println("xxxxx不能排序：");
		}
	}

	/**
	 * 冒泡排序：两个两个比较，大的放右边(或者左边)继续和右边的比较，直到把最大(或者最小)的放到最右边，依次排序。
	 * 过程就像冒泡一样，一个泡从水里冒到表面。所以叫冒泡排序
	 * 
	 * 冒泡排序的特点是：一定是相邻的两个作比较，一步一步确定位置(最值)一定在两边
	 * 
	 */
	private static void maopao() {

		// 双循环的构建流程：
		// 先写一个循环，数量是数组的全部；然后进行比较大小交换顺序；可以发现一趟循环之后只能找到最大(或者最小)的，
		// 而且位置处于数组的末尾。
		// 所以我们要接着循环，因为末尾数字已经排好了，所以不参与循环，一趟循环完毕后就把已经循环完毕的数量减去接着循环

		int[] aa = { 2, 55, 12, 66, 99, 100 };
		System.err.println("原始数据：" + Arrays.toString(aa)); // 查看初始数据
		for (int i = 0; i < aa.length - 1; i++) { // i表示已经排好序的有多少个。i在下列循环中的应用就是排除。
			for (int j = 0; j < aa.length - 1 - i; j++) {// 循环已经排好的一个数字前面的所有数字。
				// ...
				int temp;
				if (aa[j + 1] > aa[j]) { // 当前数字比后面一个数字小，那么就把后面的小的放后面去放着。也就是从大到小排
					temp = aa[j + 1]; // 把大的备份
					aa[j + 1] = aa[j]; // 因为大的已经备份了，所以把小的放到大的位置上，把大的覆盖掉
					aa[j] = temp; // 把备份数据放到小的位置上，也就是大的放到前面
				}
				System.err.println(j + "：" + Arrays.toString(aa)); // 查看排序之后的数据
			}
		}

		System.err.println("最终：" + Arrays.toString(aa));

	}

	/**
	 * 快速排序算法 -old
	 * 
	 * @param arr   被排序的数组
	 * @param start 快速排序的起始位置的数组下标
	 * @param end   快速排序的结束位置的数组下标 https://zhuanlan.zhihu.com/p/85966716
	 */
	static synchronized void quick(int[] arr, int start, int end) {
//    	System.err.println("线程名称："+Thread.currentThread().getName());

//    	System.out.println(new StringBuffer().append("比较的初始数据："+Arrays.toString(arr)));

		// 如果开始位置和结束位置重合，实际上就是一个数字，所以开始位置一定要比结束位置小，而且不能相等
		if (start < end) {
			// 设定标准数，也就是快速排序的过程中，和谁进行比较，通常用第一个数字即可
			// 注意这里用的是arr[start],按说在第一次的时候是0，应该写成arr[start],但是不可以
			// 因为快速排序是一个递归的操作，第二次进来的时候，就不是arr[0]了
			int stand = arr[start];

			// 开始找开始位置和结束位置
			// 我们知道快速排序其实就是有一个开始位置和一个结束位置，当开始位置比选定的标准数字大的时候，就会替换掉
			// 结束位置的数字，这个时候结束位置的下表开始向前移动，然后如果结束位置的数字比标准数字，则替换开始位置的数字
			// 在循环的过程中如果开始位置/结束位置的数字 不比标准数字大或者小，这个时候开始位置和结束位置就会向后/向前移动

			// 开始位置
			int low = start;
			// 结束位置
			int high = end;

			while (low < high) { // 位置还没有到重合的时候al

				String string = "start：" + start;
				String endsString = "end：" + end;

				System.err.println(string);
				System.err.println(endsString);

				// 这个时候我们已经确定了开始位置和结束位置

				// 第一次是要拿高位和低位进行比较,如果高位比标准数字大，则高位则向前移动一位
				while (low < high && arr[high] > stand) {
					high--;
				}
				// 当然了并不是所有高位数字都比低位大，如果比低位要小，则这个数字要覆盖低位的数字
				arr[low] = arr[high];
//                System.err.println(new StringBuffer().append("高位比较后数据："+Arrays.toString(arr)));
//                System.err.println("高位比较后数据："+Arrays.toString(arr));
//                System.out.println(new StringBuffer().append("被比较的高位数据："+arr[high]));
//                System.out.println("被比较的高位数据："+arr[high]);

				// 然后这个时候低位开始移动，如果低位比标准数字小，则低位的下标向后移动一位
				while (low < high && arr[low] < stand) {
					low++;
				}

				// 当然了并不是所有时候低位都比标准数字要小，如果比标准数字大的话，这个时候就要替换掉高位的数字
				arr[high] = arr[low];
//                System.err.println(new StringBuffer().append("低位比较后："+Arrays.toString(arr)));
//                System.out.println(new StringBuffer().append("被比较的低位数据："+arr[low]));
//                System.err.println(new StringBuffer().append("low<high? "+(low<high)));
			}

			// 经过上面的一番折腾，这个时候就会出现一个情况，低位和高位相同，那么这个位置就用标准数字去替换
			// 这里low和high实际上是相同的数字，所以写哪个都无所谓
			arr[low] = stand;
			System.err.println(new StringBuffer().append("重合后：" + Arrays.toString(arr)));
			// 然后第一轮排序完毕，我们就会发现以标准数字为分界线，我们有两个学列了，这个时候，我们就调用递归来
			// 分别对两个序列进行排序

			// 先出来低位的递归,最后的位置实际上有可能是高位，有可能是低位，既然上面写的是低位，这里就写低位就好了
			quick(arr, start, low);
			// 然后出来高位的数字
			quick(arr, low + 1, end);
		}
	}

	/**
	 * System.err和System.out混用测试
	 */
	private static void printtest() {
		
		int i = 0;
		while (i<10) {
			System.err.println(i);
			System.out.println(i);
//			System.err.println(i);
//			System.out.println(i);
			i++;
		}
		
//		int j = 20;
//		while (j<30) {
////			System.err.println(j);
//			System.out.println(j);
////			System.err.println(j);
////			System.out.println(j);
//
//			j++;
//		}
		

	}
	
}
