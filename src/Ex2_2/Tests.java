package Ex2_2;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.util.concurrent.*;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

class Tests {
    Callable<String> callable = ()-> {
        StringBuilder sb = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
      //  sleep(1000);
        return sb.reverse().toString();
    };

    Callable<Integer> callable2 = () -> {
        int sum = 0;
        for (int i = 1; i <= 10; i++) {
            sum += i;
        }
        return sum;            };

    public static final Logger logger = LoggerFactory.getLogger(Tests.class);
    @Test
    public void partialTest(){
        CustomExecutor customExecutor = new CustomExecutor();
        Task task = Task.createTask(()->{
            int sum = 0;
            for (int i = 1; i <= 10; i++) {
                sum += i;
            }
            return sum;
        }, TaskType.COMPUTATIONAL);
        Future sumTask = customExecutor.submit(task);
        final int sum;
        try {
            sum = (int) sumTask.get(1, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuntimeException(e);
        }
        logger.info(()-> "Sum of 1 through 10 = " + sum);
        Callable<Double> callable1 = ()-> {
            return 1000 * Math.pow(1.02, 5);
        };

        // var is used to infer the declared type automatically
        Future priceTask = customExecutor.submit(()-> {
            return 1000 * Math.pow(1.02, 5);
        }, TaskType.COMPUTATIONAL);
        Future reverseTask = customExecutor.submit(callable, TaskType.IO);
        final Double totalPrice;
        final String reversed;
        try {
            totalPrice = (Double) priceTask.get();
            reversed = (String) reverseTask.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        logger.info(()-> "Reversed String = " + reversed);
        logger.info(()->String.valueOf("Total Price = " + totalPrice));
        logger.info(()-> "Current maximum priority = " +
                customExecutor.getCurrentMax());
        customExecutor.gracefullyTerminate();

    }

    @Test
    void TestTaskConstructors(){

        Task<String> task1 = Task.createTask(callable);
        Task<String> task2 = Task.createTask(callable2,TaskType.COMPUTATIONAL);

        assertEquals(TaskType.OTHER,task1.getType());
        assertEquals(1,task2.getType().getPriorityValue());
    }

    @Test
    void TestTaskCall() throws Exception {

        Task<String> task1 = Task.createTask(callable);
        assertEquals("ZYXWVUTSRQPONMLKJIHGFEDCBA", task1.call());
    }

    @Test
    void TestTaskCompare(){

        Task<String> task1 = Task.createTask(callable);
        Task<String> task2 = Task.createTask(callable2,TaskType.IO);
        Task<String> task3 = Task.createTask(callable2,TaskType.COMPUTATIONAL);
        Task<String> task4 = Task.createTask(callable2,TaskType.OTHER);

        assertEquals(0,task1.compareTo(task4));
        assertEquals(1,task3.compareTo(task1));
        assertEquals(-1,task2.compareTo(task3));

    }

    //CustomExecutor Tests:
    @Test
    void customExecutor_submit(){
        Callable<String> callable3 = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000);
                return "Hello World";
            }
        };

        Task<String> task1 = Task.createTask(callable2,TaskType.IO);
        CustomExecutor<String> customExecutor = new CustomExecutor<>();
        assertNotNull(customExecutor.submit(task1));
        assertNotNull(customExecutor.submit(callable3));
        assertNotNull(customExecutor.submit(callable3,TaskType.COMPUTATIONAL));
        assertNull(customExecutor.submit((Task<String>) null));

    }

    @Test
    void TestGetMax() {
        Task task1 = Task.createTask(() -> {
            sleep(1000);
            return 1;
        }, TaskType.COMPUTATIONAL);
        Task task2 = Task.createTask(() -> {
            sleep(1000);
            return 1;
        }, TaskType.IO);
        Task<Integer> task3 = Task.createTask(() -> {
            Thread.sleep(1000);
            return 1;
        }, TaskType.OTHER);
        CustomExecutor<Integer> customExecutor = new CustomExecutor<>();
        for(int i = 0; i< 18;i++){
            customExecutor.submit(task3);
        }

        assertEquals(3, customExecutor.getCurrentMax());
        customExecutor.submit(task3);
        customExecutor.submit(task2);
        assertEquals(2, customExecutor.getCurrentMax());
        customExecutor.submit(task3);
        customExecutor.submit(task2);
        customExecutor.submit(task1);
        assertEquals(1, customExecutor.getCurrentMax());
    }

    @Test
    void TestTasksAnswer(){
        Task<Integer> task1 = Task.createTask(() -> {
            Thread.sleep(1000);
            return 1;
        }, TaskType.COMPUTATIONAL);
        Task<Integer> task2 = Task.createTask(() -> {
            Thread.sleep(1000);
            return 1;
        }, TaskType.IO);
        Task<Integer> task3 = Task.createTask(() -> {
            Thread.sleep(1000);
            return 1;
        }, TaskType.OTHER);
        CustomExecutor<Integer> customExecutor = new CustomExecutor<>();
        Future<Integer> future3 = customExecutor.submit(task3);
        Future<Integer> future2 = customExecutor.submit(task2);
        Future<Integer> future1 = customExecutor.submit(task1);
        int answer = 0;
        try {
            answer = future1.get() + future2.get() + future3.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        assertEquals(3, answer);
    }

    @Test
    void TestGraceFullyTerminate(){
        Task<Integer> task1 = Task.createTask(() -> {
            Thread.sleep(1000);
            return 1;
        }, TaskType.COMPUTATIONAL);
        CustomExecutor<Integer> customExecutor = new CustomExecutor<>();
        for(int i = 0; i< 50; i++){
            customExecutor.submit(task1);
        }
        customExecutor.gracefullyTerminate();
        customExecutor.submit(task1);
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertEquals(50,customExecutor.getCompletedTaskCount());
        assertEquals(true,customExecutor.isTerminated());
    }
}

