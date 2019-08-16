// inspired by https://github.com/godely/Problems/blob/master/Spoj/LONGCS.cpp
import java.io.*;
import java.util.*;
class A
{
    private static final int MAXN=(int)(2e5);
    private static int SA[],LCP[],RA[], tempSA[], tempRA[], Phi[], PLCP[];
    private static int c[],N;
    private static char str[];

    static class Pair
    {
        int first,second;
        Pair(int first, int second)
        {
            this.first=first;
            this.second=second;
        }
    }
    private static void init()
    {
        SA=new int[MAXN];
        str=new char[MAXN];
        LCP=new int[MAXN];
        RA=new int[MAXN];
        tempSA=new int[MAXN];
        tempRA=new int[MAXN];
        Phi=new int[MAXN];
        PLCP=new int[MAXN];
        c=new int[MAXN];
    }
    private static void computeLCP()
    {
        int i, L;
        Phi[SA[0]] = -1;
        for(i=1;i<N;i++) Phi[SA[i]] = SA[i-1];
        for (i = L = 0; i < N; i++) {
            if (Phi[i] == -1) { PLCP[i] = 0; continue; }
            while (str[i + L] == str[Phi[i] + L]) L++;
            PLCP[i] = L;
            L = Math.max(L-1, 0);
        }
        for (i = 0; i < N; i++) LCP[i] = PLCP[SA[i]];
    }
    private static void countingSort(int k)
    {
        int i,sum,maxi=Math.max(300,N);
        Arrays.fill(c,0);

        for (i = 0; i < N; i++) c[i + k < N ? RA[i + k] : 0]++;
        for (i = sum = 0; i < maxi; i++) {
            int t = c[i]; c[i] = sum; sum += t;
        }
        for (i = 0; i < N; i++) tempSA[c[SA[i]+k < N ? RA[SA[i]+k] : 0]++] = SA[i];
        for (i = 0; i < N; i++) SA[i] = tempSA[i];
    }
    private static void constructSA()
    {
        int i,k,r;
        for(i=0;i<N;i++)
            RA[i]=(int)str[i];
        for(i=0;i<N;i++)
            SA[i]=i;
        for(k=1;k<N;k<<=1)
        {
            countingSort(k);
            countingSort(0);
            tempRA[SA[0]] = r = 0;
            for(i=1;i<N;i++)
                tempRA[SA[i]] = (RA[SA[i]] == RA[SA[i-1]] && RA[SA[i]+k] == RA[SA[i-1]+k]) ? r : ++r;
            for (i = 0; i < N; i++) RA[i] = tempRA[i];
            if (RA[SA[N-1]] == N-1) break;
        }
    }
    public static void main(String[] args) throws IOException
    {
        Reader r=new Reader();

        int i, j, K, ans, count, val;

        init();

        int T = r.nextInt();
        StringBuilder sb = new StringBuilder();
        ArrayDeque<Pair> deque = new ArrayDeque<>();
        int identity[] = new int[MAXN];
        int track[] = new int[20];

        while (T-- > 0)
        {
            N = 0;
            K = r.nextInt();

            for (i = 0; i < K; i++)
            {
                char tmp[] = r.readLine().trim().toCharArray();
                for (j = 0; j < tmp.length; j++)
                    str[j + N] = tmp[j];

                Arrays.fill(identity, N, N + tmp.length + 1, i);
                N += tmp.length;
                str[N++] = (char) (32 + i);
            }
            if (K == 1)
            {
                sb.append(N - 1).append("\n");
                continue;
            }
            constructSA();
            computeLCP();


            Arrays.fill(track, 0);
            ans = count = 0;

            deque.clear();

            track[identity[SA[0]]]++;
            if (track[identity[SA[0]]] == 1)
                count++;

            for (i = 0, j = 1; j < N; j++)
            {
                while (!deque.isEmpty() && LCP[j] < deque.getLast().first)
                    deque.removeLast();
                deque.addLast(new Pair(LCP[j], j));

                track[identity[SA[j]]]++;
                if (track[identity[SA[j]]] == 1)
                    count++;

                while (count == K)
                {
                    ans = Math.max(ans, deque.getFirst().first);
                    track[identity[SA[i]]]--;
                    if (track[identity[SA[i]]] == 0)
                        count--;
                    if (i + 1 == deque.getFirst().second)
                        deque.removeFirst();
                    i++;
                }
            }

            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;private DataInputStream din;private byte[] buffer;private int bufferPointer, bytesRead;
        public Reader(){din=new DataInputStream(System.in);buffer=new byte[BUFFER_SIZE];bufferPointer=bytesRead=0;
        }public Reader(String file_name) throws IOException{din=new DataInputStream(new FileInputStream(file_name));buffer=new byte[BUFFER_SIZE];bufferPointer=bytesRead=0;
        }public String readLine() throws IOException{byte[] buf=new byte[(int)(3e4)];int cnt=0,c;while((c=read())!=-1){if(c=='\n')break;buf[cnt++]=(byte)c;}return new String(buf,0,cnt);
        }public int nextInt() throws IOException{int ret=0;byte c=read();while(c<=' ')c=read();boolean neg=(c=='-');if(neg)c=read();do{ret=ret*10+c-'0';}while((c=read())>='0'&&c<='9');if(neg)return -ret;return ret;
        }public long nextLong() throws IOException{long ret=0;byte c=read();while(c<=' ')c=read();boolean neg=(c=='-');if(neg)c=read();do{ret=ret*10+c-'0';}while((c=read())>='0'&&c<='9');if(neg)return -ret;return ret;
        }public double nextDouble() throws IOException{double ret=0,div=1;byte c=read();while(c<=' ')c=read();boolean neg=(c=='-');if(neg)c = read();do {ret=ret*10+c-'0';}while((c=read())>='0'&&c<='9');if(c=='.')while((c=read())>='0'&&c<='9')ret+=(c-'0')/(div*=10);if(neg)return -ret;return ret;
        }private void fillBuffer() throws IOException{bytesRead=din.read(buffer,bufferPointer=0,BUFFER_SIZE);if(bytesRead==-1)buffer[0]=-1;
        }private byte read() throws IOException{if(bufferPointer==bytesRead)fillBuffer();return buffer[bufferPointer++];
        }public void close() throws IOException{if(din==null) return;din.close();}
    }
}
