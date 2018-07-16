# Machine Learning - Assignment 4
## Markov Decision Processes

The goal of this assignment was to explore two simple Markov Decision Processes and later perform reinforcement learning on the same problems. For this, I picked the simple grid world setting since it’s easy to observe how the values change over time and is good for explicitly stepping through the algorithm. To contrast the two, the first problem was chosen as one with small number of states and the second with larger number of states.

The problem with small number of states can be viewed as a small search problem where one path exists that is shorter than the other.
- It is a 5x5 grid with the agent’s initial state as (0,0) and the goal as (4,4) with a reward of 100.
- The black squares are obstacles that the agent cannot pass through and the white blocks are traversable.
- Every step has a penalty of -1.

> The interesting part to observe in this MDP would be to see if the policy figures that out or not.

![Small Grid World]({{ "/assets/images/smallgridworld.jpeg" | absolute_url }}){:height="70%" width="70%"}

The problem with large number of states is like the hallway problem. It can be viewed as having four rooms and four hallways at the intersection of any two rooms.
- The grid is a 11x11 grid with the agent at (0,0) initially and the target at (10,10).
- The walls are through the 5th row and 5th column, with one block acting as a hallway on each wall.

> The problem is constructed in such a way that one hallway may be slightly closer to the initial state of the agent than the other nearby hallway. This can give rise to varied policies depending on the reinforcement algorithm used.

![Large Grid World]({{ "/assets/images/largegridworld.jpeg" | absolute_url }}){:height="70%" width="70%"}

## Easy MDP

### Value and Policy Iteration

The first experiment was to perform value iteration on the small grid, and it converged within 30 iterations to the optimal policy.

![Optimal Policy]({{ "/assets/images/easymdp/optimalpolicy.jpeg" | absolute_url }}){:height="60%" width="60%"}

When policy iteration was run on the small grid, it gave the exact same results as value iteration. *Hence, value iteration and policy iteration converge to the same answer.* Policy iteration also converges within 30 iterations.

![Value Iteration]({{ "/assets/images/easymdp/valueiter.jpeg" | absolute_url }})
![Policy Iteration]({{ "/assets/images/easymdp/policyiter.jpeg" | absolute_url }})

Though the initial reward is slightly lower in case of value iteration, it is also more stable as episodes increase. Both algorithms require similar number of iterations to converge to the optimal policy but value iteration takes much lesser time to generate the optimal policy.

![Times]({{ "/assets/images/easymdp/time.jpeg" | absolute_url }})


### Reinforcement learning

The next step was to try out model-free reinforcement learning algorithms on the same MDP and see how it performs as compared to the algorithms used before that learns from a model. It would definitely take more iterations than value or policy iteration for it to converge as it has to learn the model. The two reinforcement learning algorithms used were **Q-Learning** and **SARSA**.

+ The first step in applying RL is to tweak the parameters so the agent can learn.
    + Since the goal is far away and there are no intermediate rewards, not discounting the final reward by too much is important.
    + The discount rate 𝛾 at 0.99 gave good results in general.
+ The value of the **learning rate** &alpha; and **epsilon-greedy algorithm’s randomness rate** &epsilon; are what changes the outcomes of the algorithm i.e. the policy the most.
    + Fine-tuning these values gave rise to varying results.  
+ The value of epsilon decides the fraction of random actions taken by the agent as opposed to picking the best one.
    + The value of epsilon is initially fixed at say 0.5 to have random decision making, and is then reduced by 0.99 every episode.
    + This ensures that the model initially explores and doesn't get stuck, but as it learns, it should reduce these random actions eventually.
+ Next pick the learning that ensures that the algorithm learnt rewards slowly and did not converge too quickly.
    + A higher rate than that gave curves that were oscillating too much and did not converge.
    + You have to make sure it's not too slow.

#### Q-Learning

The graph for Q-Learning convergence is shown and we see that Q-Learning converges at around 50-60 iterations.

![Q-Learning]({{ "/assets/images/easymdp/qlearning.jpeg" | absolute_url }})

One such optimal policy obtained from Q-Learning after 100 iterations is shown. The algorithm produces slightly different results in the areas on the left, where the agent’s decision to go either up or right can change the optimal policy from thereon.

![Q-Learning Optimal Policy]({{ "/assets/images/easymdp/qlearning_policy.jpeg" | absolute_url }}){:height="60%" width="60%"}

Since MDPs worry about only the current state and the next state, the optimal policy depends on the randomness of the actions taken by the agent in the initial episodes. We see that it more or less corresponds to the optimal policy obtained from value and policy iteration (near the goal states in particular).

The small grid world problem is designed in such a way that there are two paths that may be taken, both nearly equal.

#### SARSA

