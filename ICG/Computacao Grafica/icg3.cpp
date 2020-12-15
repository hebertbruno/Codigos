/*
Alunos: Antonio Raggid
		Bruno Hebert

		Algoritmo para desenhar poligonos abertos, usando bresenham ou dda com opção de linha de tamanho dobrado ou nao

*/

#include <iostream>
#include <stdlib.h>
#include <math.h>
using namespace std;


char frame[100][160];

void setPixel (int x, int y){
	frame[x][y] = '*';

}

void offPixel (int x, int y)
{
	for (int i = 0; i < 100; i++) {
    for (int j = 0; j < 160; j++) {
      frame[i][j] = '_';
    }
  }
}


void LineDDA (int x0, int y0, int x1, int y1){
	int x,y;
	float length;
	float dx=abs(x1-x0);
	float dy=abs(y1-y0);
	if (dx >= dy){
   		length = dx;
   	}
   	else{
   		length = dy;
   	}
	dx = (x1-x0)/length;
	dy = (y1-y0)/length;
	x = x0 + 0.5;  /*  0.5 para arredondar os valores */
	y = y0 + 0.5;  
	int i = 1;         /* inicializa contador */
	while(i <= length){
	    setPixel(x,y);
   		x = x + dx;
    	y = y + dy;
    	i = i + 1;
    }

}


void bresenhamAlg (int x0, int y0, int x1, int y1, int l)
{
	int lin=l;
	int dx = abs (x1 - x0);
	int dy = abs (y1 - y0);
	int x, y;
	
		if (dx >= dy){ 
			int e = (dy << 1) - dx;
			int inc1 = dy << 1;
			int inc2 = (dy - dx) << 1;	
	
			if (x0 < x1) { // define ponto esquerdo para ponto de partida
				x = x0;
				y = y0;
			}
			else { // Troque o ponto direito para o ponto inicial e o ponto esquerdo até o ponto final
				x = x1;			
				y = y1;
				x1 = x0;
				y1 = y0;
			}
			setPixel (x, y); // ativar pixel
			if (lin==1)
			{
				setPixel (x+1, y);
			}
			
			while (x < x1) {
				if (e < 0)
					e += inc1;
				else {
					if (y < y1) {
						y++;
						e += inc2;
					} else {
						y--;
						e += inc2;
					}
				}
				x++;
				setPixel (x, y);
				if (lin==1){
					setPixel (x+1, y);
				}
			}
		} 
		else { 
			int e = (dx << 1) - dy;
			int inc1 = dx << 1;
			int inc2 = (dx - dy) << 1;
			if (y0 < y1) { // Defina o ponto esquerdo para o ponto de partida
				x = x0;
				y = y0;
			}
			else { // Troque o ponto direito para o ponto inicial e o ponto esquerdo até o ponto final
				x = x1;			
				y = y1;
				y1 = y0;
				x1 = x0;
			}
			setPixel (x, y); // ativar pixel
			if (lin==1){
				setPixel (x+1, y);
			}
			while (y < y1) {
				if (e < 0)
					e += inc1;
				else {
					if (x > x1){
						x--;
						e += inc2;
					} else {
						x++;
						e += inc2;
					}
				}
				y++;
				setPixel (x, y);
				if (lin==1){
					setPixel (x+1, y);
				}
			}
		}
	}
	


int main(){
	int input, x0, y0, x1, y1,lin, loop,opt=100;
  	char ch;

  	
	cout << "quantas linhas serao traçadas?" << endl;
	cin >> loop;
	cout << "escolha o metodo: 1 - dda/ 2 - bresenham" << endl;
  	cin >> input;
  	cout << "Pixels com linha dobrada? 1 - sim/ 0 - nao" << endl;
  	cin >> lin;

  	for (int i = 0; i < loop; ++i)
  	{
  		
  		cout << "De as coordenadas iniciais" << endl;
  		cin >> x0 >> y0;

  		cout << "De as coordenadas finais" << endl;
  		cin >> x1 >> y1;
  		if (input==1)
  		{
  			LineDDA(x0,y0,x1,y1);
  		}
  		else{
  			bresenhamAlg(x0,y0,x1,y1,lin);
  		}
  		
  	}
  	
  	for (int i = 0; i < 100; ++i)
  	{
  		for (int j = 0; j < 160; ++j)
  		{
  			frame[i][j] = '_';
  		}
  	}

  	
  	
  
  for (int i = 0; i < 50; ++i)
  {
  	for (int j = 0; j < 50; ++j)
  	{
  		if (frame[i][j]=='*')
  		{
  			cout << frame[i][j] << " ";
  		}
  		else
  			cout << "_" << " ";
  		
  	}
  	cout << endl;  
  }

  /*while(opt != 0){
    cout << "Escolha uma opção" << endl;
    cout << "1 - Put" << endl;
    cout << "2 - Get" << endl;
    cout << "3 - Clear" << endl;
    cout << "0 - Sair" << endl;
    cin >> opt;

    switch (opt) {
      case 1:
        cout << "Indique a posição a plotar" << endl;
        cin >> x >> y;
        putPixel(x,y,'*');
        break;
      case 2:
        cout << "Indique a posição a recuperar" << endl;
        cin >> x >> y;
        ch = getPixel(x,y);
        cout << "O pixel contém: " << ch << endl;
        break;
      case 3:
        clearBuffer();
        break;
      case 0:
        break;
    }
  }*/

  return 0;
}
