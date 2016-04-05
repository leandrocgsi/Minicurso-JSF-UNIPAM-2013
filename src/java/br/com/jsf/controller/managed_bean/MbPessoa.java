package br.com.jsf.controller.managed_bean;

import br.com.jsf.model.bean.Endereco;
import br.com.jsf.model.bean.Pessoa;
import br.com.jsf.model.dao.HibernateDAO;
import br.com.jsf.model.dao.InterfaceDAO;
import br.com.jsf.util.FacesContextUtil;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "mbPessoa")
@SessionScoped
public class MbPessoa {
    
    private Pessoa pessoa = new Pessoa();
    private Endereco endereco = new Endereco();
    
    private List<Pessoa> pessoas;
    private List<Endereco> enderecos;

    public MbPessoa() {
    }
    
    public InterfaceDAO<Pessoa> pessoaDAO(){
        InterfaceDAO<Pessoa> pessoaDAO =
        new HibernateDAO<Pessoa>(Pessoa.class,FacesContextUtil.getRequestSession());
        return pessoaDAO;
    }
    
    public InterfaceDAO<Endereco> enderecoDAO(){
        InterfaceDAO<Endereco> enderecoDAO =
        new HibernateDAO<Endereco> (Endereco.class, FacesContextUtil.getRequestSession());
        return enderecoDAO;
    }
    
    public String salvar(){
        if ((pessoa.getIdPessoa()==null) || (pessoa.getIdPessoa()==0)){
            insert();
        }else{
            update();
        }
        
        return null;
    }
    
    private String insert(){
        Date date = new Date();
        pessoa.setDataCadastro(date);
        pessoaDAO().save(pessoa);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
            (FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));
        return null;
    }

    private String update(){
        pessoaDAO().update(pessoa);
        return null;
    }

    private String delete(){
        pessoaDAO().remove(pessoa);
        return null;
    }
    
    public Pessoa getPessoa() {
        pessoaDAO().getEntities();
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Endereco> getEnderecos(){
        enderecoDAO().getEntities();
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
    
    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }       
}
