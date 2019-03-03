package test2;

import java.io.Serializable;

/**
 *	对象要可序列化，要实现Serializer接口，往往要重写serialVersionUID
 * @author qiang
 *
 */
public class Role implements Serializable {
	//序列化的版本编号
	private static final long serialVersionUID = 79797977987987977L;
	
	private long id;
	private String roleName;
	private String note;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	

}
