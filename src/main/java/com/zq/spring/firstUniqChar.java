package com.zq.spring;

import java.util.*;

public class firstUniqChar {
    private String frequencySort(String s) {
        Map<Character,Integer> map=new TreeMap<>();
        for(int i=0;i<s.length();i++){
            if(map.containsKey(s.charAt(i))){
                map.put(s.charAt(i),map.get(s.charAt(i))+1);
            }else{
                map.put(s.charAt(i),1);
            }
        }
        StringBuilder sb=new StringBuilder();
        List<Map.Entry<Character,Integer>> list=new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return o2.getValue()-o1.getValue();
            }
        });
        list.forEach(entry ->{
            for(int i=0;i<entry.getValue();i++){
                sb.append(entry.getKey());
            }
        });
        return sb.toString();
    }
    public static void main(String[] args) {

    }
}

