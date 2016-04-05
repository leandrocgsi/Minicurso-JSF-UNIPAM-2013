package br.com.jsf.controller.backing_bean;

import java.util.List;

import org.hibernate.Session;

import br.com.jsf.model.bean.Sexo;
import br.com.jsf.model.dao.HibernateDAO;
import br.com.jsf.model.dao.InterfaceDAO;
import br.com.jsf.util.FacesContextUtil;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="bbSexo")
@RequestScoped
public class BbSexo  implements Serializable {
    
    private static final long serialVersionUID = 1L;

    public List<Sexo> getSexos() {
        Session session = FacesContextUtil.getRequestSession();
        InterfaceDAO<Sexo> sexoDAO = new HibernateDAO<Sexo>(Sexo.class, session);
        return sexoDAO.getEntities();
    }
}
