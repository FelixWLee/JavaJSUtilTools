package com.liping.leecode.listnode;

import java.util.*;

/**
 * @author LiPing
 * @create 2022-12-08-20:39
 */
public class ReverseListNode {

    public static ListNode reverseList(ListNode head) {

        ListNode reverseNode = null;
        Set<ListNode> lSet = new HashSet<ListNode>();
        ListNode temp = head;
        while (temp != null) {
            lSet.add(temp);
            temp = temp.next;
        }

        Iterator<ListNode> iterator = lSet.iterator();
        Stack<ListNode> result = new Stack<>();
        while (iterator.hasNext()) {
            result.push(iterator.next());
        }
        while (!result.isEmpty()) {
            ListNode pop = result.pop();
            reverseNode = new ListNode(pop.val, pop.next);
        }

        return reverseNode;
    }

    public static void main(String[] args){
        ListNode node1= new ListNode(1);
        ListNode node2= new ListNode(2);
        ListNode node3= new ListNode(3);
        ListNode node4= new ListNode(4);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        ListNode listNode = reverseList(node1);

    }
}

 class ListNode {

     int val;
     ListNode next;

     ListNode() {
     }

     ListNode(int val) {
         this.val = val;
     }

     ListNode(int val, ListNode next) {
         this.val = val;
         this.next = next;
     }

 }