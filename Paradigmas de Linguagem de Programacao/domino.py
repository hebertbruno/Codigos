#!/usr/bin/env python
#coding: utf-8
#domino.py

import random
import sys
import copy

class Jogador:
    def __init__(self, name):
        self.nome = name
        self.mao = []
        self.pontos = 0


    def addPontos(self, ponto):
        self.pontos = self.pontos + ponto

class Mesa:

    def __init__(self, jogador1, jogador2, jogador3, jogador4):
        self.jogadores = [jogador1, jogador2, jogador3, jogador4]
        self.mesa = [[],[],[],[],[]]
        self.configuracao = [[], [], [], []]
        self.pedras = [(x, y) for x in range(0,7) for y in range(x,7,1)]
        self.status = ""
        self.posicao = None

    def alterarMesa(self, novaMesa):
        self.mesa = novaMesa

    def inicializarMaos(self):
        self.pedras = self.misturaPedras(self.pedras)
        for jogador in self.jogadores:
            for i in range(0,7):
                popPedra = random.choice(self.pedras)
                jogador.mao.append(popPedra)
                self.pedras.remove(popPedra)

    def posicaoJogador(self, jogadorNome):
        listaNomes = []
        for aux in self.jogadores:
            listaNomes.append(aux.nome)
        resposta = [i for i, j in enumerate(listaNomes) if j == jogadorNome]
        self.posicao = resposta[0]

    def inicioPartida(self):
        for jogador in self.jogadores:
            for m in jogador.mao:
                if m == (6,6):
                    self.posicaoJogador(str(jogador.nome))
                    self.mesa[0].append(m)
                    jogador.mao.remove(m)
                    self.statusJogo(str(jogador.nome) + " começou a partida!", str(0), str(0))

    def pontosNaMao(self):
        listaPedras = []
        listaSoma = []
        for aux in self.jogadores:
            for i in aux.mao:
                listaPedras.append(i)
        for j in listaPedras:
            listaSoma.append(sum(j))
        somaTotal = sum(listaSoma)
        resultado = somaTotal % 5
        return (somaTotal - resultado)

    def jogar(self, jogador):
        jogada = self.melhorJogada(self.jogadores[jogador].mao, self.mesa)
        if jogada[0] is not None:
            listaConfig = self.configuracoesAposJogar(jogada[1],self.mesa)
            self.alterarMesa(listaConfig[jogada[0]])
            self.jogadores[jogador].addPontos(jogada[2])
            self.jogadores[jogador].mao.remove(jogada[1])
            self.posicaoJogador(str(self.jogadores[jogador].nome))

            if self.jogadores[jogador].mao == []:
                print("--------------- FIM DE PARTIDA -----------------")
                print(str(self.jogadores[jogador].nome)+ " bateu!!")
                print("Pedra batida: "+str(jogada[1]))
                print("Pontos na mesa: "+ str(jogada[2]))
                print("Soma das pedras restantes: "+ str(self.pontosNaMao())+" pontos")
                self.jogadores[jogador].addPontos(self.pontosNaMao())
                print("Total de pontos: "+str(self.jogadores[jogador].pontos))
                print("------------------------------------------------")
                self.pontuacao()
                sys.exit()

            else:
                self.statusJogo(str(self.jogadores[jogador].nome + " jogou pedra " + str(jogada[1])),
                    str(jogada[2]),str(self.jogadores[jogador].pontos))

        else:
            self.posicaoJogador(str(self.jogadores[jogador].nome))
            self.statusJogo(str(self.jogadores[jogador].nome)+" passa!", str(0) , str(self.jogadores[jogador].pontos))

    def norte(self, mesa):
        return mesa[1]

    def sul(self, mesa):
        return mesa[3]

    def leste(self, mesa):
        return mesa[2]

    def oeste(self, mesa):
        return mesa[4]

    def primpedra(self, mesa):
        return mesa[0]

    def misturaPedras(self, lista):
        lista = random.sample(lista, len(lista))
        return lista

    def ponta(self, lista):#Entrada é um braço
        if lista == []:
            return (None,None)
        else:
            return lista[-1]

    def pontas(self, mesa1):
        #mesa = [x for x in mesa1 if x != []] #Remove as listas vazias
        pts = []
        for i in mesa1[1:]:
            pts.append(self.ponta(i))
        return pts

    def todasPontas(self, mesa1): #Retorna todas as pontas jogáveis
        mesa = [x for x in mesa1 if x != []] #Remove as listas vazias
        pts = []
        if len(mesa) == 2:
            for i in mesa1:
                pts.append(self.ponta(i))
        else:
            pts = self.pontas(mesa1)
        return pts

    def valorp(self, ponta):
        if(ponta[0] is None):
            return 0
        elif(ponta[0] == ponta[1]):
            return sum(ponta)
        else:
            return ponta[1]

    def pontosDomino(self, mesa1):
        soma = 0
        aux = self.todasPontas(mesa1)
        for i in aux:
            soma = soma + self.valorp(i)
        if(soma % 5 == 0):
            return soma
        else:
            return 0

    def configuracoesAposJogar(self, pedra, mesa): # Retorna as possibilidades de jogada para cada braço da mesa
        auxOriginal = []
        aux = []
        for i in range(4):
           aux.append(copy.deepcopy(mesa))

        # Inserir no norte da mesa
        mesaAux = copy.deepcopy(mesa)
        if pedra[0] == 6 and len(self.norte(mesaAux))==0:
            self.norte(mesaAux).append(pedra)
            auxOriginal.append(mesaAux)
        elif pedra[1] == 6 and len(self.norte(mesaAux))==0:
            pedraAux = reversed(pedra)
            self.norte(mesaAux).append(tuple(pedraAux))
            auxOriginal.append(mesaAux)
        elif pedra[0] == self.ponta(self.norte(mesaAux))[1]: #se for igual, inserir no norte
            self.norte(mesaAux).append(pedra)
            auxOriginal.append(mesaAux)
        elif pedra[1] == self.ponta(self.norte(mesaAux))[1]:
            pedraAux = reversed(pedra)
            self.norte(mesaAux).append(tuple(pedraAux))
            auxOriginal.append(mesaAux)
        else:
            auxOriginal.append(mesaAux)

        # Inserir no Leste da mesa
        mesaAux = copy.deepcopy(mesa)
        if pedra[0] == 6 and len(self.leste(mesaAux))==0:
            self.leste(mesaAux).append(pedra)
            auxOriginal.append(mesaAux)
        elif pedra[1] == 6 and len(self.leste(mesaAux))==0:
            pedraAux = reversed(pedra)
            self.leste(mesaAux).append(tuple(pedraAux))
            auxOriginal.append(mesaAux)
        elif pedra[0] == self.ponta(self.leste(mesaAux))[1]: #se for igual, inserir no leste
            self.leste(mesaAux).append(pedra)
            auxOriginal.append(mesaAux)
        elif pedra[1] == self.ponta(self.leste(mesaAux))[1]:
            pedraAux = reversed(pedra)
            self.leste(mesaAux).append(tuple(pedraAux))
            auxOriginal.append(mesaAux)
        else:
            auxOriginal.append(mesaAux)

        # Inserir no Sul da mesa
        mesaAux = copy.deepcopy(mesa)
        if pedra[0] == 6 and len(self.sul(mesaAux))==0:
            self.sul(mesaAux).append(pedra)
            auxOriginal.append(mesaAux)
        elif pedra[1] == 6 and len(self.sul(mesaAux))==0:
            pedraAux = reversed(pedra)
            self.sul(mesaAux).append(tuple(pedraAux))
            auxOriginal.append(mesaAux)
        elif pedra[0] == self.ponta(self.sul(mesaAux))[1]: #se for igual, inserir no sul
            self.sul(mesaAux).append(pedra)
            auxOriginal.append(mesaAux)
        elif pedra[1] == self.ponta(self.sul(mesaAux))[1]:
            pedraAux = reversed(pedra)
            self.sul(mesaAux).append(tuple(pedraAux))
            auxOriginal.append(mesaAux)
        else:
            auxOriginal.append(mesaAux)

        #Inserir no Oeste da mesa
        mesaAux = copy.deepcopy(mesa)
        if pedra[0] == 6 and len(self.oeste(mesaAux))==0:
            self.oeste(mesaAux).append(pedra)
            auxOriginal.append(mesaAux)
        elif pedra[1] == 6 and len(self.oeste(mesaAux))==0:
            pedraAux = reversed(pedra)
            self.oeste(mesaAux).append(tuple(pedraAux))
            auxOriginal.append(mesaAux)
        elif pedra[0] == self.ponta(self.oeste(mesaAux))[1]: #se for igual, inserir no sul
            self.oeste(mesaAux).append(pedra)
            auxOriginal.append(mesaAux)
        elif pedra[1] == self.ponta(self.oeste(mesaAux))[1]:
            pedraAux = reversed(pedra)
            self.oeste(mesaAux).append(tuple(pedraAux))
            auxOriginal.append(mesaAux)
        else:
            auxOriginal.append(mesaAux)

        if aux != auxOriginal:
            return auxOriginal
        else:
            return []

    def melhorConfiguracao(self, listaConfiguracoes,mesa): #A configuração que dar maior pontuação (posicao_configuracao, pontos)
        listaPontos = []
        if listaConfiguracoes != []:
            for aux in listaConfiguracoes:
                if aux == mesa:
                    listaPontos.append(None)
                else:
                    listaPontos.append(self.pontosDomino(aux))
            maiorPonto = max(listaPontos)
            listaMaioresPontos = [(i,maiorPonto) for i, j in enumerate(listaPontos) if j == maiorPonto]
            resultado = random.choice(listaMaioresPontos)
            return resultado
        else:
            return (None,None)

    def melhorJogada(self, listaPedras, mesa): # O resultado vai ser uma lista = [posicao_configuracao,pedra_usar,qtd_ponto_feito]
        listaAux = []
        listaOndeJogar = []
        for pedra in listaPedras:
            configuracoes = self.configuracoesAposJogar(pedra,mesa)
            listaAux.append(self.melhorConfiguracao(configuracoes,mesa))

        for aux in listaAux:
            listaOndeJogar.append(aux[1])

        maiorPonto = max(listaOndeJogar)
        listaMaioresPontos = [i for i, j in enumerate(listaOndeJogar) if j == maiorPonto]
        posicao = random.choice(listaMaioresPontos)
        tupleResultado = [listaAux[posicao][0],listaPedras[posicao],maiorPonto]

        return tupleResultado

    def imprimeMaos(self):
        print("-------------- Mãos ------------------")
        for i in range(4):
            print(str(self.jogadores[i].nome) + " = " + str(self.jogadores[i].mao))
        print("--------------------------------------")
    def pontuacao(self):
        print("-------------- Pontuacao ------------------")
        for i in range(4):
            print(str(self.jogadores[i].nome) + " : " + str(self.jogadores[i].pontos) + " pontos")
        print("-------------------------------------------")

    def statusJogo(self,mensagem, pontosFeitos, pontosTotais):
        index = self.posicao + 1
        if index > 3:
            index = 0
        jogador = str(self.jogadores[index].nome)
        self.status = """\n---------------- Status Jogo ----------------\n\n""" + mensagem + """\nPontos na mesa: """ + pontosFeitos + """\nTotal de pontos: """+ pontosTotais +"""\n\nPróximo jogador: """+jogador+"""\n\n---------------------------------------------"""

