package com.lgp.binarytree;

/**
 * @AUTHOR lgp
 * @DATE 2018/11/23 9:54
 * @DESCRIPTION
 **/

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

@SuppressWarnings("ALL")
public class BinaryTree {

    private TreeNode root = null;

    public BinaryTree() {
        root = new TreeNode(1, "rootNode(A)");
    }

    /**
     * 创建一棵二叉树
     *           A
     *     B          C
     *  D     E            F
     * 	 X	M   N
     * @param root
     */
    public void createBinTree(TreeNode root) {
        TreeNode newNodeB = new TreeNode(2, "B");
        TreeNode newNodeC = new TreeNode(3, "C");
        TreeNode newNodeD = new TreeNode(4, "D");
        TreeNode newNodeE = new TreeNode(5, "E");
        TreeNode newNodeF = new TreeNode(6, "F");
        root.leftChild = newNodeB;
        root.rightChild = newNodeC;
        root.leftChild.leftChild = newNodeD;
        root.leftChild.rightChild = newNodeE;
        root.rightChild.rightChild = newNodeF;
        root.leftChild.rightChild.leftChild = new TreeNode(7, "M");
        root.leftChild.rightChild.rightChild = new TreeNode(8, "N");

        root.leftChild.leftChild.rightChild = new TreeNode(9, "X");
    }

    public boolean isEmpty() {
        return root == null;
    }

    //树的高度
    public int height() {
        return height(root);
    }

    //节点个数
    public int size() {
        return size(root);
    }

    private int height(TreeNode subTree) {
        if (subTree == null) {
            //递归结束：空树高度为0
            return 0;
        } else {
            int i = height(subTree.leftChild);
            int j = height(subTree.rightChild);
            return (i < j) ? (j + 1) : (i + 1);
        }
    }

    private int size(TreeNode subTree) {
        if (subTree == null) {
            return 0;
        } else {
            return 1 + size(subTree.leftChild)
                    + size(subTree.rightChild);
        }
    }

    //返回双亲结点
    public TreeNode parent(TreeNode element) {
        return (root == null || root == element) ? null : parent(root, element);
    }

    public TreeNode parent(TreeNode subTree, TreeNode element) {
        if (subTree == null) {
            return null;
        }
        if (subTree.leftChild == element || subTree.rightChild == element) {
            //返回父结点地址
            return subTree;
        }
        TreeNode p;
        //现在左子树中找，如果左子树中没有找到，才到右子树去找
        if ((p = parent(subTree.leftChild, element)) != null) {
            //递归在左子树中搜索
            return p;
        } else {
            //递归在右子树中搜索
            return parent(subTree.rightChild, element);
        }
    }

    public TreeNode getLeftChildNode(TreeNode element) {
        return (element != null) ? element.leftChild : null;
    }

    public TreeNode getRightChildNode(TreeNode element) {
        return (element != null) ? element.rightChild : null;
    }

    public TreeNode getRoot() {
        return root;
    }

    //在释放某个结点时，该结点的左右子树都已经释放，
    //所以应该采用后续遍历，当访问某个结点时将该结点的存储空间释放
    public void destroy(TreeNode subTree) {
        //删除根为subTree的子树
        if (subTree != null) {
            //删除左子树
            destroy(subTree.leftChild);
            //删除右子树
            destroy(subTree.rightChild);
            //删除根结点
            subTree = null;
        }
    }

    public void visted(TreeNode subTree) {
        subTree.isVisted = true;
        System.out.println("key:" + subTree.key + "--name:" + subTree.data);
    }

    //前序遍历
    public void preOrder(TreeNode subTree) {
        if (subTree != null) {
            visted(subTree);
            preOrder(subTree.leftChild);
            preOrder(subTree.rightChild);
        }
    }

    //中序遍历
    public void inOrder(TreeNode subTree) {
        if (subTree != null) {
            inOrder(subTree.leftChild);
            visted(subTree);
            inOrder(subTree.rightChild);
        }
    }

    //后续遍历
    public void postOrder(TreeNode subTree) {
        if (subTree != null) {
            postOrder(subTree.leftChild);
            postOrder(subTree.rightChild);
            visted(subTree);
        }
    }

    //前序遍历的非递归实现  ABDXEMNCF
    public void nonRecPreOrder(TreeNode p) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = p;
        while (node != null || stack.size() > 0) {
            while (node != null) {
                visted(node);
                stack.push(node);
                node = node.leftChild;
            }
            if (stack.size() > 0) {
                node = stack.pop();
                node = node.rightChild;
            }
        }
    }

    //中序遍历的非递归实现
    public void nonRecInOrder(TreeNode p) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = p;
        while (node != null || stack.size() > 0) {
            //存在左子树
            while (node != null) {
                stack.push(node);
                node = node.leftChild;
            }
            //栈非空
            if (stack.size() > 0) {
                node = stack.pop();
                visted(node);
                node = node.rightChild;
            }
        }
    }

    //后序遍历的非递归实现
    public void noRecPostOrder(TreeNode p) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = p;
        while (p != null) {
            //左子树入栈
            for (; p.leftChild != null; p = p.leftChild) {
                stack.push(p);
            }
            //当前结点无右子树或右子树已经输出
            while (p != null && (p.rightChild == null || p.rightChild == node)) {
                visted(p);
                //纪录上一个已输出结点
                node = p;
                if (stack.empty()) {
                    return;
                }
                p = stack.pop();
            }
            //处理右子树
            stack.push(p);
            p = p.rightChild;
        }
    }

    //层次遍历 广度优先遍历
    public void levelIterator(TreeNode n) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(n);
        while (!queue.isEmpty()) {
            TreeNode t = queue.poll();
            if (t != null) {
                visted(t);
            }
            if (t.leftChild != null) {
                queue.offer(t.leftChild);
            }
            if (t.rightChild != null) {
                queue.offer(t.rightChild);
            }
        }
    }

    //测试
    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        bt.createBinTree(bt.root);
        System.out.println("the size of the tree is " + bt.size());
        System.out.println("the height of the tree is " + bt.height());

        System.out.println("*******(前序遍历)遍历*****************");
        bt.preOrder(bt.root);

        System.out.println("*******(中序遍历)遍历*****************");
        bt.inOrder(bt.root);

        System.out.println("*******(后序遍历)遍历*****************");
        bt.postOrder(bt.root);

        System.out.println("层次遍历*****************");
        bt.levelIterator(bt.root);

        System.out.println("***非递归实现****(前序遍历)遍历*****************");
        bt.nonRecPreOrder(bt.root);

        System.out.println("***非递归实现****(中序遍历)遍历*****************");
        bt.nonRecInOrder(bt.root);

        System.out.println("***非递归实现****(后序遍历)遍历*****************");
        bt.noRecPostOrder(bt.root);
    }
}
