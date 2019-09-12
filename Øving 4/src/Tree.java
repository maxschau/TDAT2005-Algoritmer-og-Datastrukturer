public class Tree {
    public static TreeNode root;

    public Tree(TreeNode r) {
        this.root = r;
    }

    public Tree() {
        this.root = null;
    }

    public static int findDepth(TreeNode n) {
       /* int d = -1;
        while (n != null) {
            d++;
            n = n.parent;
        }
        */
        return -1;
    }

    private int findHeight(TreeNode n) {
        if (n==null) return -1;
        else {
            int lh = findHeight(n.left);
            int rh = findHeight(n.right);
            if (lh >= rh) return lh + 1;
            else return rh+1;
        }
    }

    public int findHeight() {
        return findHeight(root);
    }

    private void printPreorden(TreeNode n) {
        if (n != null) {
            System.out.print(n.element + "_");
            printPreorden(n.left);
            printPreorden(n.right);
        }
    }

    public void printPreorden() {
        printPreorden(root);
    }

    public boolean empty() {
        return root == null;
    }
}
