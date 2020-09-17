package model;

import lombok.Data;

@Data
public class Piece {
    private String id;
    private String src;
    private String srcImg;
    private String name;
    private String positionX;
    private String positionY;
    private String positionZ;

    public Piece(String id, String src, String srcImg, String name,
                 String positionX, String positionY, String positionZ) {
        setId(id);
        setSrc(src);
        setSrcImg(srcImg);
        setName(name);
        setPositionX(positionX);
        setPositionY(positionY);
        setPositionZ(positionZ);
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
