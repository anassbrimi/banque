package ma.ensa.banque.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "users")
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long idUser;
	private String userName;
	private String password;
	private boolean actived;
	
	//Gestion Des Reations
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private Collection<Role> roles;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}



	public User(String userName, String password, boolean actived) {
		super();
		this.userName = userName;
		this.password = password;
		this.actived = actived;
	}



	public boolean isActived() {
		return actived;
	}



	public void setActived(boolean actived) {
		this.actived = actived;
	}



	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Role> getRoles() {
		if(roles == null)
			roles = new ArrayList<Role>();
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}



	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", userName=" + userName
				+ ", password=" + password + ", actived=" + actived
				+ ", roles=" + roles + "]";
	}
	
	
	
	
	

}
