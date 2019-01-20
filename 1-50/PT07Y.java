// Created by akashbhalotia
import java.io.*;
import java.util.*;
class PT07Y
{
    static class DSU
    {
        int parent[],rank[];

        DSU(int N)
        {
            parent=new int[N+1];
            rank=new int[N+1];

            for(int i=1;i<=N;i++)
                make_set(i);
        }

        void make_set(int v)
        {
            parent[v]=v;
        }

        int find_set(int v)
        {
            if(parent[v]==v)
                return v;
            return parent[v]=find_set(parent[v]);
        }

        boolean union(int a, int b)
        {
            a=find_set(a);
            b=find_set(b);

            if(a!=b)
            {
                if(rank[a]<rank[b])
                {
                    int tmp=a;
                    a=b;
                    b=tmp;
                }

                parent[b]=a;

                if(rank[a]==rank[b])
                    rank[a]++;

                return true;
            }
           return false;
        }
    }

    public static void main(String[] args) throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int i,N,M,u,v;
        boolean ans=true;

        String s[]=br.readLine().trim().split(" ");
        N=Integer.parseInt(s[0]);
        M=Integer.parseInt(s[1]);

        DSU dsu=null;

        if(M==N-1)
            dsu=new DSU(N);


        for(i=0;i<M;i++)
        {
            s=br.readLine().trim().split(" ");

            u=Integer.parseInt(s[0]);
            v=Integer.parseInt(s[1]);

            if(M==N-1)
            {
                if(!dsu.union(u,v))
                    ans=false;
            }
            else
                ans=false;
        }
        System.out.println((ans)?"YES":"NO");
    }
}
