package com.quoteguard.controller;

import com.quoteguard.repository.ClientRepository;
import com.quoteguard.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final ClientRepository clientRepository;
    private final InvoiceRepository invoiceRepository;

    @GetMapping("/stats")
    public Map<String, Long> getDashboardStats(@RequestParam Long userId) {
        long clientCount = clientRepository.countByUserId(userId);
        long invoiceCount = invoiceRepository.countByUserId(userId);
        long pendingCount = invoiceRepository.countByUserIdAndPaidFalse(userId);

        Map<String, Long> stats = new HashMap<>();
        stats.put("clients", clientCount);
        stats.put("invoices", invoiceCount);
        stats.put("pending", pendingCount);

        return stats;
    }
}