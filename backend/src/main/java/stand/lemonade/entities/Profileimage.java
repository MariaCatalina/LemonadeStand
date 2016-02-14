package stand.lemonade.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the profileimage database table.
 * 
 */
@Entity
@NamedQuery(name="Profileimage.findAll", query="SELECT p FROM Profileimage p")
public class Profileimage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private byte[] image;

	// bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	public Profileimage() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getImage() {
		return this.image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}