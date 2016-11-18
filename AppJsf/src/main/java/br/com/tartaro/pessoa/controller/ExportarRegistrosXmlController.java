package br.com.tartaro.pessoa.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.List;
 
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
 
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
 
import br.com.tartaro.model.PessoaModel;
import br.com.tartaro.repository.PessoaRepository;

/**
 * @author WillianTartaro - @date 18 de nov de 2016 - 11:37:16
 *
 *Classe com responsabilidade de controlar a exportação do XML.
 */

@Named(value="exportarRegistrosXmlController")//Quando usamos o @Named que pertence ao CDI, esta transformando a classe em um Bean.
@RequestScoped//Cada solicitação, gera um novo contexto.
public class ExportarRegistrosXmlController implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
 
	@Inject transient//pessoaRepository ira receber a injeção de dependencias.
	PessoaRepository pessoaRepository; 
 
	private StreamedContent arquivoDownload;
 
	/*
	 * metodo responsavel por realizar o download do XML
	 * @return
	 */
	public StreamedContent getArquivoDownload() {
 
		this.DownlaodArquivoXmlPessoa();
 
		return arquivoDownload;
	}
 
	/*
	 * Metodo responsavel por gerar o arquivo XML.
	 */
	private File GerarXmlPessoas(){
 
		//Mascara para formatar a data do arquivo XML.
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
 
		List<PessoaModel> pessoasModel = pessoaRepository.GetPessoas();
 
		//Elemento raiz do arquivo.
		Element elementPessoas = new Element("Pessoas");
 
		Document documentoPessoas = new Document(elementPessoas);
 
		pessoasModel.forEach(pessoa -> {
 
 
			//Monta as tags do arquivo xml juntamente com seus valores.
			Element elementPessoa = new Element("Pessoa");			
			elementPessoa.addContent(new Element("codigo").setText(pessoa.getCodigo().toString()));
			elementPessoa.addContent(new Element("nome").setText(pessoa.getNome()));
			elementPessoa.addContent(new Element("sexo").setText(pessoa.getSexo()));
 
			//Formata a data.
			String dataCadastroFormatada = pessoa.getDataCadastro().format(dateTimeFormatter);
 
			elementPessoa.addContent(new Element("dataCadastro").setText(dataCadastroFormatada));
 
			elementPessoa.addContent(new Element("email").setText(pessoa.getEmail()));
			elementPessoa.addContent(new Element("endereco").setText(pessoa.getEndereco()));
			elementPessoa.addContent(new Element("origemCadastro").setText(pessoa.getOrigemCadastro()));
			elementPessoa.addContent(new Element("usuarioCadastro").setText(pessoa.getUsuarioModel().getUsuario()));
 
			elementPessoas.addContent(elementPessoa);
		});
 
 
		XMLOutputter xmlGerado = new XMLOutputter();
 
		try {
 
 
			/*Gera o nome do arquivo*/
			String nomeArquivo =  "pessoas_".concat(java.util.UUID.randomUUID().toString()).concat(".xml");
 
			//Caminho onde vai ser encaminhado o arquivo XML.
			File arquivo = new File("C:\\TesteJava\\".concat(nomeArquivo));
 
			FileWriter fileWriter =  new FileWriter(arquivo);
 
 
			xmlGerado.output(documentoPessoas, fileWriter);
 
			return arquivo;
 
		} catch (Exception ex) {
 
			ex.printStackTrace();
		}		
 
		return null;
	}
 
	/*
	 * Metodo responsavel por preparar o arquivo XML para download.
	 */
	public void DownlaodArquivoXmlPessoa(){
 
		File arquivoXml = this.GerarXmlPessoas();
 
		InputStream inputStream;
 
		try {
 
			inputStream = new FileInputStream(arquivoXml.getPath());
 
			arquivoDownload = new DefaultStreamedContent(inputStream,"application/xml",arquivoXml.getName());
 
		} catch (FileNotFoundException e) {
 
			e.printStackTrace();
		}
 
 
 
	}
}