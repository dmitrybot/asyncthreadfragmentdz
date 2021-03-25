package ru.realityfamily.automattask.Models;

import android.os.AsyncTask;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Student {
    private final String name;
    private List<IProduct> cart;
    private final int cartCount;
    private Automat automat;

    public Student(int name, int cartCount, Automat automat) {
        this.name = "Студент №" + name;
        this.cartCount = cartCount;
        this.automat = automat;
    }

    public void ChooseProduct() throws InterruptedException {
        while (cart.size() < cartCount) {
            IProduct product = automat.GetProduct();
            if (product != null) {
                TimeUnit.SECONDS.sleep(1);
                cart.add(product);
            }
        }
    }

    public double CartCost() {
        double finalCost = 0;
        for (IProduct product : cart) {
            finalCost += product.getCost();
        }
        return finalCost;
    }

    public void PayForCart() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
    }

    public String getName() {
        return name;
    }

    public List<IProduct> getCart() {
        return cart;
    }

    class StudentTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
