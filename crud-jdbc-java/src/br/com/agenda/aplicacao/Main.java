package br.com.agenda.aplicacao;

import java.util.Date;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.model.Contato;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// insere novo contato no banco de dados

		ContatoDAO contatoDao = new ContatoDAO();

		Contato contato = new Contato();
		contato.setNome("Mikel de Paula");
		contato.setIdade(26);
		contato.setDataCadastro(new Date());

		contatoDao.save(contato);

		// Atualizar o contato.

		//Contato c1 = new Contato();
		//c1.setNome("Maria Gabriela");
		//c1.setId(37);
		//c1.setDataCadastro(new Date());
		//c1.setId(10); // o numero que esta no banco de dados da pk

		//contatoDao.update(c1);
		
		//deletar o contato pelo seu numero de id
		
		//contatoDao.deleteByID(1);

		// Visualização de todos os contatos

		for (Contato c : contatoDao.getContatos()) {
			System.out.println("Contato: " + c.getNome());
		}

	}

}
