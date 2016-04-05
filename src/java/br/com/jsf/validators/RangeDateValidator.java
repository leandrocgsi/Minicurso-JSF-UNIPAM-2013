package br.com.jsf.validators;

import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/** Validador responsável por garantir que a
 *  data inicial seja maior que a data final
 */
@FacesValidator(value="rangeDateValidator")
public class RangeDateValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value)
        throws ValidatorException {

        // Imprime a família do componente
        System.out.println(component.getFamily());
        
        // Obtém a primeira data
        Date campoDataInicio = (Date) component.getAttributes().get("campoDataInicio");

        // Procura na árvore de componentes do JSF o componnete atual para a identificação seu cliente.
        UIInput textInput = (UIInput) context.getViewRoot().findComponent(campoDataInicio.toString());
        if (textInput == null)
            throw new IllegalArgumentException(String.format("Não foi possível localizar componente com este Id %s",campoDataInicio));

        // Converte o valor, da data do primeiro campo.
        //filed1 virou data dataInicio
        Date dataInicio = (Date) textInput.getValue();

        // Obtém e converte o valor, da data do segundo campo.
        //comfirm virou data dataFim
        Date dataFim = (Date) value;

        // Confere se o primeiro texto é realmente igual ao atual compara-os.
        if (!dataFim.after(dataInicio)) {
            throw new ValidatorException(new FacesMessage("A data final deve ser menor que a data inicial."));
        }
    }
}
