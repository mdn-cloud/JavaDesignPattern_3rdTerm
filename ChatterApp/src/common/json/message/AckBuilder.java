package common.json.message;

public class AckBuilder {

	private String user;
	private String error;
	private int ack = Integer.MAX_VALUE;
	
	AckBuilder() {
		
	}
	
	public AckBuilder setUser(String user) {
		this.user = user;
		return this;
	}
	
	
	public AckBuilder setError(String error) {
		this.error = error;
		return this;
	}
	
	public AckBuilder setAck(int ack) {
		this.ack = ack;
		return this;
	}
	
	public AckPacket build() {
		if(user == null || user.isEmpty()) {
			throw new IllegalArgumentException("User can't be null or empty.");
			
		}
		
		if(error != null && error.isEmpty()) {
			throw new IllegalArgumentException("Error can't be empty.");
			
		}
		
		if(ack == Integer.MAX_VALUE) {
			throw new IllegalArgumentException("Acknoldgement can't be empty.");
			
		}
		
		AckPacket ackPack = new AckPacket(user, error, ack);
		
		
		return ackPack;
		
	}
}
