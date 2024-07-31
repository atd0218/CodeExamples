package com.tester.pkgtest.leetcodetests;

public class AddTwoNumbers {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode tail = dummyHead;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {

            //tenery operator to say if l1 is not null set digit 1 to l1.val
            int digit1 = (l1 != null) ? l1.val : 0;

//          /tenery operator to say if l2 is not null set digit 2 to l2.val
            int digit2 = (l2 != null) ? l2.val : 0;

            //hold a sum to combine digit 1, digit 2, and the carry number
            int sum = digit1 + digit2 + carry;

            // digit takes the sum and modulus by 10 so it will return the last digit of the number
            int digit = sum % 10;

            //update carry to be the sum divided by 10
            carry = sum / 10;

            //create a new list node that stores the first digit.
            ListNode newNode = new ListNode(digit);

            //set the tail to be the last item that was entered in to newNode.
            tail.next = newNode;

            //reset tail
            tail = tail.next;


            //reset l1 and l2 value to the next value to add them and compare
            l1 = (l1 != null) ? l1.next : null;
            l2 = (l2 != null) ? l2.next : null;
        }

        //store the last 3 digits of dummyHead starting at next to return the proper result.
        return dummyHead.next;
    }

    public static void main(String[] args) {
        // Create two linked lists representing the two numbers
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        // Call the addTwoNumbers method to add the two numbers represented by the linked lists
        ListNode result = addTwoNumbers(l1, l2);

        // Print the result
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }

}
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
