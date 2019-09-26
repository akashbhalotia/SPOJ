/*
  Mo's algo.
*/

// Created by akashbhalotia
import java.io.*;
import java.util.*;
class A
{
    private static int S;
    private static Pair[] query;
 
    static class Pair implements Comparable<Pair>
    {
        int L, R, pos, ans;
        Pair(int L, int R, int pos)
        {
            this.L = L;
            this.R = R;
            this.pos = pos;
        }
        public int compareTo(Pair p)
        {
            if(this.L /S<p.L /S)
                return -1;
            else if(this.L /S>p.L /S)
                return 1;
            else
                return (this.R <p.R)?-1:1;
        }
    }
    private static void solve(int a[], int Q, int max)
    {
        int count,curL,curR;
        int freq[]=new int[max+5];
 
        count=0;
        curL=curR=query[0].L;
 
        count++;
        freq[a[curL]]++;
 
        for(int q=0;q<Q;q++)
        {
            while(curL>query[q].L)
            {
                curL--;
                freq[a[curL]]++;
                if(freq[a[curL]]==1)
                    count++;
            }
            while(curR<query[q].R)
            {
                curR++;
                freq[a[curR]]++;
                if(freq[a[curR]]==1)
                    count++;
            }
            while(curL<query[q].L)
            {
                curL++;
                freq[a[curL-1]]--;
                if(freq[a[curL-1]]==0)
                    count--;
            }
            while(curR>query[q].R)
            {
                curR--;
                freq[a[curR+1]]--;
                if(freq[a[curR+1]]==0)
                    count--;
            }
            query[q].ans=count;
        }
    }
    public static void main(String[] args) throws IOException
    {
        //BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        Reader br=new Reader();
 
        int i,N,l,r,max=0;
 
        N=br.nextInt();
        S=(int)(Math.sqrt(N))+1;
 
        int a[]=new int[N];
        for(i=0;i<N;i++)
        {
            a[i] = br.nextInt();
            max=Math.max(a[i],max);
        }
 
        int Q=br.nextInt();
        query=new Pair[Q];
 
        for(i=0;i<Q;i++)
        {
            l=br.nextInt()-1;
            r=br.nextInt()-1;
 
            query[i]=new Pair(l,r,i);
        }
 
        Arrays.sort(query);
 
        solve(a,Q,max);
        Arrays.sort(query,(Pair x, Pair y)->(x.pos-y.pos));
 
        StringBuilder sb=new StringBuilder();
 
        for(i=0;i<Q;i++)
            sb.append(query[i].ans).append("\n");
 
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
