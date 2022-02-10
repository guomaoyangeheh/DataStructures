package com.gmy.datastructures.tree.test;

/**
 * 二叉搜索树删除节点
 * @Author guomaoyang
 * @Date 2022/2/8
 */
public class Test1 {
}
class TreeNode {
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
class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null){
            return root;
        }
        // 考虑删除根节点的情况
        if(root.val == key){
            // 看右子树
            if(root.right != null){
                if(root.right.left == null){
                    root.right.left = root.left;
                    return root.right;
                }else{
                    TreeNode rightMinNode = findAndRemoveMin(root.right);
                    rightMinNode.left = root.left;
                    rightMinNode.right = root.right;
                    return rightMinNode;
                }
            }else {
                return root.left;
            }
        }
        if(root.left == null && root.right == null){
            return root;
        }

        // 找到目标节点的父节点
        TreeNode targetParentNode = findTargetParentNode(root, key);
        if(targetParentNode == null){
            return root;
        }
        TreeNode targetLeftSon = null;
        TreeNode targetRightSon = null;
        // 判断目标位置
        if(targetParentNode.left != null && targetParentNode.left.val == key){
            targetLeftSon = targetParentNode.left.left;
            targetRightSon = targetParentNode.left.right;
            // 如果目标左子树为空，就让目标父节点指向目标的右子节点
            if (targetLeftSon == null){
                targetParentNode.left = targetRightSon;
                return root;
            }
            if (targetRightSon == null){
                targetParentNode.left = targetLeftSon;
                return root;
            }
            // 右子树就一个节点，替代目标节点。
            if(targetRightSon.left == null ){
                targetParentNode.left = targetRightSon;
                targetRightSon.left = targetLeftSon;
                return root;
            }
            // 找右子树中最小的节点替代目标节点
            TreeNode rightMinNode = findAndRemoveMin(targetRightSon);
            targetParentNode.left = rightMinNode;
            rightMinNode.left = targetLeftSon;
            rightMinNode.right = targetRightSon;
        }
        if(targetParentNode.right != null && targetParentNode.right.val == key){
            targetLeftSon = targetParentNode.right.left;
            targetRightSon = targetParentNode.right.right;
            // 如果目标左子树为空，就让目标父节点指向目标的右子节点
            if (targetLeftSon == null){
                targetParentNode.right = targetRightSon;
                return root;
            }
            if (targetRightSon == null){
                targetParentNode.right = targetLeftSon;
                return root;
            }
            // 右子树无左子节点
            if(targetRightSon.left == null ){
                targetParentNode.right = targetRightSon;
                targetRightSon.left = targetLeftSon;
                return root;
            }
            // 找右子树中最小的节点替代目标节点
            TreeNode rightMinNode = findAndRemoveMin(targetRightSon);
            targetParentNode.right = rightMinNode;
            rightMinNode.left = targetLeftSon;
            rightMinNode.right = targetRightSon;
        }
        return root;
    }

    private TreeNode findAndRemoveMin(TreeNode node) {
        TreeNode left = node.left;
        if(left.left == null){
            node.left = left.right;
            left.right = null;
            return left;
        }else {
            return findAndRemoveMin(left);
        }
    }

    public TreeNode findTargetParentNode(TreeNode root, int key){
        if (root == null){
            return null;
        }
        if(root.left != null && root.left.val == key){
            return root;
        }
        if(root.right != null && root.right.val == key){
            return root;
        }
        if(root.val < key){
            return findTargetParentNode(root.right,key);
        }else {
            return findTargetParentNode(root.left,key);
        }
    }
}