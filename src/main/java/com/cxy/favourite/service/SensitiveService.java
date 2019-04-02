package com.cxy.favourite.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 敏感词Service,实现InitializingBean,执行初始化操作
 */
@Service
@Slf4j
public class SensitiveService implements InitializingBean {

    private static final String REPLACEMENT ="***"; //替换文本

    //读取文件中的敏感词
    @Override
    public void afterPropertiesSet() throws Exception {
        try( InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("SensitiveWords.txt");
             InputStreamReader reader = new InputStreamReader(is);
             BufferedReader bufferedReader = new BufferedReader(reader)) {//关闭流

            String lineTxt;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                addWord(lineTxt.trim());
            }
        }
        catch (Exception e){
            log.error("读取敏感词出错",e);
        }
    }

    //读取文件方法,构造树
    private void addWord(String lineTxt){
        TrieNode tempNode = rootNode;//当前节点(根节点)
        for(int i =0;i<lineTxt.length();i++){
            Character c= lineTxt.charAt(i);
                if(isSymbol(c)){
                    continue;
                }
            TrieNode node = tempNode.getSubNode(c);
            if(node==null){
             node = new TrieNode();
             tempNode.addSubNodes(c,node);
            }
            tempNode = node;//进入下个节点
                if(i == lineTxt.length()-1){
                    // 关键词结束， 设置结束标志
                    tempNode.setKeywordEnd(true);
                }
        }
    }

    private class  TrieNode{//前缀树
        /**
         * true 关键词的终结 ； false 继续
         */
        private boolean end = false;

        /**
         * 所有节点
         * key 下一个字符
         * value 对应节点
         *
         */

        private Map<Character,TrieNode> subNodes = new HashMap<>();


        /**
         * 向指定位置添加节点树
         */
        public void addSubNodes(Character key,TrieNode node) {
            subNodes.put(key,node);
        }

        /**
         * 获取下个节点
         */
        TrieNode getSubNode(Character key){
            return subNodes.get(key);
        }

        boolean isKeywordEnd(){
            return end;
        }
        public void setKeywordEnd(boolean end){
            this.end = end;
        }

        public int getSubNodeCount() {
            return subNodes.size();
        }
    }

    //根
    private  TrieNode rootNode = new TrieNode();

    /**
     * 判断是否是一个符号 过滤如空格
     */
    private boolean isSymbol(char c) {
        int ic = (int) c;
        // 0x2E80-0x9FFF 东亚文字范围
        return !CharUtils.isAsciiAlphanumeric(c) && (ic < 0x2E80 || ic > 0x9FFF);
    }

    //过滤
    public String fliter(String text){
        StringBuilder result = new StringBuilder();
        if(StringUtils.isBlank(text)){
            return text;
        }
        TrieNode tempNode = rootNode;//节点
        int begin = 0;//确认点
        int position = 0;//移动点
        while(position<text.length()){
            char c = text.charAt(position);
            if(isSymbol(c)){
                if (tempNode == rootNode) {//刚开始判断,放到最后，不影响
                    result.append(c);
                    ++begin;
                }
                ++position;
                continue;
            }
            tempNode =tempNode.getSubNode(c);
            //匹配结束
            if(tempNode==null){
                //以begin开始的String没有敏感词
                result.append(text.charAt(begin));
                // 跳到下一个字符开始测试
                position = begin+1;
                begin = position;
                tempNode = rootNode;
            }else if(tempNode.isKeywordEnd()){//发现敏感词，begin->position 替换
                    result.append(REPLACEMENT);
                    position +=1;
                    begin = position;
                    tempNode = rootNode;
            }else{
                ++position;
            }
        }
        result.append(text.substring(begin));
        return result.toString();

    }
    public static void main(String []args){
        SensitiveService sensitiveService = new SensitiveService();
//        sensitiveService.addWord("色情");
//        sensitiveService.addWord("赌球");
        System.out.println(sensitiveService.fliter("色情 网站"));
    }

}
