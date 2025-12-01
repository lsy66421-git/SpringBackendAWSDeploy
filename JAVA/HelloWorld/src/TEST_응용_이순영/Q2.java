package TEST_응용_이순영;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Phone {
    private String name;
    private String tel;
    private String address;

    public Phone(String name, String tel, String address) {
        this.name = name;
        this.tel = tel;
        this.address = address;
    }

    public String getName() {
        return name;
    }
    public String getTel() {
        return tel;
    }
    public String getAddress() {
        return address;
    }
    
    @Override
    public String toString() {
        return name + "의 전화번호와 주소는 " + tel + ", " + address + "입니다.";
    }
}

public class Q2 {

	private static List<Phone> phoneList;
    private static Scanner sc;

    public static void main(String[] args) {
        
        sc = new Scanner(System.in);
        phoneList = new ArrayList<>();

        int count = count();
        phone(count);
        System.out.println("저장되었습니다...");

        search();

        sc.close();
        System.out.println("프로그램을 종료합니다...");
    }

    private static int count() {
        int count = 0;
        while (true) {
            System.out.print("인원수 >> ");
            if (sc.hasNextInt()) {
                count = sc.nextInt();
                sc.nextLine();
                break;
            } else {
                System.out.println("정수만 입력할 수 있습니다. 다시 입력해주세요.");
                sc.nextLine();
            }
        }
        return count;
    }

    private static void phone(int count) {
        for (int i = 0; i < count; i++) {
            System.out.print("이름과 전화번호(번호는 연속적으로 입력), 주소 >> ");
            String input = sc.nextLine().trim(); 
            String[] inputArr = input.split(" ");

            if (inputArr.length < 3) {
                System.out.println("입력 형식이 잘못되었습니다. 다시 입력해주세요.");
                i--;
                continue;
            }

            String name = inputArr[0];
            String tel = inputArr[1];
            StringBuilder address = new StringBuilder();
            for (int j = 2; j < inputArr.length; j++) {
                address.append(inputArr[j]).append(" ");
            }
            String add = address.toString().trim();

            Phone newPhone = new Phone(name, tel, add);
            phoneList.add(newPhone);
        }
    }

    private static void search() {
        while (true) {
            System.out.print("검색할 이름 >> ");
            String searchName = sc.nextLine().trim();

            if (searchName.equalsIgnoreCase("exit")) {
                break;
            }

            Phone foundPhone = find(searchName);

            if (foundPhone != null) {
                System.out.printf("%s의 번호와 주소는 %s, %s 입니다.\n", 
                                  searchName, 
                                  foundPhone.getTel(), 
                                  foundPhone.getAddress());
            } else {
                System.out.printf("%s은(는) 없습니다.\n", searchName);
            }
        }
    }

    private static Phone find(String name) {
        for (Phone phone : phoneList) {
            if (phone.getName().equals(name)) {
                return phone;
            }
        }
        return null;
    }
}
