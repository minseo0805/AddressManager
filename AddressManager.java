import java.io.*;

public class AddressManager {
	public static void main(String[] args) throws Exception{
		AddressList ad1 = new AddressList();
		ad1.open();
	}
}
class AddressList{
	BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

	Person person1[] = new Person[3];
	int i=0; 

	String name = null; 
	boolean gender = true;
	String phone = null;
	int age = 0;
	String addr = null;
	String etc = null;
	
	//0. ����
	void open() throws Exception{
		int inputNum =0; // �޴� ������ �ޱ� ���� integer ����
		boolean re=false; //�Է� ���� ���ڰ� 1~6�� �ƴ� ��� �ٽ� �Է� �ޱ� ����
		System.out.println("==========* �ּҷ� ���� ���α׷� *============");
		while(!re){
			re = true;
			System.out.println("1.���, 2.����, 3.����, 4.�˻�, 5.���, 6.����");
			System.out.print("�޴� ���� : ");
	
			try {
				inputNum = Integer.parseInt(stdin.readLine());
			} catch (NumberFormatException e) {
				re = false;
			} catch (IOException e) {
				System.out.println("�ٽ� �Է����ּ���.");
				re = false;
			}
		
			switch(inputNum){
			case 1 : inputPerson(); open();break;
			case 2 : edit(); open(); break;
			case 3 : delete(); open(); break;
			case 4 : search(); open(); break;
			case 5 : display(); open(); break;
			case 6 : end(); break;
			default : {System.out.println("�ٽ� �Է����ּ���");re=false; break;}
			}
		}
	}
	//1.���
	void inputPerson() throws Exception{		
		boolean re=false;
		
		//�ּҷϿ� ������ (�迭) �ڸ��� ����ִ��� Ȯ�� �Ѵ�// �ڸ� ������ ��� ���
		for(int j=0;j<=person1.length;j++){
			if(j==person1.length){System.out.println("�ڸ�����");return;}
			if(person1[j] == null) {i=j; break;}
		}
		//�ڸ��� ������ �Է��� ���� �Է� �޴´�.
		
		//�̸� �Է�
		System.out.println("��ϸ޴��� ���� �ϼ̽��ϴ�.");
		System.out.print("�̸� : ");	
		while(!re){
			re = true;
			try {
				name = stdin.readLine();
			}
			catch (IOException e) {
				System.out.println("�ٽ� �Է����ּ���.");
				re = false;
			}
		}
		
		//���� �Է�
		re = false;
		while(!re){
			
			int gender1=0;
			while(!re){
				re = true;
				System.out.print("���� (��:1,��:2) :");
				try {
					gender1 = Integer.parseInt(stdin.readLine());
				} catch (NumberFormatException e) {
					System.out.println("�ٽ� �Է����ּ���.");
					re = false;
				} catch (IOException e) {
					System.out.println("�ٽ� �Է����ּ���.");
					re = false;
				}
			}
			re = true;
			switch(gender1){
			case 1 : gender = true; break;
			case 2 : gender = false; break;
			default : System.out.println("�ٽ� �Է��� �ּ���."); re=false; break;
			}
		}
		//��ȭ��ȣ �Է�
		System.out.print("���� : "); phone = stdin.readLine();
		
		//���� �Է�
		re = false;
		while(!re){
			System.out.print("���� : "); 
			re=true;
			try {
				age = Integer.parseInt(stdin.readLine());
			} catch (NumberFormatException e) {
				System.out.println("�ٽ� �Է����ּ���.");
				re = false;;
			} catch (IOException e) {
				System.out.println("�ٽ� �Է����ּ���.");
				re = false;
			}
		}
		//�ּ� �Է�
		System.out.print("�ּ� : "); addr = stdin.readLine();
		//Ư�̻��� �Է�
		System.out.print("��Ÿ : "); etc = stdin.readLine();
		
		//��� ��ü ����
		Person person = new Person(name,gender,phone,age,addr,etc);
		
		//��� ��ü�� �迭�� ����
		if(i<person1.length){
			person1[i]=person;
		}
		//�ش� �ڸ��� �ڷᰪ ����.

		
	}
	// 2. ����
	void edit()throws Exception{
		System.out.println("���� �޴��� ���� �ϼ̽��ϴ�.");
		display();
		System.out.print("������ ��ȣ�Է��ϼ���(1~10): ");
		int x=Integer.parseInt(stdin.readLine());
		
		person1[x-1] = null;
		inputPerson();
		
	}
	// 3. ����
	void delete()throws Exception{
		System.out.println("���� �޴��� ���� �ϼ̽��ϴ�.");
		System.out.println("������ ��ȣ(1~10�� ��1): ");
		int x=Integer.parseInt(stdin.readLine());
		person1[x-1] = null;
		
		
	}
	//4. �˻� (�̸� �˻��� ���)
	void search() throws IOException{
		System.out.println("�˻��� ���� �ϼ̽��ϴ�.");
		boolean re=false;
		while(!re){
			re = true;
			System.out.print("ã���� �ϴ� �̸�  : ");
			String input = stdin.readLine();

			int j=0;
			for(j=0;j<person1.length;j++){
				if(person1[j] != null) {
					if(person1[j].getName().equals(input)) {	display1(j);}
					break;
				}
				else System.out.println("ã�°� �����");
			}

		}
	}

