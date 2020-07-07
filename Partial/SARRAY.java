//created by Whiplash99
//My code. O(NlogN). Gives 60 points.

import java.io.*;
import java.util.*;
class F
{
    static class SuffixArray
    {
        int[] p,c,lcp,pn,cn,freq; int N, classes;
        SuffixArray(char[] str)
        {
            N=str.length; classes=65;
            p=new int[Math.max(N,classes)]; c=new int[Math.max(N,classes)];
            pn=new int[Math.max(N,classes)]; cn=new int[Math.max(N,classes)];
            freq=new int[Math.max(N,classes)]; //lcp=new int[N-1];

            buildSA(str);
            //buildLCP(str);
        }
        void buildSA(char[] str)
        {
            for(int i=0;i<N;i++) pn[i]=i;
            for(int i=0;i<N;i++)
            {
                if(str[i]=='$') c[i]=0;
                else if(Character.isDigit(str[i])) c[i]=str[i]-'0'+1;
                else if(Character.isUpperCase(str[i])) c[i]=str[i]-'A'+11;
                else c[i]=str[i]-'a'+37;
            }

            countSort(0);
            radixSort();
        }
        void countSort(int len)
        {
            Arrays.fill(freq,0,classes,0);
            for(int i=0;i<N;i++) freq[c[pn[i]]]++;
            for(int i=1;i<classes;i++) freq[i]+=freq[i-1];
            for(int i=N-1;i>=0;i--) p[--freq[c[pn[i]]]]=pn[i];

            cn[p[0]]=0; classes=1;
            int prevF=p[0], prevS=add(p[0],len);
            for(int i=1;i<N;i++)
            {
                int curF=p[i], curS=add(p[i],len);
                if(c[curF]!=c[prevF]||c[curS]!=c[prevS]) ++classes;
                cn[p[i]]=classes-1; prevF=curF; prevS=curS;
            }
            if (N >= 0) System.arraycopy(cn, 0, c, 0, N);
        }
        void radixSort()
        {
            for(int len=1;len<N;len<<=1)
            {
                for(int i=0;i<N;i++) pn[i]=subtract(p[i],len);
                countSort(len);
            }
        }
        int add(int a, int b){return a+b<N?a+b:a+b-N;}
        int subtract(int a, int b){return a-b>=0?a-b:a-b+N;}
        void buildLCP(char[] str)
        {
            for(int i=0,K=0;i<N;i++)
            {
                if(c[i]==N-1){K=0; continue;}
                int j=p[c[i]+1];
                while (i+K<N&&j+K<N&&str[i+K]==str[j+K]) K++;
                lcp[c[i]]=K;
                if(K>0) K--;
            }
        }
    }
    public static void main(String[] args) throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int i,N;

        String s=br.readLine().trim();
        SuffixArray SA=new SuffixArray((s+'$').toCharArray());

        StringBuilder sb=new StringBuilder();
        for(i=1;i<=s.length();i++) sb.append(SA.p[i]).append("\n");
        System.out.println(sb);
    }
}
