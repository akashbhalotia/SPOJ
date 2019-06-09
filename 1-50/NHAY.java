// Created by akashbhalotia
// Rabin-Karp/Hashing
// Brute also works
// RTE issues
import java.io.*;
import java.util.*;
class A
{
    private static final int p=31,m=(int)(1e9)+9;
    private static long pow[];
    private static void compute_pow()
    {
        pow[0]=1;
        for(int i=1;i<10000000;i++)
            pow[i]=(pow[i-1]*p)%m;
    }
    private static long compute_hash(char s[], int till)
    {
        long hash=0;
        for(int i=0;i<till;i++)
            hash=(hash+(s[i]-'a'+1)*pow[i])%m;
        return hash;
    }
    private static long pow2(long a, long b)
    {
        a%=m;
        b%=m-1;

        long res=1;

        while(b>0)
        {
            if((b&1)==1)
                res=(res*a)%m;

            a=(a*a)%m;
            b>>=1;
        }

        return res%m;
    }
    public static void main(String[] args) throws Exception
    {
        BufferedReader r=new BufferedReader(new InputStreamReader(System.in));

        int i, N, len, len2;
        long hash, hash2, MMI = pow2(p, m - 2);
        String s;
        StringBuilder sb = new StringBuilder();
        pow = new long[(int) (1e7)];
        compute_pow();


        while ((s = r.readLine()) != null)
        {
            N = Integer.parseInt(s.trim());
            char subs[] = r.readLine().trim().toCharArray();
            char str[] = r.readLine().trim().toCharArray();

            len = subs.length;
            len2 = str.length;

            if (len2 >= len)
            {
                hash = compute_hash(subs, len);
                hash2 = compute_hash(str, len);

                if (hash == hash2)
                    sb.append(0).append("\n");
                for (i = 1; i < len2 - len + 1; i++)
                {
                    hash2 = (hash2 + m - (str[i - 1] - 'a' + 1)) % m;
                    hash2 = (hash2 * MMI) % m;
                    hash2 = (hash2 + (str[i + len - 1] - 'a' + 1) * pow[len - 1]) % m;

                    if (hash == hash2)
                        sb.append(i).append("\n");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
