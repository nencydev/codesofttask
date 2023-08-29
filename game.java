import java.util.*;
public class Game
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        int i;
        int GNumber = (int)(Math.random() * 100 );
        System.out.println(" ");
        System.out.print("----------" );
        System.out.print(" |WELCOME TO GAME| ");
        System.out.println("----------");
        System.out.println(" ");
        System.out.println("**you have 10 tries**");
        System.out.println(" ");
        System.out.println(" |START THE GAME| ");
        System.out.println(" ");
        for( i=1;i<=10;i++)
        {
            System.out.print("Guess the Number in 1 to 100:");
            int Number = sc.nextInt();

            if(Number==GNumber )
            {
                System.out.println("you won the game in" + " " + i + " " + "attempts");
                System.out.println(" ");
                System.out.println("-----|Play again for more fun|-----");
                break;
            }
            else if (Number>GNumber )
            {
                System.out.println("Think another smaller number: ");
            }
            else if(Number<GNumber )
            {
                System.out.println("Think another larger number: ");
            }

        }
        if((i++)>10)
        {
            System.out.println("Better luck for next time");
        }

    }
}
