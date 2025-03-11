package ass.ass.entities;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Long, Integer> items = new HashMap<>();

    public void addItem(Long productId, int quantity) {
        items.put(productId, items.getOrDefault(productId, 0) + quantity);
    }

    public void removeItem(Long productId) {
        items.remove(productId);
    }

    public Map<Long, Integer> getItems() {
        return items;
    }

    public float getTotalPrice() {
        float total = 0;
        for (Map.Entry<Long, Integer> entry : items.entrySet()) {
            // Giả sử có phương thức getPriceById để lấy giá sản phẩm
            total += getPriceById(entry.getKey()) * entry.getValue();
        }
        return total;
    }

    private float getPriceById(Long productId) {
        // Logic để lấy giá sản phẩm theo productId
        return 0; // Thay thế bằng giá thực tế
    }
}