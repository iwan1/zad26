package zad26;

import java.util.ArrayList;
import java.util.concurrent.*;

public class main {
	public static int ile = 0;
	public static Double pi = 0.0;

	public static void main(String[] args) {
		   
        ExecutorService executor = Executors.newFixedThreadPool(10);
       
        ArrayList<Future<Double>> taskList = new ArrayList<>();
 
        
        for(int i=0; i< 10000; i++){
 
            double x = ThreadLocalRandom.current().nextDouble(-1, 1);
            double y = ThreadLocalRandom.current().nextDouble(-1, 1);
            
            
            Callable<Double> callable = new Callable<Double>(){
 
                @Override
                public Double call() throws Exception {
                	
                    return x*x+y*y;
                }
            };
 

            Future<Double> futureTask = executor.submit(callable);
            
            taskList.add(futureTask);
        }
        
        
        
        for(Future<Double> future : taskList){
            try {
                
                if(future.get()<=1){
                	ile++;
                }
                
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        pi = (ile/10000.0)*4.0;
        System.out.println("Ilosc sytuacji gdy punkt miesci sie w kole: " + ile);
        System.out.println("Liczba PI: " + pi);
        executor.shutdown();
	}

}
