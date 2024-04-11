package bkendw5d3.w5d3.PayLoad;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record newBlogPostDTO(
        @NotEmpty(message = "la categoria è obbligatoria")
        @Size(min = 2, max = 15, message = "deve contenere da un minimo di 2 caratteri and un massimo di 15")
        String category,

        @NotEmpty(message = "devi mettere il titolo")
        @Size(min = 2, max = 15, message = "deve contenere da un minimo di 2 caratteri and un massimo di 15")
        String title,

        @NotEmpty(message = "url della cover è obbligatorio")
        @Size(min = 2, max = 15, message = "deve contenere da un minimo di 2 caratteri and un massimo di 15")
        String cover,

        @NotEmpty(message = "metti un contenuto")
        @Size(min = 2, max = 15, message = "deve contenere da un minimo di 2 caratteri and un massimo di 15")
        String content,

        @NotEmpty(message = "tempo di lettura è obbligatorio")
        @Size(min = 2, max = 15, message = "deve contenere da un minimo di 2 caratteri and un massimo di 15")
        double readingTime,

        @NotEmpty(message = "metti l'id dell'autore")
        @Size(min = 2, max = 15, message = "deve contenere da un minimo di 2 caratteri and un massimo di 15")
        int author_id) {
}
