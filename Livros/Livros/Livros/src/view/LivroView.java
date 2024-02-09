package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.Livro;

public class LivroView extends JFrame{
	private JTable tabela;
	private JScrollPane painel;
	private JPanel painelSalvar = new JPanel();
	private JPanel painelAlterarDeletar = new JPanel();

	private JLabel textoNome = new JLabel("Nome:");
	private JTextField campoNome = new JTextField(15);
	private JLabel textoAutor = new JLabel("Autor: ");
	private JTextField campoAutor = new JTextField(15);
	private JLabel textoGenero = new JLabel("Genero: ");
	private JTextField campoGenero = new JTextField(15);
	private JButton botaoSalvar = new JButton("Salvar");
	
	private JLabel textoIdAlterar = new JLabel("Id do BD:");
	private JTextField campoIdAlterar = new JTextField(15);
	private JLabel textoNomeAlterar = new JLabel("Nome:");
	private JTextField campoNomeAlterar = new JTextField(15);
	private JLabel textoAutorAlterar = new JLabel("Autor: ");
	private JTextField campoAutorAlterar = new JTextField(15);
	private JLabel textoGeneroAlterar = new JLabel("Genero: ");
	private JTextField campoGeneroAlterar = new JTextField(15);
	private JButton botaoAlterar = new JButton("Alterar");
	private JButton botaoDeletar = new JButton("Deletar");

	public LivroView() {
		this.setSize(540,300);
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void configurarComponentes(ActionListener ouvinte) {
		painelSalvar.add(textoNome);
		painelSalvar.add(campoNome);
		painelSalvar.add(textoAutor);
		painelSalvar.add(campoAutor);
		painelSalvar.add(textoGenero);
		painelSalvar.add(campoGenero);
		botaoSalvar.addActionListener(ouvinte);
		painelSalvar.add(botaoSalvar);
		painelSalvar.setPreferredSize(new Dimension(250, 135));
		this.add(painelSalvar);
		
		painelAlterarDeletar.add(textoIdAlterar);
		painelAlterarDeletar.add(campoIdAlterar);
		painelAlterarDeletar.add(textoNomeAlterar);
		painelAlterarDeletar.add(campoNomeAlterar);		
		painelAlterarDeletar.add(textoAutorAlterar);
		painelAlterarDeletar.add(campoAutorAlterar);
		painelAlterarDeletar.add(textoGeneroAlterar);
		painelAlterarDeletar.add(campoGeneroAlterar);
		botaoAlterar.addActionListener(ouvinte);
		botaoDeletar.addActionListener(ouvinte);
		painelAlterarDeletar.add(botaoAlterar);
		painelAlterarDeletar.add(botaoDeletar);
		painelAlterarDeletar.setPreferredSize(new Dimension(250, 135));
		this.add(painelAlterarDeletar);		
	
		this.limparCampos();
	}

	public void carregarTabela(List<Livro> listaLivros, MouseListener ouvinte2) {
		if(painel != null)
			this.remove(painel);
		String[] nomesColunas = new String[] {
				"Id","Nome", "Autor", "Genero"
		};

		Object[][] dados = new Object[listaLivros.size()][4];	
		for(int i=0; i<listaLivros.size(); i++) {
			dados[i][0] = listaLivros.get(i).getId();
			dados[i][1] = listaLivros.get(i).getNome();
			dados[i][2] = listaLivros.get(i).getAutor();
			dados[i][3] = listaLivros.get(i).getGenero();
		}
		tabela = new JTable(dados,nomesColunas);
		painel = new JScrollPane(tabela);
		painel.setPreferredSize(new Dimension(520, 115));
		this.tabela.addMouseListener(ouvinte2);
		this.add(painel);
		this.revalidate();
	}
	
	public void limparCampos() {
		this.campoIdAlterar.setText("");
		this.campoNomeAlterar.setText("");
		this.campoGeneroAlterar.setText("");
		this.campoAutorAlterar.setText("");
		this.campoNome.setText("");
		this.campoGenero.setText("");
		this.campoAutor.setText("");
		this.botaoAlterar.setEnabled(false);
		this.botaoDeletar.setEnabled(false);
	}

	
	
	public JTextField getCampoNome() {
		return campoNome;
	}

	public void setCampoNome(JTextField campoNome) {
		this.campoNome = campoNome;
	}

	public JTextField getCampoAutor() {
		return campoAutor;
	}

	public void setCampoAutor(JTextField campoAutor) {
		this.campoAutor = campoAutor;
	}

	public JTextField getCampoGenero() {
		return campoGenero;
	}

	public void setCampoGenero(JTextField campoGenero) {
		this.campoGenero = campoGenero;
	}

	public JButton getBotaoSalvar() {
		return botaoSalvar;
	}

	public void setBotaoSalvar(JButton botaoSalvar) {
		this.botaoSalvar = botaoSalvar;
	}

	public JButton getBotaoAlterar() {
		return botaoAlterar;
	}

	public void setBotaoAlterar(JButton botaoAlterar) {
		this.botaoAlterar = botaoAlterar;
	}

	public JButton getBotaoDeletar() {
		return botaoDeletar;
	}

	public void setBotaoDeletar(JButton botaoDeletar) {
		this.botaoDeletar = botaoDeletar;
	}

	public JTable getTabela() {
		return tabela;
	}

	public void setTabela(JTable tabela) {
		this.tabela = tabela;
	}

	public JTextField getCampoIdAlterar() {
		return campoIdAlterar;
	}

	public void setCampoIdAlterar(JTextField campoIdAlterar) {
		this.campoIdAlterar = campoIdAlterar;
	}

	public JTextField getCampoNomeAlterar() {
		return campoNomeAlterar;
	}

	public void setCampoNomeAlterar(JTextField campoNomeAlterar) {
		this.campoNomeAlterar = campoNomeAlterar;
	}

	public JTextField getCampoAutorAlterar() {
		return campoAutorAlterar;
	}

	public void setCampoAutorAlterar(JTextField campoAutorAlterar) {
		this.campoAutorAlterar = campoAutorAlterar;
	}

	public JTextField getCampoGeneroAlterar() {
		return campoGeneroAlterar;
	}

	public void setCampoGeneroAlterar(JTextField campoGeneroAlterar) {
		this.campoGeneroAlterar = campoGeneroAlterar;
	}
	
	
	
	
}
