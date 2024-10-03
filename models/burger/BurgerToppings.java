package CafeBazar.models.burger;

public class BurgerToppings {
    private boolean useAvocado = false;
    private boolean useSaraTogaChips = false;
    private boolean useExtraBeacon = false;

    public BurgerToppings(boolean useAvocado, boolean useSaraTogaChips, boolean useExtraBeacon) {
        this.useAvocado = useAvocado;
        this.useSaraTogaChips = useSaraTogaChips;
        this.useExtraBeacon = useExtraBeacon;
    }

    public boolean isUseAvocado() {
        return useAvocado;
    }
    public void setUseAvocado(boolean useAvocado) {
        this.useAvocado = useAvocado;
    }
    public boolean isUseSaraTogaChips() {
        return useSaraTogaChips;
    }
    public void setUseSaraTogaChips(boolean useSaraTogaChips) {
        this.useSaraTogaChips = useSaraTogaChips;
    }
    public boolean isUseExtraBeacon() {
        return useExtraBeacon;
    }
    public void setUseExtraBeacon(boolean useExtraBeacon) {
        this.useExtraBeacon = useExtraBeacon;
    }
      
}
