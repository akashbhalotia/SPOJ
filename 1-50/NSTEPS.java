// Created by akashbhalotia
import java.io.*;
class NSTEPS
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int i,N,x,y,k=2;

        int a[][]=new int[10005][2];

        a[0][0]=0;
        a[0][1]=0;
        a[1][0]=1;
        a[1][1]=1;

        for(i=2;i<=10000;i++)
        {
            if(i%2==0)
            {
                a[i][0]=k;
                a[i-1][1]=k-1;
            }
            else
            {
                a[i][0]=k-1;
                a[i-1][1]=k;
            }
            k+=2;
        }

        N=Integer.parseInt(br.readLine().trim());
        StringBuilder sb=new StringBuilder();
        String s2="No Number";

        while(N-->0)
        {
            String s[]=br.readLine().trim().split(" ");

            x=Integer.parseInt(s[0]);
            y=Integer.parseInt(s[1]);

            if(x==y)
            {
                if(a[x][0]==-1)
                    sb.append(s2);
                else
                    sb.append(a[x][1]);
            }
            else if(x==y+2)
            {
                if(a[x][1]==-1)
                    sb.append(s2);
                else
                    sb.append(a[x][0]);
            }
            else
                sb.append(s2);

            sb.append("\n");
        }

        System.out.println(sb);
    }
}
