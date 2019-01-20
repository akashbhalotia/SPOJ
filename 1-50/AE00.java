// Created by akashbhalotia
import java.io.*;
import java.util.*;
class AE00
{
    private static int counfFact(int N)
    {
        int count=0,sqrt=(int)Math.sqrt(N),i;

        for(i=1;i<=sqrt;i++)
        {
            if(N%i==0)
                count++;
        }

        return count;
    }
    public static void main(String[] args) throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int i,N;
        long sum=0;

        N=Integer.parseInt(br.readLine().trim());

        for(i=1;i<=N;i++)
            sum+=counfFact(i);

        System.out.println(sum);
    }
}
