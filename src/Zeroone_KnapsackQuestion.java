/*
给定 n 种物品和 1 个背包，物品 i 的重量是 wi，其价值为 vi，背包的
容量为 C。要求选择装入背包的物品，使得装入背包中物品的总价值最
大。
输入：每组测试数据包含 3 行，第 1 行为 n 和 c，表示有
n(0<=n<=1000)个物品且背包容量为 c (c<=2000)，第二行为这 n 个物
品的重量 wi (1<= wi<=1000)，第三行为这 n 个物品的价值 vi。背包容
量和物品重量都为整数。
输出：输出装入背包的最大总价值，每个答案一行。
例如：
输入：
5 10
2 2 6 5 4
6 3 5 4 6
输出：
15
 */

import java.util.Scanner;

public class Zeroone_KnapsackQuestion {
    //求0-1背包问题最大价值的函数
    //参数：c为背包容量，wi为物品重量数组，vi为物品价值数组
    //返回：int 最大价值
    public static int zeroone_Knapsack(int c, int[] wi, int[] vi){
        int n = wi.length;  //物品数量
        int[][] dp = new int[n+1][c+1]; //动态规划所用到的记录表，记录在当前容量中前n个物品最高能装多大价值的东西

        //dp过程，判断是否装入的相关操作
        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= c; j++){
                if (j < wi[i-1])        //如果当前物品的重量超过当前背包的容量
                    dp[i][j] = dp[i - 1][j];    //不装入当前物品，最优总价值从左复制过来
                else {
                    int NotLoad = dp[i - 1][j];
                    int Load = dp[i - 1][j - wi[i - 1]]   +   vi[i - 1];
                    dp[i][j] = Math.max(NotLoad, Load);    //若能够放置，则比较不装时的最优价值和装了之后的价值，取大值
                }
            }
        }
        return dp[n][c];    //表格的最后一个元素内容即为答案（最优装载）
    }

    //主程序
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int c = scanner.nextInt();

        int[] wi = new int[n];
        int[] vi = new int[n];

        //输入物品信息
        for (int i = 0; i < n; i++){
            wi[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++){
            vi[i] = scanner.nextInt();
        }

        int totalPreis = zeroone_Knapsack(c, wi, vi);

        System.out.print(totalPreis);
    }
}
