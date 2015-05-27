#include <iostream>
#include <vector>
#include <stack>
#define NOT_SEEN -1
#define MAX_V 1000

using namespace std;
typedef vector<int> vi;

int dfs_low[MAX_V], dfs_num[MAX_V];
bool to_process[MAX_V];
int vertex_counter, scc_counter;

stack<int> s;
vi graph[MAX_V];

void clear(int v){
    for(int i=0;i<v;++i){
        graph[i].clear();
        dfs_num[i] = dfs_low[i] = NOT_SEEN;
        to_process[i] = false;
    }
    stack<int> s;
}

void dfs_scc(int u){
    dfs_low[u] = dfs_num[u] = vertex_counter++;
    to_process[u] = true;

    s.push(u);

    for(int i=0;i<graph[u].size();++i){
        int v = graph[u][i];
        
        if(dfs_num[v]==NOT_SEEN)    dfs_scc(v);
        if(to_process[v]) dfs_low[u] = min(dfs_low[u], dfs_low[v]);
    }

    //this comparation can mean 2 things:
    //vertex u is the start of a cycle
    //or 
    //vertex u is not part of a cycle
    if(dfs_low[u] == dfs_num[u]){
        cout<<"SCC No.: "<<scc_counter++<<endl;
       
        int v;
        do{
            v = s.top();    s.pop();
            cout<<v<<" ";
            to_process[v] = false;
        }while(u!=v);
    }
}

int main(){
    int V,E;
    int x,y;
    
    cin>>V>>E;
    clear(V);
    for(int i=0;i<E;++i){
        cin>>x>>y;
        graph[x].push_back(y);
    }
    
    scc_counter = 1;
    for(int i=0;i<V;++i){
        if(dfs_num[i]==NOT_SEEN)
            dfs_scc(i);
    }
    return 0;
}
