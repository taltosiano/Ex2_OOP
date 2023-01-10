# Ex2_OOP

### Our project in divided for two parts. In the first part we had to create several files with a random number of lines, and then count the total number of lines in three ways: 
1. In a normal way without special use.
2. Count using threads
3. Count using a threadPool

In the second part 


## UML diagram Part I




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
<img width="529" alt="image" src="https://user-images.githubusercontent.com/94299489/211553019-4ae08019-bfc6-43b2-90ad-7fa11f825bbe.png">

result for the parameters - numOfFiles: 100,  seed: (int) Math.random(),  bound: 10
<img width="545" alt="image" src="https://user-images.githubusercontent.com/94299489/211552920-bdd470b4-c632-404c-9df6-9f84bfbd32ec.png">

result for the parameters - numOfFiles: 100,   seed: (int) Math.random(),  bound: 100
<img width="537" alt="image" src="https://user-images.githubusercontent.com/94299489/211553092-05298bec-15ba-477c-bc58-750a4f22168d.png">

result for the parameters - numOfFiles: 100,  seed: (int) Math.random(),  bound: 1000
<img width="546" alt="image" src="https://user-images.githubusercontent.com/94299489/211553328-0407d1e0-d147-4f03-a017-f50473ae2e10.png">

result for the parameters - numOfFiles: 1000,  seed: (int) Math.random(), bound: 100
<img width="546" alt="image" src="https://user-images.githubusercontent.com/94299489/211553456-adc25c78-9a0e-47a3-8e8b-df08364b5ab0.png">

result for the parameters - numOfFiles: 1000,  seed: (int) Math.random(), bound: 100000
<img width="571" alt="image" src="https://user-images.githubusercontent.com/94299489/211553694-66befa81-8f3f-4c12-8773-94429ae9630c.png">

result for the parameters - numOfFiles: 3000,  seed: bound: 10000
<img width="584" alt="image" src="https://user-images.githubusercontent.com/94299489/211554203-da821c3b-ad31-4bfb-b3b4-521ddd8c3178.png">

result for the parameters - numOfFiles: 10000,  seed: (int) Math.random(),  bound:

result for the parameters - numOfFiles: 10000,  seed: (int) Math.random(),  bound:





## UML diagram Part II

