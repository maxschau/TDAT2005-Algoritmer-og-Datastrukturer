from TreeNode import TreeNode


class Tree:
    def __init__(self, root=None):
        self.root = root

    def is_operator(self, operator):
        if operator == "*" or operator == "/" or operator == "+" or operator == "-":
            return True
        else:
            return False

    def print_inorder(self, n):
        if n is not None:
            self.print_inorder(n.left)
            print(n.value)
            self.print_inorder(n.right)


    def print_postorder(self, n):
        if n is not None:
            self.print_postorder(n.left)
            self.print_postorder(n.right)
            print(n.value)

    def print_preorder(self, n):
        if n is not None:
            print(n.value)
            self.print_preorder(n.left)
            self.print_preorder(n.right)

    def print_math(self, n):
        #Starts by moving down to the left
        if n.left is None and n.right is None:
            if n == n.parent.left:
                return "(" + str(n.value)
            if n == n.parent.right:
                return str(n.value) + ")"

        left_side = self.print_math(n.left) + n.value

        right_side = self.print_math(n.right)



        return str(left_side) + str(right_side)

    def solve(self, n):
        if n.left is None and n.right is None:
            #This means it is an int because int will always be the last child
            return int(n.value)

        left_sum = self.solve(n.left)

        right_sum = self.solve(n.right)

        if n.value == "*":
            return left_sum * right_sum
        elif n.value == "+":
            return left_sum + right_sum
        elif n.value == "-":
            return left_sum - right_sum
        else:
            return left_sum / right_sum






root = TreeNode("/", None)

tree = Tree(root)


root.left = TreeNode("*", root)
root.right = TreeNode("-", root)

root.left.left = TreeNode(3, root.left)
root.left.right = TreeNode("+", root.left)
root.left.right.left = TreeNode(2, root.left.right)
root.left.right.right = TreeNode(4, root.left.right)
root.right.left = TreeNode(7, root.right)
root.right.right = TreeNode("*", root.right)
root.right.right.left = TreeNode(2, root.right.right)
root.right.right.right = TreeNode(2, root.right.right)

print(tree.print_math(root))
print(tree.solve(root))

