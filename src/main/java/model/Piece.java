package model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonProperty;

@EqualsAndHashCode(callSuper = true)
@Data
public class Piece extends Document {
    private String id;
    private String src;
    private String name;
    @BsonProperty(value = "src_img")
    private String srcImg;
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

    public void setPositionX(String positionX) {

        this.positionX = positionX != null ? positionX : "10.1";
    }

    public void setPositionY(String positionY) {

        this.positionY = positionY != null ? positionY : "0";
    }

    public void setPositionZ(String positionZ) {

        this.positionZ = positionZ != null ? positionZ : "0";
    }

}	
