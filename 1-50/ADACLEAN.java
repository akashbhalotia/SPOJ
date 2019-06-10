// Created by akashbhalotia
// Two hash functions
import java.io.*;
import java.util.*;
class B
{
    private static final int p1=31,m1=(int)(1e9)+9,p2=53,m2=(int)(1e9)+7,lim=100005;
    private static long[] pow1,pow2;

    private static void calc_pow(long[] pow, long p, long m)
    {
        pow[0]=1;
        for(int i=1;i<lim;i++)
            pow[i]=(pow[i-1]*p)%m;
    }
    private static long rollingH(long[] hash, char[] str, int N, long pow[], long m)
    {
        long ans=0;
        for(int i=0;i<N;i++)
            ans=hash[i]=(ans+((str[i]-'a'+1)*pow[i])%m)%m;
        return ans;
    }
    private static long rangeH(long[] hash, int i, int j, long m)
    {
        return (i==0)?hash[j]:(hash[j]-hash[i-1]+m)%m;
    }
    public static void main(String[] args) throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int i,N,K,count;
        long rH1,rH2;

        pow1=new long[lim];
        pow2=new long[lim];
        long hash1[]=new long[lim];
        long hash2[]=new long[lim];

        calc_pow(pow1,p1,m1);
        calc_pow(pow2,p2,m2);
        HashSet<Long> set=new HashSet<>();
        HashSet<Long> set2=new HashSet<>();

        int T=Integer.parseInt(br.readLine().trim());
        StringBuilder sb=new StringBuilder();

        while(T-->0)
        {
            count=0;

            String s[]=br.readLine().trim().split(" ");
            N=Integer.parseInt(s[0]);
            K=Integer.parseInt(s[1]);
            char str[]=br.readLine().trim().toCharArray();
            set.clear();
            set2.clear();

            rollingH(hash1,str,N, pow1,m1);
            rollingH(hash2,str,N,pow2,m2);

            for(i=0;i<N-K+1;i++)
            {
                rH1=(rangeH(hash1,i,i+K-1,m1)*pow1[N-i-1])%m1;
                rH2=(rangeH(hash2,i,i+K-1,m2)*pow2[N-i-1])%m2;

                if(!(set.contains(rH1)&&set2.contains(rH2)))
                    count++;
                set.add(rH1);
                set2.add(rH2);
            }
            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }
}
