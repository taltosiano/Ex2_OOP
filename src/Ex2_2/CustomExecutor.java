package Ex2_2;

import java.util.concurrent.*;

public class CustomExecutor<V> extends ThreadPoolExecutor {
    private boolean isActive;
    private int[] MaxPriorityArray = {-1, 0, 0, 0};
    private final int MaxPriority = 0;
    public static int numOfCores = Runtime.getRuntime().availableProcessors();

    public CustomExecutor() {
        super(numOfCores/2,
                numOfCores - 1, 300, TimeUnit.MILLISECONDS,
                new PriorityBlockingQueue<>(100, (task1, task2) -> ((MyFuture)task1).compareTo((MyFuture)task2)));
        this.isActive = true;
    }

    public boolean isActive() {
        return isActive;
    }

    //    public void submit(Callable task, Ex2_2.TaskType type) {
//        Ex2_2.Task currTask = new Ex2_2.Task();
//        currTask.createTask(task, type);
//        tasks.add(currTask);
//    }
//
//    public void submit(Callable task) {
//        Ex2_2.Task currTask = new Ex2_2.Task();
//        currTask.createTask(task);
//        tasks.add(currTask);
//    }

//    public Future<V> submit(Ex2_2.Task<V> task) {
//        FutureTask futureTask = new FutureTask(task);
//        tasks.add(futureTask);
//        return futureTask;
//    }
//
//    public Future<V> submit(Callable<V> callable, Ex2_2.TaskType type) {
//        Ex2_2.Task<V> task = Ex2_2.Task.createTask(callable, type);
//        return submit(task);
//    }

    public Future<V> submit (Task<V> t) {
        if (t != null && isActive) {
            MaxPriorityArray[t.getType().getPriorityValue()]++;
            MyFuture<V> myFut = new MyFuture<>(t);
            super.execute(myFut);
            return myFut;
        }
        return null;
    }

    public Future<V> submit (Callable<V> t , TaskType taskType)
    {
        if (t != null && isActive) {
            Task<V> task = Task.createTask(t,taskType);
            return submit(task);
        }
        return null;
    }

    public Future<V> submit (Callable t) {
        if (t != null && isActive) {
            Task task = Task.createTask(t);
            return submit(task);}
        return null;
    }

    public int getCurrentMax() {
        boolean findAns = false;
        int ans = 3;
        for (int i = 3; i >= 1; i--) {
            if(MaxPriorityArray[i] > 0) {
                ans = i;
                findAns = true;
            }
        }
        if (findAns) return ans;
        return MaxPriority;
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
//        boolean exist=false;
//        for(int i=0; i < thePriority.length && !exist; i++){
//            if(thePriority[i]>0){
//                thePriority[i]= thePriority[i]-1;
//                exist =true;
//            }
//        }
        if(r != null)  MaxPriorityArray[((MyFuture) r).getPriority()]--;

    }
}