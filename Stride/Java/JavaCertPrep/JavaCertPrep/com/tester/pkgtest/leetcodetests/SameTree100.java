package com.tester.pkgtest.leetcodetests;

import sun.reflect.generics.tree.Tree;

public class SameTree100 {

    public boolean isSameTree(TreeNode p, TreeNode q) {

        //check if both nodes are null meaning all have been checked and return true
        if (p == null && q == null) {
            return true;
        }
        //check if current node is null and if so return false
        if (p == null || q == null) {
            return false;
        }
        //if at anytime p.val does not equal q.val then return false
        if (p.val != q.val) {
            return false;
        }
        //return the recursive call to both left and right subtrees checking the value each time
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {

        TreeNode p = new TreeNode(1);
        p.left  = new TreeNode(2);
        p.right = new TreeNode(3);

        TreeNode q = new TreeNode(1);
        q.left  = new TreeNode(2);
        q.right = new TreeNode(3);

//        TreeNode p = new TreeNode(1, new TreeNode(2), null);
//        TreeNode q = new TreeNode(1, null, new TreeNode(2));


        SameTree100 st = new SameTree100();
        System.out.println(st.isSameTree(p, q));

    }




}


