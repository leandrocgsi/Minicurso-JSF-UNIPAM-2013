package br.com.jsf.validators;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/** Validador responsável por garantir que a data
 * selecionada seja maior ou igual à data corrente
 */

@FacesValidator(value="futureDateValidator")
public class FutureDateValidator implements Validator {
    //Obtém a data corrente

    Date dataCapturada = new Date( );
    SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
    
    private String dataCorrente = formatDate.format(dataCapturada);

    //Obtém o valor do campo a ser validado
    @Override
    public void validate(FacesContext context,UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            return;
        }
        
        //Converte o valor do campo para Date
        Date dataSelecionada = (Date)value;
        String data = formatDate.format(dataSelecionada);
        
        //Verifica se a data a ser validada é menor que a data corrente
        if (dataCorrente == null ? data == null : dataCorrente.equals(data)) {
            System.out.println("A data selecionada é " + data + " e a data corrente é " + dataCorrente );
        } else {
            System.out.println("A data selecionada é " + data + " e a data corrente é " + dataCorrente );
            if (!dataSelecionada.after(dataCapturada)){
                throw new ValidatorException(
                new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Data selecionada menor que a data corrente. Por favor selecione uma data válida.", ""));
            }
        }
    }
}