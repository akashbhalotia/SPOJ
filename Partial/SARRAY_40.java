// Created by akashbhalotia
import java.io.*;
import java.util.*;
class A
{
    private static final int p=541,m=(int)(1e9+9);
    private static long pow[],hash[];
    private static int N;
    private static char str[];

    private static void calcPow()
    {
        pow[0]=1;
        for(int i=1;i<N;i++)
            pow[i]=(pow[i-1]*p)%m;
    }
    private static long calcHash()
    {
        hash[0]=str[0]+1;
        for(int i=1;i<N;i++)
            hash[i]=(hash[i-1]+((str[i]+1)*pow[i])%m)%m;
        return hash[N-1];
    }
    private static long rangHash(int i, int j)
    {
        if(i==0)
            return hash[j];
        return (hash[j]-hash[i-1]+m)%m;
    }

    static class SuffixArray implements Comparable<SuffixArray>
    {
        int val;

        SuffixArray(int val){this.val=val;}
        public int compareTo(SuffixArray obj)
        {
            int max=Math.max(this.val,obj.val),min=Math.min(this.val,obj.val);
            int lim=N-max;
            int l=0,r=lim-1,mid,ans=0;

            while(l<=r)
            {
                mid=l+((r-l)>>1);

                long H1=rangHash(min,min+mid);
                long H2=rangHash(max,max+mid);

                H1=(H1*pow[max-min])%m;

                if(H1==H2)
                {
                    ans=mid+1;
                    l=mid+1;
                }
                else
                    r=mid-1;
            }

            if(ans==lim||str[this.val+ans]<str[obj.val+ans])
                return -1;
            return 1;
        }
    }
    public static void main(String[] args) throws IOException
    {
        Reader r=new Reader();

        int i;

        str=r.readLine().trim().toCharArray();
        N=str.length;
        pow=new long[N];
        hash=new long[N];
        calcPow();
        calcHash();

        SuffixArray[] a=new SuffixArray[N];
        for(i=0;i<N;i++)
            a[i]=new SuffixArray(i);

        Arrays.sort(a);

        StringBuilder sb=new StringBuilder();
        for(i=0;i<N;i++)
            sb.append(a[i].val).append("\n");
        System.out.println(sb);
    }
    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;private DataInputStream din;private byte[] buffer;private int bufferPointer, bytesRead;
        public Reader(){din=new DataInputStream(System.in);buffer=new byte[BUFFER_SIZE];bufferPointer=bytesRead=0;
        }public Reader(String file_name) throws IOException{din=new DataInputStream(new FileInputStream(file_name));buffer=new byte[BUFFER_SIZE];bufferPointer=bytesRead=0;
        }public String readLine() throws IOException{byte[] buf=new byte[(int)1e6];int cnt=0,c;while((c=read())!=-1){if(c=='\n')break;buf[cnt++]=(byte)c;}return new String(buf,0,cnt);
        }public int nextInt() throws IOException{int ret=0;byte c=read();while(c<=' ')c=read();boolean neg=(c=='-');if(neg)c=read();do{ret=ret*10+c-'0';}while((c=read())>='0'&&c<='9');if(neg)return -ret;return ret;
        }public long nextLong() throws IOException{long ret=0;byte c=read();while(c<=' ')c=read();boolean neg=(c=='-');if(neg)c=read();do{ret=ret*10+c-'0';}while((c=read())>='0'&&c<='9');if(neg)return -ret;return ret;
        }public double nextDouble() throws IOException{double ret=0,div=1;byte c=read();while(c<=' ')c=read();boolean neg=(c=='-');if(neg)c = read();do {ret=ret*10+c-'0';}while((c=read())>='0'&&c<='9');if(c=='.')while((c=read())>='0'&&c<='9')ret+=(c-'0')/(div*=10);if(neg)return -ret;return ret;
        }private void fillBuffer() throws IOException{bytesRead=din.read(buffer,bufferPointer=0,BUFFER_SIZE);if(bytesRead==-1)buffer[0]=-1;
        }private byte read() throws IOException{if(bufferPointer==bytesRead)fillBuffer();return buffer[bufferPointer++];
        }public void close() throws IOException{if(din==null) return;din.close();}
    }
}
