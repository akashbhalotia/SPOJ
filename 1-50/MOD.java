//created by Whiplash99
import java.io.*;
import java.util.*;
class A
{
    private static final HashMap<Long,Long> map=new HashMap<>();
    private static long _gcd(long a, long b){return a==0?b:_gcd(b%a,a);}
    private static long _discreteLog(long a, long b, long m)
    {
        a%=m; b%=m;
        long k=1,cnt=0,g;
        while ((g=_gcd(a,m))>1)
        {
            if(b==k) return cnt;
            if(b%g!=0) return -1;

            b/=g; m/=g; ++cnt;
            k=(k*a/g)%m;
        }

        map.clear();
        long N=(long)Math.sqrt(m)+1,an=1;
        for(int i=1;i<=N;i++) an=(an*a)%m;

        for(long q=0,cur=b;q<N;q++)
        {
            map.put(cur,q);
            cur=(cur*a)%m;
        }
        for(long p=1,cur=k;p<=N;p++)
        {
            cur=(cur*an)%m;
            if(map.containsKey(cur))return N*p-map.get(cur)+cnt;
        }
        return -1;
    }
    public static void main(String[] args) throws IOException
    {
        Reader r=new Reader();

        StringBuilder sb=new StringBuilder();
        while (true)
        {
            long a=r.nextLong();
            long m=r.nextLong();
            long b=r.nextLong();

            if(a==0&&b==0&&m==0) break;
            long ans=_discreteLog(a,b,m);
            if(ans==-1) sb.append("No Solution\n");
            else sb.append(ans).append("\n");
        }
        System.out.println(sb);
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
