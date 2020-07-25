//created by Whiplash99
import java.io.*;
import java.util.*;
class A
{
    private static long digitDp(int i, int N, char[] num, long[] dp)
    {
        if(i==N) return 1;
        if(dp[i]!=-1) return dp[i];
        
        if(num[i]=='0') return dp[i]=0;

        long a=0,b=0;
        a=digitDp(i+1,N,num,dp);
        if(i<N-1)
        {
            if(num[i]=='1'||(num[i]=='2'&&num[i+1]<='6'))
                b=digitDp(i+2,N,num,dp);
        }

        return dp[i]=a+b;
    }
    public static void main(String[] args) throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int i,N;

        StringBuilder sb=new StringBuilder();
        while (true)
        {
            char[] num=br.readLine().trim().toCharArray();
            if(num.length==1&&num[0]=='0') break;

            N=num.length;
            long[] dp=new long[N];
            Arrays.fill(dp,-1);

            sb.append(digitDp(0,N,num,dp)).append("\n");
        }
        System.out.println(sb);
    }
}
