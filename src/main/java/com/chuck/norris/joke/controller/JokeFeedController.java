package com.chuck.norris.joke.controller;


import com.chuck.norris.joke.handler.JokeFeedHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.chuck.norris.joke.constant.JokesAppConstant.GET_JOKE_ENDPOINT;
import static com.chuck.norris.joke.constant.JokesAppConstant.INSULT_ENDPOINT;
import static com.chuck.norris.joke.constant.JokesAppConstant.JOKE_ENDPOINT;


/**
 * Controller class to expose REST endpoints
 */
@RestController
@RequestMapping(path = JOKE_ENDPOINT)
public class JokeFeedController {

    @Resource
    private JokeFeedHandler jokeFeedHandler;

    @GetMapping(GET_JOKE_ENDPOINT)
    public String getJoke() {
        return jokeFeedHandler.handleJoke().getValue();
    }

    @GetMapping(INSULT_ENDPOINT)
    public String getInsult() {
        return jokeFeedHandler.handleInsult();
    }
}
