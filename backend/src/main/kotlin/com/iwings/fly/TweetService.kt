package com.iwings.fly

import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.HashMap

@Service
class TweetService {

    var tweets: HashMap<Long, Wall> = HashMap()

    fun all(): Collection<Wall> {
        return tweets.values
    }

    init {
        tweets.put(
                0,
                Wall("Iimetra",
                        Arrays.asList(
                                TwitterPostDto("It is working", 0),
                                TwitterPostDto("Fuck each", 1)))

        )
    }

}