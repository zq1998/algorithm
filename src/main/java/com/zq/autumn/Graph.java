package com.zq.autumn;

import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright(c) 2019</p>
 * <p>Email: 1012872209@qq.com</p>
 *
 * @author apple
 * @date 2019-08-04:22:51
 */
public class Graph {

    private LinkedList<Edge> adj[];
    private int v;

    public Graph(int v) {
        this.v = v;
        this.adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    private class Edge {
        public int sid;
        public int tid;
        public int w;

        public Edge(int sid, int tid, int w) {
            this.sid = sid;
            this.tid = tid;
            this.w = w;
        }

    }

    private class Vertex implements Comparable<Vertex> {
        public int id; // 顶点编号ID
        public int dist; // 从起始顶点到这个顶点的距离

        public Vertex(int id, int dist) {
            this.id = id;
            this.dist = dist;
        }

        @Override
        public int compareTo(Vertex o) {
            // 按照dist从小到大排序
            if (o.dist > this.dist) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    public void dijkstra(int s, int t) { // 从顶点s到顶点t的最短路径
        int[] predecessor = new int[this.v]; // 用来还原最短路径
        Vertex[] vertexes = new Vertex[this.v]; // 记录起始顶点到这个顶点的距离
        for (int i = 0; i < v; ++i) { // 初始化dist为无穷大
            vertexes[i] = new Vertex(i, Integer.MAX_VALUE);
        }
        PriorityQueue<Vertex> queue = new PriorityQueue<>(); // 小顶堆
        boolean[] inqueue = new boolean[this.v]; // 标记是否进入过队列
        queue.add(vertexes[s]); // 先把起始顶点放到队列中
        vertexes[s].dist = 0;
        inqueue[s] = true;
        while (!queue.isEmpty()) {
            Vertex minVertex = queue.poll(); // 取dist最小的顶点
            if (minVertex.id == t) break; // 最短路径产生了
            for (int i = 0; i < adj[minVertex.id].size(); ++i) {
                Edge e = adj[minVertex.id].get(i); // 取出一条minVetex相连的边
                Vertex nextVertex = vertexes[e.tid]; // minVertex-->nextVertex
                // 找到一条到nextVertex更短的路径
                if (minVertex.dist + e.w < nextVertex.dist) {
                    nextVertex.dist = minVertex.dist + e.w; // 更新dist
                    predecessor[nextVertex.id] = minVertex.id; //更新前驱顶点
                    if (inqueue[nextVertex.id] == false) { // 如果没有在队列中
                        queue.add(nextVertex); // 就把它放到队列中
                        inqueue[nextVertex.id] = true;
                    }
                }
            }
        }

        System.out.print(s);
        print(s, t, predecessor);

    }

    private void print(int s, int t, int[] predecessor) {
        if (s == t) return;
        print(s, predecessor[t], predecessor);
        System.out.print("->" + t);
    }
}
