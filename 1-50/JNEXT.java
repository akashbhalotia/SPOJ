// Created by akashbhalotia
import java.io.*;
import java.util.*;
class B
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int i,tmp,N,max,pos;

        int T=Integer.parseInt(br.readLine().trim());
        StringBuilder sb=new StringBuilder();

        while(T-->0)
        {
            N=Integer.parseInt(br.readLine().trim());
            String s[]=br.readLine().trim().split(" ");
            int a[]=new int[N];
            for(i=0;i<N;i++)
              a[i]=Integer.parseInt(s[i]);

            ArrayDeque<Integer> stack=new ArrayDeque<>();
            int NSE[]=new int[N];
            Arrays.fill(NSE,-1);

            for(i=N-1;i>=0;i--)
            {
                while(!stack.isEmpty()&&a[i]<a[stack.peekFirst()])
                {
                    tmp=stack.pop();
                    NSE[tmp]=i;
                }
                stack.push(i);
            }

            max=-1;
            pos=-1;

            for(i=N-1;i>=0;i--)
            {
                if(NSE[i]>max)
                {
                    max=NSE[i];
                    pos=i;
                }
            }

            if(pos==-1)
            {
                sb.append(-1).append("\n");
                continue;
            }

            tmp=a[pos];
            a[pos]=a[max];
            a[max]=tmp;

            Arrays.sort(a,max+1,N);
            for(i=0;i<N;i++)
                sb.append(a[i]);
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
