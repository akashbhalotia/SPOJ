// Created by akashbhalotia
import java.io.*;
import java.util.*;
class B
{
    static class Pair implements Comparable<Pair>
    {
        int a,b;
        Pair(int a, int b)
        {
            this.a=a;
            this.b=b;
        }

        public int compareTo(Pair p)
        {
            if(this.b<p.b)
                return -1;
            else if(this.b>p.b)
                return 1;
            else if(this.a<p.a)
                return -1;
            return 1;
        }
    }

    public static void main(String[] args) throws IOException
    {
        Reader r=new Reader();
        int i,N,M,u,v,k;

        int T=r.nextInt();
        StringBuilder sb=new StringBuilder();

        int lim=1005;
        ArrayDeque<Integer>[] edge=new ArrayDeque[lim];
         ArrayDeque<Integer> Q=new  ArrayDeque<>();
        int[] indeg=new int[lim];
        int[] l=new int[lim];

        for(i=0;i<lim;i++)
            edge[i]=new ArrayDeque<>();
        Pair[] pair=new Pair[lim];

        for(int c=1;c<=T;c++)
        {
            k=0;

            N=r.nextInt();
            M=r.nextInt();

            Arrays.fill(indeg,0,N,0);
            Arrays.fill(l,0,N,1);
            Q.clear();

            for(i=0;i<N;i++)
                edge[i].clear();

            for(i=0;i<M;i++)
            {
                u=r.nextInt();
                v=r.nextInt();

                edge[v].add(u);
                indeg[u]++;
            }

            for(i=0;i<N;i++)
            {
                if(indeg[i]==0)
                    Q.add(i);
            }

            while(!Q.isEmpty())
            {
                u=Q.poll();
                pair[k++]=new Pair(u,l[u]);

                for(int tmp:edge[u])
                {
                    indeg[tmp]--;
                    if(indeg[tmp]==0)
                    {
                        l[tmp]=l[u]+1;
                        Q.add(tmp);
                    }
                }
            }

            Arrays.sort(pair,0,N);
            sb.append("Scenario #").append(c).append(":").append("\n\n");

            for (i = 0; i < N; i++)
                sb.append(pair[i].b).append(" ").append(pair[i].a).append("\n\n");
        }
        System.out.println(sb);
    }
    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;private DataInputStream din;private byte[] buffer;private int bufferPointer, bytesRead;
        public Reader(){din=new DataInputStream(System.in);buffer=new byte[BUFFER_SIZE];bufferPointer=bytesRead=0;
        }public Reader(String file_name) throws IOException{din=new DataInputStream(new FileInputStream(file_name));buffer=new byte[BUFFER_SIZE];bufferPointer=bytesRead=0;
        }public String readLine() throws IOException{byte[] buf=new byte[64];int cnt=0,c;while((c=read())!=-1){if(c=='\n')break;buf[cnt++]=(byte)c;}return new String(buf,0,cnt);
        }public int nextInt() throws IOException{int ret=0;byte c=read();while(c<=' ')c=read();boolean neg=(c=='-');if(neg)c=read();do{ret=ret*10+c-'0';}while((c=read())>='0'&&c<='9');if(neg)return -ret;return ret;
        }public long nextLong() throws IOException{long ret=0;byte c=read();while(c<=' ')c=read();boolean neg=(c=='-');if(neg)c=read();do{ret=ret*10+c-'0';}while((c=read())>='0'&&c<='9');if(neg)return -ret;return ret;
        }public double nextDouble() throws IOException{double ret=0,div=1;byte c=read();while(c<=' ')c=read();boolean neg=(c=='-');if(neg)c = read();do {ret=ret*10+c-'0';}while((c=read())>='0'&&c<='9');if(c=='.')while((c=read())>='0'&&c<='9')ret+=(c-'0')/(div*=10);if(neg)return -ret;return ret;
        }private void fillBuffer() throws IOException{bytesRead=din.read(buffer,bufferPointer=0,BUFFER_SIZE);if(bytesRead==-1)buffer[0]=-1;
        }private byte read() throws IOException{if(bufferPointer==bytesRead)fillBuffer();return buffer[bufferPointer++];
        }public void close() throws IOException{if(din==null) return;din.close();}
    }
}
