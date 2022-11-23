package com.example.firma.Controller;

import com.example.firma.DTO.APIResponse;
import com.example.firma.DTO.BolimDTO;
import com.example.firma.DTO.Firma_Dto;
import com.example.firma.Entite.Bolim;
import com.example.firma.Repozitary.FirmaRepozitary;
import com.example.firma.Service.BolimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Bolim")
public class BolimController {
    @Autowired
    BolimService bolimService;

    @PostMapping("Postbolim")
    public HttpEntity<?>Postbolim(@RequestBody BolimDTO bolimDTO){
        APIResponse apiResponse=bolimService.postbolim(bolimDTO);
        return ResponseEntity.status(apiResponse.isXolat()?200:208).body(apiResponse.getXabar());
    }
    @PutMapping("Taxrirlash/{id}")
    public HttpEntity<?>taxrirlsh(@PathVariable Integer id,@RequestBody BolimDTO bolimDTO){
        APIResponse apiResponse=bolimService.taxrirlash(id,bolimDTO);
        return ResponseEntity.status(apiResponse.isXolat()?200:208).body(apiResponse.getXabar());
    }
    @GetMapping("/oqish")
    public HttpEntity<?> oqish(@RequestBody BolimDTO bolimDTO){
        APIResponse apiResponse=bolimService.oqish(bolimDTO);
        return ResponseEntity.status(apiResponse.isXolat()?200:208).body(apiResponse.getXabar());
    }
    @GetMapping("/oqish/{id}")
    public HttpEntity<?> oqish(@PathVariable Integer id){
        APIResponse apiResponse=bolimService.oqishID(id);
        return ResponseEntity.status(apiResponse.isXolat()?200:208).body(apiResponse.getXabar());
    }
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?>Delete(@PathVariable Integer id){
        APIResponse apiResponse=bolimService.deleteid(id);
        return ResponseEntity.status(apiResponse.isXolat()?200:208).body(apiResponse.getXabar());

    }
}
