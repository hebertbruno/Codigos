all: artigo

artigo: upload findrec

upload: upload.o hashing.o primarytree.o tokenizer.o
	g++ upload.o hashing.o primarytree.o tokenizer.o -o upload -lboost_regex

findrec: findrec.o hashing.o
	g++ findrec.o hashing.o -o findrec

#seek1: seek1.o hashing.o
#	g++ seek1.o hashing.o intbtree.o -o seek1

#seek1.o: seek1.cpp
#	g++ -c seek1.cpp -std=c++1y -g

findrec.o: findrec.cpp
	g++ -c findrec.cpp -std=c++1y -g

upload.o: upload.cpp
	g++ -c upload.cpp -std=c++1y -g

hashing.o: hashing.cpp
	g++ -c hashing.cpp -std=c++1y -g

intbtree.o: primarytree.cpp
	g++ -c primarytree.cpp -std=c++1y -g

tokenizer.o: tokenizer.cpp
	g++ -c tokenizer.cpp -std=c++1y -g

