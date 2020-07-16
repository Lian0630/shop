package com.lian.shop.entity;
/**
 * 訂單商品實體類
 */
public class OrderItem extends BaseEntity{
	
	private static final long serialVersionUID = 2868191623779827803L;
	
	private Integer id;
	private Integer oid;
	private Long moneyId;
	private String moneyImage;
	private String moneyTitle;
	private Integer moneyCount;
	private Long moneyPrice;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOid() {
		return oid;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	public Long getMoneyId() {
		return moneyId;
	}
	public void setMoneyId(Long moneyId) {
		this.moneyId = moneyId;
	}
	public String getMoneyImage() {
		return moneyImage;
	}
	public void setMoneyImage(String moneyImage) {
		this.moneyImage = moneyImage;
	}
	public String getMoneyTitle() {
		return moneyTitle;
	}
	public void setMoneyTitle(String moneyTitle) {
		this.moneyTitle = moneyTitle;
	}
	public Integer getMoneyCount() {
		return moneyCount;
	}
	public void setMoneyCount(Integer moneyCount) {
		this.moneyCount = moneyCount;
	}
	public Long getMoneyPrice() {
		return moneyPrice;
	}
	public void setMoneyPrice(Long moneyPrice) {
		this.moneyPrice = moneyPrice;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", oid=" + oid + ", moneyId=" + moneyId + ", moneyImage=" + moneyImage
				+ ", moneyTitle=" + moneyTitle + ", moneyCount=" + moneyCount + ", moneyPrice=" + moneyPrice + "]";
	}
	
	
	
	
	
}
