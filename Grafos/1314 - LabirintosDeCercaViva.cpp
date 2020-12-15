#include <bits/stdc++.h>
#define D(x) cout << #x " = " << (x) << endl
#define endl '\n'

using namespace std;
#define MAX 10001

int V[MAX], L[MAX], P[MAX], R, gpe;
vector<int> G[MAX];

inline int findset(int v) {
    if (P[v] != -1 && P[v] != v)
        return P[v] = findset(P[v]);
    return v;
}

inline int unionset(int x, int y) {
    int a = findset(x), b = findset(y);
    if (a<b) swap(a,b);
    P[b] = a;
}

void DFS(int u, int v) {
    V[v] = L[v] = ++gpe;
    
    for(int i = 0; i < G[v].size(); i++) {
        int w = G[v][i];
        if(!V[w]){
            DFS(v, w);
            L[v] = min(L[v], L[w]);

            if (L[w] > V[v]) 
                unionset(v, w);
        } else if(w != u) { 
            L[v] = min(L[v], V[w]);
        }
    }
}

int main() {
    int C, Q;
    while(cin >> R >> C >> Q, R|C|Q) {
        memset(G, 0, sizeof(vector<int>)*(R+1));
        memset(V, 0, sizeof(int)*(R+1));
        memset(L, 0, sizeof(int)*(R+1));
        memset(P, -1, sizeof(int)*(R+1));
        gpe = 0;

        for(int i=0; i<C; i++) {
            int A, B; 
            cin >> A >> B;
            G[A].push_back(B);
            G[B].push_back(A);
        }
        
        for(int i=0; i<R; i++)
            if (!V[i])
                DFS(i, i);

        for(int i=0; i<Q; i++) {
            int S, T; 
            cin >> S >> T;
            cout << (findset(S)==findset(T) ? "Y" : "N") << endl;
        }
        cout << "-" << endl;
    }
}