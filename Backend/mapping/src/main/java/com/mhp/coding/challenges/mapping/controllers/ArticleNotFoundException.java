package com.mhp.coding.challenges.mapping.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Article not found")
public class ArticleNotFoundException extends RuntimeException {

}
