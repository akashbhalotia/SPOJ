// Created by akashbhalotia
import java.io.*;
class PRIME1
{
    private static boolean isPrime(int N)
    {
        int i,sqrt=(int)Math.sqrt(N);

        if(N<2)
            return false;

        for(i=2;i<=sqrt;i++)
        {
            if(N%i==0)
                return false;
        }

        return true;
    }

    public static void main(String[] args) throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int i,N,M;

        int T=Integer.parseInt(br.readLine().trim());
        StringBuilder sb=new StringBuilder();

        while(T-->0)
        {
            String s[]=br.readLine().trim().split(" ");
            N=Integer.parseInt(s[0]);
            M=Integer.parseInt(s[1]);


            for(i=N;i<=M;i++)
            {
                if(isPrime(i))
                    sb.append(i).append("\n");
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }
}
