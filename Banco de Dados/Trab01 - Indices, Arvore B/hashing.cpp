#include "hashing.hpp"

void Hash::create_file() {
    char *zeros;
    long size = this->size * BLOCK + header_size;

    zeros = (char*) malloc(size);
    memset(zeros, 0, size);

    file_descriptor = fopen("recordsHash.bin", "wb+");

    if(!file_descriptor) {
        cout << "Erro ao abrir arquivo." << endl;
        return;
    }

    fwrite(zeros, 1, size, file_descriptor);
    saveCabecalho();
}

void Hash::enderecos() {
    Info_Block aux;
    long addr_offset = header_size;
    for (int i = 0; i < this->size; i++) {
        aux.bucket_index = i;
        aux.block_addr = addr_offset;
        block_addr[i] = aux;
        addr_offset += BLOCK;
    }
}

void Hash::saveCabecalho() {
    char bloco[BLOCK];
    int fanout = BLOCK/sizeof(Info_Block);
    int i;

    enderecos();

    fseek(file_descriptor, 0, SEEK_SET);
    for (i = 0; i < (this->size); i += fanout) {
        memcpy(&bloco[0], &block_addr[i], sizeof(Info_Block)*fanout);
        fwrite(bloco, 1, BLOCK, file_descriptor);
    }
}

void Hash::carregarCabecalhos() {
    char bloco[BLOCK];
    int fanout = BLOCK/sizeof(Info_Block);
    int i;

    for (i = 0; i < (this->size); i += fanout) {
        fread(bloco, 1, BLOCK, file_descriptor);
        memcpy(&block_addr[i], &bloco[0], sizeof(Info_Block)*fanout);
    }
}

void Hash::addArtigo(Artigo registro) {
    int index = hash_function(registro.id);
    long addr = block_addr[index].block_addr;
    bool found = false;
    Bucket bucket;

    bucket.buscar(file_descriptor, addr);
    for (int i = 0; i < bucket.artigos.size(); i++) {
        if(bucket.artigos[i].id == 0) {
            bucket.artigos[i] = registro;
            found = true;
            break;
        }
    }

    if(!found) {
        bucket.addOverflow(registro, addr, file_descriptor);
    } else {
        bucket.listaOverflow.block_addr = -1;
        bucket.listaOverflow.offset = -1;
        bucket.addArtigo(file_descriptor, addr);
    }
}

void Hash::buscar() {
    int status;
    int i = 0;
    long offset = header_size;

    do {
        Bucket bucket;
        status = bucket.buscar(file_descriptor, offset);
        offset += 4096;
        i += 1;
    } while(status);

    fclose(file_descriptor);
    file_descriptor = NULL;
}

// função para pesquisar no arquivo
Artigo Hash::buscar_artigo(int id, int& count_blocks) {
    Bucket bucket;
    long int addr;
    int art_pos;
    int index = hash_function(id);

    addr = block_addr[index].block_addr;

    count_blocks = 1;
    bucket.buscar(file_descriptor, addr);
    art_pos = bucket.find_artigo(id);

    if(art_pos == -1) {
        return buscar_artigo_overflow(id, bucket, count_blocks);
    }

    return bucket.artigos[art_pos];
}

Artigo Hash::buscar_artigo_overflow(int id, Bucket bucket, int& count_blocks) {

    FILE *overflow_file;
    char bloco[BLOCK];
    Artigo registro;

    memset(&registro, 0, sizeof(Artigo));
    overflow_file = fopen("hash_overflow.bin", "rb");

    Artigo artigo;
    Info_Overflow of_addr;
    Info_Overflow next_addr = bucket.listaOverflow;

    while(next_addr.block_addr != -1) {
        count_blocks += 1;
        of_addr = next_addr;
        next_addr = bucket.find_next_addr(of_addr, overflow_file, bloco);
        memcpy(&artigo, &bloco[of_addr.offset], sizeof(Artigo));

        if(artigo.id == id) {
            registro = artigo;
            break;
        }
    }

    fclose(overflow_file);

    return registro;
}

void Hash::print() {
    int index;

    for (index = 0; index < this->size; index++) {
        for (auto artigo:buckets[index].artigos) {
            cout << "Artigo ID: " << artigo.id << " ";
        }
        cout << endl;
    }
}

int Bucket::buscar(FILE *main_file, long offset) {
    Artigo registro;
    char bloco[BLOCK];
    int i;

    fseek(main_file, offset, SEEK_SET);
    int status = fread(bloco, 1, BLOCK, main_file);
    if(status) {
        for (i = 0; i < 7; i++) {
            memcpy(&registro, &bloco[sizeof(Artigo)*i], sizeof(Artigo));

            artigos.push_back(registro);
        }
        memcpy(&listaOverflow, &bloco[sizeof(Artigo)*i], sizeof(Info_Overflow));
    }

    return status;
}

