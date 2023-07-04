package org.radel.restcontroller;

import org.radel.feign.Feign;
import org.radel.feign.FeignServiceUtil;
import org.radel.model.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/client")
public class ClientRestController {

    private final FeignServiceUtil feignServiceUtil;


private final Feign feign;
//
//    public ClientRestController(Feign feign) {
//        this.feign = feign;
//    }


    public ClientRestController(FeignServiceUtil feignServiceUtil, Feign feign) {
        this.feignServiceUtil = feignServiceUtil;
        this.feign = feign;
    }

    private static final Logger LOG = Logger.getLogger(ClientRestController.class.getName());
    @RequestMapping("/")
    public String home() {
        String home = "Client-Service running at port: ";
        LOG.log(Level.INFO, home);

        return home;
    }



    @GetMapping("/feignServiceUtil")
    public List<Book> getAllBooksFromClient() {
        return feignServiceUtil.getAllBooks();
    }

    @GetMapping("/feign")
    public List<Book> getAllBooksFromClient1() {
        return feign.getAllBooks();
    }

    @GetMapping("/test")
    public String getTestMsg() {
        return "Test message";
    }
}
