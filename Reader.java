import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.io.IOException;


public class Reader  {
		
		
		private static  Map<String, List<String>> storemp = new HashMap<String, List<String>>();
		
		
		public Map<String, List<String>> dispData () throws Exception
		{
		
				FileReader fr = new FileReader("calls.txt");
				int chk;
				//String get="";
			    /*while ((chk=fr.read()) != -1) {
			      System.out.print((char) chk);
			      String get=Integer.toString(chk);
			      String parts[] = get.substring(get.indexOf("{") + 1, get.indexOf("}")).split("\\,", 2);*/
			      
			      BufferedReader in = new BufferedReader(new FileReader("calls.txt"));
					String line = "";
					while ((line = in.readLine()) != null) {
						String parts[] = line.substring(line.indexOf("{") + 1, line.indexOf("}")).split("\\,", 2);
						storemp.put(parts[0],
								Arrays.asList(parts[1].substring(parts[1].indexOf("[") + 1, parts[1].indexOf("]")).split("\\,")));
					}
					in.close();
					
				System.out.println("** Calls to be made **");
				for (String key : storemp.keySet()) {
				System.out.println(key + ": " + storemp.get(key));
				}
				System.out.println();
		return storemp;	
		}}
			
			
			
			
			
			
			
		
				


	


