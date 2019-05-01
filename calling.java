import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;


public class calling extends Thread {
	
	private String key;
	private List<String> val;
	private Map<String, List<String>> mm;
	private LinkedBlockingQueue<String> lbq;
	private static int num=0;
	private Queue<String> reply;
	private long tt;
	private int cc=0;
	
	
	
	public calling(String key, List<String> val,LinkedBlockingQueue<String> lbq)
	{
	this.reply=new LinkedList<String>();	
	this.key=key;
	this.val=val;
	this.lbq=lbq;
	}
	
	public calling() {
		
	}

	public void run()
	{
		
		try {
		for (String names : val) {
			Thread.sleep(100);
			long ms = System.nanoTime();
			
			lbq.put(names + " received intro message from " + key + " [" + ms + "]");
			Thread.sleep(5);
			reply.add((key + " received reply message from " + names + " [" + ms + "]"));
			}
		while(!reply.isEmpty())
		{
			String ab=reply.poll();
			lbq.put(ab);			
		}
		
		checkProcess(key);
		
		
		}
		 catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
	
	public void checkProcess(String name) throws InterruptedException
	{
		tt = System.currentTimeMillis();
		while (System.currentTimeMillis() < tt + 1000){
			Thread.sleep(100);
		}
		System.out.println("Process " + name + " has received no calls for 1 seconds, ending...");
	}
	
}

	
	
