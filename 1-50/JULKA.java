// Created by akashbhalotia
import java.io.*;
import java.math.BigInteger;
class JULKA
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int i;

        BigInteger two=new BigInteger("2");

        StringBuilder sb=new StringBuilder();

        for(i=1;i<=10;i++)
        {
            BigInteger total=new BigInteger(br.readLine().trim());
            BigInteger diff=new BigInteger(br.readLine().trim());

            total=total.add(diff);
            total=total.divide(two);
            diff=total.subtract(diff);

            sb.append(total).append("\n");
            sb.append(diff).append("\n");
        }
        System.out.println(sb);
    }
}
