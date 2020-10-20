package model;

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

public class Assembly extends Document {

	@BsonProperty(value = "pieces")
	private LinkedList<Piece> pieces;
	@BsonProperty(value = "name")
    private String name;
    private Context context;

    public Assembly(Context context){
        this.context = context;
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
