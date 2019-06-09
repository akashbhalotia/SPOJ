// Created by akashbhalotia
// Rabin-Karp/Hashing
// Set byte array size to 1e6, else WA/RTE!!!!!!!!!!!!!!!!!!!!!!!!!!!
// Use try catch for NumberFormatException
// Brute also works

import java.io.*;
import java.util.*;
class A
{
    private static final int p=31,m=(int)(1e9)+9;
    private static long pow[];
    private static void compute_pow()
    {
        pow[0]=1;
        for(int i=1;i<10000000;i++)
            pow[i]=(pow[i-1]*p)%m;
    }
    private static long compute_hash(char s[], int till)
    {
        long hash=0;
        for(int i=0;i<till;i++)
            hash=(hash+(s[i]-'a'+1)*pow[i])%m;
        return hash;
    }
    private static long pow2(long a, long b)
    {
        a%=m;
        b%=m-1;

        long res=1;

        while(b>0)
        {
            if((b&1)==1)
                res=(res*a)%m;

            a=(a*a)%m;
            b>>=1;
        }

        return res%m;
    }
    public static void main(String[] args) throws Exception
    {
        Reader r=new Reader();

        int i, N, len, len2;
        long hash, hash2, MMI = pow2(p, m - 2);
        String s;
        StringBuilder sb = new StringBuilder();
        pow = new long[(int) (1e7)];
        compute_pow();


        try
        {
            while ((s = r.readLine()) != null)
            {
                N = Integer.parseInt(s.trim());
                char subs[] = r.readLine().trim().toCharArray();
                char str[] = r.readLine().trim().toCharArray();

                len = subs.length;
                len2 = str.length;

                if (len2 >= len)
                {
                    hash = compute_hash(subs, len);
                    hash2 = compute_hash(str, len);

                    if (hash == hash2)
                        sb.append(0).append("\n");
                    for (i = 1; i < len2 - len + 1; i++)
                    {
                        hash2 = (hash2 + m - (str[i - 1] - 'a' + 1)) % m;
                        hash2 = (hash2 * MMI) % m;
                        hash2 = (hash2 + (str[i + len - 1] - 'a' + 1) * pow[len - 1]) % m;

                        if (hash == hash2)
                            sb.append(i).append("\n");
                    }
                }
                sb.append("\n");
            }
        }
        catch (Exception e)
        {

        }
        System.out.println(sb);
    }
    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;private DataInputStream din;private byte[] buffer;private int bufferPointer, bytesRead;
        public Reader(){din=new DataInputStream(System.in);buffer=new byte[BUFFER_SIZE];bufferPointer=bytesRead=0;
        }public Reader(String file_name) throws IOException{din=new DataInputStream(new FileInputStream(file_name));buffer=new byte[BUFFER_SIZE];bufferPointer=bytesRead=0;
        }public String readLine() throws IOException{byte[] buf=new byte[1000000];int cnt=0,c;while((c=read())!=-1){if(c=='\n')break;buf[cnt++]=(byte)c;}return new String(buf,0,cnt);
        }public int nextInt() throws IOException{int ret=0;byte c=read();while(c<=' ')c=read();boolean neg=(c=='-');if(neg)c=read();do{ret=ret*10+c-'0';}while((c=read())>='0'&&c<='9');if(neg)return -ret;return ret;
        }public long nextLong() throws IOException{long ret=0;byte c=read();while(c<=' ')c=read();boolean neg=(c=='-');if(neg)c=read();do{ret=ret*10+c-'0';}while((c=read())>='0'&&c<='9');if(neg)return -ret;return ret;
        }public double nextDouble() throws IOException{double ret=0,div=1;byte c=read();while(c<=' ')c=read();boolean neg=(c=='-');if(neg)c = read();do {ret=ret*10+c-'0';}while((c=read())>='0'&&c<='9');if(c=='.')while((c=read())>='0'&&c<='9')ret+=(c-'0')/(div*=10);if(neg)return -ret;return ret;
        }private void fillBuffer() throws IOException{bytesRead=din.read(buffer,bufferPointer=0,BUFFER_SIZE);if(bytesRead==-1)buffer[0]=-1;
        }private byte read() throws IOException{if(bufferPointer==bytesRead)fillBuffer();return buffer[bufferPointer++];
        }public void close() throws IOException{if(din==null) return;din.close();}
    }
}
