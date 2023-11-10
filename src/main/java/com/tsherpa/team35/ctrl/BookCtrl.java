package com.tsherpa.team35.ctrl;

import java.net.URI;
import java.util.List;

import com.tsherpa.team35.entity.Book;
import com.tsherpa.team35.entity.BookVO;
import com.tsherpa.team35.entity.NaverResultVO;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("book")
public class BookCtrl {

    @GetMapping("/bookList")
    public String list(){
        return "book/bookList";
    }
    @PostMapping("/list")
    public String listing(@RequestParam("text") String text, Model model) {

        // 네이버 검색 API 요청
        String clientId = "t3prRpioTofJgssyOPNT";
        String clientSecret = "xpvqvASKIF";

//        String apiURL = "https://openapi.naver.com/v1/search/book?query=" + text;    // JSON 결과
        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/book.json")
                .queryParam("query", text)
                .queryParam("display", 30)
                .queryParam("start", 1)
                .queryParam("sort", "sim")
                .encode()
                .build()
                .toUri();

        // Spring 요청 제공 클래스
        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", clientId)
                .header("X-Naver-Client-Secret", clientSecret)
                .build();
        // Spring 제공 restTemplate
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.exchange(req, String.class);

        // JSON 파싱 (Json 문자열을 객체로 만듦, 문서화)
        ObjectMapper om = new ObjectMapper();
        NaverResultVO resultVO = null;

        try {
            resultVO = om.readValue(resp.getBody(), NaverResultVO.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        List<BookVO> books =resultVO.getItems();	// books를 list.html에 출력 -> model 선언
        model.addAttribute("books", books);

        return "book/bookList";
    }

    @GetMapping("/insert")
    public String insertPage()throws Exception{
        return "book/bookList";
    }


    @PostMapping("/insert")
    public String insert(HttpServletRequest request, Model model)throws Exception{
        BookVO book = new BookVO();
        book.setIsbn(request.getParameter("isbn"));
        book.setAuthor(request.getParameter("author"));
        book.setPubdate(request.getParameter("pubdate"));
        book.setImage(request.getParameter("image"));
        book.setTitle(request.getParameter("title"));
        book.setDiscount(request.getParameter("discount"));
        book.setPublisher(request.getParameter("publisher"));
        model.addAttribute("book",book);
        System.out.println(book);
        return "market/marketInsert";
    }

}
