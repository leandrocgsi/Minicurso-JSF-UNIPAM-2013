package br.com.jsf.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/** Validador responsável por garantir que o username escolhido pelo usuário
 * não seja nulo e seja maior que 7 caracteres e menor que 23
 */

@FacesValidator(value="userNameValidator")
public class UserNameValidator implements Validator {

    @Override
    public void validate(FacesContext context,UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            return;
        }     
        
        String loginName = (String)value;
        
        if(loginName == null || loginName.equals("")){
             throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "O Login não pode ser nulo!", ""));
        }else if(loginName.length() < 7){
             throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "O Login deve ter no mínimo 7 caracteres!", ""));
        }else if(loginName.length() >= 22){
             throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "O Login deve ter no máximo 22 caracteres!!!", ""));
        }
    }
}