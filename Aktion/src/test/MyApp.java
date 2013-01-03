package test;

public class MyApp {
    public static void main(String[] args) {
        System.out.println("Enter Text >");
        
        Action action;
//        Action action2;
  
        // create an event source - reads from stdin
        final EventSource eventSource = new EventSource();
 
        // create an observer
//        final ResponseHandler responseHandler = new ResponseHandler();
        final ForwardActionImpl iforward = new ForwardActionImpl();
        final BackwardActionImpl ibackwards = new BackwardActionImpl();
        
        action = new ForwardAction(iforward);
 
        // subscribe the observer to the event source
//        eventSource.addObserver(responseHandler);
        eventSource.addObserver(action);
 
        action = new BackwardAction(ibackwards);
        eventSource.addObserver(action);
        
        // starts the event thread
        Thread thread = new Thread(eventSource);
        thread.start();
    }
}