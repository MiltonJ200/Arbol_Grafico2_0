
package arbolgrafico2_0;

  /**
 *
 * @author ploks
 */

import java.awt.*;
import java.util.*;
import javax.swing.*;


public class TreeExpresion extends JPanel 
{
    //private Arbol miArbol;
    private TreedeExpreisiones miArbol;
    private HashMap posicionNodos = null;
    private HashMap subtreeSizes = null;
    private boolean dirty = true;
    private int parent2child = 20, child2child = 30;
    private Dimension empty = new Dimension(0,0);
    private FontMetrics fm = null;
    
    
    /**
     * Constructor de la clase ArbolExpresionGrafico.
     * El constructor permite inicializar los atributos de la clase ArbolExpresionGrafico
     * y llama al método repaint(), que es el encargado de pintar el Arbol.
     * @param miExpresion: dato de tipo ArbolExpresion que contiene el Arbol a
     * dibujar.
     */
    /*
    public TreeExpresion(Arbol miArbol) 
    {
          this.miArbol = miArbol;
          this.setBackground(Color.WHITE);
          posicionNodos = new HashMap();
          subtreeSizes = new HashMap();
          dirty = true;
          repaint();      
    }
    */
 public TreeExpresion( TreedeExpreisiones miArbol) 
    {
          this.miArbol = miArbol;
          this.setBackground(Color.WHITE);
          posicionNodos = new HashMap();
          subtreeSizes = new HashMap();
          dirty = true;
          repaint();      
    }

    /**
     * Calcula las posiciones de los respectivos subárboles y de cada nodo que 
     * forma parte de ese subárbol, para conocer en que posición van a ir dibujados
     * los rectángulos representativos del árbol de la expresión.
     */
    private void calcularPosiciones() 
    {
         posicionNodos.clear();
         subtreeSizes.clear();
         // Nodo root = this.miArbol.getRaiz();
         Nodo1 root = this.miArbol.getRaiz();
         if (root != null) 
         {
             calcularTamañoSubarbol(root);
             calcularPosicion(root, Integer.MAX_VALUE, Integer.MAX_VALUE, 0);
         }
    }

     private Dimension calcularTamañoSubarbol(Nodo1 n) 
    {
          if (n == null) 
              return new Dimension(0,0);
 
          Dimension ld = calcularTamañoSubarbol(n.getNodoIzquierdo());
          Dimension rd = calcularTamañoSubarbol(n.getNodoDerecho());
          
          int h = fm.getHeight() + parent2child + Math.max(ld.height, rd.height);
          int w = ld.width + child2child + rd.width;
          
          Dimension d = new Dimension(w, h);
          subtreeSizes.put(n, d);
          
          return d;
    }

     private void calcularPosicion(Nodo1 n, int left, int right, int top) 
    {
      if (n == null) 
          return;
      
      Dimension ld = (Dimension) subtreeSizes.get(n.getNodoIzquierdo());
      if (ld == null) 
          ld = empty;
      
      Dimension rd = (Dimension) subtreeSizes.get(n.getNodoDerecho());
      if (rd == null) 
          rd = empty;
      
      int center = 0;
      
      if (right != Integer.MAX_VALUE)
          center = right - rd.width - child2child/2;
      else if (left != Integer.MAX_VALUE)
          center = left + ld.width + child2child/2;
      int width = fm.stringWidth(n.getInformacion()+"");
 
      posicionNodos.put(n,new Rectangle(center - width/2 - 3, top, width + 6, fm.getHeight()));
      
      calcularPosicion(n.getNodoIzquierdo(), Integer.MAX_VALUE, center - child2child/2, top + fm.getHeight() + parent2child);
      calcularPosicion(n.getNodoDerecho(), center + child2child/2, Integer.MAX_VALUE, top + fm.getHeight() + parent2child);
    }
    
    

private void dibujarArbol(Graphics2D g, Nodo1 n, int puntox, int puntoy, int yoffs) 
    {
     if (n == null) 
         return;
     
     Rectangle r = (Rectangle) posicionNodos.get(n);
     g.draw(r);
     g.drawString(n.getInformacion()+"", r.x + 3, r.y + yoffs);
   
     if (puntox != Integer.MAX_VALUE)
       
     g.drawLine(puntox, puntoy, (int)(r.x + r.width/2), r.y);
     
     dibujarArbol(g, n.getNodoIzquierdo(), (int)(r.x + r.width/2), r.y + r.height, yoffs);
     dibujarArbol(g, n.getNodoDerecho(), (int)(r.x + r.width/2), r.y + r.height, yoffs);
     
   }

   public void paint(Graphics g) 
   {
         super.paint(g);
         fm = g.getFontMetrics();

         if (dirty) 
         {
           calcularPosiciones();
           dirty = false;
         }
         
         Graphics2D g2d = (Graphics2D) g;
         g2d.translate(getWidth() / 2, parent2child); 
         dibujarArbol(g2d, this.miArbol.getRaiz(), Integer.MAX_VALUE, Integer.MAX_VALUE, 
         fm.getLeading() + fm.getAscent());
         fm = null;
   }
   
 }




