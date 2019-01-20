// Created by akashbhalotia
import java.io.*;
import java.util.*;
class E
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        long N;

        N=Long.parseLong(br.readLine().trim());

        //check if it is a power of 2
        if((N&(N-1))==0)
            System.out.println("TAK");
        else
            System.out.println("NIE");
    }
}
