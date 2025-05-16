package com.biblioteca.api_biblioteca.dto;

import lombok.Data;
import java.util.List;

@Data
public class PrestamoDTO {
    private Long personaId;
    private List<Long> librosIds;
}