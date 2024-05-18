package controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
public class infraTestController {

    @GetMapping("test")
    public String awsTestController(){
        log.debug("aws is healthy");
        return "aws is healthy";
    }
}
