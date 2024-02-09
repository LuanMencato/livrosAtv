package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JOptionPane;

import DAO.LivroDAO;
import model.Livro;
import view.LivroView;

public class LivrosController {
	private LivroView view = new LivroView();

	public LivrosController() {
		
		MouseListener ouvinte2 = new MouseListener() {
			public void mouseReleased(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
			}
			public void mouseEntered(MouseEvent e) {
			}

			public void mouseClicked(MouseEvent e) {
				if(e.getSource() == view.getTabela()) {
					//tabela
		            int linha = view.getTabela().getSelectedRow();
		            
		            int id = (int) view.getTabela().getValueAt(linha, 0);
		            String nome = (String) view.getTabela().getValueAt(linha, 1);
		            String autor = (String) view.getTabela().getValueAt(linha, 2);
		            String genero = (String) view.getTabela().getValueAt(linha, 3);
		            view.getCampoIdAlterar().setText( String.format("%d", id) );
		            view.getCampoNomeAlterar().setText(nome);
		            view.getCampoAutorAlterar().setText(autor);
		            view.getCampoGeneroAlterar().setText(genero);
		            view.getBotaoAlterar().setEnabled(true);
		            view.getBotaoDeletar().setEnabled(true);
				}
			}
		};

		ActionListener ouvinte = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == view.getBotaoSalvar()) {
					// salvar
					Livro livro = new Livro();
					livro.setNome(view.getCampoNome().getText());
					livro.setAutor(view.getCampoAutor().getText());
					livro.setGenero(view.getCampoGenero().getText());
					
					LivroDAO dao = new LivroDAO();
					dao.salvar(livro);
					
					JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
					List<Livro> listaLivros = dao.listar();
					view.carregarTabela(listaLivros,ouvinte2);
					view.limparCampos();
				}else
					if(e.getSource() == view.getBotaoAlterar()) {
						// alterar
						Livro livro = new Livro();
						String id = view.getCampoIdAlterar().getText();
						if(!id.equals("")) {
							livro.setId(Integer.parseInt(id));
							livro.setNome(view.getCampoNomeAlterar().getText());
							livro.setAutor(view.getCampoAutorAlterar().getText());
							livro.setGenero(view.getCampoGeneroAlterar().getText());
							
							LivroDAO dao = new LivroDAO();
							dao.alterar(livro);
							
							JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
							List<Livro> listaLivros = dao.listar();
							view.carregarTabela(listaLivros,ouvinte2);
							view.limparCampos();
						}
					}else
						if(e.getSource() == view.getBotaoDeletar()) {
							// deletar
							String id = view.getCampoIdAlterar().getText();
							if(!id.equals("")) {
								LivroDAO dao = new LivroDAO();
								dao.deletar(Integer.parseInt(id));
								JOptionPane.showMessageDialog(null, "Deletado com sucesso!");
								List<Livro> listaLivros = dao.listar();
								view.carregarTabela(listaLivros,ouvinte2);
								view.limparCampos();
							}
						}

			}
		};

		view.configurarComponentes(ouvinte);

		LivroDAO dao = new LivroDAO();
		List<Livro> listaLivros = dao.listar();
		view.carregarTabela(listaLivros,ouvinte2);
	}
}
