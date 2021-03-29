package com.hello.store.test.service.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
 
/**
 * 简单二叉树
 * @author AL
 *
 */
public class BinTree {
    public BinTree left;
    public BinTree right;
    public BinTree root;
//    数据域
    private Object data;
    //    存节点
    public List<BinTree> datas;

    int ii = 0;
    
    public BinTree(BinTree left, BinTree right, Object data){
        this.left=left;
        this.right=right;
        this.data=data;
    }
//    将初始的左右孩子值为空
    public BinTree(Object data){
        this(null,null,data);
    }
 
    public BinTree() {
    	

    }
 
    public void creat(Object[] objs){
    	
        datas = new ArrayList<BinTree>();
        //        将一个数组的值依次转换为Node节点
        for(Object o:objs){
            datas.add(new BinTree(o));
        }
//        第一个数为根节点
        root=datas.get(0);
//        建立二叉树
        for (int i = 0; i <objs.length/2; i++) {  // 一半就行
//            左孩子
            datas.get(i).left=datas.get(i*2+1); // 按顺序依次 从上到下从左到右 排。下标为奇数，实际为偶数的作为左孩子
//            右孩子
            if(i*2+2 < datas.size()){//避免偶数的时候 下标越界
                datas.get(i).right=datas.get(i*2+2);
            }
        }
        
        System.err.println(datas);
        
    }
//先序遍历
public void preorder(BinTree root){
	
	ii++;
	System.out.println("执行次数："+ii);
	
    if(root!=null){
        System.out.println(root.data);
        preorder(root.left);
        preorder(root.right);
    }
}
//后序遍历
public void afterorder(BinTree root){
	
	ii++;
	System.out.println("执行次数："+ii);
	
  if(root!=null){ // 左孩子/右孩子为空，不执行输出
      afterorder(root.left);
      afterorder(root.right);
      System.out.println(root.data);
  }
}

//中序遍历
    public void inorder(BinTree root){
    	
    	ii++;
    	System.out.println("执行次数："+ii);
    	
        if(root!=null){
            inorder(root.left);
            System.out.println(root.data);
            inorder(root.right);
        }
    }

    public static void main(String[] args) {
        BinTree bintree=new BinTree();
        Object []a={2,4,5,7,1,6,12,32,51,22};
        bintree.creat(a);
//        System.err.println(bintree);
//        bintree.preorder(bintree.root);
//        bintree.inorder(bintree.root);
        bintree.afterorder(bintree.root);
    }
    
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	
//    	System.err.println(left);
    	
    	StringBuffer mString = new StringBuffer();
    			
    	mString.append(	"<data:"+data.toString());
    	
    	if (left != null) {
    		mString.append(
    		"左边："+left.data.toString());
		}
    	if (right != null) {
    		mString.append(
    				"右边："+right.data.toString());
    	}
    	
    	mString.append(">");
    	
    	return mString.toString();
    }
    
}

//要求：从键盘输入数，存为二叉树结构并打印。