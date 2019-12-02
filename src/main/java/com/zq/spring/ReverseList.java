package com.zq.spring;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright(c) 2019</p>
 * <p>Email: 1012872209@qq.com</p>
 *
 * @author zq
 * @date 2019-10-08:15:51
 */
public class ReverseList {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return cur;
    }

    public ListNode reverseList2(ListNode head) {
        if(head==null || head.next==null){
            return head;
        }
        ListNode node=reverseList2(head.next);
        head.next.next=head;
        head.next=null;
        return node;
    }
}
