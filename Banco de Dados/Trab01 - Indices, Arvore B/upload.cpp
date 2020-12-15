#include "hashing.hpp"
#include "primarytree.hpp"
#include "tokenizer.hpp"
#include <stdlib.h>
#include <stdio.h>
#include <boost/regex.hpp>
#include <boost/algorithm/string/regex.hpp>

void primary_index(vector<Info_Block> addr, int tam) {
    PrimaryTree btree('w');
    for (int i = 0; i < tam; i++) {
        btree.insert(addr[i].bucket_index, addr[i].block_addr);
    }
}

int main(int argc, char **argv) {
    if(argc < 2) {
        cout << "Entre com o arquivo" << endl;
        exit(1);
    }



    Hash hash('w');

    int records = parser(argv[1], hash);
    cout << "Numero de registros: " << records << endl;


   primary_index(hash.block_addr, hash.size);
}
