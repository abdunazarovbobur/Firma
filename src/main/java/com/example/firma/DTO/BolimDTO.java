package com.example.firma.DTO;

import com.example.firma.Entite.Ishchi;
import lombok.Data;

import java.util.List;

@Data
public class BolimDTO {
    private String nomi;
    private Integer firmaId;
    private List<Ishchi> ishchiList;
}
