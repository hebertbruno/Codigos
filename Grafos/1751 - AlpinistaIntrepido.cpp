#include <bits/stdc++.h>
#define D(x) cout << #x " = " << (x) << endl
#define endl '\n'

using namespace std;

struct edge{
  int a, b, c;
};

typedef  vector<vector<edge>> grafo;

bool DFS(grafo &g, vector<int> &t, int node) {
  for (auto e : g[node])
    if (DFS(g, t, e.b))
      t[node] |= true;

  return t[node];
}

int go(grafo &g, vector<int> &t, int node, bool acc) {
  if (!t[node]) return 0;

  int total = 0;
  for (auto e : g[node]) {
    if (t[e.b]) {
      if (acc)
        total += go(g, t, e.b, acc) + e.c;
      else
        total = max(total, go(g, t, e.b, acc) + e.c);
    }
  }
  return total;
}


int main() {
  int n, f;
  cin >> n >> f;
  while (n) {
    vector<vector<edge>> g(n);
    vector<int>  alvo(n);
    edge e;

    //constroi a arvore e seu custo nas arestas
    for (int i = 0; i < n - 1; ++i) {
      cin >> e.a >> e.b >> e.c;
      e.a--; e.b--;
      g[e.a].push_back(e);
    }

    // definir os alvos
    int x;
    for (int i = 0; i < f; ++i) {
      cin >> x;
      alvo[x - 1] =  true;
    }
    DFS(g, alvo, 0);

    cout <<  go(g, alvo, 0, true) - go(g, alvo, 0, false) << endl;
    n--;
  }
  return 0;
}