	//6. ����
	void end(){
		System.out.println("�����ϰڽ��ϴ�.");
		System.exit(1);
	}
	//5. ���(��ü)
	void display(){ //��ü ��� ���÷���
		System.out.println("��ü �ο� ��� ���÷����Դϴ�.");
		//System.out.println(person1);
		System.out.println("-----------------------------------------");
		for(int q=0;q<person1.length;q++){
			if(person1[q] != null) {
				System.out.println("["+(q+1)+"] "
						+person1[q].getName()+" "
						+person1[q].getGender()+" "
						+person1[q].getPhone()+" "
						+person1[q].getAge()+"�� "
						+person1[q].getAddr()+" "
						+person1[q].getEtc());
//				System.out.printf("%-6s %-8s",person1[q].getName(),person1[q].getGender()+" ");
//				System.out.println("ddddddddddddddddddddddd");
			}
		}
		System.out.println("-----------------------------------------");
	}
	//5. ���(1��)
	void display1(int q){ //�ѻ���� ����ϴ� ���÷���
		System.out.println("-----------------------------------------");
		if(person1[q] != null) {
			System.out.println("["+(q+1)+"] "
					+person1[q].getName()+" "
					+person1[q].getGender()+" "
					+person1[q].getPhone()+" "
					+person1[q].getAge()+"�� "
					+person1[q].getAddr()+" "
					+person1[q].getEtc());

		}
		System.out.println("-----------------------------------------");

	}
	
}
//��� Ŭ����
class Person{
	private String name;
	private boolean gender;
	private String phone;
	private int age;
	private String addr;
	private String etc;
	
	Person(){}
	Person(String name,	boolean gender,	String phone, int age, String addr, String etc){
		this.name=name;
		this.gender=gender;
		this.phone=phone;
		this.age=age;
		this.addr=addr;
		this.etc=etc;
	}
	public String getName() {	return name;}
	public String getGender() {	if(gender) return "����"; else return "����";	}
	public String getPhone() {	return phone;}
	public int getAge() 	{	return age;	}
	public String getAddr() {	return addr;}
	public String getEtc() {	return etc;	}	
	
	public void setName(String name) {	this.name = name;	}
	public void setPhone(String phone) {this.phone = phone;	}
	public void setAge(int age) {		this.age = age;	}
	public void setAddr(String addr) {		this.addr = addr;	}
	public void setGender(boolean gender) {	this.gender = gender;	}
	public void setEtc(String etc) {		this.etc = etc;	}
}