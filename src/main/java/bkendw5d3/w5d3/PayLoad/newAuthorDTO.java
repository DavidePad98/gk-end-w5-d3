package bkendw5d3.w5d3.PayLoad;

import bkendw5d3.w5d3.entities.Blogpost;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

import java.util.List;

public record newAuthorDTO(
        @NotEmpty(message = "devi mettere un nome")
        @Size(min = 2, max = 15, message = "il nome deve contenere da un minimo di 2 caratteri and un massimo di 15")
         String name,

        @NotEmpty(message = "devi mettere un cognome")
        @Size(min = 2, max = 15, message = "il cognome deve contenere da un minimo di 2 caratteri and un massimo di 15")
         String surname,

        @NotEmpty(message = "devi mettere un email")
        @Email(message = "L'email inserita non è valida")
         String email,

        @NotEmpty(message = "devi mettere una data di nascita")
        @Size(min = 2, max = 500,  message = "la data di nascita deve contenere da un minimo di 2 caratteri and un massimo di 15")
         String dateOfBirth,

//        @URL(message = "url non valido")
//        @NotEmpty(message = "devi mettere un url per il tuo avatar")
         String avatar,

//        @NotEmpty(message = "devi mettere l'id dei post che ha scritto questo autore")
//        @Size(min = 1, max = 10, message = "la lista di post scritti deve essere massimo di 10")
         List<Blogpost> blogposts
        ) {


}
