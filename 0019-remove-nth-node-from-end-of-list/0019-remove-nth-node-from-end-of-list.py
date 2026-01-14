# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution(object):
    def removeNthFromEnd(self, head, n):
        temp=ListNode(0)
        temp.next=head
        fast=slow=temp
        for _  in range(n):
            fast=fast.next
        while fast.next:
            fast=fast.next
            slow=slow.next
        slow.next=slow.next.next
        return temp.next
        