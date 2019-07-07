// Created by akashbhalotia
import java.io.*;
import java.util.*;
class H
{
    private static int base[]={2,3,5,7,11,13,17,19,23,29,31,37};
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
    private static boolean isPrime(long P, long a, long d, long s)
    {
        long x=pow(a,d,P);
        if(x==1||x==P-1)
            return true;
        for(int i=1;i<s;i++)
        {
            x=multiply(x,x,P)%P;
            if(x==P-1)
                return true;
        }
        return false;
    }
    private static boolean MillerR(long P)
    {
        long d,s;
        int i;

        s=0;
        d=P-1;

        if(P<2)
            return false;

        while((d&1)==0)
        {
            s++;
            d>>=1;
        }

        for(i=0;i<12;i++)
        {
            if(P==base[i])
                return true;
            if(!isPrime(P,base[i],d,s))
                return false;
        }

        return true;
    }
    public static void main(String[] args) throws IOException
    {
        Reader r=new Reader();

        int i;
        long P,rng,d,s;

        int T=r.nextInt();
        StringBuilder sb=new StringBuilder();

        while(T-->0)
        {
            P=r.nextLong();
            if(MillerR(P))
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
