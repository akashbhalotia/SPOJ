// Created by akashbhalotia
import java.io.*;
import java.util.*;
class C
{
    private static int[] arr;
    private static final int MIN=-(int)(1e14);
    static class ST
    {
        long[] sum,maxSum,maxP,maxS,tmpMaxSum,tmpSum,tmpS,tmpP,hi,lo;

        ST(int[] arr, int N)
        {
            int lim=N<<2;

            sum=new long[lim+1];
            maxSum=new long[lim+1];
            maxP=new long[lim+1];
            maxS=new long[lim+1];
            tmpMaxSum=new long[lim+1];
            tmpSum=new long[lim+1];
            tmpS=new long[lim+1];
            tmpP=new long[lim+1];
            hi=new long[lim+1];
            lo=new long[lim+1];

            init(1,1,N);
        }
        void init(int i, int a, int b)
        {
            lo[i]=a;
            hi[i]=b;

            if(a==b)
            {
                sum[i]=maxSum[i]=maxP[i]=maxS[i]=arr[a];
                return;
            }

            int m=(a+b)>>1;
            init(i<<1,a,m);
            init((i<<1)+1,m+1,b);

            sum[i]=sum[i<<1]+sum[(i<<1)+1];
            maxP[i]=Math.max(maxP[i<<1],sum[i<<1]+maxP[(i<<1)+1]);
            maxS[i]=Math.max(maxS[i<<1]+sum[(i<<1)+1],maxS[(i<<1)+1]);
            maxSum[i]=Math.max(Math.max(maxSum[i<<1],maxSum[(i<<1)+1]),maxS[i<<1]+maxP[(i<<1)+1]);
        }
        void query(int a, int b){query(1,a,b);}
        void query(int i, int a, int b)
        {
            if(hi[i]<a||b<lo[i])
            {
                tmpSum[i]=0;
                tmpMaxSum[i]=tmpS[i]=tmpP[i]=MIN;
                return;
            }
            if(a<=lo[i]&&hi[i]<=b)
            {
                tmpSum[i]=sum[i];
                tmpMaxSum[i]=maxSum[i];
                tmpS[i]=maxS[i];
                tmpP[i]=maxP[i];

                return;
            }

            query(i<<1,a,b);
            query((i<<1)+1,a,b);

            tmpSum[i]=tmpSum[i<<1]+tmpSum[(i<<1)+1];
            tmpS[i]=Math.max(tmpS[(i<<1)+1],tmpSum[(i<<1)+1]+tmpS[i<<1]);
            tmpP[i]=Math.max(tmpP[i<<1],tmpSum[i<<1]+tmpP[(i<<1)+1]);
            tmpMaxSum[i]=Math.max(Math.max(tmpMaxSum[i<<1],tmpMaxSum[(i<<1)+1]),tmpS[i<<1]+tmpP[(i<<1)+1]);
        }
    }
    public static void main(String[] args) throws IOException
    {
        Reader r=new Reader();
        int i,N;
        long ans=0,l1,r1,l2,r2;

        int T=r.nextInt();
        StringBuilder sb=new StringBuilder();

        while(T-->0)
        {
            N=r.nextInt();
            arr=new int[N+1];

            for(i=1;i<=N;i++)
                arr[i]=r.nextInt();

            ST solve=new ST(arr,N);

            int M=r.nextInt();
            while(M-->0)
            {
                int x1=r.nextInt();
                int y1=r.nextInt();
                int x2=r.nextInt();
                int y2=r.nextInt();

                l1=r1=l2=r2=0;

                if(x2>y1)
                {
                    solve.query(x1,y1);
                    l1=solve.tmpS[1];

                    solve.query(x2,y2);
                    r1=solve.tmpP[1];

                    if(y1+1<=x2-1)
                    {
                        solve.query(y1+1,x2-1);
                        r1+=solve.tmpSum[1];
                    }
                    ans=l1+r1;
                }
                else
                {
                    if(x1<=x2-1)
                    {
                        solve.query(x1,x2-1);
                        l1=solve.tmpS[1];
                    }
                    solve.query(x2,y2);
                    r1=solve.tmpP[1];

                    solve.query(x1,y1);
                    l2=solve.tmpS[1];

                    if(y1+1<=y2)
                    {
                        solve.query(y1+1,y2);
                        r2=solve.tmpP[1];
                    }

                    l1+=r1;
                    l2+=r2;

                    solve.query(x2,y1);
                    l1=Math.max(l1,solve.tmpMaxSum[1]);

                    ans=Math.max(l1,l2);
                }
                sb.append(ans).append("\n");
            }
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
