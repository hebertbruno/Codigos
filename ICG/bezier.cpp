 
/////////////////////////////////          
// bezier.cpp
// Aluno: Bruno Hebert 21200462
// 
// Introduçao a Computaçao Grafica
// 
/////////////////////////////////


#include <iostream>

#ifdef __APPLE__
#  include <GLUT/glut.h>
#else
#  include <GL/glut.h>
#endif


using namespace std;

static int numVal = 0;


// Pontos de controle da curva.
static float controlPoints2[4][3] = {
	{ -40.0, -10.0, 0.0}, { 40.0, 10.0, 0.0}, 
	{-40.0, 10.0, 0.0}, {40.0, -10.0, 0.0}};

void setup(void)
{
   glClearColor(1.0, 1.0, 1.0, 0.0); 
}

void drawScene(void)
{
   int i;

   glClear(GL_COLOR_BUFFER_BIT);

   // Desenha curva em azul.
   glMap1f(GL_MAP1_VERTEX_3, 0.0, 1.0, 3, 4, controlPoints2[0]);
   glEnable(GL_MAP1_VERTEX_3);
   glColor3f(0.0, 0.0, 1.0);
   glBegin(GL_LINE_STRIP);
      for (i = 0; i <= 30; i++) 
         glEvalCoord1f( (float)i/30.0 );
   glEnd();

 
   glPointSize(5.0);
   glBegin(GL_POINTS);


   // pontos de controle.
   glColor3f(0.0, 0.0, 1.0);
   for (i = 0; i < 4; i++) 
      glVertex3fv(controlPoints2[i]);

   // seleciona o ponto.
   glColor3f(1.0, 0.0, 0.0);
   glVertex3fv(controlPoints2[numVal]);     
   
   glEnd();
   // Poligono de controle.
   glColor3f(0.0, 0.0, 1.0);
   glBegin(GL_LINE_STRIP);
   for (i = 0; i < 4; i++) 
	  glVertex3fv(controlPoints2[i]);
   glEnd();

   glutSwapBuffers();
}

void resize(int w, int h)
{
   glViewport(0, 0, w, h);
   glMatrixMode(GL_PROJECTION);
   glLoadIdentity();
   glOrtho(-50.0, 50.0, -50.0, 50.0, -1.0, 1.0);
   glMatrixMode(GL_MODELVIEW);
   glLoadIdentity();
}

void keyInput(unsigned char key, int x, int y)
{
   switch (key) 
   {
      case 27:
         exit(0);
         break;
      case ' ':
	     if (numVal < 3) numVal++;
	     else numVal = 0;
         glutPostRedisplay();
      default:
         break;
   }
}

void specialKeyInput(int key, int x, int y)
{
   if(key == GLUT_KEY_UP) controlPoints2[numVal][1] += 1.0;
   if(key == GLUT_KEY_DOWN) controlPoints2[numVal][1] -= 1.0;
   if(key == GLUT_KEY_LEFT) controlPoints2[numVal][0] -= 1.0;
   if(key == GLUT_KEY_RIGHT) controlPoints2[numVal][0] += 1.0;
   glutPostRedisplay();
}

void printInteraction(void)
{
   cout << "Interacao:" << endl;
   cout << "Pressione space para selecionar o ponto da curva de Bezier." << endl
        << "Pressione as setas para mover o ponto selecionado." << endl; 
}

int main(int argc, char **argv) 
{
   printInteraction();
   glutInit(&argc, argv);

 
   glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGBA); 
   glutInitWindowSize(500, 500);
   glutInitWindowPosition(100, 100); 
   glutCreateWindow("Bezier.cpp");
   glutDisplayFunc(drawScene); 
   glutReshapeFunc(resize);  
   glutKeyboardFunc(keyInput);
   glutSpecialFunc(specialKeyInput);

   
   setup(); 

   glutMainLoop();  
}