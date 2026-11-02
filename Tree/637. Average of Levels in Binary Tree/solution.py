# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def averageOfLevels(self, root: TreeNode | None) -> list[float]:
        res = []
        queue = deque()
        curr = root
        total = 0
        queue.append(curr)

        while len(queue) > 0:
            row_len = len(queue)
            total = 0
            for _ in range(row_len):
                curr = queue.popleft()
                total += curr.val
                if curr.left:
                    queue.append(curr.left)
                if curr.right:
                    queue.append(curr.right)
            res.append(total/row_len)
            
        return res
