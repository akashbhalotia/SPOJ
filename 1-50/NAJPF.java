// Created by akashbhalotia
import java.io.*;
import java.util.*;
class A
{
    private static final int p=31,MOD=(int)(1e9)+9;
    private static long pow[];

    private static void computePow(int N)
    {
        pow[0]=1;
        for(int i=1;i<N;i++)
            pow[i]=(pow[i-1]*p)%MOD;
    }
    private static long rollingHash(long hash[], char str[], int N)
    {
        long ans=0;
        for(int i=0;i<N;i++)
            ans=hash[i]=(ans+((str[i]-'a'+1)*pow[i])%MOD)%MOD;
        return ans;
    }
    private static long rangeHash(long hash[], int i, int j)
    {
        if(i==0)
            return hash[j];
        long ans=(hash[j]-hash[i-1]+MOD)%MOD;
        return ans%MOD;
    }
    public static void main(String[] args) throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int i,N,len,count;
        long hash,RH;
        pow=new long[1000005];
        computePow(1000005);

        int T=Integer.parseInt(br.readLine().trim());
        StringBuilder sb=new StringBuilder();
        StringBuilder sb2=new StringBuilder();
        StringBuilder NO=new StringBuilder("Not Found");

        long hash1[]=new long[1000005];
        long hash2[]=new long[1000005];

        while(T-->0)
        {
            count=0;
            String s[]=br.readLine().trim().split(" ");
            char text[]=s[0].toCharArray();
            char pat[]=s[1].toCharArray();
            sb2.setLength(0);

            N=text.length;
            len=pat.length;

            if(len>N)
            {
                sb.append(NO).append("\n");
                continue;
            }

            rollingHash(hash1,text,N);
            hash=rollingHash(hash2,pat,len);

            for(i=0;i<N-len+1;i++)
            {
                RH=rangeHash(hash1,i,i+len-1);
                if(RH==(hash*pow[i])%MOD)
                {
                    count++;
                    sb2.append(i+1).append(" ");
                }
            }
            if(count>0)
                sb.append(count).append("\n").append(sb2);
            else
                sb.append(NO);
            sb.append("\n\n");
        }
        System.out.println(sb);
    }
}
