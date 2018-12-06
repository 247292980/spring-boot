package com.lgp.binarytree;

/**
 * @AUTHOR lgp
 * @DATE 2018/11/23 9:54
 * @DESCRIPTION
 **/
public class TreeNode {
    public int key;
    public String data;
    public TreeNode leftChild;
    public TreeNode rightChild;
    public boolean isVisted = false;

    public TreeNode(int key, String data) {
        this.key = key;
        this.data = data;
    }

    public TreeNode(int key, String data, TreeNode leftChild, TreeNode rightChild) {
        this.key = key;
        this.data = data;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }
}
