package com.uno.getinline.Controller.api;

import com.uno.getinline.constant.ErrorCode;
import com.uno.getinline.dto.APIErrorResponse;
import com.uno.getinline.exception.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class APIEventController {

    @GetMapping("/events")
    public List<String> getEvents() {
        throw new GeneralException("테스트 메시지"); // 지정한 에러
//        return List.of("event1", "event2");
    }

    @PostMapping("/events")
    public Boolean createEvent() {
        throw new RuntimeException("runtime 테스트 메시지"); // 지정하지 않은 에러 -> 공통에러 페이지로 넘어가서 적당한 해동 함(에러 페이지를 view로 보여줌)
//        return true;
    }

    @GetMapping("/events/{eventId}")
    public String getEvent(@PathVariable Integer eventId) {
        return "event " + eventId;
    }

    @PutMapping("/events/{eventId}")
    public Boolean modifyEvent(@PathVariable Integer eventId) {
        return true;
    }

    @DeleteMapping("/events/{eventId}")
    public Boolean removeEvent(@PathVariable Integer eventId) {
        return true;
    }

    @ExceptionHandler
    public ResponseEntity<APIErrorResponse> general(GeneralException e) { // 위 메소드에서 exception이 터지면 이 메소드에서 잡는다.
        ErrorCode errorCode = e.getErrorCode();
        HttpStatus status = errorCode.isClientSideError() ?
                HttpStatus.BAD_REQUEST :
                HttpStatus.INTERNAL_SERVER_ERROR;

        return ResponseEntity
                .status(status)
                .body(APIErrorResponse.of(
                        false, errorCode, errorCode.getMessage(e)
                ));
    }
}
