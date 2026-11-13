class Solution:
    def maxProfit(self, prices: list[int]) -> int:
        if len(prices) == 0:
            return 0
        min_price = prices[0]
        dp = [0]*len(prices)
        for i in range(1, len(prices)):
            min_price = min(min_price, prices[i])
            dp[i] = max(dp[i-1], prices[i]-min_price)

        return dp[-1]
