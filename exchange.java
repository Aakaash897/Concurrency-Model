import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class exchange {
	
	private final static LinkedBlockingQueue<String> lbq = new LinkedBlockingQueue<String>();
	private static  Map<String, List<String>> mainmp = new HashMap<String, List<String>>();
	private static long curTime;
	
	
	

	public static void main(String[] args) throws Exception {
		
		Reader rr=new Reader();
		mainmp=rr.dispData();
		
		curTime = System.currentTimeMillis();
		ExecutorService eservice = Executors.newFixedThreadPool(mainmp.size());
		for (String name : mainmp.keySet()) {
			calling t = new calling(name, mainmp.get(name), lbq);
			eservice.execute(t);
		}
		eservice.shutdown();
		//long nowTime=System.currentTimeMillis();
		while (!eservice.isTerminated() || System.currentTimeMillis() < curTime + 1500) {
			
		if (!lbq.isEmpty()) 
		{
			System.out.println(lbq.poll());
		}
		}
		System.out.println("Master has received no replies for 1.5 seconds, ending...");
		
		}
	}
