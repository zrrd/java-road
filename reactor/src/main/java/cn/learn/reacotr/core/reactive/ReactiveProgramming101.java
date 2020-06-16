package cn.learn.reacotr.core.reactive;

import java.util.Comparator;
import reactor.core.publisher.Flux;

/**
 * @author shaoyijiong
 * @date 2020/6/8
 */
public class ReactiveProgramming101 {

  /**
   * 返回一个包含每种类别中最贵书的列表 响应式编程
   */
  public static Flux<Book> getMostExpensiveBooksByCategoryReactive(Flux<Book> books) {
    // 根据书籍类型分组 => {CHILDREN:[b1,b2,b3],NOVEL:[b1,b2]}
    return books.collectMultimap(Book::getCategory)
        // [{CHILDREN:[b1,b2,b3]},{NOVEL:[b1,b2]}]
        .flatMapMany(m -> Flux.fromIterable(m.entrySet()))
        // 每一个book集合 [b1,b2,b3]
        .flatMap(e -> Flux.fromIterable(e.getValue())
            // 排序 b1 b2 b3
            .sort(Comparator.comparing(Book::getPrice).reversed())
            // 拿到第一个
            .next());
  }

  public static void main(String[] args) {
    Flux<Book> pipeline =
        getMostExpensiveBooksByCategoryReactive(Flux.just(InMemoryDataSource.BOOKS));
    // 打印对象
    pipeline = pipeline.doOnNext(System.out::print);
    System.out.println("什么都不会发生 , 直到pipeline开始");
    pipeline.subscribe();
  }

}
