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
	private List<Piece> pieces;
	@BsonProperty(value = "name")
    private String name;
    private Context context;

    public Assembly(Context context){
        this.context = context;
    }

    public Assembly(LinkedList<Piece> listPieces, String name) {
        this.name = name;
        this.pieces = listPieces;
    }

    public Document create(Assembly assembly) {
    	assembly.setId(new ObjectId());
    	Document toInsert = new Document();
    	toInsert.append("_id", assembly.getId());
    	toInsert.append("pieces", assembly.getName());
    	toInsert.append("name", assembly.getPieces());
    	context.assemblies.insertOne(assembly);
    	return context.assemblies.find(eq("_id", assembly.getId())).first();
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
        var cursor = context.assemblies.find().cursor();
        var list = new LinkedList<Document>();
        while (cursor.hasNext()) {
            var document = cursor.next();
            list.add(document);
        }
        return list;
    }

    public Assembly read(ObjectId id) {
    	return context.assemblies.find(eq("_id", id), Assembly.class).first();
    }

    public void delete(ObjectId id) {
    	context.assemblies.deleteOne(eq("_id", id));
    }

    public List<Piece> getPieces() {
    	return StreamSupport.stream(context.pieces.find(eq("_id", this.pieces.toArray()), Piece.class).spliterator(), false).collect(Collectors.toList());
    }


    public FindIterable<Document> searchAssembly(ObjectId assemblyId){
        Document criteria = new Document("_id",assemblyId);

        return context.assemblies.find(criteria);
    }


}
