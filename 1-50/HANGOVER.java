// Created by akashbhalotia
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
class HANGOVER
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int i;
        double total,sum,c;

        StringBuilder sb=new StringBuilder();

        while(true)
        {
            total=Double.parseDouble(br.readLine().trim());

            if(total==0.0)
                break;

            c=1;
            sum=0;

            while(true)
            {
                sum+=1/(++c);

                if(sum>=total)
                    break;
            }

            sb.append((int)(c-1)).append(" card(s)").append("\n");
        }
        System.out.println(sb);
    }
}
