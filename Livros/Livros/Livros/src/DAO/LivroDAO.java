package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Livro;

public class LivroDAO {
	private  Connection conexao;
	private  PreparedStatement statement;
	
	public void salvar(Livro livro) {
		try{
			ConexaoBD conexaoDB = new ConexaoBD();
			conexao = conexaoDB.getCon();
			conexao.setAutoCommit(false);

			//Preparando a consulta SQL
			String sql = "INSERT INTO celular values(0,?,?,?)";
			statement = conexao.prepareStatement(sql);
			//Mapeamento Relacional
			statement.setString(1, livro.getNome());
			statement.setString(2, livro.getAutor());
			statement.setString(3, livro.getGenero());
			//Execu��o do SQL
			statement.executeUpdate();

		}
		catch(Exception ex){
			try {
				conexao.rollback();
			} catch (SQLException e) {
				ex.printStackTrace();
			}
			ex.printStackTrace();
		}
		finally{
			try{
				conexao.setAutoCommit(true);
				statement.close();
				conexao.close();
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
	
	
	public void alterar(Livro livro) {
		try{
			ConexaoBD conexaoDB = new ConexaoBD();
			conexao = conexaoDB.getCon();
			conexao.setAutoCommit(false);

			//Preparando a consulta SQL
			String sql = "UPDATE Livros SET nome = ?, autor = ?, genero = ? WHERE id = ?";
			statement = conexao.prepareStatement(sql);
			//Mapeamento Relacional
			statement.setString(1, livro.getNome());
			statement.setString(2, livro.getAutor());
			statement.setString(3, livro.getGenero());
			statement.setInt(4, livro.getId());
			//Execu��o do SQL
			statement.executeUpdate();

		}
		catch(Exception ex){
			try {
				conexao.rollback();
			} catch (SQLException e) {
				ex.printStackTrace();
			}
			ex.printStackTrace();
		}
		finally{
			try{
				conexao.setAutoCommit(true);
				statement.close();
				conexao.close();
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
	
	public void deletar(int id) {
		try{
			ConexaoBD conexaoDB = new ConexaoBD();
			conexao = conexaoDB.getCon();
			conexao.setAutoCommit(false);

			//Preparando a consulta SQL
			String sql = "DELETE FROM Livros WHERE id = ?";
			statement = conexao.prepareStatement(sql);
			//Mapeamento Relacional
			statement.setInt(1, id);
			//Execu��o do SQL
			statement.executeUpdate();

		}
		catch(Exception ex){
			try {
				conexao.rollback();
			} catch (SQLException e) {
				ex.printStackTrace();
			}
			ex.printStackTrace();
		}
		finally{
			try{
				conexao.setAutoCommit(true);
				statement.close();
				conexao.close();
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
	
	public List<Livro> listar(){
		try{
			//Inst�ncia de conex�o com o BD
			ConexaoBD conexaoDB = new ConexaoBD();
			conexao = conexaoDB.getCon();
			conexao.setAutoCommit(false);

			//Prepara��o da Consulta SQL
			String sql = "SELECT id,nome, autor, genero from livros";
			statement = conexao.prepareStatement(sql);
			//Execu��o da Consulta SQL
			ResultSet result = statement.executeQuery();

			List<Livro> listaCelulares = new ArrayList<Livro>();
			while(result.next()){
				Livro livro = new Livro();
				livro.setId(result.getInt(1));
				livro.setNome(result.getString(2));
				livro.setAutor(result.getString(3));
				livro.setGenero(result.getString(4));
				listaCelulares.add(livro);
			}
			return listaLivros;
		}
		catch(Exception ex){
			try {
				conexao.rollback();
			} catch (SQLException e) {
				ex.printStackTrace();
			}
			ex.printStackTrace();
			return null;
		}
		finally{
			try{
				conexao.setAutoCommit(true);
				statement.close();
				conexao.close();
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
	

}
