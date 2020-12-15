public class StringBuilder_Insert {
	public static void main(String[] args) {	
		Object objetoRef = "Maria";
		String String = "Joaquina";
		char[] conjArray = {'S','i','l','v','a'};

		StringBuilder buffer = new StringBuilder();	
		buffer.insert(buffer.length(), objetoRef);
		buffer.insert(buffer.length(), " ");
		buffer.insert(buffer.length(), String);
		buffer.insert(buffer.length(), " ");
		buffer.insert(buffer.length(), conjArray);
		buffer.insert(buffer.length(), " ");
		
		System.out.printf("Valores:\n%s\n\n", buffer.toString());	
	}
}