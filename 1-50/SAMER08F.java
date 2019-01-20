// Created by akashbhalotia
import java.io.*;
import java.util.*;
class SAMER08F
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int i,N,count;

        StringBuilder sb=new StringBuilder();

        while(true)
        {
            N=Integer.parseInt(br.readLine().trim());

            if(N==0)
                break;

            //Basically: N*N+(N-1)(N-1)+(N-2)(N-2)+...+1*1
            count=N*(N+1)*(2*N+1)/6;

            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }
}
