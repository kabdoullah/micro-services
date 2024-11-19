package st2i.net.billingservice.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import st2i.net.billingservice.entities.Bill;
import st2i.net.billingservice.feign.CustomerRestClient;
import st2i.net.billingservice.feign.ProductRestClient;
import st2i.net.billingservice.repository.BillRepository;
import st2i.net.billingservice.repository.ProductItemRepository;

@RestController
public class BillRestController {
    private final BillRepository billRepository;
    private final ProductItemRepository productItemRepository;
    private final CustomerRestClient customerRestClient;
    private final ProductRestClient productRestClient;

    public BillRestController(BillRepository billRepository, ProductItemRepository productItemRepository, CustomerRestClient customerRestClient, ProductRestClient productRestClient) {
        this.billRepository = billRepository;
        this.productItemRepository = productItemRepository;
        this.customerRestClient = customerRestClient;
        this.productRestClient = productRestClient;
    }

    @GetMapping("/bills/{id}")
    public Bill getBill(@PathVariable Long id) {
        Bill bill = billRepository.findById(id).orElse(null);
        if (bill == null) {
            throw new RuntimeException("Bill not found");
        }
        bill.setCustomer(customerRestClient.findCustomerById(bill.getCustomerId()));
        bill.getProductItems().forEach(productItem -> productItem.setProduct(productRestClient.getProductById(productItem.getProductId())));
        return bill;
    }
}
