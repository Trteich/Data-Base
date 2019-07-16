package main;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

class Menu {
    private List<String> items;


    Menu(List<String> items) {
        this.items = items;
    }

    int select() {
        System.out.println("----------------------------------\n");
        for (String item : items) {
            System.out.println(item);
        }
        System.out.println("Введите Ваш выбор:");
        Scanner in = new Scanner(System.in);
        int n;
        while(true){
             try{
                 n = in.nextInt();
                 break;
             }catch (InputMismatchException ignore){
                 System.out.println("Неверный ввод. Попробуйте снова");   //????????????????????????????????
                 n= in.nextInt();
             }
        }

        System.out.println("----------------------------------\n");
        return n;
    }
}
