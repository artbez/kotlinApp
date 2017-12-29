package com.iwings.fly

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller(@Autowired private var tweetService: TweetService) {

    @GetMapping("/all")
    fun getTwits(): Collection<Wall> = tweetService.all()
}