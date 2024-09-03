package com.ohgiraffers.restapi.section04.hateoas;

import com.ohgiraffers.restapi.section03.valid.UserDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hateoas")
public class HateoasTestController {

    /*
    * Hateoas란?
    * 하나의 api를 동작시켰을 때, 그 전 상황이나 그 후 상황에 대한 링크를
    * 제공함으로써 클라이언트가 리소스(자원)에 대한 이해를 더욱 충분히
    * 할 수 있도로고 링크를 제공하는 것
    *
    * (Hateoas까지 구현했을 때, Restfull의 성숙도 3단계 API(최종단계)라고 한다.)
    * */

    private List<UserDTO> users;

    public HateoasTestController() {
        users = new ArrayList<>();

        users.add(new UserDTO(1, "user01", "pass01", "너구리", LocalDate.now()));
        users.add(new UserDTO(2, "user02", "pass02", "코알라", LocalDate.now()));
        users.add(new UserDTO(3, "user03", "pass03", "곰", LocalDate.now()));
    }

    // 단건조회
    @GetMapping("/users/{userNo}")
    public ResponseEntity<ResponseMessage> findUserByNo(@PathVariable int userNo) {

        // Header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));;

        // Body
        UserDTO foundUser = users.stream().filter(user -> user.getNo() == userNo).toList().get(0);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("user", foundUser);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(200, "조회 성공", responseMap));
    }
}
