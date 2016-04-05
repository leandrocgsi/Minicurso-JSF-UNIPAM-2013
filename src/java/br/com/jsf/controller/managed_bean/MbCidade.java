package br.com.jsf.controller.managed_bean;

import br.com.jsf.model.bean.Cidade;
import br.com.jsf.model.dao.HibernateDAO;
import br.com.jsf.model.dao.InterfaceDAO;
import br.com.jsf.util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "mbCidade")
@SessionScoped
public class MbCidade implements Serializable {

    private static final long serialVersionUID = 1L;
    private Cidade cidade = new Cidade();
    private List<Cidade> cidades;

    public MbCidade() {
    }

    private InterfaceDAO<Cidade> cidadeDAO() {
        InterfaceDAO<Cidade> cidadeDAO = new HibernateDAO<Cidade>(Cidade.class, FacesContextUtil.getRequestSession());
        return cidadeDAO;
    }

    public String limpaCidade() {
        cidade = new Cidade();
        return "/restrict/cadastrarcidade.faces";
    }

    public String editCidade() {
        return "/restrict/cadastrarcidade.faces";
    }

    public String addCidade() {

        InterfaceDAO<Cidade> cidadeDAO = cidadeDAO();
        if (cidade.getIdCidade() == null || cidade.getIdCidade() == 0) {
            cidadeDAO.save(cidade);
            limpaCidade();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));
            limpaCidade();
        } else {
            cidadeDAO.update(cidade);
            limpaCidade();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informações atualizadas com sucesso", ""));
        }
        return null;
    }

    public String delCidade() {
        InterfaceDAO<Cidade> cidadeDAO = cidadeDAO();
        cidadeDAO.remove(cidade);
        cidade = new Cidade();
        return "";
    }

    public List<Cidade> getcidades() {
        InterfaceDAO<Cidade> cidadeDAO = cidadeDAO();
        cidades = cidadeDAO.getEntities();
        return cidades;
    }

    public void setcidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
}