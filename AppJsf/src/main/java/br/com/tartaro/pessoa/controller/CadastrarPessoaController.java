package br.com.tartaro.pessoa.controller;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.primefaces.model.UploadedFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import br.com.tartaro.model.PessoaModel;
import br.com.tartaro.repository.PessoaRepository;
import br.com.tartaro.usuario.controller.UsuarioController;
import br.com.tartaro.uteis.Uteis;

/**
 * @author WillianTartaro - @date 11 de nov de 2016 - 17:24:16
 *
 *Classe que tem a responsabilidade de controlar os cadastros.
 */

@Named(value="cadastrarPessoaController")//Quando usamos o @Named que pertence ao CDI, esta transformando a classe em um Bean.
@RequestScoped //escopo da sessão
public class CadastrarPessoaController {
 
	@Inject //pessoaModel ira receber a injeção de dependencias.
	PessoaModel pessoaModel;
 
	@Inject //usuusuarioControllerarioModel ira receber a injeção de dependencias.
	UsuarioController usuarioController;
 
	@Inject //pessoaRepository ira receber a injeção de dependencias.
	PessoaRepository pessoaRepository;
	
	private UploadedFile file; //Variavel responsavel por agregar o xml.
	 
	/* ira capturar informações da variavel file.*/
	public UploadedFile getFile() {
		return file;
	}
 
	public void setFile(UploadedFile file) {
		this.file = file;
	}
 
	/* ira capturar informações do UsuarioModel.*/
	public PessoaModel getPessoaModel() {
		return pessoaModel;
	}
 
	public void setPessoaModel(PessoaModel pessoaModel) {
		this.pessoaModel = pessoaModel;
	}
 
	/** 
	 *Metodo para salvar uma nova Pessoa
	 */
	public void SalvarNovaPessoa(){
 
		pessoaModel.setUsuarioModel(this.usuarioController.GetUsuarioSession());
 
		//INFORMANDO QUE O CADASTRO FOI VIA INPUT
		pessoaModel.setOrigemCadastro("I");
 
		pessoaRepository.SalvarNovoRegistro(this.pessoaModel);
 
		this.pessoaModel = null;
 
		Uteis.MensagemInfo("Registro cadastrado com sucesso");
 
	}

	/**
	 * Metodo responsavel por realizar o upload do arquivo XML, contendo as informações dos usuarios.
	 */
	 public void UploadRegistros() {
 
		 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
 
		 try {
 
 
			 if(this.file.getFileName().equals("")){
				 Uteis.MensagemAtencao("Nenhum arquivo selecionado!");
				 return;
			 }
 
			 DocumentBuilder builder = factory.newDocumentBuilder();
 
	                 Document doc = builder.parse(this.file.getInputstream());
 
	                 Element element = doc.getDocumentElement();
 
	                 NodeList nodes = element.getChildNodes();
 
	                for (int i = 0; i < nodes.getLength(); i++) {
 
	        	     Node node  = nodes.item(i);
 
	        	    if(node.getNodeType() == Node.ELEMENT_NODE){
 
	        		 Element elementPessoa =(Element) node;
 
	        		 //Pega as informações do XML.
	        		 String nome     = elementPessoa.getElementsByTagName("nome").item(0).getChildNodes().item(0).getNodeValue();
	        		 String sexo     = elementPessoa.getElementsByTagName("sexo").item(0).getChildNodes().item(0).getNodeValue();
	        		 String email    = elementPessoa.getElementsByTagName("email").item(0).getChildNodes().item(0).getNodeValue();
	        		 String endereco = elementPessoa.getElementsByTagName("endereco").item(0).getChildNodes().item(0).getNodeValue();
 
	        		 PessoaModel newPessoaModel = new PessoaModel();
 
	        		 //Faz o preenchimento dos campos necessarios para que o cadastro seja efetuado.
	        		 newPessoaModel.setUsuarioModel(this.usuarioController.GetUsuarioSession());
	        		 newPessoaModel.setEmail(email);
	        		 newPessoaModel.setEndereco(endereco);
	        		 newPessoaModel.setNome(nome);
	        		 newPessoaModel.setOrigemCadastro("X");
	        		 newPessoaModel.setSexo(sexo);
 
	        		 //Salva em um novo registro, as informações vindo do XML.
	        		 pessoaRepository.SalvarNovoRegistro(newPessoaModel);
	        	   }
	              }
 
 
 
		     Uteis.MensagemInfo("Registros cadastrados com sucesso!");
 
		     //Se caso houver erro, executa.
		} catch (ParserConfigurationException e) {
 
			e.printStackTrace();
 
		} catch (SAXException e) {
 
			e.printStackTrace();
 
		} catch (IOException e) {
 
			e.printStackTrace();
		}
 
 
	 }
 
}