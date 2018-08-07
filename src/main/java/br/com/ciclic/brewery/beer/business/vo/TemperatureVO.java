package br.com.ciclic.brewery.beer.business.vo;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class TemperatureVO implements Serializable {

    private static final long serialVersionUID = 300754025090035561L;

    @NotNull(message = "Field maximum is required")
    @Max(100)
    @Min(-100)
    private Integer maximum;

    @NotNull(message = "Field minimum is required")
    @Max(100)
    @Min(-100)
    private Integer minimum;

}
