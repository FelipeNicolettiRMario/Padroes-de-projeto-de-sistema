package model;

import com.mongodb.client.FindIterable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
	@BsonProperty(value = "pieces")
	private List<ObjectId> pieces;
	@BsonProperty(value = "name")
    private String name;
    private Context context;

    public Assembly(Context context){
        this.context = context;
    }

    public Assembly() {

    }

    public Document create(Assembly assembly) {
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

    @Override
    public LinkedList<Document> fetch() {
        return null;
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

    public Assembly addAssembly(){
        Assembly assembly = new Assembly();
        assembly.setId(new ObjectId());

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
