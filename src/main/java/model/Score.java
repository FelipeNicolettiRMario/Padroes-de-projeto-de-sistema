package model;

import lombok.Data;

import java.util.Date;
@Data

public class Score {

    private int tentativas;
    private float aproveitamento;
    private int erros;
    private int ordem;
    private int acertos;
    private Date date;
    private User user;
    private Assembly assembly;

    public Score(int erros, int ordem, int acertos, User user, Assembly assembly) {
        setErros(erros);
        setAcertos(acertos);
        setOrdem(ordem);
        setTentativas(erros + acertos);
        setAproveitamento((float) acertos / tentativas);
        setUser(user);
        setAssembly(assembly);
        setDate(new Date());
    }
}
