package com.hello.store.test.service.algorithm;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * 几个Map的分析
 * @author AL
 *
 */
public class MapAl {

	public static void main(String[] args) {
		
	}
	
	/**
	 * HashMap 是最常用的Map了。
	 * 
	 * 其数据结构是数组+链表+红黑树。hash表常见的实现方式就是数组加链表，不过这jdk1.8加上了个红黑树。
	 * 数组查询效率很高，因为地址连续。链表插入和修改很快。红黑树查询速度非常快。综合起来就是综合速度要快。
	 * 线程是不安全的。比如spring controller的类有一个hashMap的成员变量，当多个用户同时操作这个hashMap时可能有key和value不对应的情况。
	 * @see springbootTest.test.RBTree 的a3() 验证了线程不安全所产生的后果。
	 * 
	 * 因为是计算的hash然后插入的，所以是没有顺序的。
	 * 
	 * new的过程： 默认16位数组长度，扩容因子0.75。
	 * 构建(put)过程：计算key的int类型的hash、hash对数组长度取模(余数)、把节点放到数组内的这一格：数组的下标等于该余数、
	 * 解决hash冲突：余数下标所在的数组已经有节点了，用该节点指向新的节点，也就是单向链表的结构、
	 * 解决访问速度慢的问题：当数组长度大于8的时候，就把链表数据转换成红黑树、当小于6的时候在转换成链表，相关源码解释：
	 * @see java.util.HashMap 的TREEIFY_THRESHOLD
	 * 
	 */
	public static void hashMapAl() {
		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("a", "");
		System.err.println(hashMap);
	}

	/**
	 * LinkedHashMap 和 HashMap联系最紧密，所以挨着一起来说。
	 * 其实就是多了两个指向前后节点的指针。
	 * 该Map继承自HashMap(简单说就是继承了HashMap的public等方法，比如put方法)，但是加了一些东西(节点加上了前节点和后节点)，弄成了一个双向链表。
	 * 
	 * 虽然put使用的是hashMap的put方法，但是此Map重写了put里面的newNode的方法，这样自然就可以使用新增的东西了。
	 * newNode把新的节点放到了双向链表的尾端:
	 * @see java.util.LinkedHashMap.newNode(int, K, V, Node<K, V>)
	 * @see java.util.LinkedHashMap.linkNodeLast(Entry<K, V>)
	 * 
	 */
	public static void linkedHashMapAl(){
		LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
		linkedHashMap.put("a", ""); // 使用hashMap的put，重写了put里面的newNode等方法。
		linkedHashMap.get("a");  // 重写了get方法，按条件往链表后面移动被get到的节点。
		linkedHashMap.remove("a");  // 使用hashMap的remove，实现了移除之后的动作afterNodeRemoval
		System.err.println(linkedHashMap);
	}
	
}







