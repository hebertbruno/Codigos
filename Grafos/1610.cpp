#include <iostream>
#include <stdlib.h>
#include <list>
#include <vector>
#include <sstream>
#include <string>
 
using namespace std;
 
const int N_VISITADO = 0;
const int VISITADO = 1;
const int finalizado = 2;
 
typedef struct graph {
vector<int> status;
vector< vector<int> > tabelaAdjacentes; 

void graph(int quantidade) {
    status = vector<int>(quantidade+1);
    tabelaAdjacentes = vector< vector<int> > (quantidade+1 , vector<int>());
}
 
void addadjacente(int vertice, int adjacente) {
    tabelaAdjacentes[vertice].push_back(adjacente);
}
 


bool buscaProfundidade(int vertice) {
    status[vertice] = VISITADO;
 
 
    for(int index=0; index<tabelaAdjacentes[vertice].size(); index++) {
        int verticeAdjacente = tabelaAdjacentes[vertice][index];
 
 
        if(status[verticeAdjacente] == N_VISITADO) {
            if(!buscaProfundidade(verticeAdjacente)) {
                return false;
            }
        } else if(status[verticeAdjacente] == VISITADO) {
            return false;
        }
    }
 
    status[vertice] = finalizado;
    return true;
}
 
void buscaCiclos() {
    for(int index = 1; index < status.size(); index++) {
        if(!buscaProfundidade(index)) {
            cout << "SIM" << endl;
            return;
        }
    }
    cout << "NAO" << endl;
}

} graph; 
 
 
int main () {
    int testeQuantidade, dependencias, verticesquantidade, vertice, adjacente;
    //string input = "";
    int input;
    cin >> input;
 
    testeQuantidade = input;
 
    for(int index = 0; index < testeQuantidade; index++) {
        graph g;
 
        cin >> input;
        verticesquantidade = input;
 
        cin >> input;
        dependencias = input;
 
        g.graph(verticesquantidade);
 
        for(int indexdependencias = 0; indexdependencias < dependencias; indexdependencias++) {
            cin >> input;
            vertice = input;
 
            cin >> input;
            adjacente = input;
 
            g.addadjacente(vertice, adjacente);
        }
 
    g.buscaCiclos();
    }
}