// created by Whiplash99
import java.io.*;
import java.util.*;
class A
{
    private static final double TAU=2*Math.PI;
    private static class Complex
    {
        double real, img;
        Complex(){real=img=0.0;}
        Complex(double real, double img){this.real=real;this.img=img;}
        Complex multiply(Complex obj)
        {
            return new Complex(real*obj.real-img*obj.img,real*obj.img+img*obj.real);
        }
        Complex add(Complex obj)
        {
            return new Complex(real+obj.real,img+obj.img);
        }
        Complex subtract(Complex obj)
        {
            return new Complex(real-obj.real,img-obj.img);
        }
        void divide(double val){real/=val;img/=val;}
    }
    private static void FFT(ArrayList<Complex> a, boolean invert)
    {
        int N=a.size(),i,j,bit,len;
        Complex tmp;
        for(i=1,j=0;i<N;i++)
        {
            bit=N>>1;
            for(;(j&bit)!=0;bit>>=1)
                j^=bit;
            j^=bit;
            if(i<j)
            {
                tmp=a.get(i);
                a.set(i,a.get(j));
                a.set(j,tmp);
            }
        }
        for(len=2;len<=N;len<<=1)
        {
            double ang=TAU/len*(invert?-1:1);
            Complex wlen=new Complex(Math.cos(ang),Math.sin(ang));
            for(i=0;i<N;i+=len)
            {
                Complex w=new Complex(1,0);
                for(j=0;j<len>>1;j++)
                {
                    Complex u=a.get(i+j),v=a.get(i+j+(len>>1)).multiply(w);
                    a.set(i+j,u.add(v));
                    a.set(i+j+(len>>1),u.subtract(v));
                    w=w.multiply(wlen);
                }
            }
        }
        if(invert)
        {
            for(i=0;i<a.size();i++)
                a.get(i).divide(N);
        }
    }
    static ArrayList<Long> multiply(ArrayList<Long> a, ArrayList<Long> b)
    {
        int N=1,i;
        while (N<a.size()+b.size())
            N<<=1;
        ArrayList<Complex> fa=new ArrayList<>(N);
        ArrayList<Complex> fb=new ArrayList<>(N);

        for(i=0;i<a.size();i++)
            fa.add(new Complex(a.get(i),0));
        for(i=0;i<b.size();i++)
            fb.add(new Complex(b.get(i),0));
        for(i=a.size();i<N;i++)
            fa.add(new Complex());
        for(i=b.size();i<N;i++)
            fb.add(new Complex());


        FFT(fa,false);
        FFT(fb,false);

        for(i=0;i<N;i++)
            fa.set(i,fa.get(i).multiply(fb.get(i)));
        FFT(fa,true);

        ArrayList<Long> res=new ArrayList<>(N);
        for(i=0;i<N;i++)
            res.add(Math.round(fa.get(i).real));
        return res;
    }
    public static void main(String[] args) throws IOException
    {
        Reader r=new Reader();

        int i,N;

        int T=r.nextInt();
        StringBuilder sb=new StringBuilder();

        while(T-->0)
        {
            N=r.nextInt();

            ArrayList<Long> a = new ArrayList<>();
            ArrayList<Long> b = new ArrayList<>();

            for (i = 0; i < N+1; i++)
                a.add(r.nextLong());
            for (i = 0; i < N+1; i++)
                b.add(r.nextLong());

            ArrayList<Long> c = multiply(a, b);

            for (i = 0; i < 2 * N+1; i++)
                sb.append(c.get(i)).append(" ");
            sb.append("\n");
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
