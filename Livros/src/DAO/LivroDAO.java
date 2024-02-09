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

			
			String sql = "INSERT INTO livro values(0,?,?,?)";
			statement = conexao.prepareStatement(sql);
			statement.setString(1, livro.getNome());
			statement.setString(2, livro.getAutor());
			statement.setString(3, livro.getGenero());
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
L
			String sql = "UPDATE livro SET nome = ?, autor = ?, genero = ? WHERE id = ?";
			statement = conexao.prepareStatement(sql);
			statement.setString(1, livro.getNome());
			statement.setString(2, livro.getAutor());
			statement.setString(3, livro.getGenero());
			statement.setInt(4, livro.getId());
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

			String sql = "DELETE FROM livro WHERE id = ?";
			statement = conexao.prepareStatement(sql);
			statement.setInt(1, id);
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
			ConexaoBD conexaoDB = new ConexaoBD();
			conexao = conexaoDB.getCon();
			conexao.setAutoCommit(false);

			String sql = "SELECT id,nome, autor, genero from livro";
			statement = conexao.prepareStatement(sql);
			ResultSet result = statement.executeQuery();

			List<Livro> listaLivros = new ArrayList<Livro>();
			while(result.next()){
				Livro livro = new Livro();
				livro.setId(result.getInt(1));
				livro.setNome(result.getString(2));
				livro.setAutor(result.getString(3));
				livro.setGenero(result.getString(4));
				listaLivros.add(livro);
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
