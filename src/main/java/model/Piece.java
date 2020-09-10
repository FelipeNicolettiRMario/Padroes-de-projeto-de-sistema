package model;

class Piece {
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
		setSrcimg(src_img);
		setName(name);
		setPositionx(positionX);
		
		
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
	
	public String getSrcimg() {
		return this.src_img;
	}
	
	public void setSrcimg(String src_img) {
		this.src_img = src_img;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPositionx() {
		return this.positionX;
	}
	
	public void setPositionx(String positionX) {
		
		this.positionX = positionX;
		this.positionX = positionX != null ? positionX:"10.1";
	}
	
	public String getPositiony() {
		return this.positionY;
	}
	
	public void setPositiony(String positionY) {
		
		this.positionY = positionY;
		this.positionY = positionY != null ? positionY:"0";
	}
	
	public String getPositionz() {
		return this.positionZ;
	}
	
	public void setPositionz(String positionZ) {
		
		this.positionZ = positionZ;
		this.positionZ = positionZ != null ? positionZ:"0";
	}

}	
