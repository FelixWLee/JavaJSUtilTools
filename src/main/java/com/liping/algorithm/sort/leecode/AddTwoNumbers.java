package com.liping.algorithm.sort.leecode;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。  您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/add-two-numbers
 *
 * @author MG01967
 * @create 2019-07-17-19:32
 */
public class AddTwoNumbers {
    
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        StringBuffer sb1 = new StringBuffer(String.valueOf(l1.val));
        StringBuffer sb2 = new StringBuffer(String.valueOf(l2.val));
        
        do  {
            sb1.append(l1.next.val);
            l1 = l1.next;
        } while ((l1.next != null));
        
        
        do {
            sb2.append(l2.next.val);
            l2 = l2.next;
        } while ((l2.next != null));
        
        int num1 = Integer.parseInt(sb1.reverse().toString());
        int num2 = Integer.parseInt(sb2.reverse().toString());
        
        int sum = num1 + num2;
        
        String sumStr = String.valueOf(sum);
        //String num = sumStr.substring(sumStr.length() - 1);
        //ListNode sumNode = new ListNode(Integer.parseInt(num));
        ListNode node;
        ListNode head = null;
        for (int i = sumStr.length() - 1; i >= 0; i--) {
            String n = sumStr.substring(i, i + 1);
             node = new ListNode(Integer.parseInt(n));
             head = node;
             node = node.next;
        }
        
        return head;
    }
    
    
    public static void main(String[] args) {
        
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        
        ListNode result = addTwoNumbers(l1, l2);
        System.out.println(result.toString());
    }
}

class ListNode {
    
    int val;
    ListNode next;
    
    ListNode(int x) {
        val = x;
    }
    
    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}