print("----- Bem vindo ao melhor simulador de dominó do mundo -----")
print("Escolha o nome dos jogadores: ")
namej1 = raw_input("Jogador 01: ")
namej2 = raw_input("Jogador 02: ")
namej3 = raw_input("Jogador 03: ")
namej4 = raw_input("Jogador 04: ")
jogador1 = Jogador(str(namej1))
jogador2 = Jogador(str(namej2))
jogador3 = Jogador(str(namej3))
jogador4 = Jogador(str(namej4))

domino = Mesa(jogador1, jogador2, jogador3, jogador4)

domino.inicializarMaos()

domino.inicioPartida()

print("Escolha as opções abaixo:\n\n")
while True:
    print("---------- Mesa ------------")
    print("\n")
    print(domino.mesa)
    print("\n")
    print("----------------------------")
    print(domino.status)
    print("\nOpções do jogo:\n")
    print("1 - Jogar proximo jogador")
    print("2 - Imprimir maos")
    print("3 - Imprimir Norte/Sul/Leste/Oeste")
    print("4 - Imprimir mesa")
    print("5 - Pontuacao")
    print("0 - Sair")
    opcao = int(raw_input("Resposta: "))
    if opcao == 4:
        print(domino.mesa)
        print("\n")
    elif opcao == 3:
        op = raw_input("N - Norte, S - Sul, L - Leste ou O - Oeste: ")
        if op == 'N' or op == 'n':
            print(domino.norte(domino.mesa))
            print("\n")
        if op == 'S' or op == 's':
            print(domino.sul(domino.mesa))
            print("\n")
        if op == 'L' or op == 'l':
            print(domino.leste(domino.mesa))
            print("\n")
        if op == 'O' or op == 'o':
            print(domino.oeste(domino.mesa))
            print("\n")
    elif opcao == 2:
        domino.imprimeMaos()
        print("\n")
    elif opcao == 1:
        index = domino.posicao+1
        if index > 3:
            index = 0
        domino.jogar(index)
        print("\n")
    elif opcao == 5:
        domino.pontuacao()
    elif opcao == 0:
        sys.exit("Ok! Saindo")
