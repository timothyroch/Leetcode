from collections import deque

class Solution:
    def islandPerimeter(self, grid: list[list[int]]) -> int:
        num_rows = len(grid)
        if num_rows == 0: return 0
        len_col = len(grid[0])
        
        seen = set()
        queue = deque()
        perimeter = 0
        
        found = False
        for i in range(num_rows):
            for j in range(len_col):
                if grid[i][j] == 1:
                    queue.append((i, j))
                    seen.add((i, j))
                    found = True
                    break
            if found: break
            
        if not found: return 0
        
      
        directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]
        
        while queue:
            r, c = queue.popleft()
            
            for dr, dc in directions:
                nr, nc = r + dr, c + dc
                
                
                if nr < 0 or nr >= num_rows or nc < 0 or nc >= len_col or grid[nr][nc] == 0:
                    perimeter += 1
                
                elif grid[nr][nc] == 1 and (nr, nc) not in seen:
                    seen.add((nr, nc))
                    queue.append((nr, nc))
                    
        return perimeter
