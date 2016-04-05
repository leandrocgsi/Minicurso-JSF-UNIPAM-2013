package br.com.jsf.controller.backing_bean;

import br.com.jsf.model.bean.Cidade;
import br.com.jsf.model.dao.HibernateDAO;
import br.com.jsf.model.dao.InterfaceDAO;
import br.com.jsf.util.FacesContextUtil;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.hibernate.Session;

@ManagedBean(name="bbCidade")
@RequestScoped
public class BbCidade  implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private List<Cidade> cidades;

    public List<Cidade> getCidades() {
        Session session = FacesContextUtil.getRequestSession();
        InterfaceDAO<Cidade> cidadeDAO = new HibernateDAO<Cidade>(Cidade.class, session);
        cidades = cidadeDAO.getEntities();
        Collections.reverse(cidades); 
        for (Cidade cidade : cidades) {  
            System.out.println("Cidade: "+ cidade.getNomeCidade() +" Id Cidade: "+ cidade.getIdCidade());  
        } 
        return cidadeDAO.getEntities();
    }
}
