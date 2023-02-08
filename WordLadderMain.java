import java.util.*;
import java.io.File; 
import java.io.FileNotFoundException; 
public class WordLadderMain  
{

    public static Stack<String> findLadder(String s, String e)
    {
        Queue<Stack<String>> queue = new LinkedList<>();
        List<String> dictionary = new ArrayList<>();


         String start = "";
         String end = "";
         start = s;
         end = e;
       
        // try {
        //     File file = new File("input.txt");
        //     Scanner scanner = new Scanner(file);
        //     start = scanner.next();
        //     end = scanner.next();
        //     scanner.close();
        // }
        // catch (FileNotFoundException e)
        // {
        //     System.out.println("error: file not found");
        //     e.printStackTrace();
        // }
        
        //get start and end word *****

        try {
            File file = new File("dictionary.txt");
            Scanner scanner2 = new Scanner(file);
            String word = "";
            while (scanner2.hasNext())
            {
                word = scanner2.nextLine();
                
                if (word.length() == start.length())
                {
                    word = word.toLowerCase();
                    dictionary.add(word);
                }
            }
        }
        catch (FileNotFoundException m)
        {
            System.out.println("error: file not found");
            m.printStackTrace();
        }
        Stack<String> satisfyReturn = new Stack<>();
        if ((dictionary.indexOf(start) < 0) || (dictionary.indexOf(end) < 0))
        {
            System.out.println("start/end word not in dictionary");
            return satisfyReturn;
        }
        if (start.equals(end))
        {
            satisfyReturn.push(end);
            return satisfyReturn;
        }
        //set dictionary *****
       
        
        for (int i = 0; i < dictionary.size(); i++)
        {
            String word = dictionary.get(i).toLowerCase();
                
            int difference = 1;
            for (int j = 0; j < start.length(); j++)
                if (word.charAt(j) != start.charAt(j))
                    difference--;
            
            if ((difference == 0))
            {
                Stack<String> x = new Stack<>();
                x.push(start);
                x.push(word);
                int index = dictionary.indexOf(word);
                if (index >= 0)
                    dictionary.remove(index);                
                queue.offer(x);        
            }


        }
        int index = dictionary.indexOf(start);
        if (index >= 0)
            dictionary.remove(index);
       
        
        while (!queue.isEmpty())
        {
            Stack<String> temp = queue.poll();
            String check = temp.peek();

            if (check.equals(end))
            {                
                return temp;
            }
            else 
            {
                for (int i = 0; i < dictionary.size(); i++)
                {
                    String word = dictionary.get(i).toLowerCase();
                    
                    int difference = 1;
                    for (int j = 0; j < start.length(); j++)
                        if (word.charAt(j) != temp.peek().charAt(j))
                            difference--;
                    
                    if ((difference == 0))
                    {
                        Stack<String> x = new Stack<>();
                        x.addAll(temp);
                        x.push(word);
                        
                        index = dictionary.indexOf(word);
                        if (index >= 0)
                            dictionary.remove(index);
                        queue.offer(x);  
                        difference = 1;      
                    }
                }
            }
        }
        System.out.println("No solution found");
        return satisfyReturn;
    }  

    public static void main(String[] args) 
    {
        
        File file;
        Scanner scanner;
        String start;
        String end;
        Stack<String> ladder;
        try {
            file = new File("input.txt");
            scanner = new Scanner(file);
            for (int i = 0; i < 16; i++)
            {
                start = scanner.next();
                end = scanner.next();
                ladder = findLadder(start, end);
                if (!ladder.isEmpty())
                    System.out.println("solution! >>> " + ladder);                
            }
            scanner.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("error: file not found");
            e.printStackTrace();
        }
        
    
        
        
    }


}