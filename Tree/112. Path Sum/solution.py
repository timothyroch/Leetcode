# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def hasPathSum(self, root: TreeNode | None, targetSum: int) -> bool:
        if not root:
            return False
        curr = [root, root.val]
        queue = deque()
        queue.append(curr)

        while len(queue) > 0:
            curr = queue.popleft()
            cur_nd = curr[0]
            val_p = curr[1]
            if val_p == targetSum:
                if not cur_nd.left and not cur_nd.right:
                    return True
            if cur_nd.left:
                new_val = val_p+cur_nd.left.val
                queue.append([cur_nd.left, new_val])
            if cur_nd.right:
                new_val = val_p+cur_nd.right.val
                queue.append([cur_nd.right, new_val])

        return False
