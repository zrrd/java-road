package cn.learn.reacotr.domain;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import lombok.var;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

public final class InMemoryDataSource {

  public static final Book[] BOOKS = new Book[]{
      new Book("000001", "CS Book #1", BigDecimal.valueOf(19.99D), "CS"),
      new Book("000002", "CS Book #2", BigDecimal.valueOf(9.99D), "CS"),
      new Book("000003", "CS Book #3", BigDecimal.valueOf(39.99D), "CS"),

      new Book("000004", "Children Book #1", BigDecimal.valueOf(20.99D), "CHILDREN"),
      new Book("000005", "Children Book #2", BigDecimal.valueOf(25.99D), "CHILDREN"),
      new Book("000006", "Children Book #3", BigDecimal.valueOf(24.99D), "CHILDREN"),
      new Book("000007", "Children Book #4", BigDecimal.valueOf(10.99D), "CHILDREN"),

      new Book("000008", "Novel #1", BigDecimal.valueOf(6.99D), "NOVEL"),
      new Book("000009", "Novel #２", BigDecimal.valueOf(12.99D), "NOVEL"),
      new Book("000010", "Novel #３", BigDecimal.valueOf(8.99D), "NOVEL"),
      new Book("000011", "Novel #４", BigDecimal.valueOf(1.99D), "NOVEL")
  };

  private static final Map<String, Book> BOOKS_MAP;

  static {
    BOOKS_MAP = Arrays.stream(BOOKS).collect(Collectors.toConcurrentMap(Book::getIsbn, book -> book));
  }

  public static Book saveBook(Book book) {
    BOOKS_MAP.put(book.getIsbn(), book);
    return book;
  }

  public static Optional<Book> findBookById(String isbn) {
    return Optional.ofNullable(BOOKS_MAP.get(isbn));
  }

  public static Collection<Book> findAllBooks() {
    return BOOKS_MAP.values();
  }

  public static void removeBook(Book book) {
    BOOKS_MAP.remove(book.getIsbn());
  }

  public static Mono<Book> findBookMonoById(String isbn) {
    return Mono.justOrEmpty(findBookById(isbn));
  }

  public static Collection<Book> findBooksByQuery(BookQuery query) {
    return BOOKS_MAP.values().stream()
        .filter(book -> {
          var matched = true;
          if (!StringUtils.isEmpty(query.getTitle())) {
            matched = book.getTitle().contains(query.getTitle());
          }
          if (query.getMinPrice() != null) {
            matched &= (book.getPrice().compareTo(query.getMinPrice()) >= 0);
          }
          if (query.getMaxPrice() != null) {
            matched &= (book.getPrice().compareTo(query.getMaxPrice()) <= 0);
          }
          return matched;
        })
        .sorted(Comparator.comparing(Book::getTitle))
        .skip((query.getPage() - 1) * query.getSize())
        .limit(query.getSize())
        .collect(Collectors.toList());
  }
}