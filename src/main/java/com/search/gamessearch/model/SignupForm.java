package com.search.gamessearch.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
@Getter
@Setter
public class SignupForm {
    @NotEmpty
    @Size(min=5, max=30)
    private String username = "";

    @NotEmpty
    @Size(min=7, max=30)
    private String password = "";

    @NotEmpty
    @Size(min=7, max=30)
    private String passwordCheck = "";

}
