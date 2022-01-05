/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Java.Tugas.Askar.Kadir;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Asus
 */
@Controller
public class proesdata {    
    @RequestMapping("/inputan")
    public String inputanuser(HttpServletRequest data, Model buah){
        dataproses dtproses = new dataproses();
        //getting data
        String namabuah = data.getParameter("var_namabuah");
        String hargabuah = data.getParameter("var_hargakilo");        
        String jumlahbuah = data.getParameter("var_jumlahbeli");
        String uangcostumer = data.getParameter("var_uangcostumer");
        //import data from proccess then turn to variabel
        Double convharga        = dtproses.newharga(hargabuah);
        Double convjumlah       = dtproses.newjumlah(jumlahbuah);
        Double jumlahbayar      = dtproses.newjumlahbayar(convharga, convjumlah);
        String diskonpercent    = dtproses.diskon(jumlahbayar);
        Double hargadiskon      = dtproses.newhargadiskon(jumlahbayar, Integer.valueOf(diskonpercent));
        Double totalbayar       = dtproses.newtotalbayar(jumlahbayar, hargadiskon);
        dtproses.math(jumlahbayar, Integer.valueOf(diskonpercent), totalbayar, hargadiskon);
        buah.addAttribute("name", namabuah);
        buah.addAttribute("price", totalbayar);
        buah.addAttribute("kilo",jumlahbuah);
        buah.addAttribute("tbayar",totalbayar); 
        buah.addAttribute("discountrp", hargadiskon);
        buah.addAttribute("disc", diskonpercent);
        buah.addAttribute("total0", jumlahbayar);
        buah.addAttribute("kembalian", dtproses.kembalianuang(Double.valueOf(uangcostumer), totalbayar));
        return "Askar";
    }
}
