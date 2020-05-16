package common.json.message;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MessagePacket {
	private String username;
	private LocalDateTime date;
	private String message;
	
	@JsonCreator
	public MessagePacket(@JsonProperty("user")String user, @JsonProperty("date")LocalDateTime date, @JsonProperty("message")String message) {
		this.username = user;
		this.date = date;
		this.message = message;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
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
		MessagePacket other = (MessagePacket) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	
	public String getUsername() {
		return username;
	}

	public LocalDateTime getDate() {
		return date;
	}


	public String getMessage() {
		return message;
	}
	
	@Override
	public String toString() {
		return "MessagePacket [username=" + username + ", date=" + date + ", message=" + message + "]";
	}
}
