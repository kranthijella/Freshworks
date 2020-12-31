import org.json.simple.JSONObject;
import java.io.*;
import java.util.Scanner;
public class JSONCRD {
    JSONObject jsonObject = new JSONObject();
    public static void main(String args[]){
     jsoncrud json_crud = new jsoncrud();
     Thread t1 = new Thread(json_crud);
     t1.start();
    }
    synchronized void create() throws IOException{
        Scanner s = new Scanner(System.in);
        do{
            System.out.println("Enter the key or Enter -1 to stop creating");
            String key = s.nextLine();
            if(key.equals("-1")){
                break;
            }
            if(jsonObject.containsKey(key)){
                System.out.println("key already exists");
                break;
            }
            System.out.println("Enter the value");
            String value = s.nextLine();
            jsonObject.put(key,value);
        }while (true);

        PrintWriter printWriter = new PrintWriter(
                new BufferedWriter(new FileWriter("json.txt",true))
        );
        printWriter.print(jsonObject);
        printWriter.close();
    }
    synchronized void print() throws IOException {
        File file = new File("json.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String str;
        while ((str = bufferedReader.readLine())!=null){
                System.out.println(str);
        }
    }
    synchronized void delete() throws IOException {
        System.out.println("Enter the key to delete");
        Scanner s= new Scanner(System.in);
        String key = s.nextLine();
        jsonObject.remove(key);
        PrintWriter pw = new PrintWriter("json.txt");
        pw.print("");
        pw.close();
        PrintWriter printWriter = new PrintWriter(
                new BufferedWriter(new FileWriter("json.txt",true))
        );
        printWriter.print(jsonObject);
        printWriter.close();
    }
}
class jsoncrud extends Thread{
    JSONCRD jsoncrd = new JSONCRD();
    public void run(){
        try {
            Scanner s = new Scanner(System.in);
            while (true) {
                System.out.println("Choose the option");
                System.out.println("1.create");
                System.out.println("2.print");
                System.out.println("3.delete");
                int value = s.nextInt();
                switch (value){
                    case 1:jsoncrd.create();
                    break;
                    case 2 :jsoncrd.print();
                    break;
                    case 3:jsoncrd.delete();
                    break;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}