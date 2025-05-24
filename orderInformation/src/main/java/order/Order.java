package order;





import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter

public class Order {
    private Integer id;
    private String customerName;
    private String itemCode;
    private Integer quentity;
    private LocalDate date;
    private Double price;


}
