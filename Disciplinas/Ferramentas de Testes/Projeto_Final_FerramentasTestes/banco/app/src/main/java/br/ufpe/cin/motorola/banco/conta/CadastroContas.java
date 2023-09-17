package br.ufpe.cin.motorola.banco.conta;

import br.ufpe.cin.motorola.banco.excecoes.ContaExistenteException;
import br.ufpe.cin.motorola.banco.excecoes.ContaInexistenteException;
import br.ufpe.cin.motorola.banco.excecoes.SaldoInsuficienteException;

import java.util.List;

public class CadastroContas {

	private IRepositorioContas contas;
	public CadastroContas(IRepositorioContas r) {
		this.contas = r;
	}

	public void cadastrar(ContaAbstrata c) throws ContaExistenteException {
		if (!contas.existe(c.getNumero())) {
			contas.inserir(c);
		} else {
			throw new ContaExistenteException();
		}
	}

	public void creditar(String n, double v) throws ContaInexistenteException {
		ContaAbstrata c = contas.procurar(n);
		c.creditar(v);
	}

	public void debitar(String n, double v) throws ContaInexistenteException, SaldoInsuficienteException {
		ContaAbstrata c = contas.procurar(n);
		//inclui a chamada da funçao de debitar logo na linha abaixo
		c.debitar(v);
	}

	public ContaAbstrata procurar(String n) throws ContaInexistenteException {
		return contas.procurar(n);
	}

	public void transferir(String origem, String destino, double val) throws ContaInexistenteException, SaldoInsuficienteException {
		ContaAbstrata o = contas.procurar(origem);
		ContaAbstrata d = contas.procurar(destino);
		//estava sendo transferido o valor de o para o, ou seja, a mesma conta. alterei para d que seria outra conta
		o.transferir(d, val);
	}

	public void remover(String n) throws ContaInexistenteException {
		contas.remover(n);
	}

	public void atualizar(ContaAbstrata c) throws ContaInexistenteException {
		contas.atualizar(c);
	}

	public List<ContaAbstrata> listar() {
		contas.listar();
		return listar();
	}
}