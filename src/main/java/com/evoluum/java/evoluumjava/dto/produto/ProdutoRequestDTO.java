package com.evoluum.java.evoluumjava.dto.produto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.evoluum.java.evoluumjava.entidades.Categoria;
import com.evoluum.java.evoluumjava.entidades.Produto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Produto requisição DTO")
public class ProdutoRequestDTO {
	
	@ApiModelProperty(value = "Descrição")
	@NotBlank(message = "Descrição")
	@Length(min = 3, max = 100, message = "Descrição")
	private String descricao;

	@ApiModelProperty(value = "Quantidade")
	@NotNull(message = "Quantidade")
	private Integer quantidade;

	@ApiModelProperty(value = "Preço Custo")
	@NotNull(message = "Preço Custo")
	private BigDecimal precoCusto;

	@ApiModelProperty(value = "Preço Venda")
	@NotNull(message = "Preço Venda")
	private BigDecimal precoVenda;

	@ApiModelProperty(value = "Observação")
	@Length(max = 500, message = "Observação")
	private String observacao;
	

	public ProdutoRequestDTO(String descricao, Integer quantidade, BigDecimal precoCusto, BigDecimal precoVenda,
			String observacao) {
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.precoCusto = precoCusto;
		this.precoVenda = precoVenda;
		this.observacao = observacao;
	}
	
	public Produto converterParaEntidade(Long codigoCategoria) {
		return new Produto(descricao, quantidade, precoCusto, precoVenda, observacao, new Categoria(codigoCategoria));
	}
	
	public Produto converterParaEntidade(Long codigoCategoria, Long codigo) {
		return new Produto(codigo, descricao, quantidade, precoCusto, precoVenda, observacao, new Categoria(codigoCategoria));
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPrecoCusto() {
		return precoCusto;
	}

	public void setPrecoCusto(BigDecimal precoCusto) {
		this.precoCusto = precoCusto;
	}

	public BigDecimal getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(BigDecimal precoVenda) {
		this.precoVenda = precoVenda;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	
}


