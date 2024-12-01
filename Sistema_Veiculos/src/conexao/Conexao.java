package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	private static final String URL = "jdbc:MySQL://localhost:3306/veiculos"; // url para conexão com o banco de dados no wamp
	private static final String USUARIO = "root"; // usuario para login no wamp
	private static final String SENHA = ""; // senha para login no wamp
	
	public static Connection conectar() { 
		Connection conexao = null; // Inicializa a conexão
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // estabelece a conexão ao sql usando o driver 
			conexao = DriverManager.getConnection(URL, USUARIO, SENHA); // Estabelece a conexão com o banco usando url, usuário e senha
			System.out.println("Conexão realizada com sucesso.");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver não encontrado: " + e.getMessage()); // caso não encontre o driver, mostra o aviso
		} catch (SQLException e) {
			System.out.println("Erro ao conectar: " + e.getMessage()); // caso de erro ao conectar, mostra o aviso
		}
		return conexao;
	}

	public static void main(String[] args) {
		conectar(); // faz o metodo de conectar para mostrar se conectou ou não
	}
}