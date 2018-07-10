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

The first experiment was to perform value iteration on the small grid, and it converged within 30 iterations to the optimal policy.

![Optimal Policy]({{ "/assets/images/easymdp/optimalpolicy.jpeg" | absolute_url }})

When policy iteration was run on the small grid, it gave the exact same results as value iteration. Hence, value iteration and policy iteration converge to the same answer. Policy iteration also converges within 30 iterations.

![Value Iteration]({{ "/assets/images/easymdp/valueiter.jpeg" | absolute_url }})
![Policy Iteration]({{ "/assets/images/easymdp/policyiter.jpeg" | absolute_url }})

Though the initial reward is slightly lower in case of value iteration, it is also more stable as episodes increase. Both algorithms require similar number of iterations to converge to the optimal policy but value iteration takes much lesser time to generate the optimal policy.

![Times]({{ "/assets/images/easymdp/time.jpeg" | absolute_url }})
