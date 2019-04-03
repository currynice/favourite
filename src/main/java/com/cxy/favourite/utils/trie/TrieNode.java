package com.cxy.favourite.utils.trie;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class TrieNode {


    private boolean isEnd = false;




    private Map<Character,TrieNode> subNodes = new HashMap<>();

     TrieNode  getTride(Character key){
         return subNodes.get(key);
     }

     public void setTrie(Character key,TrieNode value){
         subNodes.put(key,value);
     }

     boolean getIsEnd(){
        return isEnd;
     }


     public void setIsEnd(boolean v){
           this.isEnd = v;
     }

     /*获取长度*/
     public int sizeOfTrie(){
         return subNodes.size();
     }



    public void showTrieNode(){
        for(Map.Entry<Character,TrieNode> entry:subNodes.entrySet()){
            System.out.println("key:"+entry.getKey()+",value:"+entry.getValue());
        }
    }
}
