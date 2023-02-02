import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите текст");
        String input = sc.nextLine();
        int count = 0;

        if(input.length() != 0){
            count++;
            for (int i = 0; i < input.length(); i++) {
                if(input.charAt(i) == ' '){
                    count++;
                }
            }
        }
        list.add(input);
        list.stream()
                .flatMap(line -> Stream.of(line.split("\\s+")))
                .map(word -> word.replaceAll("[^A-Za-zА-Яа-яЁё]+", "").toLowerCase())
                .collect(Collectors.toMap(key -> key, val -> 1, Integer::sum))
                .entrySet().stream()
                .sorted((e1, e2) -> {
                    int val = e1.getValue().compareTo(e2.getValue()) * -1;
                    if (val == 0) {
                        val = e1.getKey().compareTo(e2.getKey());
                        if (e1.getKey().charAt(0) <= 'z'
                                && e2.getKey().charAt(0) > 'z'
                                || e1.getKey().charAt(0) > 'z'
                                && e2.getKey().charAt(0) <= 'z') {
                            val *= -1;
                        }
                    }
                    return val;
                })
                .forEach(e -> System.out.println("<"+e.getKey() +">"+ " " +"-количество повторений: "+ e.getValue()));


        System.out.println("Количество слов: "+count);
    }
}