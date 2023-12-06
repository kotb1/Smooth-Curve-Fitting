package GA;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ArrayList <Float> x = new <Float>ArrayList ();
		ArrayList <Float> y = new <Float>ArrayList ();
		ArrayList <Solution> best_Population= new ArrayList<Solution>();
		BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\bodyy\\OneDrive\\Desktop\\test.txt"));
		Path path = Paths.get("C:\\Users\\bodyy\\OneDrive\\Desktop\\result.txt");
		FileOutputStream writer = new FileOutputStream("C:\\Users\\bodyy\\OneDrive\\Desktop\\result.txt");
		writer.write(("").getBytes());
		writer.close();
		int TC = Integer.parseInt(reader.readLine());
		System.out.println("Number of TestCases: "+TC);
		Files.write(path, ("Number of TestCases: "+TC).getBytes(), StandardOpenOption.APPEND);
		Files.write(path, ("\n").getBytes(), StandardOpenOption.APPEND);
		String line1 = reader.readLine();
		String[] input1=line1.split(" ");
		int NumberOfPoints=Integer.parseInt(input1[0]);
		System.out.println("Number of Pairs: "+NumberOfPoints);
		Files.write(path, ("Number of Pairs: "+NumberOfPoints).getBytes(), StandardOpenOption.APPEND);
		Files.write(path, ("\n").getBytes(), StandardOpenOption.APPEND);
		int Requested_Degree=Integer.parseInt(input1[1]);
		System.out.println("The Equation is of Degree: "+Requested_Degree);
		Files.write(path, ("The Equation is of Degree: "+Requested_Degree).getBytes(), StandardOpenOption.APPEND);
		Files.write(path, ("\n").getBytes(), StandardOpenOption.APPEND);
		String line2=null;
		while ((line2 = reader.readLine()) != null) 
		{
			String[] input2=line2.split(" ");
			x.add(Float.parseFloat(input2[0]));
			y.add(Float.parseFloat(input2[1]));
		}
		System.out.println("Pairs Given:");
		Files.write(path, ("Pairs Given:").getBytes(), StandardOpenOption.APPEND);
		Files.write(path, ("\n").getBytes(), StandardOpenOption.APPEND);
		for(int i=0;i<NumberOfPoints;i++)
		{
			System.out.println("("+x.get(i)+","+y.get(i)+")");
			Files.write(path, ("("+x.get(i)+","+y.get(i)+")").getBytes(), StandardOpenOption.APPEND);
			Files.write(path, ("\n").getBytes(), StandardOpenOption.APPEND);
		}
		Functions f= new Functions();
		ArrayList <Solution> Population= new ArrayList<Solution>();
		Population = f.Initialize(Requested_Degree);
		for(int m=0;m<TC;m++) {
		System.out.println("Intialized Population:");
		Files.write(path, ("Intialized Population:").getBytes(), StandardOpenOption.APPEND);
		Files.write(path, ("\n").getBytes(), StandardOpenOption.APPEND);
		for(int i=0;i<Population.size();i++) 
		{
			for(int j=0;j<Requested_Degree+1;j++) 
			{
				System.out.print(Population.get(i).array.get(j)+"	");
				Files.write(path, (Population.get(i).array.get(j)+"	").getBytes(), StandardOpenOption.APPEND);
			}
			System.out.println();
			Files.write(path, ("\n").getBytes(), StandardOpenOption.APPEND);
		}
		Population = f.Fitness_Evaluation2(Population, Requested_Degree, x, y, NumberOfPoints);
		best_Population.add(f.getbest(Population));
		System.out.println("Intialized Population After evaluating fitness:");
		Files.write(path, ("Intialized Population After evaluating fitness:").getBytes(), StandardOpenOption.APPEND);
		Files.write(path, ("\n").getBytes(), StandardOpenOption.APPEND);
		for(int i=0;i<Population.size();i++) 
		{
			for(int j=0;j<Requested_Degree+1;j++) 
			{
				System.out.print(Population.get(i).array.get(j)+"	");
				Files.write(path, (Population.get(i).array.get(j)+"	").getBytes(), StandardOpenOption.APPEND);
			}
			System.out.println("Fitness = "+Population.get(i).fitness);
			Files.write(path, ("Fitness = "+Population.get(i).fitness).getBytes(), StandardOpenOption.APPEND);
			Files.write(path, ("\n").getBytes(), StandardOpenOption.APPEND);
		}
		ArrayList<Solution> Selected_Population= new ArrayList<Solution>();
		Selected_Population = f.Tournement_Selection(Population);
		System.out.println("Selected Solutions For crossover:");
		Files.write(path, ("Selected Solutions For crossover:").getBytes(), StandardOpenOption.APPEND);
		Files.write(path, ("\n").getBytes(), StandardOpenOption.APPEND);
		for(int i=0;i<Selected_Population.size();i++) 
		{
			for(int j=0;j<Requested_Degree+1;j++) 
			{
				System.out.print(Selected_Population.get(i).array.get(j)+"	");
				Files.write(path, (Selected_Population.get(i).array.get(j)+"	").getBytes(), StandardOpenOption.APPEND);
			}
			System.out.println("Fitness = "+Selected_Population.get(i).fitness);
			Files.write(path, ("Fitness = "+Selected_Population.get(i).fitness).getBytes(), StandardOpenOption.APPEND);
			Files.write(path, ("\n").getBytes(), StandardOpenOption.APPEND);
		}
		ArrayList<Solution> OffSpring= f.two_way_crossver(Requested_Degree, Selected_Population);
		System.out.println("Offspring After crossover:");
		Files.write(path, ("Offspring After crossover:").getBytes(), StandardOpenOption.APPEND);
		Files.write(path, ("\n").getBytes(), StandardOpenOption.APPEND);
		for(int i=0;i<OffSpring.size();i++) 
		{
			for(int j=0;j<Requested_Degree+1;j++) 
			{
				System.out.print(OffSpring.get(i).array.get(j)+"	");
				Files.write(path, (OffSpring.get(i).array.get(j)+"	").getBytes(), StandardOpenOption.APPEND);
			}
			//System.out.println("Fitness = "+OffSpring.get(i).fitness);
			System.out.println();
			Files.write(path, ("\n").getBytes(), StandardOpenOption.APPEND);
		}
		OffSpring = f.non_uniform_Mutation(OffSpring, m, TC);
		for(int i=0;i<OffSpring.size();i++) 
		{
			Population.add(OffSpring.get(i));
		}
	}
		//Population = f.Fitness_Evaluation(Population, Requested_Degree, x, y, NumberOfPoints);
		Population = f.Fitness_Evaluation2(Population, Requested_Degree, x, y, NumberOfPoints);
		best_Population.add(f.getbest(Population));
		Solution best = f.getbest(best_Population);
		System.out.println("The Best Solution is:");
		Files.write(path, ("The Best Solution is:").getBytes(), StandardOpenOption.APPEND);
		Files.write(path, ("\n").getBytes(), StandardOpenOption.APPEND);
		for(int i=0;i<best.array.size();i++) 
		{
			System.out.print(best.array.get(i)+"	");
			Files.write(path, (best.array.get(i)+"	").getBytes(), StandardOpenOption.APPEND);
		}
		System.out.print("Fitness = "+best.fitness);
		Files.write(path, ("Fitness = "+best.fitness).getBytes(), StandardOpenOption.APPEND);
	}

}












/*Population = f.Fitness_Evaluation(Population, Requested_Degree, x, y, NumberOfPoints);
System.out.println("Intialized Population After evaluating fitness:");
Files.write(path, ("Intialized Population After evaluating fitness:").getBytes(), StandardOpenOption.APPEND);
Files.write(path, ("\n").getBytes(), StandardOpenOption.APPEND);
for(int i=0;i<Population.size();i++) 
{
	for(int j=0;j<Requested_Degree+1;j++) 
	{
		System.out.print(Population.get(i).array.get(j)+"	");
		Files.write(path, (Population.get(i).array.get(j)+"	").getBytes(), StandardOpenOption.APPEND);
	}
	System.out.println("Fitness = "+Population.get(i).fitness);
	Files.write(path, ("Fitness = "+Population.get(i).fitness).getBytes(), StandardOpenOption.APPEND);
	Files.write(path, ("\n").getBytes(), StandardOpenOption.APPEND);
}*/
