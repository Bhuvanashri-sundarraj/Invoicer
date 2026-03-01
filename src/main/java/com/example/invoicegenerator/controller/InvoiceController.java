package com.example.invoicegenerator.controller;

import com.example.invoicegenerator.model.Invoice;
import com.example.invoicegenerator.model.InvoiceItem;
import com.example.invoicegenerator.service.NumberToWordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class InvoiceController {

    @Autowired
    private NumberToWordsService numberService;

    // TEMPLATE 1 FORM
    @GetMapping("/template1")
    public String template1Form() {
        return "template1-form";
    }

    // TEMPLATE 2 FORM
    @GetMapping("/template2")
    public String template2Form() {
        return "template2-form";
    }

    //LOGIN
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    //LOGIN VALIDATION
    @PostMapping("/login")
    public String validateLogin(@RequestParam String username, @RequestParam String password, HttpSession session, Model model){
        if("SKBchem".equals(username)&&"Skb151275".equals(password))
        {
            session.setAttribute("getUser","authenticated");
            return "redirect:/template1";
        }
        model.addAttribute("error","Invalid username or password");
        return "login";
    }

    //Protect template1
    @GetMapping("/template1-form")
    public String template1Form(HttpSession session)
    {
        if(session.getAttribute("gstUser")==null)
        {
            return "redirect:/login";
        }
        return "template1-form";
    }

    //Forgot password page
    @GetMapping("/forgot-password")
    public String forgotPasswordPage()
    {
        return "forgot-password";
    }

    //Security question check
    @PostMapping("/forgot-password")
    public String checkSecurityAnswer(@RequestParam String answer, Model model)
    {
        if("Maggie".equalsIgnoreCase(answer.trim()))
        {
            model.addAttribute("password","Skb151275");
            return "show-password";
        }
        model.addAttribute("error","Incorrect answer!");
        return "forgot-password";
    }

    // GENERATE INVOICE (COMMON FOR BOTH)
    @PostMapping("/generate")
    public String generateInvoice(

            @RequestParam String invoiceNumber,
            @RequestParam String invoiceDate,
            @RequestParam String buyerOrderDate,
            @RequestParam String copyType,

            @RequestParam String sellerName,
            @RequestParam String sellerAddress,
            @RequestParam String sellerGstin,
            @RequestParam String sellerPan,

            @RequestParam String buyerName,
            @RequestParam(required = false) String buyerAddress,
            @RequestParam(required = false) String buyerAddressLine1,
            @RequestParam(required = false) String buyerAddressLine2,
            @RequestParam(required = false) String buyerAddressLine3,
            @RequestParam(required = false) String buyerGstin,
            @RequestParam(required = false) String buyerPan,

            @RequestParam String modeOfPayment,
            @RequestParam String dispatchThrough,

            @RequestParam String bankDetails,
            @RequestParam String terms,

            @RequestParam List<String> description,
            @RequestParam(required = false) List<Double> quantity,
            @RequestParam(required = false) List<Double> rate,

            Model model) {

        List<InvoiceItem> items = new ArrayList<>();

        for (int i = 0; i < description.size(); i++) {

            if (description.get(i) == null || description.get(i).isBlank())
                continue;

            if (quantity == null || rate == null)
                continue;

            if (i >= quantity.size() || i >= rate.size())
                continue;

            InvoiceItem item = new InvoiceItem();
            item.setDescription(description.get(i));
            item.setQuantity(quantity.get(i));
            item.setRate(rate.get(i));

            items.add(item);
        }

        if (items.isEmpty()) {
            model.addAttribute("error", "Please enter at least one item.");
            return "template1-form";
        }

        Invoice invoice = new Invoice();

        invoice.setInvoiceNumber(invoiceNumber);
        invoice.setInvoiceDate(LocalDate.parse(invoiceDate));
        invoice.setBuyerOrderDate(LocalDate.parse(buyerOrderDate));
        invoice.setCopyType(copyType);

        invoice.setSellerName(sellerName);
        invoice.setSellerAddress(sellerAddress);
        invoice.setSellerGstin(sellerGstin);
        invoice.setSellerPan(sellerPan);

        invoice.setBuyerName(buyerName);

        // Handle Template 2 single address
        if (buyerAddress != null) {
            invoice.setBuyerAddressLine1(buyerAddress);
        } else {
            invoice.setBuyerAddressLine1(buyerAddressLine1);
            invoice.setBuyerAddressLine2(buyerAddressLine2);
            invoice.setBuyerAddressLine3(buyerAddressLine3);
        }

        invoice.setBuyerGstin(buyerGstin);
        invoice.setBuyerPan(buyerPan);

        invoice.setModeOfPayment(modeOfPayment);
        invoice.setDispatchThrough(dispatchThrough);

        invoice.setBankDetails(bankDetails);
        invoice.setTerms(terms);

        invoice.setItems(items);

        model.addAttribute("invoice", invoice);
        model.addAttribute("amountWords",
                numberService.convert((int) invoice.getGrandTotalRaw()) + " Only");

        // Detect template automatically
        if (buyerAddress != null) {
            return "template2-view";
        }

        return "template1-view";
    }
}
