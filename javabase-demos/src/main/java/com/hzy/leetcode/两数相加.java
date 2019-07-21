package com.hzy.leetcode;

/**
 * TODO 待优化
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 两数相加 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = null;
        int flag = 0;
        ListNode original = null;
        while(l1!=null || l2!=null){
            if(l1==null){
                l1= new ListNode(0);
            }
            if(l2==null){
                l2= new ListNode(0);
            }
            int sum = l1.val+l2.val +flag;
            flag = 0;
            if(sum>9){
                flag = 1;
            }
            if(result == null){
                result = new ListNode(sum%10);
                if(original==null){
                    original = result;
                }
            }else{
                result.next = new ListNode(sum%10);
                result = result.next;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        if(flag==1){
            result.next = new ListNode(1);
        }
        return original;
    }

    public static void main(String[] args) {
        两数相加 x = new 两数相加();
        ListNode x1 = new ListNode(1);
        x1.next = new ListNode(8);
        ListNode x2 = new ListNode(0);

        ListNode result = x.addTwoNumbers(x1, x2);
        while(result!=null){
            System.out.println(result.val);
            result = result.next;
        }
    }
}
class ListNode{
    ListNode next;
    Integer val;
    ListNode(int x){
        val = x;
    }
}
