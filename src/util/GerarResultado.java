package util;

import java.io.IOException;
import java.util.List;
import bd.ConcursoBD;
import entidade.Aposta;
import entidade.Concurso;

public class GerarResultado {
    private static final double PORCENTAGEM_15 = 68.0;
    private static final double PORCENTAGEM_14 = 32.0;
    private static final double PORCENTAGEM_DISTRIBUIDOS = 43.35;

    public void calcularResultado(Concurso concurso) throws ClassNotFoundException, IOException {
        List<Aposta> listaAposta = concurso.getApostas();
        concurso.setValorConcurso(calcularArrecadacaoConcurso(concurso, listaAposta));
        concurso.setValorConcurso(calcularValorDistribuivel(concurso));
        calcularAcertos(concurso, listaAposta);
        verificarSeAcumulou(concurso, listaAposta);
        calcularPremioFixo(concurso, listaAposta);
        calcularPremioVariavel(concurso, listaAposta);
        calcularLucroConcurso(concurso, listaAposta);
    }
    
    public double calcularLucroConcurso(Concurso concurso, List<Aposta> apostas) {
        double valorTotalArrecadado = calcularArrecadacaoConcurso(concurso, apostas);
        double porcentagemLoteria = valorTotalArrecadado * 0.5665;
        double premioFixoTotal = calcularPremioFixoTotal(apostas);

        int ganhadores15 = 0;
        int ganhadores14 = 0;

        for (Aposta aposta : apostas) {
            if (aposta.getQuantidadeAcertos() == 15) {
                ganhadores15++;
            } else if (aposta.getQuantidadeAcertos() == 14) {
                ganhadores14++;
            }
        }

        if (ganhadores15 > 0) {
            return porcentagemLoteria - (premioFixoTotal * 0.68);
        } else if (ganhadores14 > 0) {
            return porcentagemLoteria - (premioFixoTotal * 0.32);
        } else if (premioFixoTotal > 0) {
            return porcentagemLoteria - premioFixoTotal;
        } else {
            return porcentagemLoteria;
        }
    }

    private double calcularPremioFixoTotal(List<Aposta> apostas) {
        double totalPremioFixo = 0;
        for (Aposta aposta : apostas) {
            int acertos = aposta.getQuantidadeAcertos();
            switch (acertos) {
                case 11:
                    totalPremioFixo += 6.00;
                    break;
                case 12:
                    totalPremioFixo += 12.00;
                    break;
                case 13:
                    totalPremioFixo += 30.00;
                    break;
                default:
                    break;
            }
        }
        return totalPremioFixo;
    }

    
    @SuppressWarnings("unused")
    public static double retornaAcumulado(Concurso concurso, double valor) throws ClassNotFoundException, IOException {
        double totalAcumulado = 0;
        ConcursoBD concuroBd = new ConcursoBD();
        List<Concurso> listaConcurso = ConcursoBD.consultar();
        for (Concurso concursoo : listaConcurso) {
            if (concursoo.isAcumulado()) {
                totalAcumulado += concursoo.getValorConcurso();
                concursoo.setAcumulado(false);
            }
        }
        return valor + totalAcumulado;
    }
    
    public void verificarSeAcumulou(Concurso concurso, List<Aposta> listaAposta) {
        int acertosAcimaDeOnze = 0;
        for (Aposta aposta : listaAposta) {
            if(aposta.getQuantidadeAcertos() >= 11) {
            	acertosAcimaDeOnze++;
                break;
            }
        }
        if(acertosAcimaDeOnze == 0) {
            concurso.setAcumulado(true);    
        } else {
            concurso.setAcumulado(false);
        }
    }
    
    public static double calcularValorDistribuivel(Concurso concurso) {
        double valorTotal = calcularArrecadacaoConcurso(concurso, concurso.getApostas());
        double resultado = (valorTotal * PORCENTAGEM_DISTRIBUIDOS) / 100;
        return resultado;
    }
    
