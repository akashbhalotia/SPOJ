// created by Whiplash99
import java.io.*;
import java.util.*;
class A
{
    public static void main(String[] args) throws IOException
    {
        Reader r=new Reader();
        int i,N,u,v2;

        int T=r.nextInt();
        StringBuilder sb=new StringBuilder();

        while(T-->0)
        {
            N=r.nextInt();
            int R=r.nextInt();
            int M=r.nextInt();
            boolean flag=true;

            boolean visited[]=new boolean[N];
            ArrayDeque<Integer> queue=new ArrayDeque<>();
            int soldierNum[]=new int[N];
            Arrays.fill(soldierNum,-1);
            int d[]=new int[N];
            Arrays.fill(d,-1);

            ArrayDeque<Integer> edge[]=new ArrayDeque[N];
            for(i=0;i<N;i++)
                edge[i]=new ArrayDeque<>();

            for(i=0;i<R;i++)
            {
                u=r.nextInt()-1;
                v2=r.nextInt()-1;

                edge[u].add(v2);
                edge[v2].add(u);
            }

            for(i=0;i<M;i++)
            {
                int K=r.nextInt()-1;
                int S=r.nextInt();

                if(visited[K])
                {
                    flag=false;
                    break;
                }

                queue.add(K);
                visited[K]=true;
                soldierNum[K]=i;
                d[K]=0;

                while(!queue.isEmpty())
                {
                    u=queue.pollFirst();
                    if(d[u]+1>S)
                        continue;

                    for(int v:edge[u])
                    {
                        if(visited[v])
                        {
                            if(soldierNum[v]!=i)
                            {
                                flag=false;
                                break;
                            }
                            continue;
                        }
                        visited[v]=true;
                        soldierNum[v]=i;
                        d[v]=d[u]+1;

                        queue.add(v);
                    }
                }
            }

            for(i=0;i<N;i++)
            {
                if(!visited[i])
                    flag=false;
            }
            sb.append(flag?"Yes\n":"No\n");
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
