package bell.runtime;
import java.util.LinkedList;
import java.util.Queue;

public class BellRun {

	public static void main(String[] args) {

		// Graph represented by an adjacency list
		int[][][] GT = {{},{{2,3},{3,0},{7,2}},{{4,1}},{{2,1},{5,1}},{{3,2}},{{2,3},{4,2},{6,2}},{{2,2},{4,3}},{{6,4},{8,0}},{}};
		// Array of each respective distances from source node
		int[] dist = new int[GT.length];
		// array of predecessors of each respective nodes in the shortest path
		int[] pred = new int[GT.length];
		bellman(GT,1,dist,pred);
		//We will check the shortest from 1 to 8.
		showShortest(dist, pred, 1,8);
		
	}


    // Function to choose th next node to work on bellman.
	private static int chooseNext(Queue<Integer> M,int[][][] G)
	{	
		for(int i : M)
		{
			boolean found = false;
			
			for(int j=0; j< G.length; j++)
			{
				int k=0;
				while(k < G[j].length)
				{
					if(G[j][k][0]== i)
					{
						found = true;
					}
				}
				
				if(!found)
				{
					return i;
				}
			}
		}
		return 0;
	}
	
	
	private static void bellman(int[][][] G,int src, int[] dist, int[] pred)
	{
		int i,x,y;
		// M is my queue of UNVISITED nodes
		Queue<Integer> M = new LinkedList<>();
		
		// Basic Bellman Algorithm below
		for(x=1; x<G.length;x++)
		{
			dist[x] = Integer.MAX_VALUE;
			pred[x] = -1;
			M.add(x);
		}
		dist[src]=0;
		M.remove(src);
		
		while(!M.isEmpty())
		{
			y= chooseNext(M,G);
			// M is changed locally and not in the function chooseNext
			M.remove(y);
			
			for(i=0; i< G[y].length;i++)
			{
				x=G[y][i][0];
				if((dist[x] + G[y][i][1]) < dist[y])
				{
					dist[y] = dist[x] + G[y][i][1];
					pred[y] = x;
				}
			}
		}
		
	}
	
	// Function to display the shortest path from source to destination in a human friendly way.
	// It will be display from right to left: source at the right, destination at the left
	private static void showShortest(int[] dist, int[] pred, int src, int dest)
	{
		int i;
		System.out.print("The shortest path from "+src+" to "+dest+ " is: \n");
		i= pred[dest];
		if(i == src)
		{
			System.out.print(dest+"<---"+i);
		}
		else
		{
			System.out.print(dest+"<---");
			while(i != src)
			{
				System.out.print(i+"<---");
				i= pred[i];
			}
			System.out.print(src);
		}	
		
	}
	
	//TODO: Function to check whether or not there is a circuit in the graph.
}
