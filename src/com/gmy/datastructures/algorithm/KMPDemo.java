package com.gmy.datastructures.algorithm;

/**
 * @Author guomaoyang
 * @Date 2020/12/25
 */
public class KMPDemo {
    public static void main(String[] args) {
        /*String str1= "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2="尚硅谷你尚硅你";
        int i = matchChars(str1, str2);
        System.out.println("index：" + i);*/

        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        int i = kmpMatchChars(str1, str2);
        System.out.println("index：" + i);


    }

    public static int kmpMatchChars(String str1,String str2){
        // 获取子串的长度匹配表
        int[] next = nextArr(str2);
        for(int i = 0,j = 0;i < str1.length();i++){

            /**
             * 如果不匹配则对子串通过长度匹配表进行回溯
             * 回溯步骤为：j - next[j-1];
             */
            while(j > 0 && str1.charAt(i) != str2.charAt(j)){
                j = next[j-1];
            }

            // 如果字符都匹配成功则都进行++
            if(str1.charAt(i) == str2.charAt(j)){
                j++;
            }
            if(j >= str2.length()){
                // 找到了
                return i - (j-1);
            }
        }
        return -1;
    }

    /**
     * 暴力匹配字符串
     * @param str1
     * @param str2
     * @return
     */
    public static int matchChars(String str1,String str2){
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int i1 = 0;
        int i2 = 0;
        while(i1 < chars1.length && i2 < chars2.length){
            if(chars1[i1] == chars2[i2]){
                if(i2 == chars2.length-1){
                    return i1-(i2-1);
                }
                i1++;
                i2++;
            }else{
                i1 = i1-(i2-1);
                i2 = 0;
            }
        }
        return -1;
    }

    /**
     * 通过前缀，后缀获取最大长度表
     * @param str
     * @return
     */
    public static int[] nextArr(String str){
        int[] next = new int[str.length()];
        next[0] = 0;
        for(int i = 1,j = 0;i < next.length;i++){
            while (j > 0 && str.charAt(i) != str.charAt(j)){
                j = next[j-1];
            }
            if(str.charAt(i) == str.charAt(j)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
