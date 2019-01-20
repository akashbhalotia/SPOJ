// Created by akashbhalotia
import java.io.*;
import java.util.*;
class FASHION
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int i,N,ans;

        int T=Integer.parseInt(br.readLine().trim());
        StringBuilder sb=new StringBuilder();

        while(T-->0)
        {
            N=Integer.parseInt(br.readLine().trim());

            ans=0;

            int m[]=new int[N];
            int w[]=new int[N];

            String s[]=br.readLine().trim().split(" ");
            for(i=0;i<N;i++)
                m[i]=Integer.parseInt(s[i]);

            s=br.readLine().trim().split(" ");
            for(i=0;i<N;i++)
                w[i]=Integer.parseInt(s[i]);

            Arrays.sort(m);
            Arrays.sort(w);

            for(i=0;i<N;i++)
                ans+=m[i]*w[i];

            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
}
