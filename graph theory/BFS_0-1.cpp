/*
In a BFS we usually have a graph in which there are only edges with weight 1, 
so we have a distance which increases by one unit when moves from the current 
node to its childrens, but this can be extended to graphs in which there are 
also edges with weight 0 (zero). To achieve this we use double-ended-queue; 

See more in: https://chococontest.wordpress.com/category/bfs-01/
*/

/* Problems related */
// TJU 1091 – Finding Nemo
// TJU 3298 -  Prairie dogs V
// PKU 3662 – Telephone Lines
// UVA 985 – Round and Round Maze

/* Now here is a sample of algorithm working */

/* Uva Online Judge 11573 - Ocean Currents */
/* Link to problem: http://goo.gl/3msaVt */

/* A brief description of problem */
/* For a boat on a large body of water, strong currents can be dangerous, but with careful planning, they
can be harnessed to help the boat reach its destination. Your job is to help in that planning.
At each location, the current flows in some direction. The captain can choose to either go with the
flow of the current, using no energy, or to move one square in any other direction, at the cost of one
energy unit. The boat always moves in one of the following eight directions: north, south, east, west,
northeast, northwest, southeast, southwest. The boat cannot leave the boundary of the lake.
You are to help him devise a strategy to reach the destination with the minimum energy consumption. */

/* Sample input *
5 5
04125
03355
64734
72377
02062
3
4 2 4 2
4 5 1 4
5 3 3 4
* Sample input end */

/* Sample output
0
2
1
* Sample output end */

/* Sample source code Begin */

#include <queue>
#include <cstring>
#include <cstdio>
#include <utility>

using namespace std;

#define Mx 1234
#define mp make_pair

//Graph 
char G[ Mx ][ Mx ];

//Matrix of movements = { N  , NE  ,  E  , SE  ,  S  ,  SW  ,  W   ,  NW  } 
int mov[ 8 ][ 2 ] = { {-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1} };

//Distances
int dis[ Mx ][ Mx ];

int main()
{
	int R , C ; // Grid's size
	int tests , Rs , Cs , Rd , Cd , direction;

	sscanf( gets(G[0]), "%d %d", &R, &C );
	{
		// Get graph
		for( int i = 0 ; i < R; i++ )
		{
			gets( G[ i ] );
		}

		//How many tests?
		scanf("%d",&tests);

		while( tests-- )
		{
			//Get source and destination
			scanf("%d %d %d %d",&Rs,&Cs,&Rd,&Cd);
			Rs--; Cs--; Rd--; Cd--;

			// **** BFS 0/1 ***
			//Set the distances to a sentinel
			memset( dis , -1 , sizeof dis );
			deque < pair < int, int > > DQ;
			// insert source and make distance 0 to this point
			DQ.push_front( mp( Rs, Cs ) );
			dis[ Rs ][ Cs ] = 0;

			//Go through the graph.
			while( !DQ.empty() )
			{
				Rs = DQ.front().first;
				Cs = DQ.front().second;
				DQ.pop_front();

				direction = G[ Rs ][ Cs ] - '0';
				//Process all childs
				for( int k = 0; k < 8; k++ )
				{
					int Rn = Rs + mov[ k ][ 0 ];
					int Cn = Cs + mov[ k ][ 1 ];
					if( Rn >= 0 && Rn < R && Cn >= 0 && Cn < C )
					{
						// If move is in the same direction of flow then no cost
						if( k == direction ) 
						{
							// If the node was not visited or can improve distance
							if( dis[ Rn ][ Cn ] == -1 || dis[ Rs ][ Cs ] < dis[ Rn ][ Cn ] )
							{
								DQ.push_front( mp( Rn , Cn ) );
								dis[ Rn ][ Cn ] = dis[ Rs ][ Cs ];
							}
						}
						// like traditional BFS only check if was not visited yet
						else if ( dis[ Rn ][ Cn ] == -1 ) 
						{
							DQ.push_back( mp( Rn , Cn ) );
							dis[ Rn ][ Cn ] = dis[ Rs ][ Cs ] + 1;
						}
					}
				}
			}

			//Print the distance
			printf("%d\n",dis[Rd][Cd]);
		}
	} 
	return 0;
}

/* End sample source code */
