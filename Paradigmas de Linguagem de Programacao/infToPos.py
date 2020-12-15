#!/usr/bin/env python
#coding: utf-8
#infToPos.py

# Bruno Hebert Rabelo de Souza
# Wesley Monteiro Carneiro

import sys

class Stack:

    def __init__(self, itemlist=[]):
        self.items = itemlist

    def isEmpty(self):
        if self.items == []:
            return True
        else:
            return False

    def peek(self):
        return self.items[-1:][0]

    def pop(self):
        return self.items.pop()

    def push(self, item):
        self.items.append(item)
        return 0

def infixToPostfix(infixexpr):
    prec = {}
    prec["^"] = 4
    prec["*"] = 3
    prec["/"] = 3
    prec["+"] = 2
    prec["-"] = 2
    prec["("] = 1
    opStack = Stack()
    listNumber = []
    postfixList = []
    tokenList = " ".join(infixexpr).split()

    for token in tokenList:
        if token in "ABCDEFGHIJKLMNOPQRSTUVWXYZ" or token in "0123456789":
            listNumber.append(token)
            #postfixList.append(token)
        elif token == '(':
            opStack.push(token)
        elif token == ')':
            aux = ''.join(str(e) for e in listNumber)
            postfixList.append(aux)
            listNumber = []
            topToken = opStack.pop()
            while topToken != '(':
                postfixList.append(topToken)
                topToken = opStack.pop()
        else:
            aux = ''.join(str(e) for e in listNumber)
            postfixList.append(aux)
            while (not opStack.isEmpty()) and (prec[opStack.peek()] >= prec[token]):
                  postfixList.append(opStack.pop())
            opStack.push(token)
            listNumber = []

    aux = ''.join(str(e) for e in listNumber)
    postfixList.append(aux) 

    while not opStack.isEmpty():
        postfixList.append(opStack.pop())
    return " ".join(postfixList)

def is_number(s):
    try:
        float(s)
        return True
    except ValueError:
        pass

    try:
        import unicodedata
        unicodedata.numeric(s)
        return True
    except (TypeError, ValueError):
        pass

    return False

def pot(x, n):
    if n == 0:
        return 1
    y = pot(x, n/2)
    if n % 2:
        return y * y * x
    else:
        return y * y

def eval_postfix(text):
#	prec = {"*","-","+","/"}
    pilha = Stack()
    s = text.split(' ')
    plus = None
    for symbol in s:
    #    if symbol in "ABCDEFGHIJKLMNOPQRSTUVWXYZ":
    #        print("A expressão não é calculável")
    #        sys.exit()
        if is_number(symbol):
            pilha.push(int(symbol))
        elif symbol == "+":
            plus = pilha.pop() + pilha.pop()
            pilha.push(plus)
        elif symbol == "-":
            plus = pilha.pop() - pilha.pop()
            pilha.push(plus*(-1))
        elif symbol == "*":
            plus = pilha.pop() * pilha.pop()
            pilha.push(plus)
        elif symbol == "/":
            divisor = pilha.pop()
            dividendo = pilha.pop()
            plus = dividendo / divisor
            pilha.push(plus)
        elif symbol == "^":
            expoente = pilha.pop()
            base = pilha.pop()
            plus = pot(base,expoente) 
            pilha.push(plus)
        
    return pilha.pop()

entrada = raw_input("Entre com uma expressão: ")
print("Conversão de infixa para posfixa: ")
resultado = infixToPostfix(entrada)
print(resultado)
print("Resolvendo expressão: ")
print(eval_postfix(resultado))
