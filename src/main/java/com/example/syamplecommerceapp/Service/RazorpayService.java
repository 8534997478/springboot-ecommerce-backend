package com.example.syamplecommerceapp.Service;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class RazorpayService {

    private RazorpayClient client;

    public RazorpayService() throws Exception {
        this.client = new RazorpayClient("rzp_test_15dGbJgD4ajD6F", "finsneMvkCuCfKT2NiOH5qgG");
    }

    public String createOrder(int amount, String currency, String receipt) throws Exception {
        JSONObject options = new JSONObject();
        options.put("amount", amount * 100); // Amount in paise
        options.put("currency", currency);
        options.put("receipt", receipt);
        options.put("payment_capture", 1); // Auto capture

        Order order = client.orders.create(options);
        return order.toString();
    }
}
