import java.util.ArrayList;
import java.util.Scanner;

public class Pro {
  public static void main(String[] args) {
    {
      Application.run();
    }

  }
}

class User {
  String password;
  String login;

  public User(String password, String login) {
    this.password = password;
    this.login = login;
  }

}

class Cinema {
  int id;
  int creatorId;
  String name;
  int genreId;

  public Cinema(int id, int creatorId, String name, int genreId) {
    this.id = id;
    this.creatorId = creatorId;
    this.name = name;
    this.genreId = genreId;
  }
}

class Genre {
  int id;
  String name;

  public Genre(int id, String name) {
    this.id = id;
    this.name = name;
  }

}

class FilmProducer {
  int id;
  String titleName;

  public FilmProducer(int id, String titleName) {
    this.id = id;
    this.titleName = titleName;
  }
}

class Db {
  ArrayList<Cinema> films = new ArrayList<>();
  ArrayList<Genre> genres = new ArrayList<>();
  ArrayList<FilmProducer> filmProducers = new ArrayList<>();
}

class DbApi {
  int cinemaIdGenerator;
  int filmProducerIdGenerator;
  int genreIdGenerator;

  Db db;

  private int getGenreId() {
    return genreIdGenerator++;
  }

  public void init() {
    db = new Db();

    FilmProducer fp1 = new FilmProducer(1, "Ленфильм");
    FilmProducer fp2 = new FilmProducer(2, "Мосфильм");
    FilmProducer fp3 = new FilmProducer(3, "Марвел");
    genreIdGenerator = 1;

    db.filmProducers.add(fp1);
    db.filmProducers.add(fp2);
    db.filmProducers.add(fp3);
    Genre g1 = new Genre(getGenreId(), "Комедия");
    Genre g2 = new Genre(getGenreId(), "Х/ф");
    Genre g3 = new Genre(getGenreId(), "Драма");
    Genre g4 = new Genre(getGenreId(), "Ужасы");

    db.genres.add(g1);
    db.genres.add(g2);
    db.genres.add(g3);
    db.genres.add(g4);

    db.films.add(new Cinema(1, 2, "Особенности национальной охоты", 1));
    db.films.add(new Cinema(1, 3, "Паук", 4));
    db.films.add(new Cinema(1, 1, "Приключения шурика", 2));
  }

  public Db getGb() {
    return db;
  }
}

class Film {

  String creator;
  String name;
  String genre;

  public Film(String creator, String name, String genre) {
    this.creator = creator;
    this.name = name;
    this.genre = genre;
  }

  @Override
  public String toString() {

    return String.format(" 🫶🏻 %s %s %s <<<",
        creator,
        name,
        genre);
  }
}

class FilmFinder {
  Db db;

  public FilmFinder(Db db) {
    this.db = db;
  }

  public Film getFilmBy(int id) {

    Cinema c = db.films.get(id - 1);

    Genre g = db.genres.get(c.genreId - 1);
    FilmProducer p = db.filmProducers.get(c.creatorId - 1);

    return new Film(p.titleName, c.name, g.name);

  }
}

class Application {
  public static void run() {

    DbApi dbApi = new DbApi();
    dbApi.init();

    Db db = dbApi.getGb();

    FilmFinder ff = new FilmFinder(db);
    while (true) {
      Scanner s = new Scanner(System.in);
      int id = s.nextInt();
      if (id == 0)
        return;

      System.out.println(ff.getFilmBy(id));
    }
  }
}