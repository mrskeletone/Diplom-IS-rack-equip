package com.example.mes_opcua;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class EmployeeController {
    private final MesOpcUaService mesOpcUaService;
    @Autowired
    public EmployeeController(MesOpcUaService mesOpcUaService){
        this.mesOpcUaService=mesOpcUaService;
    }
    @GetMapping("/employees")
    public String showEmployees(Model model){
        List<AccessEmployees> accessEmployees=mesOpcUaService.getListEmployees();
        model.addAttribute("employees",accessEmployees);
        return "employees";
    }
    @PostMapping("/update-access")
    public String updateAccess(
            @RequestParam String employeeUID,
            @RequestParam String machineName,
            @RequestParam boolean newAccess,
            RedirectAttributes redirectAttributes) {

        mesOpcUaService.changeAccess(machineName,newAccess,employeeUID);

        redirectAttributes.addFlashAttribute("message", "Access updated successfully!");
        return "redirect:/employees";
    }
}
