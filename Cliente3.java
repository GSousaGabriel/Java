import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Stack;

public class Cliente3 {

	public static void main (String args[]) {
		
		try {
			LojaRemota loja1= (LojaRemota)Naming.lookup("rmi://localhost:2126/loja");
			LojaRemota loja2= (LojaRemota)Naming.lookup("rmi://localhost:2127/loja");
			try {
				Stack<String> prodLoja1= loja1.getAllProducts();
				Stack<String> prodLoja2= loja2.getAllProducts();
				
					System.out.println("Comparacao de valores entre as lojas 1 e 2:");
					for(int i=0; i<prodLoja1.size() ;i++){
                        
                        for(int x=0; x<prodLoja2.size() ;x++){
                            if(new String(prodLoja1.get(i)).equals(prodLoja2.get(x))){

                                if(loja1.procurarProduto(prodLoja1.get(i)).getPreco() < loja2.procurarProduto(prodLoja2.get(x)).getPreco()){
                                    System.out.println("O produto " + prodLoja2.get(x) + " esta mais barato na loja 1");
                                }else{
                                    System.out.println("O produto " + prodLoja2.get(x) + " esta mais barato na loja 2");
                                }

                            }else{
                                continue;
                            }
                        }
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
