package TEST_응용_이순영;

public class Q4 
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
        int[][] scores = {
            {80, 60, 70},
            {90, 95, 80},
            {75, 80, 100},
            {80, 70, 95},
            {100, 65, 80}
        };

        int subjectNum = 3;

        System.out.println("학생번호\t국어\t영어\t수학\t총점\t평균");
        System.out.println("===========================================");

        for (int i = 0; i < scores.length; i++)
        {
            
            int studentNum = i + 1;
            int sumScore = 0;
            
            for (int score : scores[i])
            {
                sumScore += score;
            }

            double average = (double) sumScore / subjectNum;
            
            System.out.println(studentNum+"번 학생:\t"
        						+scores[i][0]+"\t"
        						+scores[i][1]+"\t"
        						+scores[i][2]+"\t"
        						+sumScore+"\t"
        						+(int)average);
        }
    }
}