    public void calcularAcertos(Concurso concurso, List<Aposta> listaAposta) {
        List<Integer> numerosSorteados = concurso.getNumerosSorteados();
        for (Aposta aposta : listaAposta) {
            int quantAcertos = 0;
            for (Integer numero : aposta.getNumerosApostados()) {
                if (numerosSorteados.contains(numero)) {
                    quantAcertos++;
                }
            }
            aposta.setQuantidadeAcertos(quantAcertos);
        }
    }
    
    @SuppressWarnings("unused")
    public void calcularPremioFixo(Concurso concurso, List<Aposta> apostas) {
        double totalPremiosFixos = 0;
        for (Aposta aposta : apostas) {
            double valorPremio = 0;
            switch (aposta.getQuantidadeAcertos()) {
                case 11: 
                    valorPremio = 6.00;
                    aposta.setValorGanho(valorPremio);
                    totalPremiosFixos += valorPremio;
                    break;
                case 12:
                    valorPremio = 12.00;
                    aposta.setValorGanho(valorPremio);
                    totalPremiosFixos += valorPremio;
                    break;
                case 13:
                    valorPremio = 30.00;
                    aposta.setValorGanho(valorPremio);
                    totalPremiosFixos += valorPremio;
                    break;
                default:
                    break;
            }
        }
        concurso.setValorConcurso(concurso.getValorConcurso() - totalPremiosFixos);
    }

    public static double calcularArrecadacaoConcurso(Concurso concurso, List<Aposta> apostas) {
        double valorConcurso = 0;
        for (Aposta aposta : apostas) {
            valorConcurso += aposta.getValorPago();
        }
        return valorConcurso;
    }
    
    public void premioQuinzeAcertos(Concurso concurso, int ganhadores15, double valor, List<Aposta> apostas) throws ClassNotFoundException, IOException {
        if (ganhadores15 > 0) {
            valor = retornaAcumulado(concurso, valor);
            double premioPorGanhador = valor / ganhadores15;
            String valorFormatado = formatarValor(premioPorGanhador);
            for (Aposta aposta : apostas) {
                if (aposta.getQuantidadeAcertos() == 15) {
                    double valorFinal = Double.parseDouble(valorFormatado.replace(',', '.'));
                    aposta.setValorGanho(valorFinal);
                }
            }
        } else {
            System.out.println("Nenhum ganhador de 15 acertos.");
        }
    }

    public void premioQuatorzeAcertos(Concurso concurso, int ganhadores14, double valor, List<Aposta> apostas) {
        if (ganhadores14 > 0) {
            double premioPorGanhador = valor / ganhadores14;
            String valorFormatado = formatarValor(premioPorGanhador);
            for (Aposta aposta : apostas) {
                if (aposta.getQuantidadeAcertos() == 14) {
                    double valorFinal = Double.parseDouble(valorFormatado.replace(',', '.'));
                    aposta.setValorGanho(valorFinal);
                }
            }
        } else {
            System.out.println("Nenhum ganhador de 14 acertos.");
        }
    }
    
    public void calcularPremioVariavel(Concurso concurso, List<Aposta> apostas) throws ClassNotFoundException, IOException {
        int ganhadores14 = 0;
        int ganhadores15 = 0;
        double Premio = concurso.getValorConcurso();
        double valor1 = Premio * (PORCENTAGEM_15 / 100);
        double valor2 = Premio * (PORCENTAGEM_14 / 100);
        for (Aposta aposta : apostas) {
            switch (aposta.getQuantidadeAcertos()) {
                case 14:
                    ganhadores14++;
                    break;
                case 15:
                    ganhadores15++;
                    break;
                default:
                    break;
            }
        }
        premioQuatorzeAcertos(concurso, ganhadores14, valor2, apostas);
        premioQuinzeAcertos(concurso, ganhadores15, valor1, apostas);
    }
    
    public String formatarValor(double valor) {
        return String.format("%.2f", valor);
    }
}
