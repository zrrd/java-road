package cn.learn.reacotr.web.s03;

import cn.learn.reacotr.domain.Book;
import cn.learn.reacotr.domain.InMemoryDataSource;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.core.MethodParameter;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author shaoyijiong
 * @date 2020/6/20
 */
@RestController
@RequestMapping("/annotated")
public class C01AnnotationBased {

  @GetMapping("books")
  public Flux<Book> findAll() {
    return Flux.fromIterable(InMemoryDataSource.findAllBooks());
  }

  @PostMapping("book")
  public Mono<ResponseEntity<?>> create(@Valid @RequestBody Book book, BindingResult bindingResult,
      UriComponentsBuilder ucb) throws MethodArgumentNotValidException {
    // 这本书的 id 是否以经存在于数据库中
    Optional<Book> theBook = InMemoryDataSource.findBookById(book.getIsbn());
    // 如果存在 抛出异常
    if (theBook.isPresent()) {
      bindingResult.rejectValue("isbn", "already exits", "already exits");
    }
    if (bindingResult.hasErrors()) {
      throw new MethodArgumentNotValidException(new MethodParameter(new Object() {
      }.getClass().getEnclosingMethod(), 0), bindingResult);
    }
    // 保存
    InMemoryDataSource.saveBook(book);
    return Mono.just(ResponseEntity.created(ucb.path("/annotated/book/").path(book.getIsbn()).build().toUri()).build());
  }

  @GetMapping("book/{isbn}")
  public Mono<Book> find(@PathVariable String isbn) {
    return Mono.justOrEmpty(InMemoryDataSource.findBookById(isbn));
  }

  @PutMapping("book/{isbn}")
  public Mono<ResponseEntity<?>> update(@PathVariable String isbn,
      @RequestBody Book book) {
    Optional<Book> theBook = InMemoryDataSource.findBookById(isbn);
    if (!theBook.isPresent()) {
      return Mono.just(ResponseEntity.notFound().build());
    }
    InMemoryDataSource.saveBook(book);
    return Mono.just(ResponseEntity.ok().build());
  }

  @DeleteMapping("book/{isbn}")
  public Mono<ResponseEntity<?>> remove(@PathVariable String isbn) {
    Optional<Book> book = InMemoryDataSource.findBookById(isbn);
    if (!book.isPresent()) {
      return Mono.just(ResponseEntity.notFound().build());
    }

    InMemoryDataSource.removeBook(book.get());
    return Mono.just(ResponseEntity.ok().build());
  }
}
