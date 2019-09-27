// Created by akashbhalotia
import java.io.*;
import java.util.*;
class C
{
    private static int lowerBound(int a[], int l, int r, int x)
    {
        int ans=-1;
        while(l<=r)
        {
            int mid=(l+r)/2;
            if(a[mid]<=x)
            {
                ans=mid;
                l=mid+1;
            }
            else
                r=mid-1;
        }
        return ans+1;
    }
    public static void main(String[] args) throws IOException
    {
        Reader br=new Reader();

        int i,N,k=0,lim;

        N=br.nextInt();
        int Q=br.nextInt();
        final int sqrt=((int)(Math.sqrt(N))+1);

        int a[]=new int[N+10];
        int b[][]=new int[sqrt][sqrt];

        for(i=0;i<N;i++)
        {
            a[i]=br.nextInt();
            k=i/sqrt;
            b[k][i%sqrt]=a[i];
        }

        lim=N%sqrt;

        StringBuilder sb=new StringBuilder();

        for(i=0;i<k;i++)
            Arrays.sort(b[i]);
        Arrays.sort(b[k],0,lim);

        while(Q-->0)
        {
            String s[]=br.readLine().trim().split(" ");
            if(s[0].charAt(0)=='M')
            {
                int idx,newVal;

                idx=Integer.parseInt(s[1]);
                newVal=Integer.parseInt(s[2]);
                --idx;

                int block=idx/sqrt;
                int initVal=a[idx];

                int lo=0,hi=(block==k)?(lim-1):(sqrt-1);
                while (lo<=hi)
                {
                    int mid=(lo+hi)>>1;
                    if(b[block][mid]==initVal)
                    {
                        b[block][mid]=newVal;
                        break;
                    }
                    else if(b[block][mid]<initVal)
                        lo=mid+1;
                    else
                        hi=mid-1;
                }
                a[idx]=newVal;
                int tmp=(block==k)?lim:sqrt;
                Arrays.sort(b[block],0,tmp);
            }
            else
            {
                int l,r,x;
                l=Integer.parseInt(s[1]);
                r=Integer.parseInt(s[2]);
                x=Integer.parseInt(s[3]);
                --l;--r;
                int leftBlock = l/sqrt;
                int rightBlock = r/sqrt;
                int ans = 0;
                if(leftBlock==rightBlock)
                {
                    for(i=l;i<=r;i++)
                        if(a[i]<=x)
                            ans++;
                }
                else
                {
                    if(l%sqrt!=0)
                        leftBlock++;
                    for(i=l;i<leftBlock*sqrt;i++)
                    {
                        if (a[i] <= x)
                            ans++;
                    }
                    while (i+sqrt-1<=r)
                    {
                        int bb=i/sqrt;
                        int tmp=(bb==k)?lim:sqrt;
                        ans+=lowerBound(b[bb],0,tmp-1,x);
                        i+=sqrt;
                    }
                    while(i<=r)
                    {
                        if(a[i]<=x)
                            ans++;
                        i++;
                    }
                }
                sb.append(ans).append("\n");
            }
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
