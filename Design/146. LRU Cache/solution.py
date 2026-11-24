from collections import deque

class LRUCache:

    def __init__(self, capacity: int):
        self.capacity = capacity
        self.keyVal = {}
        self.size = 0
        self.start = None
        self.end = None
        

    def get(self, key: int) -> int:
        currNode = self.keyVal.get(key)
        if currNode is not None:
            
            p, n = currNode.prev, currNode.next
            if self.end == currNode:
                return currNode.val

            if p is not None:
                p.next = n
            else:
                self.start = n
            if n is not None:
                n.prev = p

            l = self.end
            if l is not None:
                l.next = currNode
                currNode.prev = l
    
            self.end = currNode
            currNode.next = None

            return currNode.val
        else:
            return -1



    def put(self, key: int, value: int) -> None:

        currNode =  self.keyVal.get(key)
        if currNode is not None:

            currNode.val = value
            if self.end == currNode:
                return

            p, n = currNode.prev, currNode.next
            if p is not None:
                p.next = n
            else:
                self.start = n
            if n is not None:
                n.prev = p
            
            l = self.end
            if l is not None:
                l.next = currNode
                currNode.prev = l
            
            self.end = currNode
            currNode.next = None

        elif self.size < self.capacity:
            
            currNode = Node(key, value)
            
            self.keyVal[key] = currNode
            if self.size == 0:
                self.start = currNode
                self.end = currNode
                self.size+=1
                return
            l = self.end
            if l is not None:
                l.next = currNode
                currNode.prev = l
            self.end = currNode
            self.size+=1
        else:

            currNode = Node(key, value)

            s = self.start
            rem = None
            snd = None
            if s is not None:
                rem = s.key
                snd = s.next
                del self.keyVal[rem]

            if snd is not None:
                snd.prev = None
                self.start = snd
            else:
                self.start = currNode

            self.keyVal[key] = currNode
            l = self.end
            if l is not None:
                l.next = currNode
                currNode.prev = l
            self.end = currNode
    
class Node:
    def __init__(self, key, val):
        self.key = key
        self.val = val
        self.prev = None
        self.next = None
