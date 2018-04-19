package assignment4.util;

import burlap.oomdp.core.values.DoubleArrayValue;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class AnalysisAggregator {
	private static List<Integer> numIterations = new ArrayList<Integer>();
	private static List<Integer> stepsToFinishValueIteration = new ArrayList<Integer>();
	private static List<Integer> stepsToFinishPolicyIteration = new ArrayList<Integer>();
	private static List<Integer> stepsToFinishQLearning = new ArrayList<Integer>();
	private static List<Integer> stepsToFinishSarsa = new ArrayList<Integer>();
	
	private static List<Integer> millisecondsToFinishValueIteration = new ArrayList<Integer>();
	private static List<Integer> millisecondsToFinishPolicyIteration = new ArrayList<Integer>();
	private static List<Integer> millisecondsToFinishQLearning = new ArrayList<Integer>();
	private static List<Integer> millisecondsToFinishSarsa = new ArrayList<Integer>();

	private static List<Double> rewardsForValueIteration = new ArrayList<Double>();
	private static List<Double> rewardsForPolicyIteration = new ArrayList<Double>();
	private static List<Double> rewardsForQLearning = new ArrayList<Double>();
	private static List<Double> rewardsForSarsa = new ArrayList<Double>();
	
	public static void addNumberOfIterations(Integer numIterations1){
		numIterations.add(numIterations1);
	}
	public static void addStepsToFinishValueIteration(Integer stepsToFinishValueIteration1){
		stepsToFinishValueIteration.add(stepsToFinishValueIteration1);
	}
	public static void addStepsToFinishPolicyIteration(Integer stepsToFinishPolicyIteration1){
		stepsToFinishPolicyIteration.add(stepsToFinishPolicyIteration1);
	}
	public static void addStepsToFinishQLearning(Integer stepsToFinishQLearning1){
		stepsToFinishQLearning.add(stepsToFinishQLearning1);
	}
	public static void addStepsToFinishSarsa(Integer stepsToFinishSarsa1){
		stepsToFinishSarsa.add(stepsToFinishSarsa1);
	}
	public static void printValueIterationResults(){
		System.out.print("Value Iteration,");	
		printList(stepsToFinishValueIteration);
	}
	public static void printPolicyIterationResults(){
		System.out.print("Policy Iteration,");	
		printList(stepsToFinishPolicyIteration);
	}
	public static void printQLearningResults(){
		System.out.print("Q Learning,");	
		printList(stepsToFinishQLearning);
	}
	public static void printSarsaResults(){
		System.out.print("SARSA,");
		printList(stepsToFinishSarsa);
	}
	

	public static void addMillisecondsToFinishValueIteration(Integer millisecondsToFinishValueIteration1){
		millisecondsToFinishValueIteration.add(millisecondsToFinishValueIteration1);
	}
	public static void addMillisecondsToFinishPolicyIteration(Integer millisecondsToFinishPolicyIteration1){
		millisecondsToFinishPolicyIteration.add(millisecondsToFinishPolicyIteration1);
	}
	public static void addMillisecondsToFinishQLearning(Integer millisecondsToFinishQLearning1){
		millisecondsToFinishQLearning.add(millisecondsToFinishQLearning1);
	}
	public static void addMillisecondsToFinishSarsa(Integer millisecondsToFinishSarsa1){
		millisecondsToFinishSarsa.add(millisecondsToFinishSarsa1);
	}
	public static void addValueIterationReward(double reward) {
		rewardsForValueIteration.add(reward);
	}
	public static void addPolicyIterationReward(double reward) {
		rewardsForPolicyIteration.add(reward);
	}
	public static void addQLearningReward(double reward) {
		rewardsForQLearning.add(reward);
	}
	public static void addSarsaReward(double reward) {
		rewardsForSarsa.add(reward);
	}
	public static void printValueIterationTimeResults(){
		System.out.print("Value Iteration,");	
		printList(millisecondsToFinishValueIteration);
	}
	public static void printPolicyIterationTimeResults(){
		System.out.print("Policy Iteration,");
		printList(millisecondsToFinishPolicyIteration);
	}

	public static void printQLearningTimeResults(){
		System.out.print("Q Learning,");	
		printList(millisecondsToFinishQLearning);
	}

	public static void printSarsaTimeResults(){
		System.out.print("Sarsa,");
		printList(millisecondsToFinishSarsa);
	}

	public static void printValueIterationRewards(){
		System.out.print("Value Iteration Rewards,");
		printDoubleList(rewardsForValueIteration);
	}

	public static void printPolicyIterationRewards(){
		System.out.print("Policy Iteration Rewards,");
		printDoubleList(rewardsForPolicyIteration);
	}

	public static void printQLearningRewards(){
		System.out.print("Q Learning Rewards,");
		printDoubleList(rewardsForQLearning);
	}

	public static void printSarsaRewards(){
		System.out.print("SARSA Rewards,");
		printDoubleList(rewardsForSarsa);
	}

	public static void printNumIterations(){
		System.out.print("Iterations,");	
		printList(numIterations);
	}
	private static void printList(List<Integer> valueList){
		int counter = 0;
		for(int value : valueList){
			System.out.print(String.valueOf(value));
			if(counter != valueList.size()-1){
				System.out.print(",");
			}
			counter++;
		}
		System.out.println();
	}
	private static void printDoubleList(List<Double> valueList){
		int counter = 0;
		for(double value : valueList){
			System.out.print(String.valueOf(value));
			if(counter != valueList.size()-1){
				System.out.print(",");
			}
			counter++;
		}
		System.out.println();
	}
	public static void printAggregateAnalysis(){
		System.out.println("//Aggregate Analysis//\n");
		System.out.println("The data below shows the number of steps/actions the agent required to reach \n"
				+ "the terminal state given the number of iterations the algorithm was run.");
		printNumIterations();
		printValueIterationResults();
		printPolicyIterationResults();
		printQLearningResults();
		printSarsaResults();
		System.out.println();
		System.out.println("The data below shows the number of milliseconds the algorithm required to generate \n"
				+ "the optimal policy given the number of iterations the algorithm was run.");
		printNumIterations();
		printValueIterationTimeResults();
		printPolicyIterationTimeResults();
		printQLearningTimeResults();
		printSarsaTimeResults();

		System.out.println("\nThe data below shows the total reward gained for \n"
				+ "the optimal policy given the number of iterations the algorithm was run.");
		printNumIterations();
		printValueIterationRewards();
		printPolicyIterationRewards();
		printQLearningRewards();
		printSarsaRewards();
	}
	public static void writeAnalysisToCsv() throws Exception{
		try {
			BufferedWriter br = new BufferedWriter(new FileWriter("rewards.csv"));
			StringBuilder sb = getRewardStringBuilder();
			br.write(sb.toString());
			br.close();
			br = new BufferedWriter(new FileWriter("times.csv"));
			sb = getTimeResultsStringBuilder();
			br.write(sb.toString());
			br.close();
			br = new BufferedWriter(new FileWriter("steps.csv"));
			sb = getStepResultsStringBuilder();
			br.write(sb.toString());
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Analysis written to CSV files");

	}

	private static StringBuilder getRewardStringBuilder() {
		StringBuilder sb = new StringBuilder();
		sb.append("Number Of Iterations, Value Iteration, Policy Iteration, Q-Learning, SARSA\n");
		for (int i=0; i < numIterations.size(); i++) {
			sb.append(Integer.toString(numIterations.get(i)) + ",");
			sb.append(rewardsForValueIteration.get(i) + ",");
			sb.append(rewardsForPolicyIteration.get(i) + ",");
			sb.append(rewardsForQLearning.get(i) + ",");
			sb.append(rewardsForSarsa.get(i) + ",");
			sb.append("\n");
		}
		return sb;
	}

	private static StringBuilder getTimeResultsStringBuilder() {
		StringBuilder sb = new StringBuilder();
		sb.append("Number Of Iterations, Value Iteration, Policy Iteration, Q-Learning, SARSA\n");
		for (int i=0; i < numIterations.size(); i++) {
			sb.append(Integer.toString(numIterations.get(i)) + ",");
			sb.append(millisecondsToFinishValueIteration.get(i) + ",");
			sb.append(millisecondsToFinishPolicyIteration.get(i) + ",");
			sb.append(millisecondsToFinishQLearning.get(i) + ",");
			sb.append(millisecondsToFinishSarsa.get(i) + ",");
			sb.append("\n");
		}
		return sb;
	}

	private static StringBuilder getStepResultsStringBuilder() {
		StringBuilder sb = new StringBuilder();
		sb.append("Number Of Iterations, Value Iteration, Policy Iteration, Q-Learning, SARSA\n");
		for (int i=0; i < numIterations.size(); i++) {
			sb.append(Integer.toString(numIterations.get(i)) + ",");
			sb.append(stepsToFinishValueIteration.get(i) + ",");
			sb.append(stepsToFinishPolicyIteration.get(i) + ",");
			sb.append(stepsToFinishQLearning.get(i) + ",");
			sb.append(stepsToFinishSarsa.get(i) + ",");
			sb.append("\n");
		}
		return sb;
	}
}
