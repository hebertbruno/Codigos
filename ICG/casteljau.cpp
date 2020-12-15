 
/////////////////////////////////          
// casteljau.cpp
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

static float u = 0.0; // Parametro da curva.
static char theStringBuffer[10]; 
static long font = (long)GLUT_BITMAP_8_BY_13; 

// pontos de controle.
static float ctrlpoints[3][3] = 
{
   { -40.0, -20.0, 0.0}, { 0.0, 40.0, 0.0}, {40.0, -20.0, 0.0}
};

// Rotina para desenhar uma cadeia de caracteres de bitmap
void writeBitmapString(void *font, char *string)
{  
   char *c;

   for (c = string; *c != '\0'; c++) glutBitmapCharacter(font, *c);
}

// Rotina para converter ponto flutuante em string char.
void floatToString(char * destStr, int precision, float val) 
{
   sprintf(destStr,"%f",val);
   destStr[precision] = '\0';
} 

// Inicializa rotina.
void setup(void)
{
   glClearColor(1.0, 1.0, 1.0, 0.0); 
}

// funcao desenha.
void drawScene(void)
{
   int i;

   glClear(GL_COLOR_BUFFER_BIT);

   // Escreva valor de u.
   glColor3f(0.0, 0.0, 0.0);
   floatToString(theStringBuffer, 4, u);
   glRasterPos3f(-40.0, 40.0, 0.0);
   writeBitmapString((void*)font, "u = ");
   writeBitmapString((void*)font, theStringBuffer);

   // A curva é desenhada de 0 até o valor do parametro.
   glColor3f(1.0, 0.0, 1.0);
   glLineWidth(2.0);
   glMap1f(GL_MAP1_VERTEX_3, 0.0, 1.0, 3, 3, &ctrlpoints[0][0]);
   glEnable(GL_MAP1_VERTEX_3);
   glMapGrid1f(100, 0.0, 1.0);
   glEvalMesh1(GL_LINE, 0, (int)(u*100));
   glLineWidth(1.0);

   glPointSize(5.0);
   glColor3f(0.0, 1.0, 0.0);
   glBegin(GL_POINTS);
      for (i = 0; i < 3; i++) 
         glVertex3fv(&ctrlpoints[i][0]);
   glEnd();

   // Linha que une os pontos de controle.
   glColor3f(0.0, 0.0, 0.0);
   glBegin(GL_LINE_STRIP);
      for (i = 0; i < 3; i++) 
         glVertex3fv(&ctrlpoints[i][0]);
   glEnd();

   // A interpolação de pontos entre os dois primeiros pontos de controle.
   glMap1f(GL_MAP1_VERTEX_3, 0.0, 1.0, 3, 2, &ctrlpoints[0][0]);
   glEnable(GL_MAP1_VERTEX_3);
   glColor3f(1.0, 0.0, 0.0);
   glBegin(GL_POINTS);
      glEvalCoord1f(u);
   glEnd();

   // Interpolação do ponto entre os dois últimos pontos de controle.
   glMap1f(GL_MAP1_VERTEX_3, 0.0, 1.0, 3, 2, &ctrlpoints[1][0]);
   glEnable(GL_MAP1_VERTEX_3);
   glColor3f(0.0, 1.0, 0.0);
   glBegin(GL_POINTS);
      glEvalCoord1f(u);
   glEnd();

   // A linha que une os dois pontos desenhados acima.
   glColor3f(0.0, 1.0, 0.0);
   glBegin(GL_LINES);
      glVertex3f( (1-u) * ctrlpoints[0][0] + u * ctrlpoints[1][0],
                (1-u) * ctrlpoints[0][1] + u * ctrlpoints[1][1],
               0.0 );
      glVertex3f( (1-u) * ctrlpoints[1][0] + u * ctrlpoints[2][0],
                (1-u) * ctrlpoints[1][1] + u * ctrlpoints[2][1],
               0.0 );
   glEnd();

   // A linha de interpolacao dos dois pontos desenhados acima.
   glColor3f(1.0, 0.0, 0.0);
   glMap1f(GL_MAP1_VERTEX_3, 0.0, 1.0, 3, 3, &ctrlpoints[0][0]);
   glEnable(GL_MAP1_VERTEX_3);
   glColor3f(0.0, 0.0, 1.0);
   glBegin(GL_POINTS);
      glEvalCoord1f(u);
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
     default:
        break;
   }
}

void specialKeyInput(int key, int x, int y)
{
   if(key == GLUT_KEY_LEFT)
      if ( u > 0.02 ) u-= 0.02;
   if(key == GLUT_KEY_RIGHT)
      if ( u < 0.98 ) u += 0.02;
   glutPostRedisplay();
}

void printInteraction(void)
{
   cout << "Interacao:" << endl;
   cout << "Pressione  left/right para aumentar/ diminuir o parametro da curva"<< endl;
}

int main(int argc, char **argv) 
{
   printInteraction();
   glutInit(&argc, argv);
 
   glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGBA); 
   glutInitWindowSize(500, 500);
   glutInitWindowPosition(100, 100); 
   glutCreateWindow("deCasteljau.cpp");
   glutDisplayFunc(drawScene); 
   glutReshapeFunc(resize);  
   glutKeyboardFunc(keyInput);
   glutSpecialFunc(specialKeyInput);

   
   setup(); 

   glutMainLoop(); 
}