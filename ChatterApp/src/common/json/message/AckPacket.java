package common.json.message;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AckPacket {
	
 private String username;
 private String error;
 private int ack;
 
 @JsonCreator
 public AckPacket(@JsonProperty("user") String user, @JsonProperty("error") String error, @JsonProperty("ack") int ack) {
	 this.username = user;
	 this.error = error;
	 this.ack = ack;
	 
 }
  
 @Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ack;
	result = prime * result + ((error == null) ? 0 : error.hashCode());
	result = prime * result + ((username == null) ? 0 : username.hashCode());
	return result;
}
 
 @Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	AckPacket other = (AckPacket) obj;
	if (ack != other.ack)
		return false;
	if (error == null) {
		if (other.error != null)
			return false;
	} else if (!error.equals(other.error))
		return false;
	if (username == null) {
		if (other.username != null)
			return false;
	} else if (!username.equals(other.username))
		return false;
	return true;
}
 
 
 @Override
public String toString() {
	return "AckPacket [username=" + username + ", error=" + error + ", ack=" + ack + "]";
}

public String getUsername() {
	return username;
}

public String getError() {
	return error;
}

	public int getAck() {
		return ack;
	}

}
