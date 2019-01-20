// Created by akashbhalotia
import java.io.*;
import java.util.*;
class COINS
{
    private static HashMap<Integer,Long> dp;
    private static long solve(int N)
    {
        if(N==0)
            return 0;

        if(dp.containsKey(N))
            return dp.get(N);

        dp.put(N,Math.max(N,solve(N/2)+solve(N/3)+solve(N/4)));

        return dp.get(N);
    }
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);

        int i,N;

        StringBuilder sb=new StringBuilder();

        dp=new HashMap<>();

        while(sc.hasNextInt())
        {
            N =sc.nextInt();
            sb.append(solve(N)).append("\n");
        }
        System.out.println(sb);
    }
}
