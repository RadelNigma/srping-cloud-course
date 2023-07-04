package org.radel.feign;

import lombok.extern.slf4j.Slf4j;
import org.radel.model.Book;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

// Что бы работало Fallback в properties нужно поставить:
/*feign:
   circuitbreaker:
     enabled: true*/

@FeignClient(name = "book-service", fallbackFactory = FeignServiceUtilFallbackFactory.class)   //вариант 2
//@FeignClient(name = "book-service", fallback = Fallback.class)  //вариант 1
//@FeignClient(name = "book-service")
public interface FeignServiceUtil {
    @GetMapping("/api/books")
    List<Book> getAllBooks();
}
//______________________________
// Этот вариант для Fallback + логирование
// Над interface нужно ставить @FeignClient(name = "book-service", fallbackFactory = FeignServiceUtilFallbackFactory.class)
@Slf4j
record FallbackWithFactory(String reason) implements FeignServiceUtil {
    @Override
    public List<Book> getAllBooks() {
        log.info("Failed send request on book service, reason {}", reason);
        List<Book> list = new ArrayList<>();
        list.add(new Book("Error", "the book service is not available", "using fallback", "FallbackWithFactory"));
        return list;
    }
}

    @Component
    class FeignServiceUtilFallbackFactory implements FallbackFactory<FallbackWithFactory> {
        @Override
        public FallbackWithFactory create(Throwable cause) {
            return new FallbackWithFactory(cause.getMessage());
        }
    }
//______________________________

//______________________________
// Этот вариант только для Fallback без логирования
// Над interface нужно ставить @FeignClient(name = "book-service", fallback = Fallback.class)
//@Component
//class Fallback implements FeignServiceUtil {
//    @Override
//    public List<Book> getAllBooks() {
//        List<Book> list = new ArrayList<>();
//        list.add(new Book("Error", "the book service is not available", "using fallback", "Fallback"));
//        return list;
//    }
//}
//______________________________



