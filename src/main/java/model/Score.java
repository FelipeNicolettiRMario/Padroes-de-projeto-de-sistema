package model;

import static com.mongodb.client.model.Filters.eq;

import java.util.Date;

import org.bson.Document;
import org.bson.types.ObjectId;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data

public class Score extends Document implements IEntity<Score>{

    /**
	 * 
	 */
	private static final long serialVersionUID = 3161525073342614633L;
	private ObjectId id;
	private ObjectId user;
	private ObjectId assembly;
	private int tentativas;
    private float aproveitamento;
    private int erros;
    private int ordem;
    private int acertos;
    private Date date;
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
    
    public Score create(Score score) {
    	score.setId(new ObjectId());
    	context.scores.insertOne(score);
    	return context.scores.find(eq("_id", score.getId()), Score.class).first();
    }

    public Score update(Score updatedScore) {
    	Score score = context.scores.find(eq("_id", id), Score.class).first();
        if(score == null) {
            throw new RuntimeException("Score not found");
        }
    	updatedScore.setId(score.getId());
    	context.scores.updateOne(eq("_id", score.getId()), updatedScore);
    	return context.scores.find(eq("_id", score.getId()), Score.class).first();
    }

    public Score read(ObjectId id) {
    	return context.scores.find(eq("_id", id), Score.class).first();
    }

    public void delete(ObjectId id) {
    	context.scores.deleteOne(eq("_id", id));
    }
    
    public void setUser(User user) {
    	this.user = user.getId();
    }
    
    public User getUser() {
    	return context.users.find(eq("_id", user), User.class).first();
    }
    
    public void setAssembly(Assembly assembly) {
    	this.assembly = assembly.getId();
    }
    
    public Assembly getAssembly() {
    	return context.assemblies.find(eq("_id", assembly), Assembly.class).first();
    }
}
