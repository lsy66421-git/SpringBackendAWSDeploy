package TEST_활용_이순영;

import java.util.Scanner;

class Circle 
{
    private int radius;

    public Circle(int radius) 
    {
        this.radius = radius;
    }
    public double getArea() 
    {
        return radius * radius * 3.14;
    }
}

public class Q1 
{
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        Circle[] c = new Circle[4];  
        System.out.println("--- 4개의 원의 반지름 입력 ---");
        for (int i = 0; i < c.length; i++) 
        {
            System.out.print((i+1)+" 반지름 >> ");
            if (sc.hasNextInt()) 
            {
                int radius = sc.nextInt();
                c[i] = new Circle(radius); 
            } else 
            {
                System.out.println("정수만 입력해야 합니다. 다시 입력해주세요.");
                sc.next();
                i--;
            }
        }      
        double sumArea = 0.0;    
        for (int i = 0; i < c.length; i++)
        {
            sumArea += c[i].getArea();
        }   
        System.out.printf("원의 면적 전체 합은 " + sumArea);
        sc.close();
	}
}
