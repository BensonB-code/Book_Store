package bbsapi.domain.model;

public class ChargeReponse {
	
	private String chargeId;
	private Integer amount;
	
	
	public ChargeReponse() {
	}


	public ChargeReponse(String chargeId, Integer amount) {
		this.chargeId = chargeId;
		this.amount = amount;
	}


	public String getChargeId() {
		return chargeId;
	}


	public void setChargeId(String chargeId) {
		this.chargeId = chargeId;
	}


	public Integer getAmount() {
		return amount;
	}


	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	
	
	

}
