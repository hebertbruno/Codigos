 
/////////////////////////////////          
// teapot.cpp
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
static float Xangle = 0.0, Yangle = 0.0, Zangle = 0.0; // Angulos de rotacao.


void drawScene(void)
{
   glClear(GL_COLOR_BUFFER_BIT);
   glColor3f(0.0, 0.0, 0.0);
   glLoadIdentity(); 
   
   glTranslatef(0.0, 0.0, -15.0);

    glRotatef(Zangle, 0.0, 0.0, 1.0);
   glRotatef(Yangle, 0.0, 1.0, 0.0);
   glRotatef(Xangle, 1.0, 0.0, 0.0);
  
   
   glScalef(1.0, 1.0, 1.0);
   
   glutWireTeapot(5.0); // Teapot.

   glFlush();
}

void setup(void) 
{
   glClearColor(1.0, 1.0, 1.0, 0.0);  
}

void resize(int w, int h)
{
   glViewport(0, 0, w, h); 
   glMatrixMode(GL_PROJECTION);
   glLoadIdentity();
   glFrustum(-5.0, 5.0, -5.0, 5.0, 5.0, 100.0);

   glMatrixMode(GL_MODELVIEW);
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

void printInteraction(void)
{
   cout << "Interacao:" << endl;
   cout << "Pressione x, X, y, Y, z, Z para virar o Teapot." << endl;
}

int main(int argc, char **argv) 
{
   printInteraction();
   glutInit(&argc, argv);


   glutInitDisplayMode(GLUT_SINGLE | GLUT_RGBA); 
   glutInitWindowSize(500, 500);
   glutInitWindowPosition(100, 100); 
   glutCreateWindow("Teapot.cpp");
   glutDisplayFunc(drawScene); 
   glutReshapeFunc(resize);  
   glutKeyboardFunc(keyInput);

   
   setup(); 

   glutMainLoop(); 
}
