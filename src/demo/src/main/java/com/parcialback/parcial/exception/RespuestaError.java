package com.parcialback.parcial.exception;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaError {

    private LocalDateTime fecha;
    private int codigo;
    private String tipo;
    private String mensaje;
    private String path;
}
