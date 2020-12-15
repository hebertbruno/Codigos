#ifndef __LIB_ARTIGO__
#define __LIB_ARTIGO__

#include <iostream>
#include <fstream>
#include <string.h>

#define BLOCK 4096

using namespace std;

class Artigo {
public:
    int id;
    int ano;
    int citacoes;
    char titulo[300];
    char autores[100];
    char atualizacao[20];
    char snippet[100];
};

#endif
