package br.com.tartaro.repository.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author WillianTartaro - @date 11 de nov de 2016 - 17:18:08
 *Classe responsavel por persistir com nossa tabela usuario(tb_pessoa) no Banco de Dados.
 *
 */

@Entity
@Table(name="tb_pessoa")//tabela de pessoa no Banco de Dados.
@NamedQueries({ //Query usada para fazer a busca de todas as pessoas cadastradas no Banco de Dados.
	 
	@NamedQuery(name = "PessoaEntity.findAll",query= "SELECT p FROM PessoaEntity p")
 
})
public class PessoaEntity {
 
	@Id
	@GeneratedValue
	@Column(name = "id_pessoa") //nome referente ao nome da coluna da tabela tb_pessoa.
	private Integer codigo;
 
	@Column(name = "nm_pessoa") //nome referente ao nome da coluna da tabela tb_pessoa.
	private String  nome;
 
	@Column(name = "fl_sexo") //nome referente ao nome da coluna da tabela tb_pessoa.
	private String  sexo;
 
	@Column(name = "dt_cadastro") //nome referente ao nome da coluna da tabela tb_pessoa.
	private LocalDateTime	dataCadastro;
 
	@Column(name = "ds_email") //nome referente ao nome da coluna da tabela tb_pessoa.
	private String  email;
 
	@Column(name = "ds_endereco") //nome referente ao nome da coluna da tabela tb_pessoa.
	private String  endereco;
 
	@Column(name = "fl_origemCadastro") //nome referente ao nome da coluna da tabela tb_pessoa.
	private String  origemCadastro;
 
	@OneToOne //OneToOne diz que a tabela terá relacionamento de 1 pra 1.
	@JoinColumn(name="id_usuario_cadastro") //Esse atributo vai ser colocado como "id_usuario_cadastro" no Banco.
	private UsuarioEntity usuarioEntity;
 
	/* Metodo resposavel por capturar o codigo que ira vir da coluna tb_pessoa.  */
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	/* Metodo resposavel por capturar o codigo que ira vir da coluna tb_pessoa.  */
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	/* Metodo resposavel por capturar o codigo que ira vir da coluna tb_pessoa.  */
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	/* Metodo resposavel por capturar o codigo que ira vir da coluna tb_pessoa.  */
	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	/* Metodo resposavel por capturar o codigo que ira vir da coluna tb_pessoa.  */
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	/* Metodo resposavel por capturar o codigo que ira vir da coluna tb_pessoa.  */
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	/* Metodo resposavel por capturar o codigo que ira vir da coluna tb_pessoa.  */
	public String getOrigemCadastro() {
		return origemCadastro;
	}
	public void setOrigemCadastro(String origemCadastro) {
		this.origemCadastro = origemCadastro;
	}
	/* Metodo resposavel por capturar o codigo que ira vir da coluna tb_pessoa.  */
	public UsuarioEntity getUsuarioEntity() {
		return usuarioEntity;
	}
	public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
		this.usuarioEntity = usuarioEntity;
	}
 
}