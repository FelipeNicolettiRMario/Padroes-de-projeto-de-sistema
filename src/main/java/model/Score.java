package model;

import java.util.Date;

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


    public int getTentativas() {
        return tentativas;
    }

    public void setTentativas(int tentativas) {
        this.tentativas = tentativas;
    }

    public float getAproveitamento() {
        return aproveitamento;
    }

    public void setAproveitamento(float aproveitamento) {
        this.aproveitamento = aproveitamento;
    }

    public int getErros() {
        return erros;
    }

    public void setErros(int erros) {
        this.erros = erros;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    public int getAcertos() {
        return acertos;
    }

    public void setAcertos(int acertos) {
        this.acertos = acertos;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date data) {
        this.date = data;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Assembly getAssembly() {
        return assembly;
    }

    public void setAssembly(Assembly assembly) {
        this.assembly = assembly;
    }
}
