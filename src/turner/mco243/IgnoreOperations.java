package turner.mco243;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class IgnoreOperations {

	public static void main(String[] args){
		
		ExecutorService service = Executors.newFixedThreadPool(0);
		final AtomicInteger total = new AtomicInteger(0);
		final AtomicBoolean allowed = new AtomicBoolean(true);
		ActionListener listener = new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0){
				if(allowed.get() ){
					allowed.set(false);
					Runnable runnable = new Runnable(){
						public void run(){
						try{
							//LONG RUNNING OPERATION
							allowed.set(true);
						}
						catch(IOException e){
							e.printStackTrace();
						}
							finally {
								allowed.set(true);
							}
						}
					}
				}
			};
	
			service.execute(runnable); 
		}

		service.shutdown(); 
		service.awaitTermination(10, TimeUnit.SECONDS); 
		System.out.println(total);

	}
}
		}
	}
