package com.lgp.netty.dto;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @AUTHOR lgp
 * @DATE 2018/11/23 9:33
 * @DESCRIPTION
 **/
public class BinTree {
    private char date;
    private BinTree lchild;   //左孩子
    private BinTree rchild;   //右孩子

    private BinTree(char c) {
        date = c;
    }

    public static void BFSOrder(BinTree t) {
        if (t == null) {
            return;
        }
        Queue<BinTree> queue = new ArrayDeque<BinTree>();
        //队列小知识：使用offer和poll优于add和remove之处在于它们返回值可以判断成功与否，而不抛出异常
        queue.offer(t);              //进入队列
        while (!queue.isEmpty()) {
            t = queue.poll();           //当前节点出队列
            System.out.print(t.date);
            //当前节点左孩子去排队，在前面哦
            if (t.lchild != null) {
                queue.offer(t.lchild);
            }
            //右孩子排第二
            if (t.rchild != null) {
                queue.offer(t.rchild);
            }
        }
    }

    public static void main(String[] args) {
        BinTree b1 = new BinTree('a');
        BinTree b2 = new BinTree('b');
        BinTree b3 = new BinTree('c');
        BinTree b4 = new BinTree('d');
        BinTree b5 = new BinTree('e');
        BinTree b6 = new BinTree('f');
        BinTree b7 = new BinTree('g');

        /**
         *      a
         *    /   \
         *   b     c
         *  / \   / \
         * d   e f   g
         */
        b1.lchild = b2;
        b1.rchild = b3;
        b2.lchild = b4;
        b2.rchild = b5;
        b3.lchild = b6;
        b3.rchild = b7;

        BinTree.BFSOrder(b1);
        System.out.println();
    }
}
