// Created by Whiplash99
import java.io.*;
import java.util.*;
public class H
{
    private static final long INF=Long.MAX_VALUE;
    static class Edge
    {
        int dest;
        long weight;
        public Edge(int dest, long weight)
        {
            this.dest=dest;
            this.weight=weight;
        }
    }

    static class Node implements Comparable<Node>
    {
        int index;
        long dist;
        public Node(int index, long dist)
        {
            this.index=index;
            this.dist=dist;
        }

        public int compare(Node a, Node b){return Long.compare(a.dist,b.dist);}

        @Override
        public int compareTo(Node node)
        {
            return Long.compare(this.dist,node.dist);
        }
    }

    public static void main(String[] args) throws IOException
    {
        //BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        Reader br = new Reader();

        int i, N, M, u, v, weight,A,B;
        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();

        while (T-- > 0)
        {

            //String s[]=br.readLine().trim().split(" ");
            N = br.nextInt();
            M = br.nextInt();

            ArrayList<Edge>[] edge = new ArrayList[N];
            int p[] = new int[N];

            for (i = 0; i < N; i++)
            {
                edge[i] = new ArrayList<>();
                p[i] = -1;
            }

            for (i = 0; i < M; i++)
            {
                //s=br.readLine().trim().split(" ");
                u = br.nextInt() - 1;
                v = br.nextInt() - 1;
                weight = br.nextInt();

                if (u == v)
                    continue;


                edge[u].add(new Edge(v, weight));
                //edge[v].add(new Edge(u, weight));
            }

            A=br.nextInt()-1;
            B=br.nextInt()-1;


            boolean vis[] = new boolean[N];
            long dist[] = new long[N];

            PriorityQueue<Node> heap = new PriorityQueue<>();

            Arrays.fill(vis, false);
            Arrays.fill(dist, INF);

            dist[A] = 0;
            p[A] = 0;

            for (int dest = 0; dest < N; dest++)
                heap.add(new Node(dest, dist[dest]));

            v = -1;

            for (i = 1; i <= N; i++)
            {
                Node cur = heap.poll();

                while (vis[cur.index])
                    cur = heap.poll();

                if (cur.dist == INF)
                    break;

                vis[cur.index] = true;

                for (Edge e : edge[cur.index])
                {
                    if (dist[e.dest] > dist[cur.index] + e.weight)
                    {
                        dist[e.dest] = dist[cur.index] + e.weight;
                        heap.add(new Node(e.dest, dist[e.dest]));
                        p[e.dest] = cur.index;
                    }
                }
            }

            if(dist[B]==INF)
                sb.append("NO");
            else
                sb.append(dist[B]);

            sb.append("\n");
        }
        System.out.println(sb);
    }
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
class Reader
{
    final private int BUFFER_SIZE = 1 << 16;
    private DataInputStream din;
    private byte[] buffer;
    private int bufferPointer, bytesRead;

    public Reader()
    {
        din = new DataInputStream(System.in);
        buffer = new byte[BUFFER_SIZE];
        bufferPointer = bytesRead = 0;
    }

    public Reader(String file_name) throws IOException
    {
        din = new DataInputStream(new FileInputStream(file_name));
        buffer = new byte[BUFFER_SIZE];
        bufferPointer = bytesRead = 0;
    }

    public String readLine() throws IOException
    {
        byte[] buf = new byte[64]; // line length
        int cnt = 0, c;
        while ((c = read()) != -1)
        {
            if (c == '\n')
                break;
            buf[cnt++] = (byte) c;
        }
        return new String(buf, 0, cnt);
    }

    public int nextInt() throws IOException
    {
        int ret = 0;
        byte c = read();
        while (c <= ' ')
            c = read();
        boolean neg = (c == '-');
        if (neg)
            c = read();
        do
        {
            ret = ret * 10 + c - '0';
        }  while ((c = read()) >= '0' && c <= '9');

        if (neg)
            return -ret;
        return ret;
    }

    public long nextLong() throws IOException
    {
        long ret = 0;
        byte c = read();
        while (c <= ' ')
            c = read();
        boolean neg = (c == '-');
        if (neg)
            c = read();
        do {
            ret = ret * 10 + c - '0';
        }
        while ((c = read()) >= '0' && c <= '9');
        if (neg)
            return -ret;
        return ret;
    }

    public double nextDouble() throws IOException
    {
        double ret = 0, div = 1;
        byte c = read();
        while (c <= ' ')
            c = read();
        boolean neg = (c == '-');
        if (neg)
            c = read();

        do {
            ret = ret * 10 + c - '0';
        }
        while ((c = read()) >= '0' && c <= '9');

        if (c == '.')
        {
            while ((c = read()) >= '0' && c <= '9')
            {
                ret += (c - '0') / (div *= 10);
            }
        }

        if (neg)
            return -ret;
        return ret;
    }

    private void fillBuffer() throws IOException
    {
        bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
        if (bytesRead == -1)
            buffer[0] = -1;
    }

    private byte read() throws IOException
    {
        if (bufferPointer == bytesRead)
            fillBuffer();
        return buffer[bufferPointer++];
    }

    public void close() throws IOException
    {
        if (din == null)
            return;
        din.close();
    }
}
