package ao.martins.newspaper.domain.entity.enums;

public enum State {
	
APPROVED,REFUSED,APPLIED;
	
	public static State fromString(String str) {
		 
		if(str.equalsIgnoreCase("APPROVED"))
			return APPROVED;
		
		else if(str.equalsIgnoreCase("Refused"))
			return REFUSED;
		
		else if(str.equalsIgnoreCase("Applied"))
			return APPLIED;
		
			throw new IllegalArgumentException("Illegal type of state inserted");
		
	}

}
