package com.lesso.beans;

public class IMDiscovery {
    private int id;
    private String itemName;
    private String itemUrl;
    private int itemPriority;
    private int status;
    private long created;
    private long updated;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemUrl() {
		return itemUrl;
	}
	public void setItemUrl(String itemUrl) {
		this.itemUrl = itemUrl;
	}
	public int getItemPriority() {
		return itemPriority;
	}
	public void setItemPriority(int itemPriority) {
		this.itemPriority = itemPriority;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getCreated() {
		return created;
	}
	public void setCreated(long created) {
		this.created = created;
	}
	public long getUpdated() {
		return updated;
	}
	public void setUpdated(long updated) {
		this.updated = updated;
	}
    
    
}
