package pl.bak.auction_shop.rest.entity;

public record RegisterRequest(String firstname, String lastname, String username, String password, String email) {}
