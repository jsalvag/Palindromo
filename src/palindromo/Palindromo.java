package palindromo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

class nodo{
	int c;
	nodo sig;
}

public class Palindromo {
	nodo top, front;
	char[] num;
	int cont;
        BigInteger res;
	BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
	void entrada() throws IOException{
		String cad;
		System.out.print("Ingrese numero: ");
		cad = bf.readLine();
		num = cad.toCharArray();
		crearPilaCola(num);
	}
	
	void crearPilaCola(char[] n){
		top = null;
		front = null;
		for (int i = 0; i < n.length; i++) {
			pila(n[i]-48);
			cola(n[i]-48);
		}
	}
	
	void pila(int a){
		nodo n = new nodo();
		nodo aux = new nodo();
		n.c = a;
		n.sig = null;
		
		if(top==null){
			top=n;
		}else{
			aux=top;
			top=n;
			n.sig=aux;
		}
	}
	
	void cola(int a){
		nodo n = new nodo();
		nodo aux = new nodo();
		n.c = a;
		n.sig = null;
		
		if(front==null){
			front=n;
		}else{
			aux=front;
			while(aux.sig!=null)
				aux=aux.sig;
			aux.sig=n;
		}
	}
	
	boolean comparador(){
		nodo auxP = new nodo();
		nodo auxC = new nodo();
		
		auxP=top;
		auxC=front;
		
		while(auxP.sig!=null || auxC.sig!=null){
			if(auxP.c!=auxC.c){
				return false;
			}
			auxP=auxP.sig;
			auxC=auxC.sig;
		}
		
		return true;
	}
	
	void sumador(){
		String sPila="", sCola="";

		nodo auxP = new nodo();
		nodo auxC = new nodo();
		
		auxP=top;
		auxC=front;
		
		if(!comparador()){
			BigInteger suma= new BigInteger("1");
			cont++;
			System.out.println("No es palindromo..."+cont);
			while(auxP!=null || auxC!=null){
				sPila+=auxP.c+"";
				sCola+=auxC.c+"";
				auxP=auxP.sig;
				auxC=auxC.sig;
			}
                        BigInteger a = new BigInteger(sPila);
                        BigInteger b = new BigInteger(sCola);
			System.out.println("Se sumara "+sCola+" con su contrapuesto "+sPila);
			suma=a.add(b);
			res=suma;
			crearPilaCola((suma+"").toCharArray());
                        if(cont<=1000)
                            sumador();
                        else
                            System.out.println("La operacion se ha realizado mas de 1000 veces, el numero ya es demasiado grande...");
		}else{
			System.out.println("Es palindromo...\t"+res+"\nSe sumó con su contrapuesto "+cont+" veces");
		}
	}
	
	void mostrar(nodo N){
		nodo aux = new nodo();
		aux=N;
		String dato = "";
		while(aux.sig!=null){
			dato += aux.c;
			aux=aux.sig;
		}
		dato += aux.c;
		System.out.println("El nodo contiene: "+dato);
	}
	
	public static void main(String[] args) throws IOException {
		Palindromo p = new Palindromo();
		p.entrada();
                
                System.out.println();
		
                p.mostrar(p.top);
		p.mostrar(p.front);
		
                System.out.println();
                
		p.sumador();

	}

}
