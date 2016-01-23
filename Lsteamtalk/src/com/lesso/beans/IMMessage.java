package com.lesso.beans;

public class IMMessage {
	  private int id;
	     private int relateId;
	     private int fromId;
	     private int toId;
	     private int msgId;
	     private String content;
	     private int type;
	     private int status;
	     private long updated;
	     private long created;
	     
	     //不存在数据库的字段
	     private String frompeople;
	     private String topeople;
	     
	     
		public String getFrompeople() {
			return frompeople;
		}
		public void setFrompeople(String frompeople) {
			this.frompeople = frompeople;
		}
		public String getTopeople() {
			return topeople;
		}
		public void setTopeople(String topeople) {
			this.topeople = topeople;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getRelateId() {
			return relateId;
		}
		public void setRelateId(int relateId) {
			this.relateId = relateId;
		}
		public int getFromId() {
			return fromId;
		}
		public void setFromId(int fromId) {
			this.fromId = fromId;
		}
		public int getToId() {
			return toId;
		}
		public void setToId(int toId) {
			this.toId = toId;
		}
		public int getMsgId() {
			return msgId;
		}
		public void setMsgId(int msgId) {
			this.msgId = msgId;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public int getType() {
			return type;
		}
		public void setType(int type) {
			this.type = type;
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public long getUpdated() {
			return updated;
		}
		public void setUpdated(long updated) {
			this.updated = updated;
		}
		public long getCreated() {
			return created;
		}
		public void setCreated(long created) {
			this.created = created;
		}
	     
	     
}
