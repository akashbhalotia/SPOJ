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
