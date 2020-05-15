//created by Whiplash99
import java.io.*;
import java.util.*;
class A
{
    private static final int INF=Integer.MAX_VALUE;
    static class Pair
    {
        int f, s;
        Pair(int f, int s){this.f = f;this.s = s;}
    }
    private static int BFS(char str[][], int R, int C) //0-1 BFS
    {
        ArrayDeque<Pair> deque=new ArrayDeque<>();
        int[][] dist=new int[R][C];

        for(int i=0;i<R;i++) Arrays.fill(dist[i],INF);
        dist[0][0]=0;

        deque.add(new Pair(0,0));
        while (!deque.isEmpty())
        {
            Pair tmp=deque.poll();
            int x=tmp.f;
            int y=tmp.s;

            if(x+1<R)
            {
                int cost=(str[x][y]==str[x+1][y])?0:1;
                if(dist[x][y]+cost<dist[x+1][y])
                {
                    dist[x+1][y]=dist[x][y]+cost;
                    if(cost==0) deque.addFirst(new Pair(x+1,y));
                    else deque.addLast(new Pair(x+1,y));
                }
            }
            if(x-1>=0)
            {
                int cost=(str[x][y]==str[x-1][y])?0:1;
                if(dist[x][y]+cost<dist[x-1][y])
                {
                    dist[x-1][y]=dist[x][y]+cost;
                    if(cost==0) deque.addFirst(new Pair(x-1,y));
                    else deque.addLast(new Pair(x-1,y));
                }
            }
            if(y+1<C)
            {
                int cost=(str[x][y]==str[x][y+1])?0:1;
                if(dist[x][y]+cost<dist[x][y+1])
                {
                    dist[x][y+1]=dist[x][y]+cost;
                    if(cost==0) deque.addFirst(new Pair(x,y+1));
                    else deque.addLast(new Pair(x,y+1));
                }
            }
            if(y-1>=0)
            {
                int cost=(str[x][y]==str[x][y-1])?0:1;
                if(dist[x][y]+cost<dist[x][y-1])
                {
                    dist[x][y-1]=dist[x][y]+cost;
                    if(cost==0) deque.addFirst(new Pair(x,y-1));
                    else deque.addLast(new Pair(x,y-1));
                }
            }
        }
        return dist[R-1][C-1];
    }
    public static void main(String[] args) throws IOException
    {
        Reader r=new Reader();

        int i,N;

        int T=r.nextInt();
        StringBuilder sb=new StringBuilder();

        while(T-->0)
        {
            int R=r.nextInt();
            int C=r.nextInt();

            char str[][]=new char[R][C];
            for(i=0;i<R;i++) str[i]=r.readLine().trim().toCharArray();

            sb.append(BFS(str,R,C)).append("\n");
        }
        System.out.println(sb);
    }
    static class Reader
    {
        final private int BUFFER_SIZE = 1 << 16;private DataInputStream din;private byte[] buffer;private int bufferPointer, bytesRead;
        public Reader(){din=new DataInputStream(System.in);buffer=new byte[BUFFER_SIZE];bufferPointer=bytesRead=0;
        }public Reader(String file_name) throws IOException{din=new DataInputStream(new FileInputStream(file_name));buffer=new byte[BUFFER_SIZE];bufferPointer=bytesRead=0;
        }public String readLine() throws IOException{byte[] buf=new byte[(int)(1e3)];int cnt=0,c;while((c=read())!=-1){if(c=='\n')break;buf[cnt++]=(byte)c;}return new String(buf,0,cnt);
        }public int nextInt() throws IOException{int ret=0;byte c=read();while(c<=' ')c=read();boolean neg=(c=='-');if(neg)c=read();do{ret=ret*10+c-'0';}while((c=read())>='0'&&c<='9');if(neg)return -ret;return ret;
        }public long nextLong() throws IOException{long ret=0;byte c=read();while(c<=' ')c=read();boolean neg=(c=='-');if(neg)c=read();do{ret=ret*10+c-'0';}while((c=read())>='0'&&c<='9');if(neg)return -ret;return ret;
        }public double nextDouble() throws IOException{double ret=0,div=1;byte c=read();while(c<=' ')c=read();boolean neg=(c=='-');if(neg)c = read();do {ret=ret*10+c-'0';}while((c=read())>='0'&&c<='9');if(c=='.')while((c=read())>='0'&&c<='9')ret+=(c-'0')/(div*=10);if(neg)return -ret;return ret;
        }private void fillBuffer() throws IOException{bytesRead=din.read(buffer,bufferPointer=0,BUFFER_SIZE);if(bytesRead==-1)buffer[0]=-1;
        }private byte read() throws IOException{if(bufferPointer==bytesRead)fillBuffer();return buffer[bufferPointer++];
        }public void close() throws IOException{if(din==null) return;din.close();}
    }
}
