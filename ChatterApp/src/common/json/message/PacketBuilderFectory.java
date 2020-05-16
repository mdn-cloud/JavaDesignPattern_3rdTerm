package common.json.message;

public class PacketBuilderFectory {

	private static final String PACKAGE = "common.json.message.";

	private PacketBuilderFectory() {

	}
	
	public static <T> T createFactory(String builderName) {
		
		try{Class<?> clazz = Class.forName(PACKAGE + builderName);
		Object builder = clazz.newInstance();
		return (T) builder;
		}catch(InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
			throw new IllegalArgumentException("bad builder name: " + builderName, ex);
		}
	}
	
	public static <T> T createFactory(Class<T> type) {
		return createFactory(type.getSimpleName());
		
	}
}
