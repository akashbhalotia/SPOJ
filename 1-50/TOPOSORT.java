// Created by akashbhalotia
import java.io.*;
import java.util.*;
class A
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
 
        int i,N,M,count=0,u,v;
 
        String s[]=br.readLine().trim().split(" ");
        N=Integer.parseInt(s[0]);
        M=Integer.parseInt(s[1]);
 
        StringBuilder sb=new StringBuilder();
        PriorityQueue<Integer> Q=new PriorityQueue<>();
        ArrayDeque<Integer>[] edge=new ArrayDeque[N];
        int[] indeg=new int[N];
 
        for(i=0;i<N;i++)
            edge[i]=new ArrayDeque<>();
 
        for(i=0;i<M;i++)
        {
            s=br.readLine().trim().split(" ");
            u=Integer.parseInt(s[0])-1;
            v=Integer.parseInt(s[1])-1;
 
            edge[u].add(v);
            indeg[v]++;
        }
 
        for(i=0;i<N;i++)
        {
            if (indeg[i] == 0)
                Q.add(i);
        }
 
        while(!Q.isEmpty())
        {
            u=Q.poll();
            sb.append(u+1).append(" ");
            count++;
 
            for(int tmp: edge[u])
            {
                indeg[tmp]--;
                if(indeg[tmp]==0)
                    Q.add(tmp);
            }
        }
 
        if(count<N)
            System.out.println("Sandro fails.");
        else
            System.out.println(sb);
    }
} 
