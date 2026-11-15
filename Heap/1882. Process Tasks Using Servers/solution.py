class Solution:
    def assignTasks(self, servers: list[int], tasks: list[int]) -> list[int]:

        heap_A = [(w, i) for i, w in enumerate(servers)]
        heap_W = []

        heapq.heapify(heap_A)
        heapq.heapify(heap_W)

        ans =[]

        for i in range(len(tasks)):

            curr_len = tasks[i]

            if not heap_A:
                time, w, ind = heapq.heappop(heap_W)
                ans.append(ind)
                new = (time+curr_len, w, ind)
                heapq.heappush(heap_W, new)

            else:  
                w, ind = heapq.heappop(heap_A)
                ans.append(ind)
                heapq.heappush(heap_W, (i+curr_len, w, ind))

            while True:
                if not heap_W:
                    break
                t, w, ind = heap_W[0]
                if t<=i+1:
                    time, weight, index = heapq.heappop(heap_W)
                    heapq.heappush(heap_A, (weight, index))
                elif t>i+1:
                    break

        return ans

