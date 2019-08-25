// Created by akashbhalotia
import java.io.*;
import java.util.*;
class B
{
    private static long merge(int a[], int tmp[], int left, int mid, int right)
    {
        int i,j,k;
        long count=0;

        i=left;
        j=mid;
        k=left;

        while(i<mid&&j<=right)
        {
            if(a[i]<=a[j])
            {
                tmp[k++]=a[i++];
            }
            else
            {
                tmp[k++]=a[j++];
                count+=mid-i;
            }
        }

        while(i<mid)
            tmp[k++]=a[i++];
        while (j<=right)
            tmp[k++]=a[j++];

        for(i=left;i<=right;i++)
            a[i]=tmp[i];

        return count;
    }
    private static long mergeSort(int a[], int tmp[], int left, int right)
    {
        int mid;
        long count=0;

        if(left<right)
        {
            mid=(left+right)/2;
            count+=mergeSort(a,tmp,left,mid);
            count+=mergeSort(a,tmp,mid+1,right);

            count+=merge(a,tmp,left,mid+1,right);
        }
        return count;
    }
    private static long countInv(int a[], int N, int tmp[])
    {
        return mergeSort(a,tmp,0,N-1);
    }
    public static void main(String[] args) throws IOException
    {
        Reader r=new Reader();
        int i,N;

        int T=r.nextInt();
        int tmp[]=new int[(int)(1e6)];
        StringBuilder sb=new StringBuilder();
        while(T-->0)
        {
            N=r.nextInt();
            int a[]=new int[N];
            for(i=0;i<N;i++)
                a[i]=r.nextInt();

            sb.append(countInv(a,N,tmp)).append("\n");
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
