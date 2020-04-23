/*
    https://math.stackexchange.com/questions/48682/maximization-with-xor-operator
 */

//created by Whiplash99
import java.io.*;
import java.util.*;
class A
{
    private static long[] pow;
    private static int highestBit(long N)
    {
        for(int i=60;i>=0;i--) if((pow[i]&N)>0) return i;
        return -1;
    }
    public static void main(String[] args) throws IOException
    {
        Reader r=new Reader();

        int i,N;

        N=r.nextInt();
        long[] a=new long[N];
        for(i=0;i<N;i++) a[i]=r.nextLong();

        long ans=0;
        pow=new long[65]; pow[0]=1;
        for(i=1;i<=60;i++) pow[i]=pow[i-1]*2;

        ArrayList<Long> list=new ArrayList<>();
        ArrayList<Long> initial[]=new ArrayList[65];
        for(i=0;i<=60;i++) initial[i]=new ArrayList<>();

        for(i=0;i<N;i++) initial[highestBit(a[i])].add(a[i]);
        for(i=60;i>=0;i--)
        {
            boolean flag=false; long first=0;
            for(long j:initial[i])
            {
                if(!flag)
                {
                    first=j; flag=true;
                    list.add(j);
                }
                if((first^j)>0) initial[highestBit(first^j)].add(first^j);
            }
        }

        for(long j:list) if((ans^j)>ans) ans^=j;
        System.out.println(ans);
    }
    static class Reader
    {
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
