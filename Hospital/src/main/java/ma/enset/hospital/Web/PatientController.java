package ma.enset.hospital.Web;

import lombok.AllArgsConstructor;
import ma.enset.hospital.repository.patientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import ma.enset.hospital.entities.Patient;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
@AllArgsConstructor
public class PatientController {

 private patientRepository patientRep;

@GetMapping("/index")
 public String index(Model model,
                     @RequestParam(name="page", defaultValue = "0") int p,
                     @RequestParam(name="size", defaultValue = "4") int s,
                     @RequestParam(name="keyword", defaultValue = "") String ky
                     ){
 Page<Patient> pagePatients=patientRep.findByNomContains(ky,PageRequest.of(p,s));
model.addAttribute("listPatients",pagePatients.getContent());
 model.addAttribute("pages",new int[pagePatients.getTotalPages()]);
 model.addAttribute("currentPage",p);
 model.addAttribute("keyword",ky);
 return "patients";}
 @GetMapping("/delete")
public String delete(Long id, String keyword, int page){
 patientRep.deleteById(id);
 return "redirect:/index?page="+page+"&keyword="+keyword;
}

}
