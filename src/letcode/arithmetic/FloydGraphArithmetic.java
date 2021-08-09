package letcode.arithmetic;

/**
 * 弗洛伊德算法
 *
 * @author CaiYongcheng
 * @date 2021-08-06 10:07
 **/
public class FloydGraphArithmetic {


    /**
     * 三次循环 时间复杂度O（n^3），dp思想
     * 考虑这样一个问题 如果通过了graph[i,k], graph[k,j]更新了graph[i，j], 而新的graph[i，j]对之前的graph[i,k],
     * graph[k,j]产生了影响，导致的这种影响是否会循环传播。
     * 实际上，如果graph[i][j]的更新，影响了graph[i,k], graph[k,j]，说明了graph[i,k], graph[k,j]是包含了graph[i][j]。
     * 既然包含了graph[i][j]又怎么可是新的最短距离呢，这与假设矛盾。故这种影响是不会产生的。
     * @param graph 图的邻接矩阵表示
     */
    public void floyd(int[][] graph) {
        int n = graph.length;
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }
    }



    public static void main(String[] args) {

    }


}
