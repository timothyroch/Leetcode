"""
# Definition for a Node.
class Node:
    def __init__(self, val = 0, neighbors = None):
        self.val = val
        self.neighbors = neighbors if neighbors is not None else []
"""

from typing import Optional
class Solution:
    def cloneGraph(self, node: Optional['Node']) -> Optional['Node']:
        if not node:
            return None
        seen = set()
        queue = deque()
        queue.append((node, None))
        curr = node

        mapnd = {}
        res = None

        while len(queue)>0:

            curr, parent = queue.popleft()
            if curr in seen:
                parent.neighbors.append(mapnd[curr])
                continue

            seen.add(curr)


            nn = Node(curr.val)
            if not parent:
                res = nn

            mapnd[curr] = nn
            if parent:
                parent.neighbors.append(nn)

            for nd in curr.neighbors:
                if nd and nd not in seen:
                    queue.append((nd, nn))
                elif nd:
                    nn.neighbors.append(mapnd[nd])
            
        return res
