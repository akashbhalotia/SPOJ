// Created by akashbhalotia
import java.io.*;
import java.util.*;
class H
{
    public static void main(String[] args) throws IOException
    {
        Scanner r=new Scanner(System.in);

        int i,N,a,b;
        int T=r.nextInt();
        StringBuilder sb=new StringBuilder();

        while(T-->0)
        {
            a=r.nextInt();
            b=r.nextInt();

            if(b==0)
            {
                sb.append(1).append("\n");
                continue;
            }

            b=(b%4==0)?4:(b%4);
            a%=10;

            sb.append((int)(Math.pow(a,b))%10).append("\n");
        }
        System.out.println(sb);
    }
}
