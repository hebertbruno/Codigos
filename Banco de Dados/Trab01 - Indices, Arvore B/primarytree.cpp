#include "primarytree.hpp"



void PrimaryTree::imprimir(long curr_node_addr) {
    if(curr_node_addr == -1) return;

    int i;
    Node node = get_node(curr_node_addr);

    cout << "qtd chaves: " << node.qtd_keys << endl;
    cout << "keys: " << endl;
    for (i = 0; i < node.qtd_keys; i++) {
        cout << node.keys[i] << "|" << node.data_pointers[i] << endl;
    }

    cout << "pointer: ";
    for (i = 0; i <= node.qtd_keys; i++) {
        cout << node.node_pointers[i] << endl;
        imprimir(node.node_pointers[i]);
    }

    cout << endl;
}

void PrimaryTree::print() {
    if(!arquivo) {
        arquivo = fopen("index_file.bin", "rb");
        if(!arquivo) {
            printf("Arquivo de índice não existente.\n");
            return;
        }
    }

    imprimir(root_addr);
}

void PrimaryTree::insert(int key, long data_addr) {
    bool has_new_root;
    int new_key;
    long key_data_offset, right_child_addr;
    Node node;

    has_new_root = inserir(this->root_addr, key, data_addr, new_key, key_data_offset, right_child_addr);

    if(has_new_root) {
        node.qtd_keys = 1;
        node.keys[0] = new_key;
        node.data_pointers[0] = key_data_offset;
        node.node_pointers[0] = root_addr;
        node.node_pointers[1] = right_child_addr;
        this->root_addr = write_node(node);
    }
}

bool PrimaryTree::inserir(long curr_node_addr, int key, long data_offset, int& up_key, long& up_data_offset, long& right_child_addr) {
    if(curr_node_addr == -1) {
        up_key = key;
        up_data_offset = data_offset;
        right_child_addr = -1;

        return true;
    }

    int position;
    bool has_new_key;
    Node node = get_node(curr_node_addr);

    if(find_key(node, key, position)) {
        cout << "chave duplicada \n";
        exit(1);
    }

    has_new_key = inserir(node.node_pointers[position], key, data_offset, up_key, up_data_offset, right_child_addr);

    if(has_new_key) {
        if(node.qtd_keys < MAX_KEYS) {

            add_key(node, up_key, up_data_offset, right_child_addr, position);
            write_node(node, curr_node_addr);

            has_new_key = false;

        } else {
            split(node, up_key, up_data_offset, right_child_addr, curr_node_addr);
            cout << up_key << " " << right_child_addr << endl;
            has_new_key = true;
        }
    }

    return has_new_key;
}

void PrimaryTree::create_index() {
    if(op_mode == 'w') arquivo = fopen("index_file.bin", "wb+");
    else if(op_mode == 'r') arquivo = fopen("index_file.bin", "rb");
    else exit(1);

    Node node;
    node = create_empty_node();
    this->root_addr = write_node(node);
}


void PrimaryTree::split(Node node, int &new_key, long &new_data_addr, long &new_key_right, long curr_node_addr) {
    int middle, start;
    int new_key_location;
    Node new_node;

    new_node = create_empty_node();

    middle = MAX_KEYS/2;

    if(new_key > node.keys[middle]) start = middle+1;
    else start = middle;
    int count = 0;
    for (int i = start; i < MAX_KEYS; i++) {
        add_key(new_node, node.keys[i], node.data_pointers[i], node.node_pointers[i+1], count);

        node.qtd_keys -= 1;
        count += 1;
    }
    new_node.node_pointers[0] = node.node_pointers[node.qtd_keys+1];

    if(start == middle+1) {
        find_key(new_node, new_key, new_key_location);
        add_key(new_node, new_key, new_data_addr, new_key_right, new_key_location);

    } else {
        find_key(node, new_key, new_key_location);
        add_key(node, new_key, new_data_addr, new_key_right, new_key_location);
    }

    new_key = node.keys[middle];
    new_data_addr = node.data_pointers[middle];

    node.keys[middle] = -1;
    node.data_pointers[middle] = -1;
    node.node_pointers[middle+1] = -1;

    node.qtd_keys -= 1;

    write_node(node, curr_node_addr);
    new_key_right = write_node(new_node);
}

long PrimaryTree::buscarChave(int key, int& count_blocks) {
    count_blocks = 0;
    return busca(key, root_addr, count_blocks);
}

long PrimaryTree::busca(int key, long curr_node_addr, int& count_blocks) {
    if(curr_node_addr == -1) return -1;

    int position;
    count_blocks += 1;
    Node node = get_node(curr_node_addr);
    if(find_key(node, key, position)) {
        return node.data_pointers[position];
    }

    return busca(key, node.node_pointers[position], count_blocks);
}

Node PrimaryTree::create_empty_node() {
    Node node;
    node.qtd_keys = 0;
    memset(node.keys, -1, MAX_KEYS*4);
    memset(node.data_pointers, -1, MAX_KEYS*8);
    memset(node.node_pointers, -1, (MAX_KEYS+1)*8);

    return node;
}

void PrimaryTree::add_key(Node& node, int key, long data_offset, long right_child_addr, int position) {
    int i;

    node.qtd_keys += 1;
    for (i = node.qtd_keys; i > position; i--) {
        node.keys[i] = node.keys[i-1];
        node.data_pointers[i] = node.data_pointers[i-1];
        node.node_pointers[i+1] = node.node_pointers[i];
    }

    node.keys[i] = key;
    node.data_pointers[i] = data_offset;
    node.node_pointers[i+1] = right_child_addr;
}

bool PrimaryTree::find_key(Node node, int key, int &index) {
    bool found = false;
    index = node.qtd_keys;

    for (index = 0; index < node.qtd_keys; index++) {
        if(key < node.keys[index]) {
            break;
        } else {
            if(key == node.keys[index]) {
                found = true;
                break;
            }
        }
    }
    return found;
}

Node PrimaryTree::get_node(long node_addr) {
    char bloco[BLOCK];
    Node node;

    fseek(arquivo, node_addr, SEEK_SET);
    fread(bloco, 1, BLOCK, arquivo);

    memcpy(&node, bloco, sizeof(Node));

    return node;
}

long PrimaryTree::write_node(Node node, long addr) {
    char bloco[BLOCK];

    memcpy(bloco, &node, sizeof(Node));
    if(addr != -1) {
        fseek(arquivo, addr, SEEK_SET);
    }
    else {
        fseek(arquivo, 0, SEEK_END);
        addr = ftell(arquivo);
    }

    fwrite(bloco, 1, BLOCK, arquivo);

    return addr;
}