void Bucket::addArtigo(FILE *main_file, long offset) {
    char bloco[BLOCK];
    int count = 0;

    memset(bloco, 0, BLOCK);
    for (auto artigo:artigos) {
        memcpy(&bloco[sizeof(Artigo)*count], &artigo, sizeof(Artigo));
        count += 1;
    }
    memcpy(&bloco[sizeof(Artigo)*count], &listaOverflow, sizeof(Info_Overflow));

    fseek(main_file, offset, SEEK_SET);
    fwrite(bloco, BLOCK, 1, main_file);
}

void Bucket::addOverflow(Artigo registro, long main_block_addr, FILE *main_file) {
    FILE *overflow_file = NULL;
    char bloco[BLOCK];
    long file_size;
    int block_addr;
    bool found_free = false;
    Info_Overflow of_addr;

    overflow_file = fopen("overflow.bin", "rb+");

    if(!overflow_file) {
        overflow_file = fopen("overflow.bin", "wb+");
    }

    fseek(overflow_file, 0, SEEK_END);
    file_size = ftell(overflow_file);

    if(file_size > 0) block_addr = file_size - 4096;
    else block_addr = 0;

    if(file_size > 0) {
        fseek(overflow_file, block_addr, SEEK_SET);
        fread(bloco, 1, BLOCK, overflow_file);

        int passo = sizeof(Artigo)+sizeof(Info_Overflow);
        for(int i = 0; i < BLOCK-passo; i += passo) {
            Artigo artigo;
            Info_Overflow null_off_addr;
            memcpy(&artigo, &bloco[i], sizeof(Artigo));

            if(artigo.id == 0) {
                fseek(overflow_file, -BLOCK, SEEK_CUR);
                memcpy(&bloco[i], &registro, sizeof(Artigo));
                memcpy(&bloco[i+sizeof(Artigo)], &null_off_addr, sizeof(Info_Overflow));
                of_addr.block_addr = block_addr;
                of_addr.offset = i;

                found_free = true;

                break;
            }
        }

        if(!found_free) {
            generate_new_overflow_block(registro, bloco);
            of_addr.block_addr = block_addr + BLOCK;
            of_addr.offset = 0;
        }
    } else {

        generate_new_overflow_block(registro, bloco);
        of_addr.block_addr = 0;
        of_addr.offset = 0;
    }

    fwrite(bloco, 1, BLOCK, overflow_file);

    update_listaOverflow(of_addr, overflow_file, main_file, main_block_addr);

    fclose(overflow_file);
}

void Bucket::generate_new_overflow_block(Artigo registro, char bloco[]) {
    Info_Overflow null_off_addr;

    memset(bloco, 0, BLOCK);
    // escreve o novo registro no início do bloco
    memcpy(&bloco[0], &registro, sizeof(Artigo));
    // escreve o campo de endereco null
    memcpy(&bloco[sizeof(Artigo)], &null_off_addr, sizeof(Info_Overflow));
}

void Bucket::update_listaOverflow(Info_Overflow new_reg_addr, FILE *overflow_file, FILE *main_file, long main_block_addr) {
    char bloco[BLOCK];

    // faz o update da lista de overflow
    if(listaOverflow.block_addr == -1) {
        listaOverflow = new_reg_addr;
        addArtigo(main_file, main_block_addr);
    } else {
        Info_Overflow of_addr;
        Info_Overflow next_addr = listaOverflow;
        while(next_addr.block_addr != -1) {
            of_addr = next_addr;
            next_addr = find_next_addr(of_addr, overflow_file, bloco);
        }

        fseek(overflow_file, of_addr.block_addr, SEEK_SET);

        memcpy(&bloco[of_addr.offset+sizeof(Artigo)], &new_reg_addr, sizeof(Info_Overflow));

        fwrite(bloco, 1, BLOCK, overflow_file);
    }
}

Info_Overflow Bucket::find_next_addr(Info_Overflow actual_addr, FILE *overflow_descriptor, char bloco[]) {
    Info_Overflow next_addr;

    fseek(overflow_descriptor, actual_addr.block_addr, SEEK_SET);
    fread(bloco, 1, BLOCK, overflow_descriptor);

    memcpy(&next_addr, &bloco[actual_addr.offset+sizeof(Artigo)], sizeof(Info_Overflow));

    return next_addr;
}

int Bucket::find_artigo(int id) {
    for (int i = 0; i < artigos.size(); i++) {
        if(artigos[i].id == id) return i;
    }
    return -1;
}
