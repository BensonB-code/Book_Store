package bbsapi.app.payment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.stripe.Stripe;
import com.stripe.model.Charge;

@Component
public class StripeClient{

    public StripeClient() {
    	//Add your secret Stripe payment key 
        Stripe.apiKey = "sk_test_XXXXXXXXXXXXXXXXXXXXX";
    }

    public String chargeCreditCard(String token, Integer amount) throws Exception {
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", amount);
        chargeParams.put("currency", "USD");
        chargeParams.put("source", token);
        Charge charge = Charge.create(chargeParams);
        return charge.getId();
    }
}