package model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.Document;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data

public class Score extends Document {

    private int tentativas;
    private float aproveitamento;
    private int erros;
    private int ordem;
    private int acertos;
    private Date date;
    private User user;
    private Assembly assembly;
    private Context context;

    public Score(Context context) {
        this.context = context;
    }

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
