class Solution:
    def sortItems(self, n: int, m: int, group: list[int], beforeItems: list[list[int]]) -> list[int]:

        groups = set(group)
        if -1 in groups:
            groups.remove(-1)

        after_gr = {} 
        rem_gr = {}
        after_ng = {}
        rem_ng = {}                


        no_groups = []
        gr_map = {}
        for i in range(n):
            gr_map.setdefault(group[i], set()).add(i)     
            if group[i] != -1:

                after_gr[group[i]] = (set(), set())
                rem_gr[group[i]] = 0

            else:
                no_groups.append(i)
                after_ng[i] = (set(), set())
                rem_ng[i] = 0
   

        after_in_gr = {i:set() for i in range(n)}
        rem_in_gr = {i:0 for i in range(n)}

        for i in range(n):
            group_i = group[i]
            
            for it in beforeItems[i]:
                if group_i != -1:

                    if group[it] != group_i:
                        if group[it] != -1:
                            if group_i not in after_gr[group[it]][0]:
                                after_gr[group[it]][0].add(group_i)
                                rem_gr[group_i] += 1
                        else:
                            
                            after_ng[it][0].add(group_i)
                            rem_gr[group_i] += 1
                    else:
                        after_in_gr[it].add(i)
                        rem_in_gr[i]+=1
                        

                else:
                    if group[it] == -1:
                        after_ng[it][1].add(i)
                        rem_ng[i]+=1
                    else:
                        after_gr[group[it]][1].add(i)
                        rem_ng[i]+=1


        heap_gr = []
        heapq.heapify(heap_gr)
        for gr in groups:
            if (rem_gr[gr]==0):
                heapq.heappush(heap_gr, gr)

        heap_ng = []
        heapq.heapify(heap_ng)
        for elem in no_groups:
            if (rem_ng[elem] == 0):
                heapq.heappush(heap_ng, elem)





        res = []
        while len(res) < n:

            stop1 = True
            if heap_gr:
                stop1 = False
                curr_gr = heapq.heappop(heap_gr)
                
                size = len(gr_map[curr_gr])
                if not self.addGroup(res, gr_map[curr_gr], after_in_gr, rem_in_gr, size):
                    return []
                for gr in after_gr[curr_gr][0]:
                    rem_gr[gr]-=1
                    if rem_gr[gr]==0:
                        heapq.heappush(heap_gr, gr)
                for ng in after_gr[curr_gr][1]:
                    rem_ng[ng]-=1
                    if rem_ng[ng]==0:
                        heapq.heappush(heap_ng, ng)



            elif heap_ng:
                stop1 = False
                curr_ng = heapq.heappop(heap_ng)
                res.append(curr_ng)
                # groups that comes after
                for gr in after_ng[curr_ng][0]:
                    rem_gr[gr]-=1
                    if rem_gr[gr]==0:
                        heapq.heappush(heap_gr, gr)
                for ng in after_ng[curr_ng][1]:
                    rem_ng[ng]-=1
                    if rem_ng[ng]==0:
                        heapq.heappush(heap_ng, ng)
            if stop1:
                return []
        return res


    def addGroup(self, res, curr_gr, after_in_gr, rem_in_gr, size):
        heap = []
        for it in curr_gr:
            if rem_in_gr[it]==0:
                heapq.heappush(heap, it)

        count = 0
        while heap:
            curr = heapq.heappop(heap)
            res.append(curr)
            count+=1
            for elem in after_in_gr[curr]:
                rem_in_gr[elem]-=1
                if rem_in_gr[elem]==0:
                    heapq.heappush(heap, elem)
        return count==size

        
