package GA;

import java.util.ArrayList;
import java.util.Random;

public class Functions 
{
	public ArrayList<Solution> Initialize(int NumberOfFloatingPoints) 
	{
		ArrayList<Solution> Solutions = new ArrayList<Solution> ();
		float lowerbound = (float) -10;
		float upperbound = (float) 10;
		Random r = new Random();
		for(int i =0;i<10;i++) 
		{
			Solution x = new Solution();
			for(int j=0;j<NumberOfFloatingPoints+1;j++) 
			{
				x.array.add(lowerbound + r.nextFloat() * (upperbound - lowerbound));
			}
			Solutions.add(x);
		}
		return Solutions;
	}
	public ArrayList<Solution> Tournement_Selection(ArrayList<Solution>s) 
	{
		ArrayList<Solution> k = new ArrayList<Solution>();
		for(int j =0;j<4;j++) 
		{
			float min = (float) 0x1.fffffep127;
			int m = -1;
			Solution temp = new Solution();
			for(int i=0;i<s.size();i++) 
			{
				if(s.get(i).fitness<min) 
				{
					temp=s.get(i);
					min=s.get(i).fitness;
					m=i;
				}
			}
			s.remove(m);
			k.add(temp);
		}
		return k;
	}
	public ArrayList <Solution> non_uniform_Mutation(ArrayList<Solution>s,int Iteration_N,int Gen_N)
	{
		for(int i =0;i<s.size();i++) {
			for(int j=0;j<s.get(i).array.size();j++) {
				Random r = new Random();
				int upperbound=100;
				int int_random = r.nextInt(upperbound);
				if(int_random>95) 
				{
					float old_value=s.get(i).array.get(j);
					float delta_L= s.get(i).array.get(j)+10;
					float delta_U= 10-s.get(i).array.get(j);
					int int_random2 = r.nextInt(upperbound);
					if(int_random2>50) 
					{
						float y= delta_U;
						int int_random3 = r.nextInt();
						int b = r.nextInt(6);
						float result = (float) (y*Math.pow(1-int_random3,Math.pow(1-(Iteration_N/Gen_N),b)));
						s.get(i).array.set(j,old_value-result);
					}
					else if(int_random2<=50) 
					{
						float y= delta_L;
						int int_random3 = r.nextInt();
						int b = r.nextInt(6);
						float result = (float) (y*Math.pow(1-int_random3,Math.pow(1-(Iteration_N/Gen_N),b)));
						s.get(i).array.set(j,result-old_value);//Question??
					}
				}
		else
			return s;
			}
		}
		return null;
	}
	public ArrayList <Solution> two_way_crossver(int NumberOfFloatingPoints,ArrayList<Solution>s) 
	{
		if(NumberOfFloatingPoints == 1) 
		{
			for(int i=0;i<3;i=i+2) 
			{
				Solution x= s.get(i);//0
				Solution y= s.get(i+1);//1
				Solution temp = new Solution();
				Solution temp2 = new Solution();
				temp.array.add(x.array.get(0));
				temp.array.add(y.array.get(1));
				temp2.array.add(y.array.get(0));
				temp2.array.add(x.array.get(1));
				s.set(i, temp);
				s.set(i+1, temp2);
			}
		}
		else if(NumberOfFloatingPoints == 2) 
		{
			for(int i=0;i<3;i=i+2) 
			{
				Solution x= s.get(i);//0
				Solution y= s.get(i+1);//1
				Solution temp = new Solution();
				Solution temp2 = new Solution();
				temp.array.add(x.array.get(0));
				temp.array.add(y.array.get(1));
				temp.array.add(x.array.get(2));
				temp2.array.add(y.array.get(0));
				temp2.array.add(x.array.get(1));
				temp2.array.add(y.array.get(2));
				s.set(i, temp);
				s.set(i+1, temp2);
			}
		}
		else if(NumberOfFloatingPoints == 3) 
		{
			for(int i=0;i<3;i=i+2) 
			{
				Solution x= s.get(i);//0
				Solution y= s.get(i+1);//1
				Solution temp = new Solution();
				Solution temp2 = new Solution();
				temp.array.add(x.array.get(0));
				temp.array.add(y.array.get(1));
				temp.array.add(x.array.get(2));
				temp.array.add(x.array.get(3));
				temp2.array.add(y.array.get(0));
				temp2.array.add(x.array.get(1));
				temp2.array.add(y.array.get(2));
				temp2.array.add(y.array.get(3));
				s.set(i, temp);
				s.set(i+1, temp2);
			}
		}
		return s;
	}
	public Solution getbest(ArrayList <Solution>s) 
	{
		Solution f = new Solution();
		float min = (float) 0x1.fffffep127;
		for(int i=0;i<s.size();i++) 
		{
			if(s.get(i).fitness<min) 
			{
				f=s.get(i);
				min=s.get(i).fitness;
			}
		}
		return f;
		
	}
	public ArrayList<Solution> Fitness_Evaluation2(ArrayList<Solution>Solutions,int NumberOfFloatingPoints,ArrayList<Float>x,ArrayList<Float>y,int NumberOfPairs) 
	{
		float Fitness=0;
		float totalerror=0;
			for(int i =0;i<10;i++) 
			{
				for(int q=0;q<x.size();q++) {
				for(int j=0;j<NumberOfFloatingPoints+1;j++) 
				{
					Fitness+=(float) ((Solutions.get(i).array.get(j)*Math.pow(x.get(q),j)));
				}
				Fitness-=y.get(q);
				Fitness *=Fitness;
				totalerror+=Fitness;
				Fitness=0;
			}
				Solutions.get(i).fitness=totalerror/NumberOfPairs;
				totalerror=0;
			}
		return Solutions;
	}
	
	
	
	
	
	
	
	
	
	
	
