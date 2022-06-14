package arbolgrafico2_0;
public class NodoArbol {
  public Object valor;           //Valor donde se almazenara el nodo
  public NodoArbol izquierda;    //Liga a la izquierda
  public NodoArbol derecha;      //Liga a la derecha


  public NodoArbol () {
      this (null, null, null);
  }

  public NodoArbol (Object valor) { 
      this (null, valor, null);
  }

  public NodoArbol (NodoArbol iz, Object v, NodoArbol der) {
    valor = v;       //valor  del nodo
    izquierda = iz;  //liga al hijo izquierdo
    derecha = der;   //liga al hijo derecho
  }
}