package com.tester.pkgtest.leetcodetests;

import java.util.List;

public class LinkedListCycle {

    public static boolean hasCycle(ListNode head) {

        int count = 0;
        ListNode fast = head;
        ListNode slow = head;

        //check if there is only one node in the list and mark it false if so.
        //also check if the head is null meaning nothing was passed in and if so that is false.
        if(head == null || fast.next == null) {
            return false;
        }

        /**
         * loop through while fast and slow are not null
         * first check if fast == slow and if so a cycle was found and true will be returned.
         * use the try block to catch a NullPointerException meaning the end of the list was reached
         * and no cycle was found. In this case return false.
         *
         * If possible set slow to 1 position ahead and fast to 2 positions ahead and run the loop
         * again to find if a cycle exists.
         */
        while (fast != null && slow !=null) {
            if (fast == slow && count != 0) {
                return true;
            }
            try {
                slow = slow.next;
                fast = fast.next.next;
                count++;
            }
            catch (NullPointerException n) {
                return false;

            }
        }
        return false;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static void main(String[] args) {
//        ListNode e = new ListNode(3);
//        e.next = new ListNode(2);
//        e.next.next = new ListNode(0);
//        e.next.next.next = new ListNode(-4);
//        e.next.next.next.next = e.next;

//        ListNode e = new ListNode(1);
//        e.next = new ListNode(2);
//        e.next.next = e;

        ListNode e = new ListNode(1);

        System.out.println(hasCycle(e));



    }

}
