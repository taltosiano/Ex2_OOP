package Ex2_2;

import java.util.concurrent.*;

public class CustomExecutor<V> extends ThreadPoolExecutor {
    private boolean isActive;
    private int[] maxPriority = {0, 0, 0};
    public static int numOfCores = Runtime.getRuntime().availableProcessors();

    /**
     * default constructor that define the super ThreadPoolExecutor.
     *  corePoolSize - is the min num of action's threads.
     *  maximumPoolSize - is the max num of action's threads.
     *  PriorityBlockingQueue - queue of tasks that have not yet been accepted by the threads
     */
    public CustomExecutor() {
        super(numOfCores/2, numOfCores - 1, 300, TimeUnit.MILLISECONDS,
                new PriorityBlockingQueue<>(100, (task1, task2) -> ((MyFuture)task1).compareTo((MyFuture)task2)));
        this.isActive = true;
    }


    /**
     *  A method for submitting Task instances to a priority task queue the method receive task
     *  and saves the task as a to-do task.
     *  if there is a free thread without task, the task will be done immediately.
     * @param task - the specified take the method receive
     * @return the "recipe" of the task that should be executed - typed FutureTask.
     */
    public Future<V> submit (Task<V> task) {
        if (task != null && isActive) {
            maxPriority[task.getType().getPriorityValue() - 1]++;
            MyFuture<V> myFut = new MyFuture<>(task);
            super.execute(myFut);
            return myFut;
        }
        return null;
    }

    /**
     * A method whose purpose is to submit to the queue an operation that can be performed
     * asynchronously with the addition of task's type
     * @param c - the operation
     * @param taskType - the type of task - which priority the task have.
     * @return use the Submit method so return the same
     */
    public Future<V> submit (Callable<V> c , TaskType taskType) {
        if (c != null && isActive) {
            Task<V> task = Task.createTask(c,taskType);
            return submit(task);
        }
        return null;
    }

    /**
     * A method whose purpose is to submit to a queue an operation that can be performed
     * asynchronously without a TaskType as a parameter
     * @param c - the operation
     * @return use the Submit method so return the same
     */
    public Future<V> submit (Callable c) {
        if (c != null && isActive) {
            Task task = Task.createTask(c);
            return submit(task);}
        return null;
    }

    /**
     * Current Max method check the highest priority of a found task
     * Now in line with (1(O) in terms of runtime complexity and space complexity.
     * @return the highest priority of a found task
     */
    public int getCurrentMax() {

        for (int i = 0; i < 3; i++) {
            if (maxPriority[i] != 0)
                return i + 1;
        }
            return 0;
        }

    /**
     * The method shutdown the Custom executor
     * its stops the activity of the CustomExecutor as follows:
     * a. It is not possible to add additional tasks to the queue
     * b. Execution of all tasks remaining in the queue
     * c. Termination of all the tasks that are currently being executed in the collection of threads of the CustomExecutor
     */
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

    /**
     *  Method from ThreadPoolExecutor class is invoked before executing the given Runnable in the given thread.
     * @param t this is the thread that will run some task
     * @param r this is the task that will be executed
     */
    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        if(r != null)  maxPriority[((MyFuture)r).getPriority() - 1]--;
    }
}
