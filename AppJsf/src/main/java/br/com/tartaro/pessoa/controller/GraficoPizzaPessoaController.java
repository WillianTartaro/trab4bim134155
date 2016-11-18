package br.com.tartaro.pessoa.controller;

import java.util.Hashtable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
 
import org.primefaces.model.chart.PieChartModel;
 
import br.com.tartaro.repository.PessoaRepository;

/**
 * @author WillianTartaro - @date 18 de nov de 2016 - 08:15:18
 *
 *Classe com a responsabilidade por controllar e gerar o grafico das pessoas cadastradas.
 */

@Named(value="graficoPizzaPessoaController")//Quando usamos o @Named que pertence ao CDI, esta transformando a classe em um Bean.
@RequestScoped//Cada solicitação, gera um novo contexto.
public class GraficoPizzaPessoaController {
 
	@Inject//pessoaRepository ira receber a injeção de dependencias.
	private PessoaRepository pessoaRepository;
 
 
	private PieChartModel pieChartModel;
	
	/*
	 * retorna o valor da variavel global.
	 */
	public PieChartModel getPieChartModel() {
		return pieChartModel;
	}
 
	/*
	 * metodo para chamar o metodo MontaGrafico().
	 */
	@PostConstruct//logo em seguida da criação do construtor da classe, ele irá executar esse metodo em seguida.
	public  void init(){
 
		this.pieChartModel = new PieChartModel();
 
		this.MontaGrafico();
	}
 
	/*
	 *Metodo responsavel por montar o grafico na tela.
	 */
	private void MontaGrafico(){
 
		//Faz a consulta, para montar o grafico.
		Hashtable<String, Integer> hashtableRegistros = pessoaRepository.GetOrigemPessoa();
 
 
		//Passa os valores da consulta, para montar.
		hashtableRegistros.forEach((chave,valor) -> {
 
 
			pieChartModel.set(chave, valor);
 
		});
 
		pieChartModel.setTitle("Total de Pessoas cadastrado por Tipo");
		pieChartModel.setShowDataLabels(true);
		pieChartModel.setLegendPosition("e");
 
 
	}
}