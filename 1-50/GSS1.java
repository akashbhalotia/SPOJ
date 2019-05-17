// Created by Whiplash99
import java.io.*;
import java.util.*;
class A
{
    private static final int MIN=-(int)(1e9);
    static class ST
    {
        int N;
        int[] sum,maxSum,maxP,maxS,hi,lo,tmpS,tmpP;

        ST(int arr[], int N)
        {
            this.N=N;
            int lim=N<<2;

            sum=new int[lim+1];
            hi=new int[lim+1];
            lo=new int[lim+1];
            maxSum=new int[lim+1];
            maxS=new int[lim+1];
            maxP=new int[lim+1];
            tmpP=new int[lim+1];
            tmpS=new int[lim+1];

            sum[0]=0;
            for(int i=1;i<=N;i++)
                sum[i]=sum[i-1]+arr[i];

            init(1,1,N,arr);
        }
        int getSum(int i)
        {
            return sum[hi[i]]-sum[lo[i]-1];
        }
        void init(int i, int a, int b, int arr[])
        {
            lo[i]=a;
            hi[i]=b;

            if(a==b)
            {
                maxSum[i]=maxS[i]=maxP[i]=arr[a];
                return;
            }

            int m=(a+b)/2;
            init(i<<1,a,m,arr);
            init((i<<1)+1,m+1,b,arr);

            maxSum[i]=Math.max(Math.max(maxSum[i<<1],maxSum[(i<<1)+1]),(maxS[i<<1]+maxP[(i<<1)+1]));
            maxS[i]=Math.max(maxS[i<<1]+getSum((i<<1)+1),maxS[(i<<1)+1]);
            maxP[i]=Math.max(maxP[i<<1],getSum(i<<1)+maxP[(i<<1)+1]);
        }
        int query(int l, int r){return query(1,l,r);}
        int query(int i, int a, int b)
        {
            if(hi[i]<a||b<lo[i])
            {
                tmpP[i]=tmpS[i]=MIN;
                return MIN;
            }
            if(a<=lo[i]&&hi[i]<=b)
            {
                tmpS[i]=maxS[i];
                tmpP[i]=maxP[i];
                return maxSum[i];
            }

            int maxL=query(i<<1,a,b);
            int maxR=query((i<<1)+1,a,b);

            tmpS[i]=Math.max(tmpS[i<<1]+getSum((i<<1)+1),tmpS[(i<<1)+1]);
            tmpP[i]=Math.max(tmpP[i<<1],getSum(i<<1)+tmpP[(i<<1)+1]);

            int ans=Math.max(maxL,maxR);
            ans=Math.max(ans,tmpS[i<<1]+tmpP[(i<<1)+1]);

            return ans;
        }
    }
    public static void main(String[] args) throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int i,N;

        N=Integer.parseInt(br.readLine().trim());
        String s[]=br.readLine().trim().split(" ");

        int a[]=new int[N+1];
        for(i=0;i<N;i++)
          a[i+1]=Integer.parseInt(s[i]);

        ST solve=new ST(a,N);

        int M=Integer.parseInt(br.readLine().trim());
        StringBuilder sb=new StringBuilder();

        while(M-->0)
        {
            s=br.readLine().trim().split(" ");
            int l=Integer.parseInt(s[0]);
            int r=Integer.parseInt(s[1]);

            sb.append(solve.query(l,r)).append("\n");
        }
        System.out.println(sb);
    }
}
