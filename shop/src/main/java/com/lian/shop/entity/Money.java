package com.lian.shop.entity;
/**
 * 商品數據
 */
public class Money extends BaseEntity{
	
	
	private static final long serialVersionUID = 19943392586156573L;
	
	private Long id;
	private String title;
	private String image;
	private String sellPoint;
	private Long price;
	private Integer num;
	private String barcode;
	private Integer status;
	private Integer priority;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getSellPoint() {
		return sellPoint;
	}
	public void setSellPoint(String sellPoint) {
		this.sellPoint = sellPoint;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "money [id=" + id + ", title=" + title + ", image=" + image + ", sellPoint=" + sellPoint + ", price="
				+ price + ", num=" + num + ", barcode=" + barcode + ", status=" + status + ", priority=" + priority
				+ "]";
	}

	
	
}
