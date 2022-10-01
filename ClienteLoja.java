import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ClienteLoja {

	public static void main (String args[]) {
		
		try {
			LojaRemota loja = (LojaRemota)Naming.lookup("rmi://localhost:2126/loja");
			LojaRemota loja2 = (LojaRemota)Naming.lookup("rmi://localhost:2127/loja");
			loja.inserirProduto("Banana",10);
			loja.inserirProduto("Maca",24);
			loja.inserirProduto("Goiaba",5);
			loja2.inserirProduto("Mamao",40);
			loja2.inserirProduto("Maca",25);
			loja2.inserirProduto("Laranja",3);
			loja2.inserirProduto("Banana",8);
			try {
				loja.alterarPreco("banana", 10);
			} catch (Excecao e2) {
				// TODO Auto-generated catch block
				System.out.println(e2.getMessage());
			}
			try {
				loja.removerProduto("roupa");
				
			} catch (Excecao e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getMessage());
			}
			try {
				loja.removerProduto("bala");
			} catch (Excecao e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getMessage());
			}
			
			try {
				System.out.println(loja.procurarProduto("banana").getPreco());
				System.out.println(loja.procurarProduto("bala").getPreco());
			} catch (Excecao e) {
				System.out.println(e.getMessage());
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
