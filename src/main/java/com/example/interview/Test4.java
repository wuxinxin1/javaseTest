package com.example.interview;


  //Definition for a binary tree node.

import java.util.ArrayList;
import java.util.List;

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
  }
class Solution {

    private List<Integer> list=new ArrayList<Integer>();

    public void inorderTraversal(TreeNode root) {

        if(root==null){
            return;
        }

        list.add(root.val);

        inorderTraversal(root.left);

        inorderTraversal(root.right);
    }
}