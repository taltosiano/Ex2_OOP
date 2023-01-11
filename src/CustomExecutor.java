import java.util.Comparator;
import java.util.concurrent.*;

import static java.lang.Runtime.getRuntime;

public class CustomExecutor<V> extends ThreadPoolExecutor {
    private boolean isActive;
    int [] thePriority;
    int minNumOfThreads = getRuntime().availableProcessors()/2;
    int maxNumOfThreads = getRuntime().availableProcessors() - 1;
    private final PriorityBlockingQueue<Runnable> tasks = new PriorityBlockingQueue<>(minNumOfThreads, (task1, task2) -> ((Task)task1).compareTo((Task) task2)) ;

    public CustomExecutor() {
//        super(getRuntime().availableProcessors()/2, getRuntime().availableProcessors() - 1, 300, TimeUnit.MILLISECONDS,
//                new PriorityBlockingQueue<>(getRuntime().availableProcessors()/2, (task1, task2) -> ((Task)task1).compareTo((Task) task2)));
        super(getRuntime().availableProcessors()/2, getRuntime().availableProcessors() - 1, 300, TimeUnit.MILLISECONDS,
                new PriorityBlockingQueue<>(getRuntime().availableProcessors() / 2, Comparator.comparing(runnable -> ((MyFuture) runnable))));
        this.isActive = true;
        this.thePriority = new int[10];
    }

//    public void submit(Callable task, TaskType type) {
//        Task currTask = new Task();
//        currTask.createTask(task, type);
//        tasks.add(currTask);
//    }
//
//    public void submit(Callable task) {
//        Task currTask = new Task();
//        currTask.createTask(task);
//        tasks.add(currTask);
//    }

//    public Future<V> submit(Task<V> task) {
//        FutureTask futureTask = new FutureTask(task);
//        tasks.add(futureTask);
//        return futureTask;
//    }
//
//    public Future<V> submit(Callable<V> callable, TaskType type) {
//        Task<V> task = Task.createTask(callable, type);
//        return submit(task);
//    }

    public Future<V> submit (Task t) {
        if (isActive) {
            thePriority[t.getType().getPriorityValue()]++;
            return super.submit(t);}
        return null;
    }

    public  Future<V> submit (Callable t , TaskType taskType)
    {
        if (t != null && isActive) {
            Callable task = Task.createTask(t,taskType);
            return submit(task);}
        return null;
    }

    public Future<V> submit (Callable t) {
        if (t != null && isActive) {
            Callable task = Task.createTask(t);
            return submit((Task)task);}
        return null;
    }

    //    public String getCurrentMax() {
//        return "Current maximum priority =" + max;
//    }
    public int getCurrentMax()
    {
        for (int i = 1; i < 10; i++) {
            if (thePriority[i] > 0)
                return i;
        }
        return 0;
    }

    public void gracefullyTerminate() {
        if(isActive) {
            isActive = false;
            super.shutdown();
            try {
                if(!super.awaitTermination(300L *(getQueue().size()) , TimeUnit.MILLISECONDS)){
                    super.awaitTermination(300L *(getQueue().size() + 4) , TimeUnit.MILLISECONDS);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.err.println("exception while determination");
            }
        }    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        boolean exist=false;
        for(int i=0; i < thePriority.length && !exist; i++){
            if(thePriority[i]>0){
                thePriority[i]= thePriority[i]-1;
                exist =true;
            }
        }
    }
}