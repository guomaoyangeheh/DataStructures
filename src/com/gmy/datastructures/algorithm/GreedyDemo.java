package com.gmy.datastructures.algorithm;

import java.util.*;

/**
 * @Author guomaoyang
 * @Date 2020/12/23
 */
public class GreedyDemo {
    public static void main(String[] args) {
        //创建广播电台,放入到 Map
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>(); //将各个电台放入到 broadcasts
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");
        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");
        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");
        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");
        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");
        //加入到 map
        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);
        //allAreas 存放所有的地区
        HashSet<String> allAreas = new HashSet<>(); allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        // 存放结果值
        List<String> resultKeys = new ArrayList<>();
        while(allAreas.size() > 0){
            String maxKey = null;
            for (String key : broadcasts.keySet()) {
                HashSet<String> set = broadcasts.get(key);
                HashSet<String> temp = (HashSet<String>) set.clone();
                // 取交集
                temp.retainAll(allAreas);
                if(temp.size() > 0 && maxKey == null ){
                    maxKey = key;
                    continue;
                }
                if(maxKey != null && temp.size() >0){
                    // 取maxKey交集
                    HashSet<String> maxSet = broadcasts.get(maxKey);
                    HashSet<String> temp2 = (HashSet<String>) maxSet.clone();
                    temp2.retainAll(allAreas);
                    if(temp.size() > temp2.size()){
                        maxKey = key;
                    }
                }
            }
            // 经过一轮循环选出了一个maxKey
            if(maxKey != null){
                resultKeys.add(maxKey);
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }

        System.out.println("选出最终结果：" + resultKeys);
    }
}
