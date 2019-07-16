package main;

import tables.Actor;
import tables.Film;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MyPU");
    static EntityManager em = entityManagerFactory.createEntityManager();

    public static void main(String[] args) {
        new Main().run();


    }

    private void run() {

        Menu menu = new Menu(List.of("1. Поиск фильма по году",
                "2. Поиск актеров по фильму",
                "3. Поиск актеров по количеству фильмов",
                "4. Поиск по студии"
        ));
        int counter;
        while ((counter = menu.select()) != 0) {
            switch (counter) {
                case 1:
                    searchByYear();
                    break;

                case 2:
                    searchActorByFilm();
                    break;

                case 3:
                    searchActorByNum();
                    break;

                case 4:
                    searchByStudio();
                    break;

            }
        }
    }


    private void searchByYear() {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите временной промежуток выхода фильма:");
        String[] yearInterval = in.nextLine().split("-");
        List<Film> resultFilms = em.createQuery("SELECT f FROM Film f " +
                "WHERE f.realese>=:y1 AND f.realese<=:y2 order by f.realese", Film.class)
                .setParameter("y1", yearInterval[0])
                .setParameter("y2", yearInterval[1])
                .getResultList();

        if (resultFilms.size() != 0) {
            System.out.println("В єти годы на экран вышли:");
            resultFilms.forEach(f -> System.out.println(f.getTitle() + f.getRealese()));
        } else {
            System.out.println("В базе нет фильмов снятых в указанные годы");
        }
    }

    private void searchActorByFilm() {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите название фильма:");
        String filmName = in.nextLine();
        List<Actor> actorResult = em.createQuery("SELECT f.actors FROM Film f where f.title=:n1", Actor.class)
                .setParameter("n1", filmName)
                .getResultList();
        if (actorResult.size() != 0) {
            System.out.println("В фильме снимались:");
            actorResult.forEach(System.out::println);
        } else {
            System.out.println("Фильм отсутствует в базе");
        }
    }

    private void searchActorByNum() {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите количество фильмов:");
        int number = in.nextInt();
        List<Actor> actorsResult = em.createQuery("SELECT a FROM Actor a where SIZE(a.films)=:n1", Actor.class)
                .setParameter("n1", number)
                .getResultList();
        if (actorsResult.size() != 0) {
            System.out.println("Как минимум в " + number + " фильме(ах) снимались");
            actorsResult.forEach(System.out::println);
        } else {
            System.out.println("Никто не снимался в таком количестве фильмов");
        }

    }

    private void searchByStudio() {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите название студии:");
        String studio = in.nextLine();

        List<Film> filmResult = em.createQuery("SELECT f FROM Film f WHERE f.studio.title=:s", Film.class)
                .setParameter("s", studio)
                .getResultList();
        if (filmResult.size() != 0) {
            System.out.println("Кинокомпания " + studio + " сняла следующие фильмы:");
            filmResult.forEach(film -> System.out.println(film.getTitle() + film.getRealese()));
        } else {
            System.out.println("В базе нет фильмов снятых данной студией");
        }
    }

}