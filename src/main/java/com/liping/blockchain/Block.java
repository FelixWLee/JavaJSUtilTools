package com.liping.blockchain;

import com.google.gson.GsonBuilder;
import com.liping.utils.StringUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @author MG01967
 * @create 2019-11-04-14:47
 */
public class Block {
    
    //自己的数字签名
    public String hash;
    
    //上一个区块的数字签名
    public String previousHash;
    
    //一切需要加密的数据
    private String data;
    
    //时间错
    private long timeStamp;
    
    //工作量证明
    private int nonce;
    
    public Block(String data, String previousHash) {
        this.previousHash = previousHash;
        this.data = data;
        timeStamp = System.currentTimeMillis();
        this.hash = calculateHash();
    }
    
    public String calculateHash() {
        String calculatedhash = StringUtil.applySha256(
                previousHash +
                        timeStamp +
                        nonce +
                        data
        );
        return calculatedhash;
    }
    
    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0"
        while(!hash.substring( 0, difficulty).equals(target)) {
            nonce ++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }
    
    
    public static ArrayList<Block> blockChain = new ArrayList<>();
    public static int difficulty = 6;
    
    public static void main(String[] args){
       /* //第一个块称为创世块，因为它是头区块，所以我们只需输入“0”作为前一个块的previous hash。
        Block genesisBlock = new Block("HI im the first block", "0");
        System.out.println("Hash for block 1 :" + genesisBlock.hash);

        Block secondBlock = new Block("Yo im the sencond block", genesisBlock.hash);
        System.out.println("Hash for block 2:" + secondBlock.hash);

        Block thirdBlock = new Block("Hey im the block 3", secondBlock.hash);
        System.out.println("Hash for block 2:" + thirdBlock.hash);*/
        
      /*  每一个区块都必须要有自己的数据签名即hash值，这个hash值依赖于自身的信息（data）和上一个区块的数字签名（previousHash），
         但这个还不是区块链，下面让我们存储区块到数组中，这里我会引入gson包，目的是可以用json方式查看整个一条区块链结构。
        blockChain.add(new Block("HI im the first block", "0"));
        blockChain.add(new Block("Yo im the sencond block", blockChain.get(blockChain.size() - 1).hash));
        blockChain.add(new Block("Hey im the block 3", blockChain.get(blockChain.size() - 1).hash));

        String blockChainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockChain);
        //这样的输出结构就更类似于我们所期待的区块链的样子。
        System.out.println(blockChainJson);*/
    
    
        LocalDateTime begin = LocalDateTime.now();
        System.out.println(begin);
        blockChain.add(new Block("Hi im the first block", "0"));
        System.out.println("Trying to Mine block 1... ");
        blockChain.get(0).mineBlock(difficulty);
    
        blockChain.add(new Block("Yo im the second block",blockChain.get(blockChain.size()-1).hash));
        System.out.println("Trying to Mine block 2... ");
        blockChain.get(1).mineBlock(difficulty);
    
        blockChain.add(new Block("Hey im the third block",blockChain.get(blockChain.size()-1).hash));
        System.out.println("Trying to Mine block 3... ");
        blockChain.get(2).mineBlock(difficulty);
    
        System.out.println("\nblockChain is Valid: " + isChainValid());
        LocalDateTime end = LocalDateTime.now();
        
        System.out.println(end);
        String blockChainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockChain);
        System.out.println("\nThe block chain: ");
        System.out.println(blockChainJson);
        
        
    }
    
    /**
     * 在主方法中增加一个isChainValid()方法，目的是循环区块链中的所有区块并且比较hash值，
     * 这个方法用来检查hash值是否是于计算出来的hash值相等，同时previousHash值是否和前一个区块的hash值相等。
     * 或许你会产生如下的疑问，我们就在一个主函数中创建区块链中的区块，所以不存在被修改的可能性，但是你要注意的是，
     * 区块链中的一个核心概念就是去中心化，每一个区块可能是在网络中的某一个节点中产生的，所以很有可能某个节点把自己节点中的数据修改了，
     * 那么根据上述的理论数据改变会导致整个区块链的破裂，也就是区块链就无效了。
     * @return
     */
    public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');
        
        //loop through blockChain to check hashes:
        for(int i=1; i < blockChain.size(); i++) {
            currentBlock = blockChain.get(i);
            previousBlock = blockChain.get(i-1);
            //compare registered hash and calculated hash:
            if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
                System.out.println("Current Hashes not equal");
                return false;
            }
            //compare previous hash and registered previous hash
            if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
            //check if hash is solved
            if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
                System.out.println("This block hasn't been mined");
                return false;
            }
        }
        return true;
    }
}
