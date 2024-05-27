package com.findbug.findbugbackend.controller;


import com.findbug.findbugbackend.dto.MyShopPage.MyShopDetectedPageDto;
import com.findbug.findbugbackend.dto.MyShopPage.MyShopPredictPageDto;
import com.findbug.findbugbackend.dto.MyShopPage.MyShopStatusDto;
import com.findbug.findbugbackend.service.MyShopPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MyShopPageApi {

    private final MyShopPageService myShopPageService;

    /**
     * 1.
     * 나의 가게 / 벌레 발견 여부 API
     * 벌레가 발견됬는지 여부를 확인한다.
     * @param memberId 사용자 ID
     * @return true : 클라이언트 벌레 발견 API 호출
     * @return false : 클라이언트 벌레 예방 API 호출
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("myShopPage/{memberId}")
    public MyShopStatusDto MyShopStatusApi(@PathVariable Long memberId) {
        return new MyShopStatusDto(myShopPageService.getMyShopStatus(memberId));
    }

    /**
     * 2.
     * 나의 가게 / 벌레 예방 정보 API
     * 벌레 예방 정보를 제공한다.
     * @param memberId 사용자 ID
     * @return {@link MyShopPredictPageDto}git
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("myShopPage/{memberId}/predict")
    public MyShopPredictPageDto MyShopPredictApi(@PathVariable Long memberId) {
        return myShopPageService.getPredictPage();
    }

    /**
     * 3.
     * 나의 가게 / 벌레 발견 정보 API
     * 벌레 발견 정보와 처리 방법에 대한 정보를 제공한다.
     * @param memberId 사용자 ID
     * @return {@link MyShopDetectedPageDto}
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("myShopPage/{memberId}/detected")
    public MyShopDetectedPageDto MyShopDetectedApi(@PathVariable Long memberId) {
        return myShopPageService.getDetectedPage(memberId);
    }
}
