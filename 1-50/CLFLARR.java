// Created by akashbhalotia
import java.io.*;
import java.util.*;
class D
{
    private static int find_set(int v, int parent[])
    {
        if(v==parent.length||v==parent[v])
            return v;
        return parent[v]=find_set(parent[v],parent);
    }
    public static void main(String[] args) throws IOException
    {
        Reader r=new Reader();

        int i,j,N,Q;
        int[] l,u,c,parent,colour;

        N=r.nextInt();
        Q=r.nextInt();

        l=new int[Q];
        u=new int[Q];
        c=new int[Q];
        parent=new int[N];
        colour=new int[N];

        for(i=0;i<Q;i++)
        {
            l[i]=r.nextInt()-1;
            u[i]=r.nextInt()-1;
            c[i]=r.nextInt();
        }

        for(i=0;i<N;i++)
            parent[i]=i;

        for(i=Q-1;i>=0;i--)
        {
            for(j=find_set(l[i],parent);j<=u[i];j=find_set(j,parent))
            {
                colour[j]=c[i];
                parent[j]=j+1;
            }
        }

        StringBuilder sb=new StringBuilder();
        for(i=0;i<N;i++)
            sb.append(colour[i]).append("\n");
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