In case of SARSA, there is an extra parameter &lambda;𝑠𝑎𝑟𝑠𝑎, which determines the extent to which future interactions are considered when updating the Q-value of each episode. The larger the value, the more these interactions are considered. When &lambda;𝑠𝑎𝑟𝑠𝑎 is not too high, the algorithm is much more stable. The convergence graph of SARSA is shown above. It can be seen that SARSA also converges are around 50-60, maybe slighter faster than Q-Learning did.

![SARSA]({{ "/assets/images/easymdp/sarsa.jpeg" | absolute_url }})


Value and policy iteration converge the fastest and more stable, owing to the model-based learning. Q-Learning and SARSA also converge pretty quickly because of the small number of states in this MDP. The number of steps to reach the terminal state is the opposite curve of what we see for rewards vs episodes, and it stabilizes to a very low value once it converges.

![Steps]({{ "/assets/images/easymdp/steps.jpeg" | absolute_url }})
![Rewards]({{ "/assets/images/easymdp/rewards.jpeg" | absolute_url }})

> One of the advantages of Q-Learning and SARSA is that the time required to generate the optimal policy is much smaller compared to value iteration and policy iteration.

Since in each episode, only the Q-values are updated for the two RL algorithms, it is just O(1). For policy and value iteration, there are ‘iterations’ in each episode and seems to increase linearly as O(n).

![Times]({{ "/assets/images/easymdp/times_comparison.jpeg" | absolute_url }})

## Hard MDP


This MDP has 74 states as compared to 18 states in the previous MDP. The grid is designed like a hall with four rooms, with hallways connecting any two rooms.

### Value and Policy Iteration

It can be observed that depending on the first move of the agent, the agent can move to either the room on the bottom right or the room on the top left and then proceed towards the goal. The optimal policy is slightly biased towards the hallway on the top left as it is closer to the agent’s location.

![Optimal Policy]({{ "/assets/images/hardmdp/value_policy.jpeg" | absolute_url }}){:height="60%" width="60%"}

To test that hypothesis, I tried value iteration by slightly moving the location of the first two hallways such that the top left hallway is further, and the bottom hallway is closer to the agent. We see that now the optimal policy is biased towards the bottom hallway.

![Biased Policy]({{ "/assets/images/hardmdp/policy_biased.jpeg" | absolute_url }}){:height="60%" width="60%"}

### Reinforcement learning

The major difference seen in this MDP compared to the previous one is that **_Q-Learning takes a lot longer to converge._**

+ This MDP takes at least 1500-2000 iterations to converge, owing to the large number of states it has to learn.
+ The algorithm converges to a total reward in the range of 70-80, which is also lower.
+ There was also more exploration required in this problem.

> It’s also possible to see an interesting crowding behavior occurring in the hallways where the optimal policy looks like its queueing around the hallway. The general direction of the optimal policy is aligned towards the goal.

![Q-Learning Policy]({{ "/assets/images/hardmdp/qlearning_policy.jpeg" | absolute_url }}){:height="60%" width="60%"}

The trend of the time taken to converge to the optimal policy is same as in the previous MDP. Q-Learning seems to be the fastest algorithm and Policy Iteration the slowest. Overall, the RL algorithms are much faster.

![Times]({{ "/assets/images/hardmdp/times.jpeg" | absolute_url }})

Overall the RL algorithms match up to the value and policy iteration rewards and converge eventually to those values as shown below.

![Steps]({{ "/assets/images/hardmdp/steps.jpeg" | absolute_url }})
![Rewards]({{ "/assets/images/hardmdp/rewards.jpeg" | absolute_url }})


### Here are some of my observations from the experiments:
+ Value Iteration and Policy Iteration converge to the same answer (given that they are allowed to run until they do).
+ For the grid world problems used here, both the algorithms take less than 50 iterations to converge.
+ However, they also take a long time to run for each iteration, and this increases linearly with the number of iterations.
+ Number of states does not affect things much for these two algorithms, except for the time taken to generate the policy. Policy iteration is the slowest.
+ Q-Learning and SARSA are much faster than Policy and Value Iteration, with Q-Learning being the fastest to generate the policy in each iteration.
+ As the number of states increase, it takes many more iterations to converge to the optimal policy for the RL algorithms.
+ When there are no intermediate rewards, &gamma; has to be high so that it does not get discouraged from pursuing the terminal goal.
+ The learning rate &alpha; and randomness rate &epsilon; values affect the exploration vs exploitation trade-off the most. Tweaking them leads to varied results.



#### References

1. [BURLAP](http://burlap.cs.brown.edu)
2. [juanjose49's repository](https://github.com/juanjose49/omscs-cs7641-machine-learning-assignment-4)
3. [danielcy715's repository](https://github.com/danielcy715/CS7641-Machine-Learning)
4. [POMDP tutorial](https://www.techfak.uni-bielefeld.de/~skopp/Lehre/STdKI_SS10/POMDP_tutorial.pdf)
