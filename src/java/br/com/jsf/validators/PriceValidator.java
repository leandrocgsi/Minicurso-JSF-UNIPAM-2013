package br.com.jsf.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/** Validador responsável por garantir que o preço
 * cadastrado seja positivo, maior que zero e não seja texto
 */

@FacesValidator(value="priceValidator")
public class PriceValidator implements Validator {

    @Override
    public void validate(FacesContext context,UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            return;
        }      
        
        try {
            String aux = String.valueOf(value);
            double preco = new Double(aux.trim());
            if (preco > 9999999.99d) {
                FacesMessage message = new FacesMessage("O preco nao pode ser maior do que 9999999.99");
                throw new ValidatorException(message);
            } else if (preco < 0) {
                FacesMessage message = new FacesMessage("O preco nao pode ser negativo");
                throw new ValidatorException(message);
            }

        } catch (NumberFormatException e) {
            FacesMessage message = new FacesMessage("Preco em formato invalido, formato correto: 9999999.99");
            throw new ValidatorException(message);
        }
    }
}