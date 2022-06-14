/**
 * Clase para manejar los nodos de la pila
 * @author Amparo Lopez Gaona
 * @version 1a. ed.
 */
package arbolgrafico2_0;
public class Nodo_1 { 
    public Object elemento;      // Valor almacenado en el nodo 
    public Nodo_1 sgte;            // Referencia al siguiente elemento 

 
  Nodo_1(Object valor) { 
      this (valor, null);
  }

  /**
   * Metodo para crear un nodo antes del indicado, con elemento igual a valor.
   * @param valor -- objeto que es el valor de nodo
   * @param n --  nodo al que apunta el recien creado
   */
  Nodo_1(Object valor, Nodo_1 n) {
      elemento = valor; //objeto que es el valor de nodo
      sgte = n;         //nodo al que apunta el recien creado
  }

  /**
   * Metodo que devuelve el valor de un nodo
   * @return Object el valor del nodo
   */
  public Object obtenerElemento () { return elemento; }

  /**
   * Metodo que devuelve la referencia del siguiente nodo
   * @return NOdo el siguietne nodo
   */
  public Nodo_1 obtenerSgte() { return sgte;}
}

