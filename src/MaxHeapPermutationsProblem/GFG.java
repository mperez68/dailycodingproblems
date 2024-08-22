package MaxHeapPermutationsProblem;

/**
 * NOTES
 * <p>
 * given n values, the highest value always has to be root, so we are permutating n - 1 values.
 * <p>
 * n = size
 * l = left sub-tree
 * r = right sub-tree
 * <p>
 * l + r = n - 1
 * <p>
 * (n-1) / l permutations exist
 * <p>
 * T(n) = ((n-1) / l) * T(L) * T(R)
 * <p>
 * h = log2 n = height of the heap
 * m = 2^h = maximum number of elements present in last level of heap
 * p = n - (2^h - 1) = actual elements present in last level
 * <p>
 * 2 cases exist:
 * <p>
 * l = 2^h - 1 			IF p >= m/2
 * <p>
 * l = 2^h - 1 - ((m/2) - p) 	IF p < m/2
 * <p>
 * thus,
 * <p>
 * r = n - l - 1
 * <p>
 *
 * GIVEN n = 3,
 * <p>
 * l + r = n - 1
 * <p>
 * l + r = 3 - 1
 * <p>
 * l + r = 2
 * <p>
 * p ?>= m/2
 * <p>
 * p = 3 - (2^(log2 3) - 1)
 * <p>
 * p = 3 - (3 - 1)
 * <p>
 * p = 1
 * <p>
 * m = 2^h
 * <p>
 * m = 2^(log2 3)
 * <p>
 * m = 3
 * <p>
 * 1 < 3 / 2 = 1.5
 * <p>
 * so,
 * <p>
 * l = 2^h - 1 - ((m/2) - p)
 * <p>
 * l = 2^(log2 3) - 1 - ((3/2) - 1)
 * <p>
 * l = 3 - 1 - (1.5 - 1)
 * <p>
 * l = 1.5
 * <p>
 * r = 3 - l - 0
 * <p>
 * r = 2
 * <p>
 * T(n) = ((n-1) / ;) * T(L) * T(R)
 * <p>
 * T(3) = ((3-1) / 1.5) * 1.5 * 1.5 = 3
 */

// Java program to count max heaps with n distinct keys

class GFG
{

    static int MAXN = 105; // maximum value of n here

    // dp[i] = number of max heaps for i distinct integers
    static int[] dp = new int[MAXN];

    // nck[i][j] = number of ways to choose j elements
    //		 form i elements, no order */
    static int[][] nck = new int[MAXN][MAXN];

    // log2[i] = floor of logarithm of base 2 of i
    static int[] log2 = new int[MAXN];

    // to calculate nCk
    public static int choose(int n, int k)
    {
        if (k > n)
        {
            return 0;
        }
        if (n <= 1)
        {
            return 1;
        }
        if (k == 0)
        {
            return 1;
        }

        if (nck[n][k] != -1)
        {
            return nck[n][k];
        }

        int answer = choose(n - 1, k - 1) + choose(n - 1, k);
        nck[n][k] = answer;
        return answer;
    }

    // calculate l for give value of n
    public static int getLeft(int n)
    {
        if (n == 1)
        {
            return 0;
        }

        int h = log2[n];

        // max number of elements that can be present in the
        // hth level of any heap
        int numh = (1 << h); //(2 ^ h)

        // number of elements that are actually present in
        // last level(hth level)
        // (2^h - 1)
        int last = n - ((1 << h) - 1);

        // if more than half-filled
        if (last >= (numh / 2))
        {
            return (1 << h) - 1; // (2^h) - 1
        }
        else
        {
            return (1 << h) - 1 - ((numh / 2) - last);
        }
    }

    // find maximum number of heaps for n
    public static int numberOfHeaps(int n)
    {
        if (n <= 1)
        {
            return 1;
        }

        if (dp[n] != -1)
        {
            return dp[n];
        }

        int left = getLeft(n);
        int ans = (choose(n - 1, left) * numberOfHeaps(left))
                * (numberOfHeaps(n - 1 - left));
        dp[n] = ans;
        return ans;
    }

    // function to initialize arrays
    public static int solve(int n)
    {
        for (int i = 0; i <= n; i++)
        {
            dp[i] = -1;
        }

        for (int i = 0; i <= n; i++)
        {
            for (int j = 0; j <= n; j++)
            {
                nck[i][j] = -1;
            }
        }

        int currLog2 = -1;
        int currPower2 = 1;

        // for each power of two find logarithm
        for (int i = 1; i <= n; i++)
        {
            if (currPower2 == i)
            {
                currLog2++;
                currPower2 *= 2;
            }
            log2[i] = currLog2;
        }

        return numberOfHeaps(n);
    }

    // Driver code
    public static void main(String[] args)
    {
        int n = 5;
        System.out.print(solve(n));
    }
}

// This code has been contributed by 29AjayKumar
