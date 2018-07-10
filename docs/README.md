# Machine Learning - Assignment 4
## Reinforcement Learning

The goal of this assignment was to explore two simple Markov Decision Processes and later perform reinforcement learning on the same problems. For this, I picked the simple grid world setting since it’s easy to observe how the values change over time and is good for explicitly stepping through the algorithm. To contrast the two, the first problem was chosen as one with small number of states and the second with larger number of states.

The problem with small number of states can be viewed as a small search problem where one path exists that is shorter than the other.
- It is a 5x5 grid with the agent’s initial state as (0,0) and the goal as (4,4) with a reward of 100.
- The black squares are obstacles that the agent cannot pass through and the white blocks are traversable.
- Every step has a penalty of -1.
> The interesting part to observe in this MDP would be to see if the policy figures that out or not.

![Small Grid World]({{ "/assets/images/smallgridworld.jpeg" | absolute_url }})

The problem with large number of states is like the hallway problem. It can be viewed as having four rooms and four hallways at the intersection of any two rooms.
- The grid is a 11x11 grid with the agent at (0,0) initially and the target at (10,10).
- The walls are through the 5th row and 5th column, with one block acting as a hallway on each wall.
> The problem is constructed in such a way that one hallway may be slightly closer to the initial state of the agent than the other nearby hallway. This can give rise to varied policies depending on the reinforcement algorithm used.

![Large Grid World]({{ "/assets/images/largegridworld.jpeg" | absolute_url }})


### Easy MDP

#### Value and Policy Iteration

The first experiment was to perform value iteration on the small grid, and it converged within 30 iterations to the optimal policy.

![Optimal Policy]({{ "/assets/images/easymdp/optimalpolicy.jpeg" | absolute_url }})

When policy iteration was run on the small grid, it gave the exact same results as value iteration. *Hence, value iteration and policy iteration converge to the same answer.* Policy iteration also converges within 30 iterations.

![Value Iteration]({{ "/assets/images/easymdp/valueiter.jpeg" | absolute_url }})
![Policy Iteration]({{ "/assets/images/easymdp/policyiter.jpeg" | absolute_url }})

Though the initial reward is slightly lower in case of value iteration, it is also more stable as episodes increase. Both algorithms require similar number of iterations to converge to the optimal policy but value iteration takes much lesser time to generate the optimal policy.

![Times]({{ "/assets/images/easymdp/time.jpeg" | absolute_url }})


#### Reinforcement learning

The next step was to try out model-free reinforcement learning algorithms on the same MDP and see how it performs as compared to the algorithms used before that learns from a model. It would definitely take more iterations than value or policy iteration for it to converge as it has to learn the model. The two reinforcement learning algorithms used were **Q-Learning** and **SARSA**.

The first step in applying reinforcement learning is to tweak the parameters so the agent can learn. Since the goal is far away and there are no intermediate rewards, not discounting the final reward by too much is important. The discount rate 𝛾 at 0.99 gave good results in general.

The value of the *learning rate 𝛼* and *epsilon-greedy algorithm’s randomness rate 𝜖* are what changes the outcomes of the algorithm i.e. the policy the most. Fine-tuning these values gave rise to varying results.

The value of epsilon decides the fraction of random actions taken by the agent as opposed to picking the best one. The value of epsilon is initially fixed between 0.4 and 0.7 to have a half-and-half random nature, and then is reduced by 0.99 every episode. This ensures that as the algorithm trains and learns the model, it reduces the rate of random actions it takes.

The graph for Q-Learning convergence is shown and we see that Q-Learning converges at around 50-60 iterations.

![Times]({{ "/assets/images/easymdp/qlearning.jpeg" | absolute_url }})