	/*public ArrayList<Solution> Fitness_Evaluation(ArrayList<Solution>Solutions,int NumberOfFloatingPoints,ArrayList<Float>x,ArrayList<Float>y,int NumberOfPairs) 
	{
		float Fitness=0;
		float totalerror=0;
		if(NumberOfFloatingPoints == 1) 
		{
			for(int i =0;i<10;i++) 
			{
				for(int j=0;j<x.size();j++) 
				{
					Fitness=((Solutions.get(i).array.get(1)*x.get(j))+Solutions.get(i).array.get(0))-y.get(j);
					Fitness *=Fitness;
					totalerror+=Fitness;
				}
				Solutions.get(i).fitness=totalerror/NumberOfPairs;
				totalerror=0;
			}
		}
		else if(NumberOfFloatingPoints == 2) 
		{
			for(int i =0;i<10;i++) 
			{
				for(int j=0;j<x.size();j++) 
				{
					Fitness=((Solutions.get(i).array.get(2)*x.get(j)*x.get(j))+(Solutions.get(i).array.get(1)*x.get(j))+Solutions.get(i).array.get(0))-y.get(j);
					Fitness *=Fitness;
					totalerror+=Fitness;
				}
				Solutions.get(i).fitness=totalerror/NumberOfPairs;
				totalerror=0;
			}
		}
		else if(NumberOfFloatingPoints == 3) 
		{
			for(int i =0;i<10;i++) 
			{
				for(int j=0;j<x.size();j++) 
				{
					Fitness=((Solutions.get(i).array.get(3)*x.get(j)*x.get(j)*x.get(j))+(Solutions.get(i).array.get(2)*x.get(j)*x.get(j))+(Solutions.get(i).array.get(1)*x.get(j))+Solutions.get(i).array.get(0))-y.get(j);
					Fitness *=Fitness;
					totalerror+=Fitness;
				}
				Solutions.get(i).fitness=totalerror/NumberOfPairs;
				totalerror=0;
			}
		}
		return Solutions;
	}*/
}
