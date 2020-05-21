//created by Whiplash99
import java.io.*;
import java.util.*;
class A
{
    static class BIT
    {
        int size;
        int[] table;

        BIT(int size)
        {
            table=new int[size+1];
            this.size=size+1;
        }
        void update(int i, int delta)
        {
            i++;
            while(i<size)
            {
                table[i]+=delta;
                i+=Integer.lowestOneBit(i); //i+=i&-i
            }
        }
        int sum(int i)
        {
            int s=0; i++;
            while (i>0)
            {
                s+=table[i];
                i-=Integer.lowestOneBit(i);//i-=i&-i
            }
            return s;
        }
        int rangeSum(int i, int j){return sum(j)-sum(i-1);}
        int search(int val)
        {
            int idx=Integer.highestOneBit(size-1), pos=-1, i=idx;
            while (i>0)
            {
                i>>=1;
                if(idx>=size) idx-=i;
                else if(table[idx]>=val) {pos=idx-1; idx-=i;}
                else {val-=table[idx]; idx+=i;}
            }
            return pos;
        }
    }
    private static void shuffleArray(int[] arr)
    {
        int n = arr.length;
        Random rnd = new Random();
        for(int i=0; i<n; ++i)
        {
            int tmp = arr[i];
            int randomPos = i + rnd.nextInt(n-i);
            arr[i] = arr[randomPos];
            arr[randomPos] = tmp;
        }
    }
    private static int binarySearch(int[] a, int N, int key)
    {
        int l=0,r=N-1,mid,pos=-1;
        while (l<=r)
        {
            mid=(l+r)/2;
            if(a[mid]>=key)
            {
                pos=mid;
                r=mid-1;
            }
            else l=mid+1;
        }
        return pos;
    }
    public static void main(String[] args) throws IOException
    {
        Reader r=new Reader();

        int i;
        final String message="invalid";

        int Q=r.nextInt();
        StringBuilder sb=new StringBuilder();

        int[] q=new int[Q];
        int[] tmp=new int[Q];
        char[] type=new char[Q];

        for(i=0;i<Q;i++)
        {
            String s[]=r.readLine().trim().split(" ");
            type[i]=s[0].charAt(0);
            q[i]=tmp[i]=Integer.parseInt(s[1]);
        }

        shuffleArray(tmp);
        Arrays.sort(tmp);

        BIT FT=new BIT(Q+5);
        for(i=0;i<Q;i++)
        {
            if(type[i]=='I')
            {
                int x=binarySearch(tmp,Q,q[i]);
                if(FT.rangeSum(x,x)>0) continue;
                FT.update(x,1);
            }
            else if(type[i]=='D')
            {
                int x=binarySearch(tmp,Q,q[i]);
                if(FT.rangeSum(x,x)==0) continue;
                FT.update(x,-1);
            }
            else if(type[i]=='K')
            {
                int pos=FT.search(q[i]);
                if(pos==-1) sb.append(message).append("\n");
                else sb.append(tmp[pos]).append("\n");
            }
            else
            {
                int x=binarySearch(tmp,Q,q[i])-1;
                sb.append(FT.sum(x)).append("\n");
            }
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
