package com.person.crud_app.controller;

import org.springframework.ui.Model;
import com.person.crud_app.model.Client;
import com.person.crud_app.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Client> clients = clientService.getAllClients();
        model.addAttribute("clients", clients);
        return "index";
    }

    @PostMapping("/save")
    public String saveClient(@ModelAttribute("client") Client client) {
        clientService.saveClient(client);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable(value = "id") Long id) {
        clientService.deleteClient(id);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editClient(@PathVariable(value = "id") Long id, Model model) {
        Client client = clientService.getClientById(id).orElseThrow(() -> new IllegalArgumentException("Invalid client ID:" + id));
        model.addAttribute("client", client);
        return "edit";
    }

    @PostMapping("/update")
    public String updateClient(@ModelAttribute(value = "id") Client client) {
        clientService.saveClient(client);
        return "redirect:/";
    }
}