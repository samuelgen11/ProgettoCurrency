package node.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrencyDto {
	@JsonProperty("CurrencyResponse")	
	
	private int id;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String pair1;
	private String pair2;
	private Long timestamp;
	private Double last;
	
	
	
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
