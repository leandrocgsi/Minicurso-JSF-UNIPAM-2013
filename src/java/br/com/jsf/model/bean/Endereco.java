package br.com.jsf.model.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name="endereco")
public class Endereco implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    @Column(name = "IdEndereco", nullable = false)
    private Integer idEndereco;
    @Column(name = "Bairro", length = 60)
    private String bairro;
    @Column(name = "NomeLogradouro", nullable = false, length = 90)
    private String nomeLogradouro;
    @Column(name = "CEP", nullable = false, length = 9)
    private String cep;
    @Column(name = "Numero", nullable = false, length = 6)
    private String numero;
    @Column(name = "Complemento", length = 20)
    private String complemento;

    @OneToOne(optional=true, fetch = FetchType.LAZY)
    @ForeignKey(name="Pessoa_Possui_Endereco")
    @JoinColumn(name = "IdPessoa", referencedColumnName="IdPessoa")
    private Pessoa pessoa;
    
    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    @ForeignKey(name="Endereco_TipoLogradouro")
    @JoinColumn(name = "IdTipoLogradouro", referencedColumnName="IdTipoLogradouro")
    private TipoLogradouro tipologradouro;
    
    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    @ForeignKey(name="Endereco_Estado")
    @JoinColumn(name = "IdEstado", nullable = false)
    private Estado estado;
        
    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    @ForeignKey(name="Endereco_TipoEndereco")
    @JoinColumn(name = "IdTipoEndereco", referencedColumnName="IdTipoEndereco")
    private TipoEndereco tipoendereco;
    
    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    @ForeignKey(name="Endereco_Cidade")
    @JoinColumn(name = "IdCidade", referencedColumnName="IdCidade")
    private Cidade cidade;
    
    public Endereco() {
        this.cidade = new Cidade();
        this.estado = new Estado();
        this.tipoendereco = new TipoEndereco();
        this.tipologradouro = new TipoLogradouro();
        this.pessoa =new Pessoa();
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Integer getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getNomeLogradouro() {
        return nomeLogradouro;
    }

    public void setNomeLogradouro(String nomeLogradouro) {
        this.nomeLogradouro = nomeLogradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public TipoEndereco getTipoendereco() {
        return tipoendereco;
    }

    public void setTipoendereco(TipoEndereco tipoendereco) {
        this.tipoendereco = tipoendereco;
    }

    public TipoLogradouro getTipologradouro() {
        return tipologradouro;
    }

    public void setTipologradouro(TipoLogradouro tipologradouro) {
        this.tipologradouro = tipologradouro;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Endereco other = (Endereco) obj;
        if (this.idEndereco != other.idEndereco && (this.idEndereco == null || !this.idEndereco.equals(other.idEndereco))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.idEndereco != null ? this.idEndereco.hashCode() : 0);
        return hash;
    }
}
