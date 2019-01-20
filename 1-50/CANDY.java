// Created by akashbhalotia
import java.io.*;
class CANDY
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int i,N,sum,opt,ans;

        StringBuilder sb=new StringBuilder();

        while(true)
        {
            N=Integer.parseInt(br.readLine().trim());

            if(N==-1)
                break;

            int a[]=new int[N];
            sum=0;
            ans=0;

            for(i=0;i<N;i++)
            {
                a[i]=Integer.parseInt(br.readLine().trim());
                sum+=a[i];
            }

            if(sum%N==0)
            {
                opt=sum/N;

                for(i=0;i<N;i++)
                {
                    if(a[i]>opt)
                        ans+=a[i]-opt;
                }

                sb.append(ans);
            }
            else
                sb.append(-1);

            sb.append("\n");
        }

        System.out.println(sb);
    }
}
