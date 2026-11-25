from bisect import bisect_right

class TimeMap:

    def __init__(self):
        self.tbstore = {}

    def set(self, key: str, value: str, timestamp: int) -> None:

        k = self.tbstore.get(key)
        if k is None:
            self.tbstore[key] = [{},[]]
        self.tbstore[key][0][timestamp] = value
        self.tbstore[key][1].append(timestamp)

    def get(self, key: str, timestamp: int) -> str:

        entry = self.tbstore.get(key)
        if entry is None:
            return ""

        values, times = entry
        exact = values.get(timestamp)
        if exact is not None:
            return values[timestamp]
        ind = bisect_right(times, timestamp)-1
        return values[times[ind]] if ind >=0 else ""
