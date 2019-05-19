// Created by akashbhalotia
import java.io.*;
import java.util.*;
class B
{
    public static void main(String[] args) throws IOException
    {
        Reader r=new Reader();

        int i,N,k;

        N=r.nextInt();
        StringBuilder sb=new StringBuilder();

        while(N!=0)
        {
            k=1;
            int a[] = new int[N];
            for (i = 0; i < N; i++)
                a[i] = r.nextInt();

            ArrayDeque<Integer> stack = new ArrayDeque<>();

            for (i = 0; i < N; i++)
            {
                while (!stack.isEmpty() && stack.peekFirst() == k)
                {
                    k++;
                    stack.pop();
                }

                if (a[i] == k)
                    k++;
                else
                    stack.push(a[i]);
            }

            while (!stack.isEmpty() && stack.peekFirst() == k)
            {
                k++;
                stack.pop();
            }

            if (stack.isEmpty())
                sb.append("yes");
            else
                sb.append("no");
            
            sb.append("\n");

            N=r.nextInt();
        }
        System.out.println(sb);
    }
    static class Reader
    {final private int BUFFER_SIZE = 1 << 16;private DataInputStream din;private byte[] buffer;
        private int bufferPointer, bytesRead;
        public Reader() { din = new DataInputStream(System.in);buffer = new byte[BUFFER_SIZE];bufferPointer = bytesRead = 0; }
        public Reader(String file_name) throws IOException { din = new DataInputStream(new FileInputStream(file_name));buffer = new byte[BUFFER_SIZE];bufferPointer = bytesRead = 0; }
        public String readLine() throws IOException { byte[] buf = new byte[64]; int cnt = 0, c;while ((c = read()) != -1) { if (c == '\n')break;buf[cnt++] = (byte) c;}return new String(buf, 0, cnt); }
        public int nextInt() throws IOException{ int ret = 0;byte c = read();while (c <= ' ')c = read();boolean neg = (c == '-');if (neg) c = read();do { ret = ret * 10 + c - '0'; }  while ((c = read()) >= '0' && c <= '9');if (neg) return -ret;return ret; }
        public long nextLong() throws IOException { long ret = 0;byte c = read();while (c <= ' ') c = read();boolean neg = (c == '-');if (neg) c = read();do { ret = ret * 10 + c - '0'; } while ((c = read()) >= '0' && c <= '9');if (neg) return -ret;return ret; }
        public double nextDouble() throws IOException { double ret = 0, div = 1;byte c = read();while (c <= ' ') c = read();boolean neg = (c == '-');if (neg) c = read();do { ret = ret * 10 + c - '0'; } while ((c = read()) >= '0' && c <= '9');if (c == '.') {while ((c = read()) >= '0' && c <= '9') { ret += (c - '0') / (div *= 10); } }if (neg) return -ret;return ret; }
        private void fillBuffer() throws IOException { bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);if (bytesRead == -1) buffer[0] = -1; }
        private byte read() throws IOException { if (bufferPointer == bytesRead) fillBuffer();return buffer[bufferPointer++]; }
        public void close() throws IOException { if (din == null) return;din.close(); }
    }
}
