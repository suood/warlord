//package com.suood.algorithm.search;
//
//import sun.security.mscapi.KeyStore;
//
//import java.util.LinkedList;
//
///**
// * Created by Alexander on 2017/3/23.
// */
//public class BinaryTree {
//    public BinaryTree(){
//    }
//
//    LinkedList<Node> Nodes ;
//    private Node rootNode;
//    public setRootNode(Node node){
//        if (node.equals(rootNode)) {
//            return;
//        }else {
//            rootNode.setLeft(true); //左下沉
//            node.setRoot(true);
//        }
//    }
//}
//class Node{
//    public boolean isLeft() {
//        return left;
//    }
//
//    public void setLeft(boolean left) {
//        this.left = left;
//    }
//
//    public boolean isRight() {
//        return right;
//    }
//
//    public void setRight(boolean right) {
//        this.right = right;
//    }
//
//    public boolean isRoot() {
//        return root;
//    }
//
//    public void setRoot(boolean root) {
//        this.root = root;
//    }
//
//    public void setLeftNode(Node leftNode) {
//        this.leftNode = leftNode;
//    }
//
//    public void setRightNode(Node rightNode) {
//        this.rightNode = rightNode;
//    }
//
//    public boolean left;
//    public boolean right;
//    public boolean root;
//
//    public Node leftNode;
//    public Node rightNode;
//
//    public Node (){
//    }
//    public Node getLeftNode(){
//        return this.leftNode;
//    }
//    public Node getRightNode(){
//        return rightNode;
//    }
//
//}