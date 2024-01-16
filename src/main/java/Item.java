public class Item {
    private final String name;
    private final int quantity;

    public Item(String name, int quantity) {

        this.name = name;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public void check() {
        if ((getName() == null) || (getName().isEmpty())) {
            throw new ItemNameException("Item name should not be null or empty!");
        }

        if ((getQuantity() < 1) || (getQuantity() > 100)) {
            throw new ItemQuantityException("Item quantity should not be less than 1 and also" +
                        " should not be greater than 100");
        }
    }
}
