import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App 
{
    public static Scanner s = new Scanner(System.in);
    public static boolean running = true;
    
    static ArrayList<QuizData> Quiz = new ArrayList<>(); 
    static ObjectMapper warteg = new ObjectMapper();
    static File savePath = new File("src/quiz.json");

    public static void main(String[] args) throws Exception 
    {
        if(savePath.exists())
        {
            LoadDefinitionJSON();
            for(int i = 0; i < Quiz.size(); i++)
            {
                String question = Quiz.get(i).getQuestion();
                System.out.println(question);

                String option = Quiz.get(i).getOptions();
                //System.out.println("The option = " + option);

                String[] options = option.split(":");

                for(int j = 0; j < options.length; j++)
                {
                    System.out.println();
                    System.out.println(options[j]);
                }
                String answer = Quiz.get(i).getAnswer();
                System.out.println("The asnwer = " + answer);

                System.out.println();
            }
        }
    }


    public static void LoadDefinitionJSON() throws IOException
    {
        List<QuizData> QuizList = warteg.readValue(savePath, new TypeReference<List<QuizData>>() {});
        QuizList.forEach(e ->
        {
            Quiz.add(e);
        });
    }

    public static void MainMenu(int jawaban) 
    {
        switch (jawaban) 
        {
            case 1:

                break;

            case 2:
        
                break;

            case 3:
                System.out.println("Keluar dari game...");
                running = false;
                break;

            default:
                System.out.println("Pilihan tidak valid! Silakan coba lagi.");
                break;
        }
    }
}

class QuizData 
{
    private String question;
    private String options;
    private String answer;

    public QuizData(){}
    // Constructor with parameters
    public QuizData(String question, String options, String answer)
    {
        super();
        this.question = question;
        this.options = options;
        this.answer = answer;
    }

    //getter setter
    public String getQuestion()
    {
        return question;
    }
    public void setQuestion(String question)
    {
        this.question = question;
    }
    public String getOptions()
    {
        return options;
    }
    public void setOptions(String options)
    {
        this.options = options;
    }
    public String getAnswer()
    {
        return answer;
    }
    public void setAnswer(String answer)
    {
        this.answer = answer;
    }
}