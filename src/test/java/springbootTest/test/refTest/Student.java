package springbootTest.test.refTest;

public class Student {
	private String id;
	
	private String name;
 
	public String getId() {
		return id;
	}
 
	public void setId(String id) {
		this.id = id;
	}
 
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
	
	public void show(){
		System.out.println(id+":"+name);
	}
}