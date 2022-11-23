package com.example.firma.Controller;

import com.example.firma.DTO.APIResponse;
import com.example.firma.DTO.Firma_Dto;
import com.example.firma.DTO.IshchiDTO;
import com.example.firma.Repozitary.IshchiRepozitary;
import com.example.firma.Service.IshchiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/Ishchi")
public class IshchiController {
    @Autowired
    IshchiService ishchiService;
    @PostMapping("/ishchipost")
    public HttpEntity<?> postIshchi(@RequestBody IshchiDTO ishchiDTO){
        APIResponse apiResponse=ishchiService.postIshchi(ishchiDTO);
        return ResponseEntity.status(apiResponse.isXolat()?200:208).body(apiResponse.getXabar());
    }

    @PutMapping("Taxrirlash/{id}")
    public HttpEntity<?>taxrirlsh(@PathVariable Integer id,@RequestBody IshchiDTO ishchiDTO){
        APIResponse apiResponse=ishchiService.taxrirlash(id,ishchiDTO);
        return ResponseEntity.status(apiResponse.isXolat()?200:208).body(apiResponse.getXabar());
    }
    @GetMapping("/oqish")
    public HttpEntity<?> oqish(@RequestBody IshchiDTO ishchiDTO){
        APIResponse apiResponse=ishchiService.oqish(ishchiDTO);
        return ResponseEntity.status(apiResponse.isXolat()?200:208).body(apiResponse.getXabar());
    }
    @GetMapping("/oqish/{id}")
    public HttpEntity<?> oqish(@PathVariable Integer id){
        APIResponse apiResponse=ishchiService.oqishID(id);
        return ResponseEntity.status(apiResponse.isXolat()?200:208).body(apiResponse.getXabar());
    }
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?>Delete(@PathVariable Integer id){
        APIResponse apiResponse=ishchiService.deleteid(id);
        return ResponseEntity.status(apiResponse.isXolat()?200:208).body(apiResponse.getXabar());

    }
}
