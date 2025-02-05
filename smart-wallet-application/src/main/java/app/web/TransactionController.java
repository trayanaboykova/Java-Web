package app.web;

import app.transaction.model.Transaction;
import app.transaction.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ModelAndView getAllTransactions(Model model) {

        List<Transaction> transactions = transactionService.getAllByOwnerId(UUID.fromString("3467407c-9663-4fc7-a771-9acb7a6f721f"));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("transactions");
        modelAndView.addObject("transactions", transactions);

        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getTransactionById(@PathVariable UUID id, Model model) {
        return null;
    }
}
