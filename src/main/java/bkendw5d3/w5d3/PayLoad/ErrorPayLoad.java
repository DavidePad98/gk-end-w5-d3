package bkendw5d3.w5d3.PayLoad;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ErrorPayLoad {
    private String msg;
    private LocalDateTime timestamp;
}
