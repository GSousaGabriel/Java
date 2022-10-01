import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Stack;

public class Cliente2 {

	public static void main (String args[]) {
		
		try {
			LojaRemota loja1= (LojaRemota)Naming.lookup("rmi://localhost:2126/loja");
			LojaRemota loja2= (LojaRemota)Naming.lookup("rmi://localhost:2127/loja");
			try {
				Stack<String> prodLoja1= loja1.getAllProducts();
				Stack<String> prodLoja2= loja2.getAllProducts();
				
					System.out.println("produtos Loja 1:");
					for(int i=0; i<prodLoja1.size() ;i++){
						System.out.println(prodLoja1.get(i));
					}

					System.out.println("produtos Loja 2:");
					for(int x=0; x<prodLoja2.size() ;x++){
						System.out.println(prodLoja2.get(x));
					}

			} catch (Excecao e2) {
				// TODO Auto-generated catch block
				System.out.println(e2.getMessage());
			}
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
	}
	
}
