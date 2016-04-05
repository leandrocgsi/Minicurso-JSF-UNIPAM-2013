package br.com.jsf.validators;

import java.util.Date;
import java.util.GregorianCalendar;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/** Validador responsável por garantir que a data
 * selecionada não seja maior que a data corrente
 */

@FacesValidator(value="pastDateValidator")
public class PastDateValidator implements Validator {
    
    //Obtém a data corrente
    private Date dataCorrente = GregorianCalendar.getInstance().getTime();
    
    
    
    //Obtém o valor do campo a ser validado
    @Override
    public void validate(FacesContext context,UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            return;
        }
        
        //Converte o valor do campo para Date
        
        Date data = (Date)value;
        
        //Verifica se a data a ser validada é maior que a data corrente
        if (data.equals(dataCorrente)) {
            System.out.println("A data selecionada é " + data + " e a data corrente é " + dataCorrente );
        } else {
            if(data.after(dataCorrente)){
                System.out.println("A data selecionada é " + data + " e a data corrente é " + dataCorrente );
                throw new ValidatorException(
                new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Data selecionada maior que a data corrente. Por favor selecione uma data válida.", ""));                                
            }
        }                    
    }
}