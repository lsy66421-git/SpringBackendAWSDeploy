package Practice3;

import java.util.Arrays;

public class Q6{

    public static void main(String[] args) {
        
        int[] lottoNum = new int[6];
        for (int i = 0; i < 6; i++) {
            int num = (int)(Math.random() * 45) + 1;
            boolean comp = false;
            for (int j = 0; j < i; j++) {
                if (lottoNum[j] == num) {
                    comp = true;
                    break;
                }
            }
            if (comp) {
                i--; 
            } else {
                lottoNum[i] = num;
            }
        }    
        Arrays.sort(lottoNum);
        System.out.println("로또번호: " + Arrays.toString(lottoNum));
    }
}

