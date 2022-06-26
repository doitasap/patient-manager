package doitasap.me.patient.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 2022-06-25 오후 6:23
 * author DoitA$ap
 */
@RequestMapping("/visit")
@Controller
public class VisitController {
    @GetMapping("/insertForm")
    public String insertForm(){
        return "visit/insertForm";
    }
}
