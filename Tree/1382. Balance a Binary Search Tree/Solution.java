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
    public TreeNode balanceBST(TreeNode root) {
        ArrayList<TreeNode> arr = new ArrayList<>();
        storeinorder(root, arr);

        root = buildBST(arr, 0, arr.size()-1);

        return root; 
        

    }

    public TreeNode buildBST(ArrayList<TreeNode> arr, int start, int end){
        if (start > end) return null;
        int middle = (start+end)/2;
        TreeNode root = arr.get(middle);
        root.left = buildBST(arr, start, middle-1);
        root.right = buildBST(arr, middle+1, end);
        return root;
    }

    public void storeinorder(TreeNode tnode, ArrayList<TreeNode> arr){
        if (tnode == null) return;
        storeinorder(tnode.left, arr);
        arr.add(tnode);
        storeinorder(tnode.right, arr);
    }


}
