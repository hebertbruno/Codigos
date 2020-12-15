#include <GL/glut.h>


// função para desenhar um triângulo
void bico()
{

// define a cor verde para o desenho do bico
	glColor3f(0, 1, 0);

// desenha um triângulo

	glBegin(GL_TRIANGLES);
	glVertex3f(3.0,6.0,0);
	glVertex3f(4.0,8.0,0);
	glVertex3f(5.0,6.0,0);
	glEnd();

}

// função para desenhar um quadrado
void corpo()
{
// define a cor azul para o desenho do quadrado
	glColor3f(0, 0, 1);

// desenha um quadrado
	glBegin(GL_QUADS);
	glVertex3f(3.0,1.0,0); // canto esquerdo inferior
	
	glVertex3f(5.0,1.0,0); // canto esquerdo superior
	
	glVertex3f(5.0,6.0,0); // canto direito superior
	
	glVertex3f(3.0,6.0,0); // canto direito inferior
	
	
	glEnd();

}

// função para desenhar um triangulo
void asaesquerda()
{

// define a cor vermelho para o desenho do retangulo
	glColor3f(1, 0, 0);

// desenha um triangulo
	glBegin(GL_TRIANGLES);
	glVertex3f(1.5, 1.0, 0);
	glVertex3f(3.0, 1.0, 0);
	glVertex3f(3.0, 3.0, 0);
	glEnd();

}
// função para desenhar um triangulo
void asadireita()
{

// define a cor vermelho para o desenho do retangulo
	glColor3f(1, 0, 0);

// desenha um triangulo
	glBegin(GL_TRIANGLES);
	glVertex3f(5.0, 1.0, 0);
	glVertex3f(6.5, 1.0, 0);
	glVertex3f(5.0, 3.0, 0);
	glEnd();

}


void desenha_foguete(){
	glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
	glClear(GL_COLOR_BUFFER_BIT);
	
	glPushMatrix(); // guardo a matriz original
	glTranslatef(3.0f, 2.0f, 0.0f); // efetuo a tranlaçao
	bico(); // mostro o objeto bico aṕos translaçao

	glPopMatrix(); // retorno a matrix original
	bico(); // mostro o objeto bico na posiçao original

	glPushMatrix(); // guardo a matriz original
	glRotatef(20, 0.0f, 0.0f, 1.0f); // efetuo rotaçao
	corpo(); // mostro o objeto corpo apos rotaçao

	glPopMatrix(); // retorno a matrix original
	glScalef(0.5f, 0.5f, 1.0f); //diminuo a escala e apresento
		bico(); 				// o objeto original
		corpo();
		asaesquerda();
		asadireita();
	
	glFlush();

}

// função responsável por inicializar parâmetros e variáveis
void inicializar()
{
// define a janela de visualização 2D
	glMatrixMode(GL_PROJECTION);
	gluOrtho2D(0, 10.0, 0, 10.0);
	glMatrixMode(GL_MODELVIEW);
}

int main(int argc, char *argv[])
{
	glutInit(&argc, argv);
// define do modo de operação da GLUT
	glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);

// especifica o tamanho inicial em pixels da janela GLUT
	glutInitWindowSize(400, 400);

// cria a janela passando como argumento o título
	glutCreateWindow("Foguete");

// registra a função callback de redesenho da janela de visualização
	glutDisplayFunc(desenha_foguete);

// chama a função responsável por fazer as inicializações
	inicializar();

// loop de processamento de eventos GLUT
	glutMainLoop();

	return 0;
}
