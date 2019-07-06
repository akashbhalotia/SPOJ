// Created by akashbhalotia
import java.io.*;
import java.util.*;
class H
{
    private static long multiply(long a, long x, long mod)
    {
        a%=mod;
        x%=mod;
        long res=0;

        while(x>0)
        {
            if((x&1)==1)
                res=(res+a)%mod;

            x>>=1;
            a=(a<<1)%mod;
        }
        return res%mod;
    }
    private static long pow(long a, long x, long mod)
    {
        a%=mod;
        long res=1;

        while(x>0)
        {
            if((x&1)==1)
                res=multiply(res,a,mod)%mod;

            x>>=1;
            a=multiply(a,a,mod)%mod;
        }
        return res%mod;
    }
    private static boolean isPrime(long P)
    {
        if(P<4)
            return (P==2||P==3);

        for(int i=1;i<=100;i++)
        {
            long rng=2+(long)(Math.random()*(P-3));
            if(pow(rng,P-1,P)!=1)
                return false;
        }
        return true;
    }
    public static void main(String[] args) throws IOException
    {
        Reader r=new Reader();

        int i;
        long P;
        int T=r.nextInt();
        StringBuilder sb=new StringBuilder();

        while(T-->0)
        {
            P=r.nextLong();

            if(isPrime(P))
                sb.append("YES").append("\n");
            else
                sb.append("NO").append("\n");
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
