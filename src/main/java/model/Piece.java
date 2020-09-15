package model;

public class Piece {
    private String id;
    private String src;
    private String src_img;
    private String name;
    private String positionX;
    private String positionY;
    private String positionZ;

    public Piece() {

    }

    public Piece(String id, String src, String src_img, String name,
                 String positionX, String positionY, String positionZ) {
        setId(id);
        setSrc(src);
        setSrcImg(src_img);
        setName(name);
        setPositionX(positionX);
        setPositionY(positionY);
        setPositionZ(positionZ);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSrc() {
        return this.src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getSrcImg() {
        return this.src_img;
    }

    public void setSrcImg(String src_img) {
        this.src_img = src_img;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPositionX() {
        return this.positionX;
    }

    public void setPositionX(String positionX) {

        this.positionX = positionX != null ? positionX : "10.1";
    }

    public String getPositionY() {
        return this.positionY;
    }

    public void setPositionY(String positionY) {

        this.positionY = positionY != null ? positionY : "0";
    }

    public String getPositionZ() {
        return this.positionZ;
    }

    public void setPositionZ(String positionZ) {

        this.positionZ = positionZ != null ? positionZ : "0";
    }

}	
