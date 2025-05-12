package util;

import java.time.LocalDate;
import java.time.Month;
import java.time.DateTimeException;

public class ValidadorDeData {

    public static boolean verificarDataNascimento(String dia, String mes, String ano) {
        
        if (dia == null || mes == null || ano == null || dia.equals("Dia") || mes.equals("Mês") || ano.equals("Ano")) {
            return false;
        }

        Month mesEnum = null;
        switch (mes) {
        case "Janeiro": mesEnum = Month.JANUARY; break;
        case "Fevereiro": mesEnum = Month.FEBRUARY; break;
        case "Março": mesEnum = Month.MARCH; break;
        case "Abril": mesEnum = Month.APRIL; break;
        case "Maio": mesEnum = Month.MAY; break;
        case "Junho": mesEnum = Month.JUNE; break;
        case "Julho": mesEnum = Month.JULY; break;
        case "Agosto": mesEnum = Month.AUGUST; break;
        case "Setembro": mesEnum = Month.SEPTEMBER; break;
        case "Outubro": mesEnum = Month.OCTOBER; break;
        case "Novembro": mesEnum = Month.NOVEMBER; break;
        case "Dezembro": mesEnum = Month.DECEMBER; break;
        default:
            return false;
        }

        try {
            int diaInt = Integer.parseInt(dia);
            int anoInt = Integer.parseInt(ano);

            LocalDate data = LocalDate.of(anoInt, mesEnum, diaInt);
            return true;
        } catch (DateTimeException | NumberFormatException e) {
            return false;
        }
    }
    
    public static boolean verificarDataFutura(String dia, String mes, String ano) {
        if (dia == null || mes == null || ano == null || dia.equals("Dia") || mes.equals("Mês") || ano.equals("Ano")) {
            return false;
        }

        Month mesEnum = null;
        switch (mes) {
            case "Janeiro": mesEnum = Month.JANUARY; break;
            case "Fevereiro": mesEnum = Month.FEBRUARY; break;
            case "Março": mesEnum = Month.MARCH; break;
            case "Abril": mesEnum = Month.APRIL; break;
            case "Maio": mesEnum = Month.MAY; break;
            case "Junho": mesEnum = Month.JUNE; break;
            case "Julho": mesEnum = Month.JULY; break;
            case "Agosto": mesEnum = Month.AUGUST; break;
            case "Setembro": mesEnum = Month.SEPTEMBER; break;
            case "Outubro": mesEnum = Month.OCTOBER; break;
            case "Novembro": mesEnum = Month.NOVEMBER; break;
            case "Dezembro": mesEnum = Month.DECEMBER; break;
            default:
                return false;
        }

        try {
            int diaInt = Integer.parseInt(dia);
            int anoInt = Integer.parseInt(ano);

            LocalDate dataConcurso = LocalDate.of(anoInt, mesEnum, diaInt);
            
            // Verifica a data de hoje
            LocalDate dataAtual = LocalDate.now();
            
            if (dataConcurso.isEqual(dataAtual)) {
                return false;
            }
            
            if (dataConcurso.isAfter(dataAtual)) {
                if (dataConcurso.isBefore(dataAtual.plusDays(1))) {
                    return false; // Caso a data seja no mesmo dia ou no dia seguinte, é inválido
                }
                return true;
            }
            return false;
        } catch (DateTimeException | NumberFormatException e) {
            return false;
        }
    }
    
}
