package sub1;

import javax.servlet.jsp.JspWriter;

public class Account {
	
	private String bank;
	private String id;
	private String name;
	private int money;
	
	public Account(String bank, String id, String name, int money) {
		
		this.bank = bank;
		this.id = id;
		this.name = name;
		this.money = money;
	}
	
	public void deposit(int _money) {
		money += _money;
		
	}
	
	public void withdraw(int _money) {
		money -= _money;
	}
	
	public void show(JspWriter out) throws Exception{
		out.println("<h4> ÇöÀçÀÜ¿¢ </h4>");
		out.println("<p>");
		out.println("ÀºÇà¸í : "+bank+"<br/>");
		out.println("°èÁÂ¹øÈ£ : "+id+"<br/>");
		out.println("ÀÔ±İÁÖ : "+name+"<br/>");
		out.println("ÇöÀçÀÜ¾× : "+money+"<br/>");
		out.println("</p>");
	}
}
