#include <bits/stdc++.h>
#define D(x) cout << #x " = " << (x) << endl
#define endl '\n'

using namespace std;

struct aresta{
  int indo, para;
  aresta() {}
  aresta(int a, int b) : indo(a), para(b) {}
};

typedef  vector<vector<aresta>> graph;

int busca_profundidade(graph &g, vector<int> &v, int node) {

  v[node] = true;
  int total = 0;
  for (auto e : g[node])
    if (!v[e.para])
      total += busca_profundidade(g, v, e.para) + 1;

  return total;
}

void solucao() {
  int x; cin >> x;
  int n, m;
  cin >> n >> m;
  aresta e;
  graph g(n);
  vector<int> seen(n);
  for (int i = 0; i < m; ++i) {
    cin >> e.indo >> e.para;
    g[e.indo].push_back(e);
    g[e.para].emplace_back(e.para, e.indo);
  }
  cout << busca_profundidade(g, seen, x) * 2 << endl;
}

int main() {
  ios_base::sync_with_stdio(false);cin.tie(NULL);
  int tc; 
  cin >> tc;
  for (int i = 0; i < tc; ++i) {
    solucao();
  }
  return 0;
}
