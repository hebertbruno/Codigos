#include "hashing.hpp"

int main(int argc, char **argv) {
    Hash r_hash('r');
    int count_blocks;
    int id;

    if(argc < 2) {
        cout << "./findrec <ID>\n";
        exit(1);
    }

    id = atoi(argv[1]);
    Artigo artigo = r_hash.buscar_artigo(id, count_blocks);
    if(artigo.id != 0) {
        cout << "Id: " << artigo.id << endl;
        cout << "Título: " << artigo.titulo << endl;
        cout << "Ano: " << artigo.ano << endl;
        cout << "Autores: " << artigo.autores << endl;        
        cout << "Citações: " << artigo.citacoes << endl;
        cout << "Atualização: " << artigo.atualizacao << endl;
        cout << "Snippet: \"" << artigo.snippet << "\"."<< endl;
    } else {
        cout << "Artigo não encontrado.\n";
    }

    return 0;
}