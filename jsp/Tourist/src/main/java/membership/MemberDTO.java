package membership;

import java.sql.Date;

public class MemberDTO {
	private String email_id;
	private String email_address;
	private String name;
	private String password;
	private String tel;
	private String gender;
	private String agree;
	private String content;
	private java.sql.Date regidate;
	
	public MemberDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public MemberDTO(String email_id, String email_address, String name, String password, String tel, String gender,
			String agree, String content, Date regidate) {
		super();
		this.email_id = email_id;
		this.email_address = email_address;
		this.name = name;
		this.password = password;
		this.tel = tel;
		this.gender = gender;
		this.agree = agree;
		this.content = content;
		this.regidate = regidate;
	}

	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getEmail_address() {
		return email_address;
	}
	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAgree() {
		return agree;
	}
	public void setAgree(String agree) {
		this.agree = agree;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegidate() {
		return regidate;
	}
	public void setRegidate(Date regidate) {
		this.regidate = regidate;
	}
	
	
}
