package br.com.jsf.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/** Validador responsável por garantir que nome
 * digitado em um formulário seja correto
 */

@FacesValidator(value="nameValidator")
public class NameValidator implements Validator {

    //Obtém o valor do campo a ser validado
    @Override
    public void validate(FacesContext context,UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            return;
        }
        
        //Converte o valor do campo para String
        
        String nome = (String)value;
                           
        if(nome.length()< 6)  
            throw new ValidatorException(new FacesMessage( "O [Nome] digitado está menor que o limite mínimo.", ""));
        if(nome.length()> 120)
            throw new ValidatorException(new FacesMessage( "O [Nome] digitado está maior que o limite máximo.", ""));
        
        /*Monta um array de Chars e verifica se seus valores estão entre 45 e 57
        que na tabela ASCII corresponde a '- . / e aos numeros de 0 à 9'*/
        
        for(int i=0;i<nome.length();i++)
            if(nome.charAt(i)>=45 && nome.charAt(i)<=57)
                throw new ValidatorException(new FacesMessage( "O [Nome] não deve conter números.", ""));
        char[] caracteresInvalidos={'=','|','!','@','#','$','%','^','&','*','(',')','{','}','[',']',';',':','.',',','<','>','?','~','+','-','_','\'','"'};
        for(int i=0;i<nome.length();i++)
            for(int j=0;j<caracteresInvalidos.length;j++)
                if(nome.charAt(i)==caracteresInvalidos[j])
                    throw new ValidatorException(new FacesMessage( "O [Nome] digitado possui caracteres inválidos.", ""));
    }
}