/* 
 *  MIT License
 *  
 *  Copyright (c) 2024 Lalit Hajare
 *  
 *  Created on Thu Oct 03 2024 by lalit
 *  
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *  
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *  
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */

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

    public Burger updateToppings(Burger burger){
        burger.isAvocadoUsed = useAvocado;
        burger.isExtraBeaconUsed = useExtraBeacon;
        burger.isSaraTogaChipsUsed = useSaraTogaChips;
        return burger;
    }
      
}
