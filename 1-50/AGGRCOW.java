// created by Whiplash99
import java.io.*;
import java.util.*;
class A
{
    private static void shuffleArray(int[] arr)
    {
        int n = arr.length;
        Random rnd = new Random();
        for (int i = 0; i < n; ++i)
        {
            int tmp = arr[i];
            int randomPos = i + rnd.nextInt(n - i);
            arr[i] = arr[randomPos];
            arr[randomPos] = tmp;
        }
    }
    private static int binarySearch(int a[], int N, int C)
    {
        int l=1,r=(int)(1e9),mid,ans=0,c;

        while(l<=r)
        {
            mid=(l+r)/2;
            int i,prev=0;

            for(c=2;c<=C;c++)
            {
                i=prev+1;
                while(i<N&&a[i]-a[prev]<mid)i++;
                if(i==N)
                    break;
                prev=i;
            }
            if(c==C+1)
            {
                ans=mid;
                l=mid+1;
            }
            else
                r=mid-1;
        }
        return ans;
    }
    public static void main(String[] args) throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int i,N;

        int T=Integer.parseInt(br.readLine().trim());
        StringBuilder sb=new StringBuilder();

        while(T-->0)
        {
            String s[]=br.readLine().trim().split(" ");
            N=Integer.parseInt(s[0]);
            int C=Integer.parseInt(s[1]);

            int a[]=new int[N];
            for(i=0;i<N;i++)
                a[i]=Integer.parseInt(br.readLine().trim());
            shuffleArray(a);
            Arrays.sort(a);
            sb.append(binarySearch(a,N,C)).append("\n");
        }
        System.out.println(sb);
    }
}
