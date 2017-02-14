package com.example.juanmunhoesjunior.myapplication.validator;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by juanmunhoesjunior on 19/04/16.
 */

public class JValidatorMaster {

    /*
    * Método para validar um email utilizando regex
    * */
    public static boolean validateEmail(String email) {
        String emailRegex = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
        return Pattern.compile(emailRegex).matcher(email).find();
    }

    /*
    * Método para validar um cnpj
    * */
    public static boolean validateCnpj(String CNPJ) {
        CNPJ = CNPJ.replace(".", "");
        CNPJ = CNPJ.replace("/", "");
        CNPJ = CNPJ.replace("-", "");
        CNPJ = CNPJ.replace("_","");
        // considera-se erro CNPJ's formados por uma sequencia de numeros iguais
        if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111") || CNPJ.equals("22222222222222")
                || CNPJ.equals("33333333333333") || CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555")
                || CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777") || CNPJ.equals("88888888888888")
                || CNPJ.equals("99999999999999") || (CNPJ.length() != 14))
            return (false);

        char dig13, dig14;
        int sm, i, r, num, peso;

        try { // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i = 11; i >= 0; i--) {
                // converte o i-ésimo caractere do CNPJ em um número:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posição de '0' na tabela ASCII)
                num = (int) (CNPJ.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10)
                    peso = 2;
            }

            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig13 = '0';

            else dig13 = (char) ((11 - r) + 48);
            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i = 12; i >= 0; i--) {
                num = (int) (CNPJ.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10)
                    peso = 2;
            }
            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig14 = '0';
            else dig14 = (char) ((11 - r) + 48);

            // Verifica se os dígitos calculados conferem com os dígitos informados.
            if ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13)))
                return (true);
            else return (false);
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    /*
    Método para validar um cpf
    * */
    public static boolean validateCpf(String cpf) {

        cpf = cpf.replace(".", "");
        cpf = cpf.replace("-", "");
        cpf = cpf.replace("_", "");


        int[] multiplicador1 = {10, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] multiplicador2 = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
        String tempCpf;
        String digito;
        int soma;
        int resto;


        if (cpf.length() != 11)
            return false;

        //Checar se possui todos numeros repetidos
        if ((cpf.equals("00000000000")) || (cpf.equals("11111111111")) ||
                (cpf.equals("22222222222")) || (cpf.equals("33333333333")) ||
                (cpf.equals("44444444444")) || (cpf.equals("55555555555")) ||
                (cpf.equals("66666666666")) || (cpf.equals("77777777777")) ||
                (cpf.equals("88888888888")) || (cpf.equals("99999999999")))
            return false;

        tempCpf = cpf.substring(0, 9);
        soma = 0;

        for (int i = 0; i < 9; i++) {
            int result = Integer.parseInt(String.valueOf(tempCpf.charAt(i)));
            soma += result * multiplicador1[i];
        }
        resto = soma % 11;
        if (resto < 2)
            resto = 0;
        else
            resto = 11 - resto;

        digito = String.valueOf(resto);
        tempCpf += digito;
        soma = 0;

        for (int i = 0; i < 10; i++) {
            int result = Integer.parseInt(String.valueOf(tempCpf.charAt(i)));
            soma += result * multiplicador2[i];
        }
        resto = soma % 11;
        if (resto < 2)
            resto = 0;
        else
            resto = 11 - resto;

        digito += String.valueOf(resto);
        return cpf.endsWith(digito);
    }

    /*
    * Metodo que verifica se a string é numerica para trabalhar com segurança e evitar crash.
    * */
    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /*
    * Método para validar celulares com 8 e 9 numeros
    * */
    public static boolean validateInvalidPhoneNumber(String number){

        number = number.replace("-","");

        if(number.length() < 4)
            return true;

        if(number.contains("(00)"))
            return true;

        number = number.substring(4,number.length());

        if(number.length() < 8)
            return true;

        else if(number.length() == 8){
            if(number.equals("00000000") || number.equals("11111111") || number.equals("22222222") || number.equals("33333333") ||
                    number.equals("44444444") || number.equals("55555555") || number.equals("66666666") ||
                    number.equals("77777777") || number.equals("88888888") || number.equals("99999999"))
                return true;
        }

        else if(number.length() == 9){
            if(number.equals("000000000") || number.equals("111111111") || number.equals("222222222") || number.equals("333333333") ||
                    number.equals("444444444") || number.equals("555555555") || number.equals("6666666666") ||
                    number.equals("777777777") || number.equals("888888888") || number.equals("999999999"))
                return true;
        }

        return false;
    }

    /*
    * Método para validar telefones inválidos
    * */
    public static boolean validateInvalidTelephone(String number){

        if(number.contains("_")) return true;

        number = number.replace("-","").replace("_","");


        if(number.length() < 4)
            return true;

        if(number.contains("(00)"))
            return true;

        number = number.substring(4,number.length());

        if(number.length() < 8)
            return true;

        else if(number.length() == 8){
            if(number.equals("00000000") || number.equals("11111111") || number.equals("22222222") || number.equals("33333333") ||
                    number.equals("44444444") || number.equals("55555555") || number.equals("66666666") ||
                    number.equals("77777777") || number.equals("88888888") || number.equals("99999999"))
                return true;
        }

        return false;
    }


    /*
    * Método para validar um número de cartão de crédito baseado no algoritmo de luhn
    * */
    public boolean validateCardNumber(String cardNumber) {
        int sum = 0;
        boolean alternate = false;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(cardNumber.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }

    /**
     * Método para detectar o tipo de cartão de crédito.
     * (Reconhece apenas VISA, MASTER, AMERICAN_EXPRESS, DINERS_CLUB, DISCOVER e JCB.
     */
    public String recognizeCardType(String cardNumber){

        String result = "Unknown";

        Map<String,String> patterns = new HashMap<>();

        String visaRegex = "^4[0-9]{12}(?:[0-9]{3})?$";  patterns.put(visaRegex, "Visa");
        String mastercardRegex = "^5[1-5][0-9]{14}$"; patterns.put(mastercardRegex, "Master Card");
        String americanexRegex = "^3[47][0-9]{13}$"; patterns.put(americanexRegex, "American Express");
        String dinersRegex = "^3(?:0[0-5]|[68][0-9])[0-9]{11}$"; patterns.put(dinersRegex,"Diners");
        String discoverRegex = "^6(?:011|5[0-9]{2})[0-9]{12}$"; patterns.put(discoverRegex,"Discover");
        String jcbRegex = "^(?:2131|1800|35\\\\d{3})\\\\d{11}$"; patterns.put(jcbRegex,"JCB");

        for(String regex : patterns.keySet()){
            if(Pattern.compile(regex).matcher(cardNumber).find()){

                // Encontrou
                result = patterns.get(regex);
                break;
            }
        }

        return result;
    }

}
