public class Book {
    private String id;
    private String author;
    private String title;
    private String genre;
    private String price;
    private String publish_date;
    private String description;

    public Book(String id, String author, String title, String genre, String price, String publish_date, String description) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.price = price;
        this.publish_date = publish_date;
        this.description = description;
    }

    public String getId() {
        return id;
    }


    public String getAuthor() {
        return author;
    }


    public String getTitle() {
        return title;
    }


    public String getGenre() {
        return genre;
    }

    public String getPrice() {
        return price;
    }

    public String getPublish_date() {
        return publish_date;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Book{" + "id='" + id + '\'' + ", author='" + author + '\'' + ", title='" + title + '\'' + ", genre='" + genre + '\'' + ", price='" + price + '\'' + ", publish_date='" + publish_date + '\'' + ", description='" + description + '\'' + '}';
    }
}
