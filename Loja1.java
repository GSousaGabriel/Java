import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Stack;

public class Loja1 extends UnicastRemoteObject implements LojaRemota {
	
	private Produto[] produtos;
	int proxima;
	
	public Loja1(int tamanho) throws RemoteException {
		
		super();
		
		this.produtos = new Produto[tamanho];
		this.proxima = 0;
	}
	
	public synchronized void inserirProduto(String nome, int preco) throws RemoteException {
		
		if (this.proxima == this.produtos.length) {
			
			proxima = 0;	
		}
		
		produtos[proxima] = new Produto(nome,preco);
		proxima++;
	}
	
	public synchronized int procurarPosicao (String nome) {
		
		int i = 0;
		
		while (i < this.produtos.length){
			
			if (this.produtos[i] != null && produtos[i].getNome().equalsIgnoreCase(nome)) {
				return i;
			}
			i++;
		}
		
		return -1;
	}
	
	public synchronized void removerProduto(String nome) throws RemoteException, Excecao {
		
		int i = this.procurarPosicao(nome);
		
		if (i != -1) {
			
			this.produtos[i] = this.produtos[proxima-1];
			this.produtos[proxima-1] = null;
			proxima = proxima -1;
		} else {
			
			throw new Excecao("Produto n�o encontrado");
		}
		
	}
	
	public synchronized Produto procurarProduto(String nome) throws RemoteException, Excecao {
		
		int i = this.procurarPosicao(nome);
		
		if (i != -1) {
			
			return produtos[i];
			
		} else throw new Excecao("Produto n�o encontrado");	
		
	}
	
	public synchronized Stack<String> getAllProducts () {
		
		Stack<String> products = new Stack<String>();
		int i= 0;

		while (i < this.produtos.length){
			
			if (this.produtos[i] != null) {
				products.push(this.produtos[i].getNome());
			}
			i++;
		}
        
		return products;
	}
	
	public synchronized void alterarPreco(String nome, int valor) throws RemoteException {
		
		int i = this.procurarPosicao(nome);
		this.produtos[i].setPreco(valor);
	}
	
	public static void main (String args[]) {
		
		try {
			Loja1 loja1 = new Loja1(10);
			Registry r = LocateRegistry.createRegistry(2126);
			Naming.rebind("rmi://localhost:2126/loja", loja1);
			System.out.println("Servidor da loja 1 ar!");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			
		}
		
	}
	
}