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

import CafeBazar.models.IExtraChargeableBurgerToppings;
import CafeBazar.models.MenuItem;

public class Burger implements MenuItem, IExtraChargeableBurgerToppings{

    protected boolean isAvocadoUsed = false;
    protected boolean isSaraTogaChipsUsed = false;
    protected boolean isExtraBeaconUsed = false;

    @Override
    public String getTitle() {        
        return "Burger";
    }

    @Override
    public double getPrice() {
        double basePrice = 10.00;
        if (isAvocadoUsed) {
            basePrice += 1.50;
        }
        if (isSaraTogaChipsUsed) {
            basePrice += 0.50;
        }
        if (isExtraBeaconUsed) {
            basePrice += 2.50;
        }
        return basePrice;
    }

    @Override
    public void setAvocadoUsed(boolean isUsed) {
        isAvocadoUsed = isUsed;
    }

    @Override
    public void setSaraTogaChipsUsed(boolean isUsed) {
        isSaraTogaChipsUsed = isUsed;
    }

    @Override
    public void setExtraBeaconUsed(boolean isUsed) {
       isExtraBeaconUsed = isUsed;
    }
}

