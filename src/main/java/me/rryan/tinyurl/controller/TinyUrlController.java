package me.rryan.tinyurl.controller;

import io.micrometer.common.util.StringUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.rryan.tinyurl.annotation.TinyUrlAccessLog;
import me.rryan.tinyurl.model.ResponseResult;
import me.rryan.tinyurl.model.req.UrlShortenReq;
import me.rryan.tinyurl.model.resp.UrlShortenResp;
import me.rryan.tinyurl.service.TinyUrlService;
import org.apache.catalina.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Tag(name = "TinyUrlController")
@RestController
@RequestMapping("/api/tinyurl")
public class TinyUrlController {


    private final TinyUrlService tinyUrlService;

    @Autowired
    public TinyUrlController(TinyUrlService tinyUrlService) {
        this.tinyUrlService = tinyUrlService;
    }

    @Operation(method = "POST", description = "Create Short Url")
    @PostMapping("/shorten")
    public ResponseResult<UrlShortenResp> createShortUrl(@RequestBody UrlShortenReq req) {
        UrlShortenResp resp = tinyUrlService.createSHortUrl(req);
        return ResponseResult.success(resp);
    }

    @Operation(method = "GET", description = "Redirect To Long URL")
    @GetMapping("/{shortCode}")
    @TinyUrlAccessLog
    public ResponseEntity<Void> redirectToLongUrl(@PathVariable String shortCode) {
        // 假设我们通过 shortCode 获取对应的长链接
        String longUrl = tinyUrlService.getLongUrlByShortCode(shortCode);

        // 如果找不到对应的长链接，可以返回404错误
        if (StringUtils.isEmpty(longUrl)) {
            return ResponseEntity.notFound().build();
        }

        // 返回302重定向到长链接
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(longUrl))
                .build();
    }

}
