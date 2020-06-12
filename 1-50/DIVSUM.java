//created by Whiplash99
import java.io.*;
import java.util.*;
class A
{
    private static final int lim=(int)(5e5);
    private static int[] sum;
    private static void sieve()
    {
        sum=new int[lim+5];
        for(int i=1;i<=lim;i++)
            for(int j=i+i;j<=lim;j+=i)
                sum[j]+=i;
    }
    public static void main(String[] args) throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int i,N;

        sieve();

        int T=Integer.parseInt(br.readLine().trim());
        StringBuilder sb=new StringBuilder();

        while(T-->0)
        {
            N=Integer.parseInt(br.readLine().trim());
            sb.append(sum[N]).append("\n");
        }
        System.out.println(sb);
    }
}

***********************************************************************************************************
//Method 2

//created by Whiplash99
import java.io.*;
import java.util.*;
class A
{
    private static final int lim=(int)(5e5);
    private static int[] spf;
    private static void sieve()
    {
        spf=new int[lim+5];
        spf[0]=spf[1]=-1;

        for(int i=2;i<=lim;i++)
        {
            if(spf[i]==0)
            {
                spf[i] = i;
                if ((long) i * i <= lim)
                {
                    for (int j = i * i; j <= lim; j += i)
                        if (spf[j] == 0) spf[j] = i;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int i,N;

        sieve();

        int T=Integer.parseInt(br.readLine().trim());
        StringBuilder sb=new StringBuilder();

        while(T-->0)
        {
            N=Integer.parseInt(br.readLine().trim());
            if(N==1) sb.append(0).append("\n");
            else
            {
                int sum=1, count, cur, copy=N;
                while (N>1)
                {
                    count=0; cur=spf[N];
                    while (spf[N]==cur)
                    {
                        N/=cur;
                        count++;
                    }
                    sum*=(Math.pow(cur,count+1)-1)/(cur-1);
                }
                sb.append(sum-copy).append("\n");
            }
        }
        System.out.println(sb);
    }
}
