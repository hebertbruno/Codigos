/////////////////////////////////          
// 
// cubocolorido.cpp
// Aluno: Bruno Hebert 21200462
// 
// Introduçao a Computaçao Grafica
// 
/////////////////////////////////
#include <stdio.h>
#include <stdarg.h>
#include <math.h>
#define GL_GLEXT_PROTOTYPES
#include <iostream>

#ifdef __APPLE__
#  include <GLUT/glut.h>
#else
#  include <GL/glut.h>
#endif


using namespace std;

static int objID = 1; // Object ID.
static float Xangle = 0.0, Yangle = 0.0, Zangle = 0.0; // Angulos de rotacao.


void drawScene(void)
{
  glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);
 
  glLoadIdentity();
 
  
  glRotatef(Zangle, 0.0, 0.0, 1.0);
   glRotatef(Yangle, 0.0, 1.0, 0.0);
   glRotatef(Xangle, 1.0, 0.0, 0.0);
  
  glBegin(GL_POLYGON);
 
  glColor3f( 1.0, 0.0, 0.0 );     glVertex3f(  0.5, -0.5, -0.5 );      // P1 é vermelho
  glColor3f( 0.0, 1.0, 0.0 );     glVertex3f(  0.5,  0.5, -0.5 );      // P2 é verde
  glColor3f( 0.0, 0.0, 1.0 );     glVertex3f( -0.5,  0.5, -0.5 );      // P3 é azul
  glColor3f( 1.0, 0.0, 1.0 );     glVertex3f( -0.5, -0.5, -0.5 );      // P4 é roxo
 
  glEnd();
 
  glBegin(GL_POLYGON);
  glColor3f(   1.0,  1.0, 1.0 );
  glVertex3f(  0.5, -0.5, 0.5 );
  glVertex3f(  0.5,  0.5, 0.5 );
  glVertex3f( -0.5,  0.5, 0.5 );
  glVertex3f( -0.5, -0.5, 0.5 );
  glEnd();
 
  glBegin(GL_POLYGON);
  glColor3f(  1.0,  0.0,  1.0 );
  glVertex3f( 0.5, -0.5, -0.5 );
  glVertex3f( 0.5,  0.5, -0.5 );
  glVertex3f( 0.5,  0.5,  0.5 );
  glVertex3f( 0.5, -0.5,  0.5 );
  glEnd();
 
   glBegin(GL_POLYGON);
  glColor3f(   0.0,  1.0,  0.0 );
  glVertex3f( -0.5, -0.5, 0.5 );
  glVertex3f( -0.5,  0.5,  0.5 );
  glVertex3f( -0.5,  0.5, -0.5 );
  glVertex3f( -0.5, -0.5, -0.5 );
  glEnd();
 
  glBegin(GL_POLYGON);
  glColor3f(   0.0,  0.0,  1.0 );
  glVertex3f(  0.5,  0.5,  0.5 );
  glVertex3f(  0.5,  0.5, -0.5 );
  glVertex3f( -0.5,  0.5, -0.5 );
  glVertex3f( -0.5,  0.5,  0.5 );
  glEnd();
 
  glBegin(GL_POLYGON);
  glColor3f(   1.0,  0.0,  0.0 );
  glVertex3f(  0.5, -0.5, -0.5 );
  glVertex3f(  0.5, -0.5,  0.5 );
  glVertex3f( -0.5, -0.5, 0.5 );
  glVertex3f( -0.5, -0.5, -0.5 );
  glEnd();
 
  glFlush();
  glutSwapBuffers();
}


void keyInput(unsigned char key, int x, int y)
{
  
   switch(key) 
   {
      case 27:
         exit(0);
         break;
      case 'x':
         Xangle += 5.0;
       if (Xangle > 360.0) Xangle -= 360.0;
         glutPostRedisplay();
         break;
      case 'X':
         Xangle -= 5.0;
       if (Xangle < 0.0) Xangle += 360.0;
         glutPostRedisplay();
         break;
      case 'y':
         Yangle += 5.0;
       if (Yangle > 360.0) Yangle -= 360.0;
         glutPostRedisplay();
         break;
      case 'Y':
         Yangle -= 5.0;
       if (Yangle < 0.0) Yangle += 360.0;
         glutPostRedisplay();
         break;
      case 'z':
         Zangle += 5.0;
       if (Zangle > 360.0) Zangle -= 360.0;
         glutPostRedisplay();
         break;
      case 'Z':
         Zangle -= 5.0;
       if (Zangle < 0.0) Zangle += 360.0;
         glutPostRedisplay();
         break;
      default:
         break;
   }
}

void specialKeyInput(int key, int x, int y)
{
   
   if(key == GLUT_KEY_LEFT)
   {
      if (objID > 1) objID--;
      else objID = 2;
   }

   if(key == GLUT_KEY_RIGHT)
   {
      if (objID < 1) objID++;
      else objID = 2;
   }

   Xangle = Yangle = Zangle = 0.0;
   glutPostRedisplay();
}

void printInteraction(void)
{
   cout << "Interacao:" << endl;
   cout << "Pressione x, X, y, Y, z, Z virar o cubo." << endl;
}

int main(int argc, char **argv) 
{
    printInteraction();
   glutInit(&argc, argv);


   glutInitDisplayMode(GLUT_SINGLE | GLUT_RGBA); 
   glutInitWindowSize(500, 500);
   glutInitWindowPosition(100, 100); 
   glutCreateWindow("Cubo.cpp");
   glutDisplayFunc(drawScene); 
   glutKeyboardFunc(keyInput);
   glutSpecialFunc(specialKeyInput);
  

   

   glutMainLoop(); 
}
