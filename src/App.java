import java.util.Scanner;


public class App {
    public static Scanner s = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        
        do {
            System.out.println("============ SELAMAT DATANG KE ZERO-WASTE WARTEG ============");
            System.out.println("1. Mulai Game Baru");
            System.out.println("2. Lanjutkan Game Sebelumnya");
            System.out.println("3. Keluar");
            System.out.print("Input Opsi = ");
            int jawaban = s.nextInt();
            MainMenu(jawaban);
            break;
        } while (true);

        s.close();
    }

    public static void MainMenu(int jawaban) {
        switch (jawaban) {
            case 1:

                break;

            case 2:
        
                break;

            case 3:
                break;
        }
    }
}
