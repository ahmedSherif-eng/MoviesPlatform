package com.fawry.moviesplatform.controllers;

import com.fawry.moviesplatform.service.MoviesService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Setter
@Getter
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {
    private final MoviesService moviesService;

}
