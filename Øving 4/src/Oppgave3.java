public class Oppgave3 {
    public static void main(String[] args) {
        Tree t = new Tree(new TreeNode(10));
        System.out.println(t.root.element);
        t.root.right = new TreeNode(6);
        t.root.left = new TreeNode(4);
        t.root.left = new TreeNode(4);
    }
}
