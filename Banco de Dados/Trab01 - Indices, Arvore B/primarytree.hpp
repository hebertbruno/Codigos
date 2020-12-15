#ifndef _PRIMARYTREE
#define _PRIMARYTREE

#include "artigo.hpp"

#define ORDER 204 // m

struct Node {
    int qtd_keys; // quantidade de chaves armazenadas no nó
    int keys[ORDER]; // chaves
    long data_pointers[ORDER];
    long node_pointers[ORDER+1];
};

class PrimaryTree {
public:
    const int MAX_KEYS = ORDER;
    long root_addr;
    char op_mode;
    FILE *arquivo = NULL;
    FILE *data_file = NULL;

    PrimaryTree(char op_mode) {
        this->op_mode = op_mode;
        root_addr = -1;
    }

    ~PrimaryTree() {
        if(arquivo) fclose(arquivo);
    }

    void create_index();
    void print();
    void insert(int key, long data_addr);
    long buscarChave(int key, int& count_blocks);

private:
    long busca(int key, long curr_node_addr, int& count_blocks);
    bool inserir(long curr_node_addr, int key, long data_offset, int& up_key, long& up_data_offset, long& right_child_addr);
    void split(Node node, int &new_key, long &new_data_addr, long &new_key_right, long curr_node_addr);
    void imprimir(long curr_node_addr);
    void add_key(Node& node, int key, long data_offset, long right_child_addr, int position);
    bool find_key(Node node, int key, int &index);
    Node get_node(long node_addr);
    long write_node(Node node, long addr=-1);
    Node create_empty_node();
};

#endif
