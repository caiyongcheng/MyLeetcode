package letcode.utils.extraordinary;

import java.util.List;

/**
 * LeetCode Node
 *
 * @author CaiYongcheng
 * @since 2021-05-08 15:52
 **/
public class Node {

    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }

}
