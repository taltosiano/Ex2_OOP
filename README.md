# Ex2_OOP

## Created By 
|Tal Tosiano |  208846600 | 
| ------------ | ------------ | 
| Tair Mazriv | 209188382 | 

### you can clone our repositorty here :point_down: :slightly_smiling_face:	:point_down: 
```
https://github.com/taltosiano/Ex2_OOP.git
```

### Our project in divided for two parts. In the first part we had to create several files with a random number of lines, and then count the total number of lines in three ways: 
1. In a normal way without special use.
2. Count using threads
3. Count using a threadPool

## classes

### Ex2_1
The main class of this part. It's contain 4 function - the first is creats a files, And the other three functions calculate the total number of lines of all the files in other forms as explained above.

### NumOfLinesThreads
A class that inherits from the Thread class has a constructor that accepts the name of the file and a run function that calculates the number of lines of this file.

### NumOfLinesThreadPool
A helper class that implements the Callable interface, returns a method
call calculates the number of lines of one file.

## UML diagram Part I

<img width="522" alt="image" src="https://user-images.githubusercontent.com/94299489/211607485-bc996dc5-fc4f-445d-ba49-f7c9f5de91f9.png">


## The results of this Part
We have tested the 4 functions with differents parameters for "num of files" & "seed" & "bound", then we saw the difference between the results.
In general, it can be seen that as we increase the amount of data and files, the difference between the efficiency of the function without the use of threads and the function with will stand out. 

And it's not just that, the more we increase activity, the more it will be necessary to divide tasks and arrive at a faster solution. When little production of files is required, there is no need for threads, and on the contrary, this will slow down the solution because just producing them takes time. 

And when there is a demand for a lot of files, we will want this "waste" of time creating the threads because it will make the continuation more efficient.
In big required for most:
1. The first function will be extremely slow
2. The function with the threads will be faster
3. The function with the third pool will be extremely fast

result for the parameters - numOfFiles: 10,  seed: (int) Math.random(),  bound: 10
<img width="521" alt="image" src="https://user-images.githubusercontent.com/94299489/212277167-d8db49e7-00fa-47f2-8056-11c8a186e6b6.png">

result for the parameters - numOfFiles: 100,  seed: (int) Math.random(),  bound: 10
<img width="525" alt="image" src="https://user-images.githubusercontent.com/94299489/212277249-7f91879e-152b-44aa-8f2b-9f2ef547d04f.png">

result for the parameters - numOfFiles: 100,   seed: (int) Math.random(),  bound: 100
<img width="538" alt="image" src="https://user-images.githubusercontent.com/94299489/212277391-de5f16ef-f8d3-46cb-a4a0-2f9b498f7ad7.png">

result for the parameters - numOfFiles: 100,  seed: (int) Math.random(),  bound: 1000
<img width="552" alt="image" src="https://user-images.githubusercontent.com/94299489/212277470-519c683f-e775-4afa-8e9f-4c5c5a54c70e.png">

result for the parameters - numOfFiles: 1000,  seed: (int) Math.random(), bound: 1000
<img width="560" alt="image" src="https://user-images.githubusercontent.com/94299489/212277715-5e49d9f8-adeb-452c-afa2-dba884103cbf.png">

result for the parameters - numOfFiles: 1000,  seed: (int) Math.random(), bound: 100000
<img width="567" alt="image" src="https://user-images.githubusercontent.com/94299489/212277914-101a4a42-8304-4403-8830-c5a0809d3704.png">

result for the parameters - numOfFiles: 3000,  seed: bound: 10000
<img width="584" alt="image" src="https://user-images.githubusercontent.com/94299489/211554203-da821c3b-ad31-4bfb-b3b4-521ddd8c3178.png">

result for the parameters - numOfFiles: 10000,  seed: (int) Math.random(),  bound: 1000
![image](https://user-images.githubusercontent.com/121244682/211620273-d505ddd2-181d-43ca-87ba-e95db248263e.png)

result for the parameters - numOfFiles: 10000,  seed: (int) Math.random(),  bound: 10000
![image](https://user-images.githubusercontent.com/121244682/211611511-ee42b202-5cc7-446d-9055-dc336a49ab68.png)

result for the parameters - numOfFiles: 10000,  seed: (int) Math.random(),  bound: 100000
![image](https://user-images.githubusercontent.com/121244682/211616879-71b70caf-c6d1-4abb-b8ba-959a6cac9cc4.png)

### *************************************************************************************************************

### In the second part of the project we made a new type that provides an asynchronous task with priority and a type of ThreadPool that supports tasks priority.

## classes

### Task
The class represents an operation that can be run asynchronously and can return a value of some type - so we will define it as a type
Generic return. It is not necessary for the operation to succeed and in case of failure, an exception will be thrown.

### TaskType
The class is an enum that describes the task type (COMPUTATIONAL/IO/OTHER) and its priority based on the value
The number of the task type.

### CustomExecutor
The class represents a new ThreadPool type that supports a queue of priority tasks. Each task in the queue is of type Task. 
CustomExecutor will create a Task before putting it in the queue by passing <V<Callable and
enum of type TaskType.
CustomExecutor will execute the tasks according to their priority.

### MyFuture
The purpose of the department is to coordinate between 2 departments. The class extends the FutureTask class and implements the Comparable interface.
The class allow the Task objects to be used as elements in the priority queue which used by the thread pool.
The task objects are passed to the submit method of the CustomExecutor class.
                                                                                       
### Tests
As in every test department, here we checked the correctness of all the functions in the classes.

## UML diagram Part II
<img width="605" alt="image" src="https://user-images.githubusercontent.com/94299489/212065871-3c020296-2bb5-4fea-b119-81f7322d3456.png">


