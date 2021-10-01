package ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto;

import javax.validation.constraints.*;

public class UserDto {
    @NotBlank(message = "Name is mandatory")
    @NotNull(message = "Name is mandatory")
    @Size(min=10, max=30)
    public String name;

    @NotBlank(message = "LastName is mandatory")
    @NotNull(message = "LastName is mandatory")
    @Size(min=10, max=30)
    public String lastName;

    @NotBlank(message = "Email is mandatory")
    @NotNull(message = "Email is mandatory")
    @Email(message = "Email is not valid", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    public String email;

    @NotBlank(message = "Address is mandatory")
    @NotNull(message = "Address is mandatory")
    @Size(min=0, max=30)
    public String address;

    @NotBlank(message = "Password is mandatory")
    @NotNull(message = "Password is mandatory")
    public String password;

    @NotBlank(message = "Cvu is mandatory")
    @NotNull(message = "Cvu is mandatory")
    @Size(min=22, max=22)
    public String cvu;

    @NotBlank(message = "CriptoWallet is mandatory")
    @NotNull(message = "CriptoWallet is mandatory")
    @Size(min=8, max=8)
    public String criptoWallet;
}
