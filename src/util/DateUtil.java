package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static Date converterStringParaData(String data) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			return sdf.parse(data);
		} catch (ParseException e) {
			System.out.println(
				String.format("Não foi possível converter a data %s para Date", data)
			);
			return null;
		}
	}
	
	public static String converterDateParaData(Date data) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(data);
	}
	
	public static Date converterStringParaDataHora(String data) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:SS");
			return sdf.parse(data);
		} catch (ParseException e) {
			System.out.println(
				String.format("Não foi possível converter a data %s para Date", data)
			);
			return null;
		}
	}
	
	public static String converterDateParaDataHora(Date data) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:SS");
		return sdf.format(data);
	}
	
    public static String getMesNumerico(String mesNome) {
        switch (mesNome) {
            case "Janeiro":
                return "01";
            case "Fevereiro":
                return "02";
            case "Março":
                return "03";
            case "Abril":
                return "04";
            case "Maio":
                return "05";
            case "Junho":
                return "06";
            case "Julho":
                return "07";
            case "Agosto":
                return "08";
            case "Setembro":
                return "09";
            case "Outubro":
                return "10";
            case "Novembro":
                return "11";
            case "Dezembro":
                return "12";
            default:
                return "01";
        }
    }
    
    public static String getMesNome(String mesNumero) {
        switch (mesNumero) {
            case "01":
                return "Janeiro";
            case "02":
                return "Fevereiro";
            case "03":
                return "Março";
            case "04":
                return "Abril";
            case "05":
                return "Maio";
            case "06":
                return "Junho";
            case "07":
                return "Julho";
            case "08":
                return "Agosto";
            case "09":
                return "Setembro";
            case "10":
                return "Outubro";
            case "11":
                return "Novembro";
            case "12":
                return "Dezembro";
            default:
                return "Janeiro";
        }
    }
}
