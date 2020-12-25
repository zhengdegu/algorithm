package com.gu.algorithm.binarytree;

/**
 * @author gu
 * @create 2020/12/24 上午9:32
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        //先需要创建一颗二叉树B
        BinaryTree binaryTree = new BinaryTree();
        //创建需要的结点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");
         //说明，我们先手动创建该二叉树，后面我们学习递归的方式创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setHeroNode(root);
        //测试
        System.out.println("前序遍历"); // 1,2,3,5,4
        binaryTree.preOrder();
        //测试
        System.out.println("中序遍历");
        binaryTree.infixOrder(); // 2,1,5,3,4
        System.out.println("后序遍历");
        binaryTree.postOrder(); // 2,5,4,3,1
    }

    public static class BinaryTree {
        public BinaryTree() {
        }

        HeroNode heroNode;

        public HeroNode getHeroNode() {
            return heroNode;
        }

        public void setHeroNode(HeroNode heroNode) {
            this.heroNode = heroNode;
        }

        public BinaryTree(HeroNode heroNode) {
            this.heroNode = heroNode;
        }

        //前序遍历
        public void preOrder() {
            if (this.heroNode != null) {
                this.heroNode.preOrder();
            } else {
                System.out.println("二叉树为空，无法遍历");
            }
        }

        public void infixOrder() {
            if (this.heroNode != null) {
                this.heroNode.infixOrder();
            } else {
                System.out.println("二叉树为空，无法遍历");
            }
        }

        public void postOrder() {
            if (this.heroNode != null) {
                this.heroNode.postOrder();
            } else {
                System.out.println("二叉树为空，无法遍历");
            }
        }
    }

    public static class HeroNode {

        int id;
        String name;
        HeroNode left;
        HeroNode right;

        public HeroNode(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public HeroNode getLeft() {
            return left;
        }

        public void setLeft(HeroNode left) {
            this.left = left;
        }

        public HeroNode getRight() {
            return right;
        }

        public void setRight(HeroNode right) {
            this.right = right;
        }

        @Override
        public String toString() {
            return "HeroNode{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }

        //前序遍历
        public void preOrder() {
            System.out.println(this);
            if (this.left != null) {
                this.left.preOrder();
            }
            if (this.right != null) {
                this.right.preOrder();
            }
        }

        public void infixOrder() {
            //递归向左子树中序遍历
            if (this.left != null) {
                this.left.infixOrder();
            }
            System.out.println(this);
            if (this.right != null) {
                this.right.infixOrder();
            }
        }

        //后序遍历
        public void postOrder() {
            if (this.left != null) {
                this.left.postOrder();
            }
            if (this.right != null) {
                this.right.postOrder();
            }
            System.out.println(this);
        }
    }
}
