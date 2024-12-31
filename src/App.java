import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
// import java.util.Random;

public class App 
{
    public static Scanner s = new Scanner(System.in);
    public static boolean running = true;
    public static Random random = new Random();
    
    static ArrayList<QuizData> Quiz = new ArrayList<>(); 
    static ObjectMapper warteg = new ObjectMapper();
    static File quizSavePath = new File("src/quiz.json");

    static ArrayList<MessageData> Message = new ArrayList<>(); 
    static File messageSavePath = new File("src/message.json");

    public static void main(String[] args) throws Exception 
    {
        System.out.println("");
        do {
            System.out.println("Main Menu:");
            System.out.println("1. Mulai Game Baru");
            System.out.println("2. Lanjutkan Game Sebelumnya");
            System.out.println("3. Penjelasan Game");
            System.out.println("4. Keluar");
            System.out.print("Pilih opsi (1/2/3/4): ");
            int choice = s.nextInt();
            MainMenu(choice);
        break;
        } while (running);
    }

    public static void LoadQuizJSON() throws IOException
    {
        List<QuizData> QuizList = warteg.readValue(quizSavePath, new TypeReference<List<QuizData>>() {});
        QuizList.forEach(e ->
        {
            Quiz.add(e);
        });
    }

    public static void LoadMessageJSON() throws IOException
    {
        List<MessageData> MessageList = warteg.readValue(messageSavePath, new TypeReference<List<MessageData>>() {});
        MessageList.forEach(e ->
        {
            Message.add(e);
        });
    }

    public static void MainMenu(int jawaban) throws Exception
    {
        switch (jawaban) 
        {
            case 1:
                MainGame();
                break;

            case 2:
        
                break;

            case 3:
                System.out.println("Keluar dari game...");
                running = false;
                break;

            case 4:
                System.out.println("Pilih opsi (1/2/3/4): ");

            default:
                System.out.println("Pilihan tidak valid! Silakan coba lagi.");
                break;
        }
    }

    public static void MainGame() throws Exception 
    { 
        // Dialog

        // Quiz
        QuizFunction();
        // Message
        MessageFunction();
    }

    public static void MessageFunction() throws Exception 
    {
        if(messageSavePath.exists())
        {
            int randomIndex = random.nextInt(10);
            LoadMessageJSON();
            String message = Message.get(randomIndex).getText();
            System.out.println(message);

            System.out.println();
        }
    }

    public static void QuizFunction() throws Exception 
    {
        if(quizSavePath.exists())
        {
            int randomIndex = random.nextInt(10);
            LoadQuizJSON();
            String question = Quiz.get(randomIndex).getQuestion();
            System.out.println(question);

            String option = Quiz.get(randomIndex).getOptions();
            //System.out.println("The option = " + option);

            String[] options = option.split(":");

            for(int j = 0; j < options.length; j++)
            {
                System.out.println();
                System.out.println(options[j]);
            }

            System.out.print("Input jawaban yang benar : ");
            String userInput = s.next() + s.nextLine();

            char answer = Quiz.get(randomIndex).getAnswer().toUpperCase().charAt(0);
            if (userInput.toUpperCase().charAt(0) == answer) {
                System.out.println("Jawaban benar!");
            } else {
                System.out.println("Jawaban salah!");
            }
            System.out.println("The asnwer = " + answer);

            System.out.println();
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

class MessageData
{
    private int id;
    private String text;

    public int getID()
    {
        return id;
    }
    public void setID(String text)
    {
        this.id = id;
    }
    public String getText()
    {
        return text;
    }
    public void setText(String text)
    {
        this.text = text;
    }
}