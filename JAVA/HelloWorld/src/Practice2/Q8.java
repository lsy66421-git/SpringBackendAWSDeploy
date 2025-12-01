package Practice2;

import java.util.Scanner;

public class Q8 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 8. 조건문과 반복문을 이용하여 가위바위보 게임을 만들어보세요
		// 5번을 이겼을 경우 게임이 끝나게 되며 한번 가위바위보를 실행할 때마다
		// 사람과 컴퓨터의 가위바위보 내용 승패 승리 횟수 패배 횟수를 출력합니다.
		// -------실행 결과 ----------
		// 가위(1), 바위(2), 보(3) 중에 선택해주세요
		// 플레이어 가위 바위 컴퓨터 : 패배
		// 승리 : 0/5 ,  패배 : 1
		// 가위 바위 보 중에 선택해주세요 >> 바위
		// 플레이어 바위 vs 가위 컴퓨 : 승리
		// 승리 : 1/5 ,  패배 : 1
		Scanner sc = new Scanner(System.in);
		int winMe = 0; // 승리 횟구
		int loseMe = 0; // 패배 횟수
		int none = 0; // 무승부 횟수
		int num = 5;  //  게임 진행 횟수
		outerLoop:
		while(true) {
			System.out.println();
			System.out.print("게임을 시작할려면 아무키(A)나, 종료는 '종료' 또는 4번키를 선택 후 엔터 쳐주세요.");
			String player = sc.next();		
			if(player.equals("4") || player.equals("종료")) {
				System.out.println("게임을 종료 합니다.");
				break;
		    }else {	
		    	for(int i=num; i>=1; i--) {
		    		System.out.println();
		    		System.out.print("가위(1),바위(2),보(3),종료(4) 중에 선택해주세요 >> ");
		    		String playerAgain = sc.next();
		    		if(playerAgain.equals("4") || playerAgain.equals("종료")) {
						System.out.println("게임을 종료 합니다.");
						break outerLoop;
		    		}else {
			    		System.out.println("가위,바위,보 게임을 "+num+"회중 "+(num-i+1)+"회 진행 합니다.");
						int computer = (int)(Math.random()*3)+1;  // 1~3 사이의 임의의 수 발생
						switch(playerAgain) {
							case("가위"): case("1"):
								switch(computer) {
								case(1):
									System.out.println("무승부 입니다.");
									none ++;
								break;
								case(2):
									System.out.println("당신이 패배 했습니다.");
									loseMe ++;
								break;
								case(3):
									System.out.println("당신이 승리 했습니다.");
									winMe ++;
								break;
								} break;
							case("바위"): case("2"):
								switch(computer) {
								case(1):
									System.out.println("당신이 승리 했습니다.");
									winMe ++;
								break;
								case(2):
									System.out.println("무승부 입니다.");
									none ++;
								break;
								case(3):
									System.out.println("당신이 패배 했습니다.");
									loseMe ++;
								break;
								} break;
							case("보"): case("3"):
								switch(computer) {
								case(1):
									System.out.println("당신이 패배 했습니다.");
									loseMe ++;
								break;
								case(2):
									System.out.println("당신이 승리 했습니다.");
									winMe ++;
								break;
								case(3):
									System.out.println("무승부 입니다.");
									none ++;
								break;
								} break;
							default:
								i++;
								System.out.println("(❁´◡`❁) 가위(1),바위(2),보(3),종료(4) 중에 다시 선택해주세요");
						}
						System.out.println("** 승리:"+winMe+"번, 패배:"+loseMe+"번, 무승부:"+none+"번");
					}
	    		}
				System.out.println();
				if(winMe==loseMe) {
					System.out.println(">>> 최종결과: 당신은 "+winMe+"승 "+loseMe+"패 "+none+"무승부로 무승부 입니다.");
				}else if(winMe>loseMe) {
					System.out.println(">>> 최종결과: 당신이 "+winMe+"승 "+loseMe+"패 "+none+"무승부로 승리 하셨습니다.");
				}else{
					System.out.println(">>> 최종결과: 당신은 "+winMe+"승 "+loseMe+"패 "+none+"무승부로 패배 하셨습니다.");
				}
				if(winMe==5) {
					System.out.println();
					System.out.println("<<<5회 승리하여 게임을 완전히 종료 합니다.>>>");
					break;
				}
			}
		}
	}
}
