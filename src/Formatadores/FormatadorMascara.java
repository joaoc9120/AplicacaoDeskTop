package Formatadores;

/**
 *
 * @author João Carlos Batista
 */
public class FormatadorMascara {

    public static String limpaMascTel(String tel) {

        tel = tel.replace("(", "");
        tel = tel.replace(")", "");
        tel = tel.replace("-", "");

        return tel;
    }

    public static String limpaMasc_Cpf_Cnpj(String Cpf_Cnpj) {

        Cpf_Cnpj = Cpf_Cnpj.replace(".", "");
        Cpf_Cnpj = Cpf_Cnpj.replace("-", "");
        Cpf_Cnpj = Cpf_Cnpj.replace("/", "");

        return Cpf_Cnpj;
    }

    public static String formatMascCpf(String cpf) {

        return (cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "."
                + cpf.substring(6, 9) + "-" + cpf.substring(9, 11));
    }

    public static String formatMascCnpj(String cnpj) {

        return (cnpj.substring(0, 2) + "." + cnpj.substring(2, 5) + "."
                + cnpj.substring(5, 8) + "/" + cnpj.substring(8, 12) + "-"
                + cnpj.substring(12, 14));
    }

    public static String formatMascTelRes(String tel) {

        if (tel.length() == 10) {

            tel = tel.substring(0, 0) + "(" + tel.substring(0, 2) + ")"
                    + tel.substring(2, 6) + "-" + tel.substring(6, 10);
        } else {
            if (tel.length() == 8) {
                tel = tel.substring(0, 4) + "-" + tel.substring(4, 8);
            } else {
                return tel;
            }
        }

        return tel;
    }

    public static String formatMascTelCel(String tel) {

        if (tel.length() == 11) {
            tel = (tel.substring(0, 0) + "(") + tel.substring(0, 2) + ")"
                    + tel.substring(2, 7) + "-" + tel.substring(7, 11);
        } else {
            if (tel.length() == 9) {
                tel = tel.substring(0, 5) + "-" + tel.substring(5, 9);

            }
        }
        return tel;
    }
}
