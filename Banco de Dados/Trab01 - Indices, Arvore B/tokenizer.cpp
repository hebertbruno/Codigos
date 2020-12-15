#include "tokenizer.hpp"
#include "artigo.hpp"
#include "hashing.hpp"
#include <boost/regex.hpp>
#include <boost/algorithm/string/regex.hpp>
#include <stdlib.h>
#include <stdio.h>


// Verificar se um certo dado em um vetor de string é um número
bool Tokenizer::verify_number(const char string[]) {
    if(string[0] != '-' && !isdigit(string[0])) { return false; }

    int tam = strlen(string);
    int i;
    for (i = 1; i < tam; i++) {
        if(!isdigit(string[i])) {
            return false;
        }
    }

    return true;
}


void Tokenizer::fix_string(std::string &str) {
    str.erase(remove_if(str.begin(), str.end(), [](char c) { return !isprint(c); }), str.end());  // Retira o valor de um vetor
}

int Tokenizer::parser(const char arquivo[], const Hash &hash) {
    Artigo artigo;
    int tokens_number;
    string buffer, aux;
    vector<string> tokens;
    int count = 0;

    ifstream file(arquivo);

    if(!file.is_open()) {
        cout << "ERRO! Não foi possível abrir o arquivo!" << arquivo << ".\n";
        return 0;
    }
    buffer = "";
    while(getline(file, aux)) {
        // Auxilia na leitura total de uma linha quebrada
        buffer = buffer + aux;
        boost::algorithm::split_regex(tokens, buffer, boost::regex("\";\"|\";;\"|\";"));

        tokens_number = tokens.size();
        if(tokens_number > 5) {
            int next_position;
            buffer = "";

            tokens[0].erase(0, 1);
            size_t position = tokens[tokens_number-1].find("\"");
            if(position != string::position){
                tokens[tokens_number-1].erase(position);
            }

            artigo.id = stoi(tokens[0]);
            if(verify_number(tokens[2].c_str())) {
                artigo.ano = stoi(tokens[2]);
                fix_string(tokens[1]);
                strcpy(artigo.titulo, tokens[1].c_str());
                next_position = 3;
            } else {
                artigo.ano = stoi(tokens[1]);
                artigo.titulo[0] = '\0';
                next_position = 2;
            }

            if(verify_number(tokens[next_position+1].c_str())) {
                artigo.citacoes = stoi(tokens[next_position+1]);
                fix_string(tokens[next_position]);
                memcpy(artigo.autores, tokens[next_position].c_str(), 99);
                artigo.autores[99] = '\0';
                next_position += 2;
            } else {
                artigo.citacoes = stoi(tokens[next_position]);
                artigo.autores[0] = '\0';
                next_position += 1;
            }

            strcpy(artigo.atualizacao, tokens[next_position].c_str());
            fix_string(tokens[next_position+1]);
            memcpy(artigo.snippet, tokens[next_position+1].c_str(), 99);
            artigo.snippet[99] = '\0';

            hash.addArtigo(artigo);
            count += 1;
        }
    }

    file.close();

    return count;
}
