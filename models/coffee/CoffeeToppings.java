package CafeBazar.models.coffee;

public class CoffeeToppings {
    private boolean useExtraCream = false;
    private boolean useSugarFreeSweatner = false;
    
    public CoffeeToppings(boolean useExtraCream, boolean useSugarFreeSweatner) {
        this.useExtraCream = useExtraCream;
        this.useSugarFreeSweatner = useSugarFreeSweatner;
    }

    public boolean isUseExtraCream() {
        return useExtraCream;
    }

    public boolean isUseSugarFreeSweatner() {
        return useSugarFreeSweatner;
    }    
}