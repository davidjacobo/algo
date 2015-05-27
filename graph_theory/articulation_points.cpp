#include <iostream>
#include <vector>
#define NOT_SEEN -1
#define MAX_V 1000000

using namespace std;
typedef vector<int> vi;

bool art[MAX_V];
int dfs_num[MAX_V], dfs_low[MAX_V], parent[MAX_V];
int root_sons, root_counter, root;
vi graph[MAX_V];

void clean(int v){
    for(int i=0;i<v;++i){
        graph[i].clear();
        parent[i] = dfs_num[i] = dfs_low[i] = NOT_SEEN;   
        art[i] = false;
    }
}

void dfs_art(int u){
    dfs_num[u] = dfs_low[u] = root_counter++;
    
    for(int i=0;i<graph[u].size();++i){
        int v = graph[u][i];
        
        if(dfs_num[v]==NOT_SEEN){
            parent[v] = u;
            dfs_art(v);

            if(u==root) root_sons++;

            if(dfs_low[v] >= dfs_num[u])    art[u] = true;
            dfs_low[u] = min(dfs_low[u], dfs_low[v]);
        } else if(v!=parent[u]) {
            dfs_low[u] = min(dfs_low[u], dfs_num[v]);
        }
    }   
}

int main(){
    int V,E;
    int x,y;
    cin>>V>>E;

    clean(V);

    for(int i=0;i<E;++i){
        cin>>x>>y;
        graph[x].push_back(y);
        graph[y].push_back(x);
    }

    root_counter = 0;
    for(int i=0;i<V;++i){
        if(dfs_num[i]==NOT_SEEN){
            root_sons = 0;
            root = i;
            dfs_art(i);

            art[i] = (root_sons > 1);           
        }
    }

    cout<<"Articulation vertices"<<endl;
    for(int i=0;i<V;++i){
        if(art[i])
            cout<<i<<" ";
    }
    cout<<endl;

    return 0;
}
