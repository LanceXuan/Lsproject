package com.lesso.beans;
/** * @author  Lance 
 * @date 创建时间：2015年12月26日 下午3:02:12 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  */
public class tbl_NoticesReceived {
public int Id;
public int NoticeId;
public int UserId;
public int Status;
public String Receivetime;
public String Created;
public int getId() {
	return Id;
}
public void setId(int id) {
	Id = id;
}
public int getNoticeId() {
	return NoticeId;
}
public void setNoticeId(int noticeId) {
	NoticeId = noticeId;
}
public int getUserId() {
	return UserId;
}
public void setUserId(int userId) {
	UserId = userId;
}
public int getStatus() {
	return Status;
}
public void setStatus(int status) {
	Status = status;
}
public String getReceivetime() {
	return Receivetime;
}
public void setReceivetime(String receivetime) {
	Receivetime = receivetime;
}
public String getCreated() {
	return Created;
}
public void setCreated(String created) {
	Created = created;
}

}
