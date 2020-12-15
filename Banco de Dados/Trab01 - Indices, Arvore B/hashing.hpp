#ifndef HASHING_HPP
#define HASHING_HPP

#include "artigo.hpp"
#include <vector>
#include <string.h>


struct Info_Block {
    long block_addr;
    int bucket_index;
};

struct Info_Overflow {
    long block_addr = -1;
    int offset = -1;
};

class Bucket {
public:
    
    Info_Overflow listaOverflow;
    vector<Artigo> artigos;

    int buscar(FILE *arquivo, long offset);
    void addArtigo(FILE *arquivo, long offset);
    void addOverflow(Artigo registro, long block_addr, FILE *arquivo);
    Info_Overflow find_next_addr(Info_Overflow actual_addr, FILE *overflow_descriptor, char bloco[]);
    int find_artigo(int id);

private:
    void update_listaOverflow(Info_Overflow reg_addr, FILE *overflow_file, FILE *arquivo, long block_addr);
    void generate_new_overflow_block(Artigo registro, char bloco[]);
};

class Hash {
public:
    int size = 180004;
    int header_size = size*sizeof(Info_Block);
    vector<Info_Block> block_addr;
    vector<Bucket> buckets;
    Bucket bucket_overflow;
    FILE *file_descriptor = NULL;
    char op_mode;

    Hash(char op_mode) {
        this->op_mode = op_mode;
        block_addr.reserve(header_size);

        switch(op_mode) {
            case 'w':
                create_file();
                break;
            case 'r':
                file_descriptor = fopen("recordsHash.bin", "rb");
                carregarCabecalhos();
                break;
            default:
                break;
        }
    }

    ~Hash() {
        if(file_descriptor) fclose(file_descriptor);
    }

    inline int hash_function(int id) {
        int index = id % size;

        return index;
    }

    void create_file();
    void enderecos();
    void saveCabecalho();
    void carregarCabecalhos();
    void addArtigo(Artigo artigo);
    void buscar();
    Artigo buscar_artigo(int id, int& count_blocks);
    void print();

private:
    Artigo buscar_artigo_overflow(int id, Bucket bucket, int& count_blocks);
};

#endif