package model;

import static com.mongodb.client.model.Filters.eq;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import com.mongodb.client.ClientSession;
import com.mongodb.client.FindIterable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import javax.print.Doc;
import java.util.LinkedList;

import static com.mongodb.client.model.Filters.eq;

@EqualsAndHashCode(callSuper = true)
@Data

public class Assembly extends Document implements IEntity<Assembly> {
    /**
	 * 
	 */
	private static final long serialVersionUID = -9103792658302394297L;
	@BsonProperty(value = "_id")
	private ObjectId id;
	private List<ObjectId> pieces;


	@BsonProperty(value = "pieces")
	private LinkedList<Piece> pieces;
	@BsonProperty(value = "name")

    private String name;
    private Context context;

    public Assembly(Context context){
        this.context = context;
    }
    
    public Assembly create(Assembly assembly) {
    	assembly.setId(new ObjectId());
    	context.assemblies.insertOne(assembly);
    	return context.assemblies.find(eq("_id", assembly.getId()), Assembly.class).first();
    	
    }

    public Assembly update(Assembly updatedAssembly) {
    	Assembly assembly = context.assemblies.find(eq("_id", id), Assembly.class).first();
        if(assembly == null) {
            throw new RuntimeException("Assembly not found");
        }
    	updatedAssembly.setId(assembly.getId());
    	context.assemblies.updateOne(eq("_id", assembly.getId()), updatedAssembly);
    	return context.assemblies.find(eq("_id", assembly.getId()), Assembly.class).first();
    }

    public Assembly read(ObjectId id) {
    	return context.assemblies.find(eq("_id", id), Assembly.class).first();
    }

    public void delete(ObjectId id) {
    	context.assemblies.deleteOne(eq("_id", id));
    }
    
    public void setPieces(List<Piece> pieces) {
    	this.pieces = pieces.stream().map(Piece::getId).collect(Collectors.toList());
    }
    
    public List<Piece> getPieces() {
    	return StreamSupport.stream(context.pieces.find(eq("_id", this.pieces.toArray()), Piece.class).spliterator(), false).collect(Collectors.toList());
    }
    
    public Document addAssembly(){
        Document assembly = new Document("_id",new ObjectId());

        assembly.append("pieces",this.pieces)
                .append("name",this.name);


        context.assemblies.insertOne(assembly);

        return assembly;
    }

    public FindIterable<Document> searchAssembly(ObjectId assemblyId){
        Document criteria = new Document("_id",assemblyId);

        return context.assemblies.find(criteria);
    }


}
