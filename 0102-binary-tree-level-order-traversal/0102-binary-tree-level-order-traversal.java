/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>>ans=new ArrayList<>();
        if (root == null) return ans;

        Queue<TreeNode>que=new LinkedList<>();
        que.offer(root);

        while(!que.isEmpty()){
            List<Integer>curr= new ArrayList<>();
            int len=que.size();
            for(int i=0;i<len;i++){
                TreeNode node =que.poll();
                curr.add(node.val);
                if(node.left!=null) que.offer(node.left);
                if(node.right!=null) que.offer(node.right);
            }
            ans.add(curr);
        }
        return ans;
    }
}