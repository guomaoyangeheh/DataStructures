package com.gmy.datastructures.algorithm;

import java.util.HashMap;

/**
 * 01背包问题
 * @Author guomaoyang
 * @Date 2020/12/29
 */
public class DynamicProgramDemo {
    public static void main(String[] args) {
        int i = superEggDrop2(2, 100);
        System.out.println(i);


    }

    public static int superEggDrop2(int K, int N) {
        HashMap<Integer,Integer> map = new HashMap();
        return dp(K,N,map);
    }

    public static int dp(int K, int N,HashMap<Integer,Integer> map){

        if(K == 1) return N;
        if(K == 0) return 0;
        if(map.containsKey(N*100+K)){
            return map.get(N*100+K);
        }
        int res = N;
        // 穷举从每一层扔的可能性
        for (int i = 1; i < N+1; i++) {// 需要尝试的楼层数是 1->N
            int currMax = Math.max(dp(K, N - i,map), dp(K - 1, i - 1,map));
            res = Math.min(res,currMax+1);
        }
        // 记录到备忘录
        map.put(N*100+K,res);
        return res;
    }


    public static int superEggDrop(int K, int N) {
        int[][] dp = new int[N+1][K+1];
        for(int i = 1;i<dp.length;i++){
            for(int j =1;j<dp[0].length;j++){
                if(j == 1 ){
                    dp[i][j] = i;
                } else{
                    if(i == 1 || i ==2){
                        dp[i][j] = i;
                    }else{
                        int one = 1+dp[(i+1)/2-1][j-1];
                        int two = 1+dp[i-((i+1)/2)][j];
                        dp[i][j] = Math.max(one,two);
                    }
                }
            }
        }
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j]+"  ");
            }
            System.out.println();
        }
        System.out.println(N+" 层扔 "+K+" 个鸡蛋的最小移动次数为 "+dp[N][K]+" 次");
        return dp[N][K];

    }

    // 01背包问题
    public void pack(){
        /**
         * 物品   重量       价值
         * 吉他    1        1500
         * 电脑    4        3000
         * 收音机  3        2000
         */
        int[] arr1 = {1,4,3,1};
        int[] arr2 = {1500,3000,2000,1800};
        int[] arr3 = {0,1,2,3,4};
        int[][] dp = new int[arr1.length+1][arr3.length];
        // 填充，不填充也是0
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 0;
        }
        for (int j = 0; j < dp[0].length; j++) {
            dp[0][j] = 0;
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                //dp[i][j] = Math.max(dp[i-1][j],);
                if(arr3[j]-arr1[i-1] >= 0){
                    // 当前物品的价值
                    int currVal = arr2[i-1];
                    // 剩余重量的最大价值，直接取上一行对应重量的价值即可
                    int residueVal = dp[i - 1][j - arr1[i-1]];
                    // 拿以上2个价值之和 与 上一行的价值比较
                    dp[i][j] = Math.max(dp[i-1][j],currVal+residueVal);
                }else{// 说明放不下当前物品
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j]+"  ");
            }
            System.out.println();
        }
        System.out.println("背包的最大价值：" + dp[dp.length-1][dp[0].length-1]);

    }
}
