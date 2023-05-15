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
	
	//0. 시작
	void open() throws Exception{
		int inputNum =0; // 메뉴 선택을 받기 위한 integer 변수
		boolean re=false; //입력 받은 숫자가 1~6이 아닌 경우 다시 입력 받기 위해
		System.out.println("==========* 주소록 관리 프로그램 *============");
		while(!re){
			re = true;
			System.out.println("1.등록, 2.수정, 3.삭제, 4.검색, 5.출력, 6.종료");
			System.out.print("메뉴 선택 : ");
	
			try {
				inputNum = Integer.parseInt(stdin.readLine());
			} catch (NumberFormatException e) {
				re = false;
			} catch (IOException e) {
				System.out.println("다시 입력해주세요.");
				re = false;
			}
		
			switch(inputNum){
			case 1 : inputPerson(); open();break;
			case 2 : edit(); open(); break;
			case 3 : delete(); open(); break;
			case 4 : search(); open(); break;
			case 5 : display(); open(); break;
			case 6 : end(); break;
			default : {System.out.println("다시 입력해주세요");re=false; break;}
			}
		}
	}
	//1.등록
	void inputPerson() throws Exception{		
		boolean re=false;
		
		//주소록에 저장할 (배열) 자리가 비어있는지 확인 한다// 자리 없으면 등록 취소
		for(int j=0;j<=person1.length;j++){
			if(j==person1.length){System.out.println("자리없음");return;}
			if(person1[j] == null) {i=j; break;}
		}
		//자리가 있으면 입력할 값을 입력 받는다.
		
		//이름 입력
		System.out.println("등록메뉴를 선택 하셨습니다.");
		System.out.print("이름 : ");	
		while(!re){
			re = true;
			try {
				name = stdin.readLine();
			}
			catch (IOException e) {
				System.out.println("다시 입력해주세요.");
				re = false;
			}
		}
		
		//성별 입력
		re = false;
		while(!re){
			
			int gender1=0;
			while(!re){
				re = true;
				System.out.print("성별 (남:1,여:2) :");
				try {
					gender1 = Integer.parseInt(stdin.readLine());
				} catch (NumberFormatException e) {
					System.out.println("다시 입력해주세요.");
					re = false;
				} catch (IOException e) {
					System.out.println("다시 입력해주세요.");
					re = false;
				}
			}
			re = true;
			switch(gender1){
			case 1 : gender = true; break;
			case 2 : gender = false; break;
			default : System.out.println("다시 입력해 주세요."); re=false; break;
			}
		}
		//전화번호 입력
		System.out.print("전번 : "); phone = stdin.readLine();
		
		//나이 입력
		re = false;
		while(!re){
			System.out.print("나이 : "); 
			re=true;
			try {
				age = Integer.parseInt(stdin.readLine());
			} catch (NumberFormatException e) {
				System.out.println("다시 입력해주세요.");
				re = false;;
			} catch (IOException e) {
				System.out.println("다시 입력해주세요.");
				re = false;
			}
		}
		//주소 입력
		System.out.print("주소 : "); addr = stdin.readLine();
		//특이사항 입력
		System.out.print("기타 : "); etc = stdin.readLine();
		
		//사람 객체 생성
		Person person = new Person(name,gender,phone,age,addr,etc);
		
		//사람 객체를 배열에 저장
		if(i<person1.length){
			person1[i]=person;
		}
		//해당 자리에 자료값 저장.

		
	}
	// 2. 수정
	void edit()throws Exception{
		System.out.println("수정 메뉴를 선택 하셨습니다.");
		display();
		System.out.print("수정할 번호입력하세요(1~10): ");
		int x=Integer.parseInt(stdin.readLine());
		
		person1[x-1] = null;
		inputPerson();
		
	}
	// 3. 삭제
	void delete()throws Exception{
		System.out.println("삭제 메뉴를 선택 하셨습니다.");
		System.out.println("삭제할 번호(1~10중 택1): ");
		int x=Integer.parseInt(stdin.readLine());
		person1[x-1] = null;
		
		
	}
	//4. 검색 (이름 검색만 허용)
	void search() throws IOException{
		System.out.println("검색을 선택 하셨습니다.");
		boolean re=false;
		while(!re){
			re = true;
			System.out.print("찾고자 하는 이름  : ");
			String input = stdin.readLine();

			int j=0;
			for(j=0;j<person1.length;j++){
				if(person1[j] != null) {
					if(person1[j].getName().equals(input)) {	display1(j);}
					break;
				}
				else System.out.println("찾는게 없어요");
			}

		}
	}

	//6. 종료
	void end(){
		System.out.println("종료하겠습니다.");
		System.exit(1);
	}
	//5. 출력(전체)
	void display(){ //전체 출력 디스플레이
		System.out.println("전체 인원 출력 디스플레이입니다.");
		//System.out.println(person1);
		System.out.println("-----------------------------------------");
		for(int q=0;q<person1.length;q++){
			if(person1[q] != null) {
				System.out.println("["+(q+1)+"] "
						+person1[q].getName()+" "
						+person1[q].getGender()+" "
						+person1[q].getPhone()+" "
						+person1[q].getAge()+"세 "
						+person1[q].getAddr()+" "
						+person1[q].getEtc());
//				System.out.printf("%-6s %-8s",person1[q].getName(),person1[q].getGender()+" ");
//				System.out.println("ddddddddddddddddddddddd");
			}
		}
		System.out.println("-----------------------------------------");
	}
	//5. 출력(1개)
	void display1(int q){ //한사람만 출력하는 디스플레이
		System.out.println("-----------------------------------------");
		if(person1[q] != null) {
			System.out.println("["+(q+1)+"] "
					+person1[q].getName()+" "
					+person1[q].getGender()+" "
					+person1[q].getPhone()+" "
					+person1[q].getAge()+"세 "
					+person1[q].getAddr()+" "
					+person1[q].getEtc());

		}
		System.out.println("-----------------------------------------");

	}
	
}
//사람 클래스
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
	public String getGender() {	if(gender) return "남자"; else return "여자";	}
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