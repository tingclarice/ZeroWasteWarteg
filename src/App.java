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
    
    static ArrayList<MessageData> Message = new ArrayList<>();
    static ArrayList<QuizData> Quiz = new ArrayList<>(); 
    static ObjectMapper warteg = new ObjectMapper();
    static File savePath = new File("src/quiz.json");

    public static void main(String[] args) throws Exception 
    {
        System.out.println("");
        do {
            System.out.println("Main Menu:");
            System.out.println("1. Mulai Game Baru");
            System.out.println("2. Lanjutkan Game Sebelumnya");
            System.out.println("3. Keluar");
            System.out.print("Pilih opsi (1/2/3): ");
            int choice = s.nextInt();
            MainMenu(choice);
        break;
        } while (running);
    }


    public static void LoadDefinitionJSON() throws IOException
    {
        List<QuizData> QuizList = warteg.readValue(savePath, new TypeReference<List<QuizData>>() {});
        QuizList.forEach(e ->
        {
            Quiz.add(e);
        });

        List<MessageData> MessageList = warteg.readValue(savePath, new TypeReference<List<MessageData>>() {});
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
                System.out.println("Pilih opsi (1/2/3): ");

            default:
                System.out.println("Pilihan tidak valid! Silakan coba lagi.");
                break;
        }
    }

    public static void MainGame() throws Exception 
    { 
        // Dialog
        Penjelasan();
        // Quiz
        QuizFunction();
        // Message

    }

    public static void QuizFunction() throws Exception 
    {
        if(savePath.exists())
        {
            int randomIndex = random.nextInt(10);
            LoadDefinitionJSON();
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

    public static void Message() throws Exception 
    {
        if(savePath.exists())
        {
            int randomIndex = random.nextInt(10);
            LoadDefinitionJSON();
            String text = Message.get(randomIndex).getText();
            System.out.println(text);
        }
    }

    public static void Penjelasan () {
        System.out.println("Dunia sedang menghadapi krisis limbah. Setiap hari, jutaan ton sampah terbuang sia-sia, mencemari bumi dan merusak ekosistem. Namun, di tengah kekacauan ini, ada harapan...");
        System.out.println("Selamat datang di Zero Waste Warteg! Di sini, kami percaya bahwa setiap langkah kecil bisa membawa perubahan besar. Tapi mengelola warteg yang ramah lingkungan bukanlah hal mudah... Kami membutuhkan bantuanmu untuk menyajikan makanan lezat sambil menjaga bumi tetap sehat!");
        System.out.println("Tugasmu adalah melayani pelanggan yang datang dengan cepat dan tepat. Namun, di sini ada tantangan: setiap pesanan hanya bisa disajikan jika kamu berhasil menjawab pertanyaan tentang pengelolaan limbah. Jangan khawatir, aku akan membimbingmu");
        System.out.println("Cara Bermain : ");
        System.out.println("1. Pelanggan akan memesan.");
        System.out.println("2. Jawab kuis terkait zero waste untuk menyelesaikan pesanan.");
        System.out.println("3. Jawaban yang benar = pesanan disajikan. Jawaban salah = pesanan gagal.");
        System.out.println("4. Tujuanmu: Jawab kuis dengan benar agar dapat meningkatkan pengetahuan menjaga lingkungan!");
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
    private String text;
    public String getText()
    {
        return text;
    }
    public void setAnswer(String text)
    {
        this.text = text;
    }
}

