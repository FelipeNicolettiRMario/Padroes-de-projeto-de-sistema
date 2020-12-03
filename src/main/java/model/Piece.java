package model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.LinkedList;

import static com.mongodb.client.model.Filters.eq;

@EqualsAndHashCode(callSuper = true)
@Data
public class Piece extends Document implements IEntity<Piece> {
    /**
     * 
     */
    private static final long serialVersionUID = -5630351000707414593L;
    @BsonProperty(value = "_id")
    private ObjectId id;
    @BsonProperty(value = "src")
    private String src;
    @BsonProperty(value = "name")
    private String name;
    @BsonProperty(value = "src_img")
    private String src_img;
    @BsonProperty(value = "position_x")
    private String positionX;
    @BsonProperty(value = "position_y")
    private String positionY;
    @BsonProperty(value = "position_z")
    private String positionZ;

    private Context context;

    public Piece(Context context) {
        this.context = context;
    }

    public Piece(String name, String src, String src_img, String positionX, String positionY, String positionZ) {
        this.name = name;
        this.src =src;
        this.src_img = src_img;
        this.positionX = positionX;
        this.positionY = positionY;
        this.positionZ = positionZ;
    }

    public void setPositionX(String positionX) {

        this.positionX = positionX != null ? positionX : "10.1";
    }

    public void setPositionY(String positionY) {

        this.positionY = positionY != null ? positionY : "0";
    }

    public void setPositionZ(String positionZ) {

        this.positionZ = positionZ != null ? positionZ : "0";
    }

    public Document create(Piece piece) {
        piece.setId(new ObjectId());
        context.pieces.insertOne(piece);
        return context.pieces.find(eq("_id", piece.getId()), Piece.class).first();

    }

    public Piece update(Piece updatedPiece) {
        Piece piece = context.pieces.find(eq("_id", id), Piece.class).first();
        if (piece == null) {
            throw new RuntimeException("Piece not found");
        }
        updatedPiece.setId(piece.getId());
        context.pieces.updateOne(eq("_id", piece.getId()), updatedPiece);
        return context.pieces.find(eq("_id", piece.getId()), Piece.class).first();
    }

    @Override
    public LinkedList<Document> fetch() {
        return null;
    }

    public Piece read(ObjectId id) {
        return context.pieces.find(eq("_id", id), Piece.class).first();
    }

    public void delete(ObjectId id) {
        context.pieces.deleteOne(eq("_id", id));
    }

}
