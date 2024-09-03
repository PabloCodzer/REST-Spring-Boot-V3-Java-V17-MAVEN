package TREST.Models;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User implements UserDetails, Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "user_name", unique = true)
	private String user_name;
	
	@Column(name = "full_name")
	private String full_name;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "account_non_expired")
	private boolean account_non_expired;
	
	@Column(name = "account_non_locked")
	private boolean account_non_locked;
	
	@Column(name = "credentials_non_expired")
	private boolean credentials_non_expired;
	
	@Column(name = "enabled")
	private boolean enabled;
	
	
	public User() {}


	
	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getAccountNonExpired() 
	{
		return this.account_non_expired;
	}
	
	public boolean getAccountNonLocked() 
	{
		return this.account_non_locked;
	}
	
	public boolean getCredentialsNonExpired() 
	{
		return this.credentials_non_expired;
	}
	
	public boolean itsEnabled() 
	{
		return this.enabled;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public boolean isAccount_non_expired() {
		return account_non_expired;
	}

	public void setAccount_non_expired(boolean account_non_expired) {
		this.account_non_expired = account_non_expired;
	}

	public boolean isAccount_non_locked() {
		return account_non_locked;
	}

	public void setAccount_non_locked(boolean account_non_locked) {
		this.account_non_locked = account_non_locked;
	}

	public boolean isCredentials_non_expired() {
		return credentials_non_expired;
	}

	public void setCredentials_non_expired(boolean credentials_non_expired) {
		this.credentials_non_expired = credentials_non_expired;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.user_name;
	}
	
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
}
