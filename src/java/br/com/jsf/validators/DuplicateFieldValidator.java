package br.com.jsf.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value="duplicateFieldValidator")
public class DuplicateFieldValidator implements Validator {

@Override
public void validate(FacesContext context, UIComponent component, Object value)
    throws ValidatorException {
        // Obtém o id do primeiro campo
        System.out.println(component.getFamily());
        String field1Id = (String) component.getAttributes().get("field1Id");

        // Procura o componente JSF atual para a identificação do id cliente.
        UIInput textInput = (UIInput) context.getViewRoot().findComponent(field1Id);
        
        // Obtém o valor, do texto digitado no primeiro campo.
        String field1 = (String) textInput.getValue();

        // Converte o valor digitado no segundo campo para String.
        String confirm = (String) value;
        
        if (textInput == null) {
            throw new IllegalArgumentException(String.format("Não foi possível localizar componente com este Id %s",field1Id));
        }         
        // Confere se o primeiro texto é realmente igual ao atual compara-os.
        if (field1 != null && field1.length() != 0 && !field1.equals(confirm)) {
            throw new ValidatorException(new FacesMessage("As senhas devem ser iguais."));
        }
    }
}