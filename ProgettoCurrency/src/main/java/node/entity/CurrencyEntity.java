package node.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CURRENCY")
public class CurrencyEntity {

	@Id
	@Column(name="id_currency")
	private int id;
    @Column(name="pair1")
	private String pair1;
   
	@Column (name ="pair2")
	private String pair2;
	@Column(name="times")
	private Long timestamp;
	@Column (name="lastvalue")
	private Double last;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Double getLast() {
		return last;
	}

	public void setLast(Double last) {
		this.last = last;
	}

	public String getPair1() {
		return pair1;
	}

	public void setPair1(String pair1) {
		this.pair1 = pair1;
	}

	public String getPair2() {
		return pair2;
	}

	public void setPair2(String pair2) {
		this.pair2 = pair2;
	}

	
     
}
