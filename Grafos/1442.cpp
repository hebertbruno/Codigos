#include <iostream>
#include <stack>
#include <list>
#include <vector>

using namespace std;

// cada vértice tem o seu componente
vector<int> vertices_componentes;
int componente;
bool flag_atravessa;

class Grafo{
private:
	int V;
	list<int> * adj;
	bool orientado;

	void preenche(int v, bool visitados[], stack<int>& pilha)
	{
		visitados[v] = true;

		list<int>::iterator i;
		for(i = adj[v].begin(); i != adj[v].end(); i++)
		{
			if(visitados[*i] == false)
				preenche(*i, visitados, pilha);
		}
		pilha.push(v);
	}

	void DFS(int v, bool visitados[])
	{
		visitados[v] = true;

		if(orientado)
			vertices_componentes[v] = componente;

		list<int>::iterator i;
		for(i = adj[v].begin(); i != adj[v].end(); i++)
		{
			if(visitados[*i] == false)
				DFS(*i, visitados);
		}
	}

public:

	Grafo(int V, bool orientado = true)
	{
		this->V = V;
		adj = new list<int>[V];
		this->orientado = orientado;
	}

	void adicionarAresta(int i, int j)
	{
		adj[i].push_back(j);
	}

	Grafo obterGrafoTransposto()
	{
		Grafo grafo_transposto(V);

		for(int v = 0; v < V; v++)
		{
			list<int>::iterator i;
			for(i = adj[v].begin(); i != adj[v].end(); i++)
				grafo_transposto.adj[*i].push_back(v);
		}

		return grafo_transposto;
	}

	int obterComponentes()
	{
		int qte_componentes = 0;
		stack<int> pilha;
		bool * visitados = new bool[V];

		for(int i = 0; i < V; i++)
			visitados[i] = false;

		for(int i = 0; i < V; i++)
		{
			if(visitados[i] == false)
				preenche(i, visitados, pilha);
		}

		Grafo gt = obterGrafoTransposto();

		for(int i = 0; i < V; i++)
			visitados[i] = false;

		while(!pilha.empty())
		{
			int v = pilha.top();

			pilha.pop();

			if(visitados[v] == false)
			{
				gt.DFS(v, visitados);
				qte_componentes++;

				if(orientado)
					componente++;
			}
		}

		return qte_componentes;
	}

	bool ehConectado()
	{
		bool * visitados = new bool[V];

		for(int i = 0; i < V; i++)
			visitados[i] = false;

		DFS(0, visitados);

		for(int i = 0; i < V; i++)
		{
			if(!visitados[i])
				return false;
		}

		return true;
	}


	/*
		As duas funções abaixo servem para detectar todas as pontes.
		As funções foram retiradas do geeksforgeeks.
		As funções foram levemente modificadas para atender ao problema.
		Endereço: http://www.geeksforgeeks.org/bridge-in-a-graph/
		Algoritmo de Tarjan, complexidade: O(V + E)
	*/

	void bridgeUtil(int u, bool visited[], int disc[], int low[], int parent[])
	{
		if(flag_atravessa == false)
		{
			static int time = 0;

			visited[u] = true;

			disc[u] = low[u] = ++time;

			list<int>::iterator i;
			for(i = adj[u].begin(); i != adj[u].end(); ++i)
			{
				int v = *i;

				if(!visited[v])
				{
					parent[v] = u;
					bridgeUtil(v, visited, disc, low, parent);

					low[u] = min(low[u], low[v]);

					// vértices que formam a ponte
					if(low[v] > disc[u])
					{
						/*
							verifica se os vértices estão em componentes
							separados no grafo original
						*/
						if(vertices_componentes[v] != vertices_componentes[u])
						{
							flag_atravessa = true;
							break;
						}
					}
				}
				else if(v != parent[u])
					low[u] = min(low[u], disc[v]);
			}
		}
	}

	void bridge()
	{
		bool *visited = new bool[V];
		int *disc = new int[V];
		int *low = new int[V];
		int *parent = new int[V];

		for(int i = 0; i < V; i++)
		{
			parent[i] = -1;
			visited[i] = false;
		}

		for(int i = 0; i < V; i++)
			if(visited[i] == false)
				bridgeUtil(i, visited, disc, low, parent);
	}
};

int main(int argc, char *argv[])
{
	int N, M;


	while(cin >> N)
	{
		componente = 1;
		vertices_componentes.clear();
		flag_atravessa = false;

		for(int i = 0; i < N; i++)
			vertices_componentes.push_back(0);

		cin >> M;
		Grafo g(N); // grafo original
		Grafo g2(N, false); // grafo não orientado
		int A, B, T;

		cin >> A >> B >> T;

		for(int i = 1; i < M; i++){
			cin >> A >> B >> T;

			if(T == 1)
			{
				g.adicionarAresta(A - 1, B - 1);
			}
			else
			{
				g.adicionarAresta(A - 1, B - 1);
				g.adicionarAresta(B - 1, A - 1);
			}

			// grafo não orientado: adiciona a ida e a volta
			g2.adicionarAresta(A - 1, B - 1);
			g2.adicionarAresta(B - 1, A - 1);
		}

		int ssc = g.obterComponentes();

		// se for 1, então não precisa mudar direção alguma, imprime "-"
		if(ssc == 1)
		{
			cout << "-";
		}
		else if (ssc > 1)
		{
			/*
				se for maior que 1, verifica se o grafo não orientado
				é conectado, se não for imprime "*"
			*/

			if(!g2.ehConectado())
				cout << "*";
			else
			{
				/*
					verifica há pontes no grafo não orientado
					que cruzam componentes conectados do grafo
					original, isso porque o grafo modificado
					pode ter pontes em arestas que no grafo original
					eram de mão dupla
					basta guardar o componente de cada vértice,
					se não existe ponte entre vértices de componentes
					diferentes, então a resposta é 1, isso porque
					existem dois caminhos entre esses componentes e
					um deles pode ser com a aresta invertida.
					a função bridge detecta todas as pontes em um grafo
					não direcionado com um tempo O(V + E)
				*/

				g2.bridge();

				if(flag_atravessa == false)
					cout << "1";
				else
					cout << "2";
			}
		}

		cout << endl;
	}

	return 0;
}