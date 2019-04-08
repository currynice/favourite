package com.cxy.favourite.utils.trie;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.CharUtils;

/**
 * 前缀树构建
 * 我们通过搜索trie插入一个键。我们从根开始并搜索一个链接，该链接对应于第一个关键字符。有两种情况：

 存在链接。然后我们沿着指向下一个子级别的链接向下移动树。该算法继续搜索下一个关键字符。
 链接不存在。然后我们创建一个新节点并将其链接到与当前关键字符匹配的父链接。
 我们重复此步骤，直到遇到键的最后一个字符，然后我们将当前节点标记为结束节点，算法结束。
 */
@Slf4j
public class Trie {
    private TrieNode rootNode= new TrieNode();


    TrieNode tempNode = rootNode;

    //insert text into Trie树
    public void insert(String text){
        for(int i=0;i<text.length();i++){
            Character c= text.charAt(i);
            if(isSymbol(c)){
                continue;
            }
            //
            TrieNode node = tempNode.getTride(c);
            if(null==node){
                node = new TrieNode();
                tempNode.setTrie(c,node);

            }
            tempNode = node;//进入下一节点
            if(i==text.length()-1){
                tempNode.setIsEnd(true);

            }
        }
    }




    public static  void main(String[] args){
        Trie trie = new Trie();
        String space = "赌 球";
        trie.insert(space);




    }


    /**
     * 判断是否是一个符号 过滤如空格
     */
    private static boolean isSymbol(char c) {
        int ic = (int) c;
        // 0x2E80-0x9FFF 东亚文字范围
        return !CharUtils.isAsciiAlphanumeric(c) && (ic < 0x2E80 || ic > 0x9FFF);
    }




}
