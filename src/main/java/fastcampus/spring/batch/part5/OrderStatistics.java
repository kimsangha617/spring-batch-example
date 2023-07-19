package fastcampus.spring.batch.part5;

import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@Getter
public class OrderStatistics {

    private String amount;
    private LocalDate date;


}
