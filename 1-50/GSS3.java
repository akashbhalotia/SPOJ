// Created by Whiplash99
import java.io.*;
import java.util.*;
class B
{
    private static final int MIN=-(int)(1e9);
    static class ST
    {
        int N;
        int[] sum,maxSum,maxP,maxS,hi,lo,tmpS,tmpP,tmpSum;

        ST(int arr[], int N) {this.N=N;int lim=N<<2;sum=new int[lim+1];hi=new int[lim+1];lo=new int[lim+1];maxSum=new int[lim+1];maxS=new int[lim+1];maxP=new int[lim+1];tmpP=new int[lim+1];tmpS=new int[lim+1];tmpSum=new int[lim+1];init(1,1,N,arr); }
        void init(int i, int a, int b, int arr[]) {lo[i]=a;hi[i]=b;if(a==b) { maxSum[i]=maxS[i]=maxP[i]=sum[i]=arr[a];return; }int m=(a+b)/2;init(i<<1,a,m,arr);init((i<<1)+1,m+1,b,arr);maxSum[i]=Math.max(Math.max(maxSum[i<<1],maxSum[(i<<1)+1]),(maxS[i<<1]+maxP[(i<<1)+1]));sum[i]=sum[i<<1]+sum[(i<<1)+1];maxS[i]=Math.max(maxS[i<<1]+sum[((i<<1)+1)],maxS[(i<<1)+1]);maxP[i]=Math.max(maxP[i<<1],sum[(i<<1)]+maxP[(i<<1)+1]); }
        int query(int l, int r){return query(1,l,r);}
        int query(int i, int a, int b) {if(hi[i]<a||b<lo[i]) { tmpP[i]=tmpS[i]=MIN;tmpSum[i]=0;return MIN; }if(a<=lo[i]&&hi[i]<=b) { tmpS[i]=maxS[i];tmpP[i]=maxP[i];tmpSum[i]=sum[i];return maxSum[i]; }int maxL=query(i<<1,a,b);int maxR=query((i<<1)+1,a,b);tmpS[i]=Math.max(tmpS[i<<1]+tmpSum[((i<<1)+1)],tmpS[(i<<1)+1]);tmpP[i]=Math.max(tmpP[i<<1],tmpSum[(i<<1)]+tmpP[(i<<1)+1]);tmpSum[i]=tmpSum[i<<1]+tmpSum[(i<<1)+1];int ans=Math.max(maxL,maxR);ans=Math.max(ans,tmpS[i<<1]+tmpP[(i<<1)+1]);return ans; }
        void update(int x, int y){update(1,x,y);}
        void update(int i, int x, int y)
        {
            if(hi[i]<x||x<lo[i])
                return;
            if(hi[i]==lo[i])
            {
                maxSum[i]=maxS[i]=maxP[i]=sum[i]=y;
                return;
            }

            update(i<<1,x,y);
            update((i<<1)+1,x,y);

            maxSum[i]=Math.max(Math.max(maxSum[i<<1],maxSum[(i<<1)+1]),(maxS[i<<1]+maxP[(i<<1)+1]));
            maxS[i]=Math.max(maxS[i<<1]+sum[((i<<1)+1)],maxS[(i<<1)+1]);
            maxP[i]=Math.max(maxP[i<<1],sum[(i<<1)]+maxP[(i<<1)+1]);
            sum[i]=sum[i<<1]+sum[(i<<1)+1];
        }
    }
    public static void main(String[] args) throws IOException
    {
        Reader r=new Reader();
        int i,N;

        N=r.nextInt();

        int a[]=new int[N+1];
        for(i=0;i<N;i++)
          a[i+1]=r.nextInt();

        ST solve=new ST(a,N);

        int M=r.nextInt();
        StringBuilder sb=new StringBuilder();

        while(M-->0)
        {
            int type=r.nextInt();
            int l1=r.nextInt();
            int r1=r.nextInt();

            if(type==0)
                solve.update(l1,r1);
            else
                sb.append(solve.query(l1,r1)).append("\n");
        }
        System.out.println(sb);
    }
    static class Reader
    {final private int BUFFER_SIZE = 1 << 16;private DataInputStream din;private byte[] buffer;
     private int bufferPointer, bytesRead;
     public Reader() { din = new DataInputStream(System.in);buffer = new byte[BUFFER_SIZE];bufferPointer = bytesRead = 0; }
     public Reader(String file_name) throws IOException { din = new DataInputStream(new FileInputStream(file_name));buffer = new byte[BUFFER_SIZE];bufferPointer = bytesRead = 0; }
     public String readLine() throws IOException { byte[] buf = new byte[64]; int cnt = 0, c;while ((c = read()) != -1) { if (c == '\n')break;buf[cnt++] = (byte) c;}return new String(buf, 0, cnt); }
     public int nextInt() throws IOException{ int ret = 0;byte c = read();while (c <= ' ')c = read();boolean neg = (c == '-');if (neg) c = read();do { ret = ret * 10 + c - '0'; }  while ((c = read()) >= '0' && c <= '9');if (neg) return -ret;return ret; }
     public long nextLong() throws IOException { long ret = 0;byte c = read();while (c <= ' ') c = read();boolean neg = (c == '-');if (neg) c = read();do { ret = ret * 10 + c - '0'; } while ((c = read()) >= '0' && c <= '9');if (neg) return -ret;return ret; }
     public double nextDouble() throws IOException { double ret = 0, div = 1;byte c = read();while (c <= ' ') c = read();boolean neg = (c == '-');if (neg) c = read();do { ret = ret * 10 + c - '0'; } while ((c = read()) >= '0' && c <= '9');if (c == '.') {while ((c = read()) >= '0' && c <= '9') { ret += (c - '0') / (div *= 10); } }if (neg) return -ret;return ret; }
     private void fillBuffer() throws IOException { bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);if (bytesRead == -1) buffer[0] = -1; }
     private byte read() throws IOException { if (bufferPointer == bytesRead) fillBuffer();return buffer[bufferPointer++]; }
     public void close() throws IOException { if (din == null) return;din.close(); }
    }
}
