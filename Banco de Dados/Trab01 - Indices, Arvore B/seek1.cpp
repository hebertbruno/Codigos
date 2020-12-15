#include "hashing.hpp"
#include "primarytree.hpp"

int main(int argc, char **argv) {
    Bucket block;
    Hash hash('r');
    PrimaryTree btree('r');
    Artigo artigo;
    int id, index, count_blocks;
    long block_addr;
    bool found = false;

    if(argc < 2) {
        cout << "./seek1 <ID>\n";
        exit(1);
    }

    id = atoi(argv[1]);
    index = hash.hash_function(id);
    block_addr = btree.buscarChave(index, count_blocks);

    block.busca(hash.file_descriptor, block_addr);
    for (auto registro:block.artigos) {
        if(registro.id == 0) break;

        if(registro.id == id) {
            artigo = registro;
            found = true;
            break;
        }
    }

    if(found) {
        cout << "Id: " << artigo.id << endl;
        cout << "Título: " << artigo.titulo << endl;
        cout << "Autores: " << artigo.autores << endl;
        cout << "Ano: " << artigo.ano << endl;
        cout << "Citações: " << artigo.citacoes << endl;
        cout << "Atualização: " << artigo.atualizacao << endl;
        cout << "Snippet: \"" << artigo.snippet << "\"."<< endl;
    } else {
        cout << "Artigo não encontrado.\n";
    }

    cout << "Número de blocos lidos: " << count_blocks + 1 << endl;
}