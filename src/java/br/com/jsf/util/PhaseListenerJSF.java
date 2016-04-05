package br.com.jsf.util;

import br.com.jsf.util.FacesContextUtil;
import br.com.jsf.util.HibernateUtil;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import org.hibernate.Session;

public class PhaseListenerJSF implements PhaseListener {

    	@SuppressWarnings({ "unchecked" })
        @Override
        public void beforePhase(PhaseEvent fase) {
        
        System.out.println("Antes da Fase: "+fase.getPhaseId());
            
        if(fase.getPhaseId().equals(PhaseId.RESTORE_VIEW))
        {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            FacesContextUtil.setRequestSession(session);
        }
    }

    @Override
    public void afterPhase(PhaseEvent fase) {
        System.out.println("Depois da Fase: "+fase.getPhaseId());

        if(fase.getPhaseId().equals(PhaseId.RENDER_RESPONSE))
        {
            Session session = FacesContextUtil.getRequestSession();
            try{                
                session.getTransaction().commit();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação gravada com sucesso!", ""));

            }
            catch(Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um erro ao commitar a transação!", ""));
                System.out.println("Erro ao commitar a transação: "+e);
                System.out.println(e.getMessage());
                if(session.getTransaction().isActive())
                {
                    session.getTransaction().rollback();
                }
            }
            finally {
                session.close();
            }
        }

    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

}