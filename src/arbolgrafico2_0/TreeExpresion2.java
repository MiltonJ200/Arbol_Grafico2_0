package arbolgrafico2_0;
import java.util.*;


public class TreeExpresion2 {
    Pila pOperandos;               // Pila de operandos
    Pila pOperadores;              // Pila de operadores
    final String blanco;           // Cadena de espacios en blanco
    final String operadores;       // Cadena con operadores para expresiones
String msg="";
  /**
   * Constructor por omision
   */
    public TreeExpresion2() {
	pOperandos = new Pila();
	pOperadores = new Pila();
	blanco = " \t";
	operadores = ")+-*/%^(";  //acomodados por precedencia;
    }

  /**
   * Metodo para construir un arbol para una expresion aritmetica dada.
   */
    public NodoArbol construirArbol(String expresion) {
       	StringTokenizer tokenizer;
	String token;
	NodoArbol raiz = null;

	tokenizer = new StringTokenizer(expresion, blanco+operadores, true);
	while (tokenizer.hasMoreTokens()) {
	    token = tokenizer.nextToken();
	    if (blanco.indexOf(token) >= 0) 
		;               // Es un espacio en blanco, se ignora
	    else if (operadores.indexOf(token) < 0) {
		                // Es operando y lo guarda como nodo del arbol
		pOperandos.push(new NodoArbol(token));
	    } else if(token.equals(")")) { // Saca elementos hasta encontrar (
		while (!pOperadores.estaVacia() && !pOperadores.top().equals("(")) {
		    guardarSubArbol();
		}
		pOperadores.pop();  // Saca el parentesis izquierdo
	    } else {
		if (!token.equals("(") && !pOperadores.estaVacia()) {
		           //operador diferente de cualquier parentesis
		    String op = (String) pOperadores.top();
		    while (!op.equals("(") && !pOperadores.estaVacia() 
			   && operadores.indexOf(op) >= operadores.indexOf(token)) {
			guardarSubArbol();
			if (!pOperadores.estaVacia()) 
			    op = (String)pOperadores.top();
		    }
		}
		pOperadores.push(token);  //Guarda el operador
	    }
	}
	//Sacar todo lo que queda
	raiz = (NodoArbol)pOperandos.top();
	while (!pOperadores.estaVacia()) {
	    if (pOperadores.top().equals("(")) {
		pOperadores.pop();
	    } else {
	    guardarSubArbol();
	    raiz = (NodoArbol) pOperandos.top();
	    }
	}
	return raiz;
    }

  /*
   * Metodo privado para almacenar en la pila un subarbol
   */
    private void guardarSubArbol() {
	NodoArbol op2 = (NodoArbol)pOperandos.pop();
	NodoArbol op1 = (NodoArbol)pOperandos.pop();
	pOperandos.push(new NodoArbol(op1, pOperadores.pop(), op2));

    }

    /**
     * Metodo para imprimir un arbol en inorden
     
     */
    public String imprime(NodoArbol n) {
	if (n != null) {
	    imprime(n.izquierda);
	    msg+=n.valor+" ";
            System.out.print(n.valor+" ");
	    imprime(n.derecha);
	}
        return msg;
    }

    /**
     * Metodo para imprimir un arbol en postorden
     * @param n -- nodo raiz
     */
    public String imprimePos(NodoArbol n) {
	if (n != null) {
	    imprimePos(n.izquierda);
	    imprimePos(n.derecha);
            msg+=n.valor+" ";
	    System.out.print(n.valor+" ");
	}
        return msg;
    }

    /**
     * Metodo para imprimir un arbol en preorden
     * @param n -- nodo raiz
     */
    public String imprimePre(NodoArbol n) {
	if (n != null) {
	    System.out.print(n.valor+" ");
            msg+=n.valor+" ";
	    imprimePre(n.izquierda);
	    imprimePre(n.derecha);
	}
        return msg;
    }

    public static void main (String[] pps) {
	TreeExpresion2 expr = new TreeExpresion2();

       	if (pps.length < 1)
	    System.out.println("Debe ejecutarse: ArbolExp \"expresion con espacios y parentesis\" ");
	else 
	    for (int i = 0; i < pps.length; i++) {
		NodoArbol raiz = expr.construirArbol(pps[i]);
		System.out.print("El arbol es ");
		expr.imprime(raiz);
		System.out.print("\nEl arbol en postfija es ");
		expr.imprimePos(raiz);
		//		System.out.println("\n");
		System.out.print("\nEl arbol en prefija es ");
		expr.imprimePre(raiz);
		System.out.println("\n\n");
	    }
    }
}

